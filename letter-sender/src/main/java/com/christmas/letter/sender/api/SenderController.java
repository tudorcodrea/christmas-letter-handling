package com.christmas.letter.sender.api;

import com.christmas.letter.sender.dto.LetterDto;
import com.christmas.letter.sender.service.LetterSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SenderController {
  private final LetterSenderService letterSenderService;

  @PostMapping("/send")
  public ResponseEntity<HttpStatus> sendLetter(@RequestBody LetterDto letterDto) {
    log.info("Sending letter to Santa from {}.", letterDto.getName());
    letterSenderService.send(letterDto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
