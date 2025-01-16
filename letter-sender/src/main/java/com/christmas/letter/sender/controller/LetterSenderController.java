package com.christmas.letter.sender.controller;

import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.MessageToChannelDto;
import com.christmas.letter.sender.service.LetterPublisherService;
import com.christmas.letter.sender.service.MessagePublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/santa")
public class LetterSenderController {

  @Autowired
  private LetterPublisherService letterPublisherService;

  @Autowired
  private MessagePublisherService messagePublisherService;

  @GetMapping("/text")
  public String sample() {
    return "sample";
  }

  @PostMapping("/send/letter")
  public void sendLetterToSanta(@RequestBody LetterDto letterDTO) {
    letterPublisherService.publishLetter(letterDTO);
  }

  @PostMapping("/send/message")
  public ResponseEntity<Object> sendMessageToChannel(@Valid @RequestBody MessageToChannelDto messageChannelDTO) {

    messagePublisherService.publishMessage(messageChannelDTO);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

}
