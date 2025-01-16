package com.christmas.letter.sender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChannelDetails {

  private String topicName;
  private String queueName;
  private String topicArn;
  private String queueUrl;
  private String subscriptionArn;

}
