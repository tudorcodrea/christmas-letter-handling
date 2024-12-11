package com.christmas.letter.sender.controller;

import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.service.LetterPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping("/text")
  public String sample() {
    return "sample";
  }

  @PostMapping("/send/letter")
  public void sendLetterToSanta(@RequestBody LetterDto letterDTO) {
    letterPublisherService.publishLetter(letterDTO);
  }

}
