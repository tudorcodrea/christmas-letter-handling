package com.christmas.letter.sender.service;

import com.christmas.letter.sender.model.LetterMessage;
import com.christmas.letter.sender.model.MessageBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LetterReceiverListenerService {

  //   http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/letter-queue
  // automatically deletes the messages from queue upon successful receiving
  // need spring cloud to configure object mapper to convert the message to the object
  // listener behind we can configure delay in receiving messages
  @SqsListener(value = "${spring.cloud.aws.sqs.queue-name}", maxConcurrentMessages = "10", acknowledgementMode = "ON_SUCCESS")
  public void receiveLetterMessages(@Valid @NonNull final MessageBody messageBody) {

    log.info("Received message: {}", messageBody);

    try {

      var objectMapper = getObjectMapper();
      var letterMessage = objectMapper.readValue(messageBody.getMessage(), LetterMessage.class);
      log.info("Letter Message object {}", letterMessage);

    } catch (JsonProcessingException e) {
      log.error("Json mapping exception {}", e.getMessage());
    }
  }


  //  public void receiveLetterMessages(final LetterMessage letterMessage,  @Header("SenderId") String senderId)
  private ObjectMapper getObjectMapper() {
    var om = new ObjectMapper();
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return om;
  }


}
