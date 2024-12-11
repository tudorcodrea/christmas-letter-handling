import com.christmas.letter.sender.LetterSenderApplication;
import com.christmas.letter.sender.model.Letter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.bytebuddy.utility.RandomString;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest(classes = LetterSenderApplication.class)
public class LetterControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static LocalStackContainer localStackContainer;

    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:000000000000:letter-topic";

    static {
        localStackContainer = new LocalStackContainer( DockerImageName.parse("localstack/localstack:3.0"))
                .withCopyFileToContainer(MountableFile.forClasspathResource("init/init-script.sh", 0744), "/etc/localstack/init/ready.d/init-local-stack.sh")
                .withServices(LocalStackContainer.Service.SNS, LocalStackContainer.Service.SQS);
        localStackContainer.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.credentials.access-key", localStackContainer::getAccessKey);
        registry.add("spring.cloud.aws.credentials.secret-key", localStackContainer::getSecretKey);

        registry.add("spring.cloud.aws.sns.region", localStackContainer::getRegion);
        registry.add("spring.cloud.aws.sns.endpoint", localStackContainer::getEndpoint);
        registry.add("aws.sns.topic.arn", () -> TOPIC_ARN);

        registry.add("spring.cloud.aws.sqs.region", localStackContainer::getRegion);
        registry.add("spring.cloud.aws.sqs.endpoint", localStackContainer::getEndpoint);
    }

    @Test
    @SneakyThrows
    void test(CapturedOutput output) {
        final String email= RandomString.make() + "@test.com";
        final String name = RandomString.make();
        final List<String> wishes = List.of(RandomString.make());
        final String location = RandomString.make();
        Letter letter = new Letter(email, name, wishes, location);

        final String userCreationApiPath = "/letter/";
        mockMvc.perform(post(userCreationApiPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(letter)))
                .andExpect(status().isCreated());

        final String expectedPublisherLog = String.format("Successfully published message to topic ARN: %s", TOPIC_ARN);
        Awaitility.await().atMost(1, TimeUnit.SECONDS).until(() -> output.getAll().contains(expectedPublisherLog));

    }
}
