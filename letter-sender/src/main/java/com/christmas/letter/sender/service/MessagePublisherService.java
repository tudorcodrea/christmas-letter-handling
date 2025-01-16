package com.christmas.letter.sender.service;

import com.christmas.letter.sender.config.AwsProperties;
import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.LetterEntity;
import com.christmas.letter.sender.model.MessageToChannelDto;
import com.christmas.letter.sender.model.MessageType;
import com.christmas.letter.sender.model.WishCategory;
import com.christmas.letter.sender.model.adapter.LetterModelAdapter;
import com.christmas.letter.sender.model.adapter.MessageChannelModelAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.awspring.cloud.sns.core.SnsTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class MessagePublisherService {

  private final SnsTemplate snsTemplate;
  private final MessageChannelModelAdapter messageChannelModelAdapter;

  public void publishMessage(MessageToChannelDto letter) {

    var msgChannelEntity = messageChannelModelAdapter.toEntity(letter);
//    IF --attributes RawMessageDelivery=true \ payload is JSON
    // enhance it with HEADERS for validation and filtering
//    snsTemplate.convertAndSend(awsProperties.getSnsDestination(), letterEntity, msgHeader);

    try {
      snsTemplate.convertAndSend(msgChannelEntity.getTopicName(), msgChannelEntity.getMessageAsJson());

    } catch (MessagingException e) {
      log.error("Messaging exception {}", e.getMessage());
    }
  }

//  THOSE will be coming from PAYLOAD -- keeping as input format
//  private Map<String, Object> createMessageHeaders() {
//    var headersMap = new HashMap<String, Object>();
//    headersMap.put("messageGroupId", messageGroupId);
//    headersMap.put("senderId", messageSenderId);
//    headersMap.put("messageType", MessageType.LETTER.getMessageType());
//    headersMap.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//    return headersMap;
//  }
}
