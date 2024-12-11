package com.christmas.letter.sender.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Service
@Log4j2
public class LetterReceiverService {

  @Value("${aws.queueName}")
  private String queueName;

  @Autowired
  private SqsAsyncClient amazonSQSClient;

//  @Scheduled(fixedDelay = 5000) // It runs every 5 seconds.
//  public void consumeMessages() {
//    try {
//      String queueUrl = amazonSQSClient.getQueueUrl(queueName).getQueueUrl();
//
//      ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
//
//      if (!receiveMessageResult.getMessages().isEmpty()) {
//        com.amazonaws.services.sqs.model.Message message = receiveMessageResult.getMessages().get(0);
//        log.info("Read Message from queue: {}", message.getBody());
//        amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
//      }
//
//    } catch (Exception e) {
//      log.error("Queue Exception Message: {}", e.getMessage());
//    }
//  }

//  @SqsListener(value = "${spring.cloud.aws.sqs.queue-name}")
//  public void receiveLetterMessages(LetterMessage letterMessage) {
//    log.info("Received message: {}", letterMessage);
//  }

//  @SqsListener("${events.queues.letter-queue}")
//  public void receiveLetterMessages(LetterMessage letterMessage) {
//    log.info("Received message: {}", letterMessage);
//  }

}
