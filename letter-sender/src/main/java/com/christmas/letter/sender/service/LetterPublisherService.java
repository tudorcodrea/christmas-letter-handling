package com.christmas.letter.sender.service;

import com.christmas.letter.sender.config.AwsProperties;
import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.LetterEntity;
import com.christmas.letter.sender.model.WishCategory;
import com.christmas.letter.sender.model.adapter.LetterModelAdapter;
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
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class LetterPublisherService {

  @Value("${spring.cloud.aws.sns.group.name}")
  private String messageGroupId;

  @Value("${spring.cloud.aws.sns.group.sender-id}")
  private String messageSenderId;

  private final SnsTemplate snsTemplate;
  private final AwsProperties awsProperties;

  private final LetterModelAdapter letterModelAdapter;

  public void publishLetter(LetterDto letter) {

    WishCategory[] items = WishCategory.values();
    int rand = new Random().nextInt(items.length);

    var msgHeader = createMessageHeaders();
    msgHeader.put("wishCategory", items[rand].getCategory());


    LetterEntity letterEntity = letterModelAdapter.toEntity(letter);
//    IF --attributes RawMessageDelivery=true \ payload is JSON
//    snsTemplate.convertAndSend(awsProperties.getSnsDestination(), letterEntity, msgHeader);


    try {
      ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
      var letterJson = ow.writeValueAsString(letterEntity);

      snsTemplate.convertAndSend(awsProperties.getSnsDestination(), letterJson, msgHeader);

    } catch (JsonProcessingException e) {
      log.error("Json exception {}", e.getMessage());
    } catch (MessagingException e) {
      log.error("Messaging exception {}", e.getMessage());
    }


  }

  private Map<String, Object> createMessageHeaders() {
    var headersMap = new HashMap<String, Object>();
    headersMap.put("messageGroupId", messageGroupId);
    headersMap.put("senderId", messageSenderId);
    return headersMap;
  }
}
