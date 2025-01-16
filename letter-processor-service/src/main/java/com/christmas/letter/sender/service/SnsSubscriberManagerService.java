package com.christmas.letter.sender.service;

import com.christmas.letter.sender.config.ChannelConfiguration;
import com.christmas.letter.sender.model.ChannelDetails;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.DeleteTopicRequest;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.UnsubscribeRequest;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.QueueAttributeName;

@Service
@RequiredArgsConstructor
@Log4j2
public class SnsSubscriberManagerService {

  private final SnsClient snsClient;
  private final SqsClient sqsClient;

  private final ChannelConfiguration channelConfiguration;

  // put into a concurrent hash map --- later on into Dynamo/Postgres

  public void addCommunicationChannel(String channelName) {

    var createTopicRequest = CreateTopicRequest.builder()
        .name(channelName + "-topic")
        .attributes(Map.of("RawMessageDelivery", "true"))
        .build();
//    "maxReceivesPerSecond": 10
//    "minDelayTarget": 1,
//        "maxDelayTarget": 60,
//        "numRetries": 50,
//        "numNoDelayRetries": 3,
//        "numMinDelayRetries": 2,
//        "numMaxDelayRetries": 35,
//        "backoffFunction": "exponential"

    CreateTopicResponse createTopicResult = snsClient.createTopic(createTopicRequest);
    String topicArn = createTopicResult.topicArn();

    log.info("Topic ARN: " + topicArn);


    CreateQueueRequest request = CreateQueueRequest.builder()
        .queueName(channelName + "-queue")
        .attributes(Map.of(QueueAttributeName.FIFO_QUEUE, "true",
            QueueAttributeName.CONTENT_BASED_DEDUPLICATION, "true",
            QueueAttributeName.APPROXIMATE_NUMBER_OF_MESSAGES, "10"))
        .build();

    CreateQueueResponse createQueueResponse = sqsClient.createQueue(request);
    String queueUrl = createQueueResponse.queueUrl();

    log.info("Queue URL: " + queueUrl);

    // Subscribe SQS queue to SNS topic
    SubscribeRequest subscribeRequest = SubscribeRequest.builder()
        .topicArn(topicArn)
        .protocol("sqs")
        .endpoint(queueUrl)
        .build();

    var subscribeResponse = snsClient.subscribe(subscribeRequest);
    var subscriptionArn = subscribeResponse.subscriptionArn();
    System.out.println();
    log.info("subscriptionArn: " + subscriptionArn);

    channelConfiguration.addChannelDetails(channelName,
        createChannelDetails(channelName + "-topic", channelName + "-queue", topicArn, queueUrl, subscriptionArn));
  }

  public void removeCommunicationChannel(String channelName) {
    String subscriptionArn = "your-subscription-arn";
    String topicArn = "your-topic-arn";
    String queueUrl = "your-queue-url";

    // Unsubscribe the SQS queue from the SNS topic
    UnsubscribeRequest unsubscribeRequest = UnsubscribeRequest.builder()
        .subscriptionArn(subscriptionArn)
        .build();
    var unsubscribeResp = snsClient.unsubscribe(unsubscribeRequest);
    log.info("unsubscribeResp: " + unsubscribeResp.toString());

    // Delete the SNS topic
    DeleteTopicRequest deleteTopicRequest = DeleteTopicRequest.builder()
        .topicArn(topicArn)
        .build();
    var deleteTopicResp = snsClient.deleteTopic(deleteTopicRequest);
    log.info("deleteTopicResp: " + deleteTopicResp.toString());

    // Delete the SQS queue
    DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder()
        .queueUrl(queueUrl)
        .build();
    var deleteQueueResp = sqsClient.deleteQueue(deleteQueueRequest);
    log.info("deleteQueueResp: " + deleteQueueResp.toString());
  }

  private ChannelDetails createChannelDetails(String topicName, String queueName, String topicArn,
                                              String queueUrl, String subscriptionArn) {
    return ChannelDetails.builder()
        .topicName(topicName)
        .queueName(queueName)
        .topicArn(topicArn)
        .queueUrl(queueUrl)
        .subscriptionArn(subscriptionArn)
        .build();
  }

}
