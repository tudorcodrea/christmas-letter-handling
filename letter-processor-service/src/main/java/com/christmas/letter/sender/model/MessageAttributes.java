package com.christmas.letter.sender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageAttributes {
  private String id;
  private String contentType;
  private long timestamp;
}
