package com.christmas.letter.sender.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "arn.aws.sns")
public class AwsSnsTopicConfig {

    @NotBlank(message = "SNS topic ARN must be configured")
    private String topicArn;
}
