package com.christmas.letter.sender.service;

import com.christmas.letter.sender.config.AwsProperties;
import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.LetterEntity;
import com.christmas.letter.sender.model.adapter.LetterModelAdapter;
import io.awspring.cloud.sns.core.SnsTemplate;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LetterPublisherService {

  @Value("${spring.cloud.aws.sns.group.name}")
  private String messageGroupId;

  @Value("${spring.cloud.aws.sns.group.sender-id}")
  private String messageSenderId;

  private final SnsTemplate snsTemplate;
  private final AwsProperties awsProperties;

  private final LetterModelAdapter letterModelAdapter;

  public void publishLetter(LetterDto letter) {

    LetterEntity letterEntity = letterModelAdapter.toEntity(letter);
    snsTemplate.convertAndSend(awsProperties.getSnsDestination(), letterEntity);
  }

  private Map<String, Object> createMessageHeaders() {
    var headersMap = new HashMap<String, Object>();
    headersMap.put("message-group-id", messageGroupId);
    headersMap.put("sender-id", messageSenderId);

    return new HashMap<>();
  }
}
