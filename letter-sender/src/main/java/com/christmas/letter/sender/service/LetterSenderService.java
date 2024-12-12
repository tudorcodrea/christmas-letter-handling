package com.christmas.letter.sender.service;

import com.christmas.letter.sender.config.AwsSnsTopicConfig;
import com.christmas.letter.sender.dto.LetterDto;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsSnsTopicConfig.class)
public class LetterSenderService {

    private final SnsTemplate snsTemplate;
    private final AwsSnsTopicConfig awsSnsTopicConfig;

    public void send(LetterDto letterDto) {
        var topicArn = awsSnsTopicConfig.getTopicArn();
        var payload = letterDto.toString();
        snsTemplate.convertAndSend(topicArn, payload);

        log.info("Successfully published message to topic ARN: {}", topicArn);
    }

}
