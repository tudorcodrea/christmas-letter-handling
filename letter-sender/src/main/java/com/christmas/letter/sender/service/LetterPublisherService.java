package com.christmas.letter.sender.service;

import com.christmas.letter.sender.config.AwsProperties;
import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.LetterEntity;
import com.christmas.letter.sender.model.adapter.LetterModelAdapter;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LetterPublisherService {

  private final SnsTemplate snsTemplate;
  private final AwsProperties awsProperties;

  private final LetterModelAdapter letterModelAdapter;

  public void publishLetter(LetterDto letter) {

    LetterEntity letterEntity = letterModelAdapter.toEntity(letter);
    snsTemplate.convertAndSend(awsProperties.getSnsDestination(), letterEntity);
  }
}
