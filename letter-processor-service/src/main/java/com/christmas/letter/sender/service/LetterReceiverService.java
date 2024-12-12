package com.christmas.letter.sender.service;

import com.christmas.letter.sender.model.LetterMessage;
import com.christmas.letter.sender.model.MessageBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.QueueDoesNotExistException;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

//@Service
//@Log4j2
public class LetterReceiverService {

//  @Value("${aws.queueName}")
//  private String queueName;
//
//  @Autowired
//  private SqsClient amazonSQSClient;
//
//  // polling based approach executes on every 5 seconds
//  @Scheduled(fixedDelay = 5000)
//  public void receiveMessages() {
//    try {
//
//      var queueUrlReq = GetQueueUrlRequest.builder()
//          .queueName(queueName)
//          .build();
//
//      String queueUrl = amazonSQSClient.getQueueUrl(queueUrlReq).queueUrl();
//
//      var receiveMessageRequest = ReceiveMessageRequest.builder()
//          .queueUrl(queueUrl)
//          .maxNumberOfMessages(10)
//          .build();
//
//      var objectMapper = getObjectMapper();
//
//      log.info("Reading SQS Queue done: URL {}", queueUrl);
//      ReceiveMessageResponse receiveMessageResult = amazonSQSClient.receiveMessage(receiveMessageRequest);
//      if (!receiveMessageResult.messages().isEmpty()) {
//        Message message = receiveMessageResult.messages().get(0);
//        log.info("Incoming Message From SQS {}", message.messageId());
//        log.info("Message Body {}", message.body());
//
//        var messageBody = objectMapper.readValue(message.body(), MessageBody.class);
//
//        var letterMessage = objectMapper.readValue(messageBody.getMessage(), LetterMessage.class);
//
//        log.info("Letter Message object {}", letterMessage);
//
//        var deleteMessageRequest = DeleteMessageRequest.builder()
//            .queueUrl(queueUrl)
//            .receiptHandle(message.receiptHandle())
//            .build();
//        amazonSQSClient.deleteMessage(deleteMessageRequest);
//      }
//    } catch (QueueDoesNotExistException e) {
//      log.error("Queue does not exist {}", e.getMessage());
//    } catch (JsonMappingException e) {
//      log.error("Json mapping exception {}", e.getMessage());
//    } catch (JsonProcessingException e) {
//      log.error("Json processing exception {}", e.getMessage());
//    }
//  }
//
//  private ObjectMapper getObjectMapper() {
//    var om = new ObjectMapper();
//    om.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    return om;
//  }

}
