package com.christmas.letter.sender.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageBody {

  @JsonProperty("Type")
  private String type;

  @JsonProperty("MessageId")
  private String messageId;

  @JsonProperty("TopicArn")
  private String topicArn;

  @JsonProperty("Message")
  private String message;

  @JsonProperty("Timestamp")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private String timestamp;

  @JsonProperty("UnsubscribeURL")
  private String unsubscribeURL;

//  @JsonProperty("MessageAttributes")
//  private MessageAttributes messageAttributes;

  @JsonProperty("SignatureVersion")
  private String signatureVersion;

  @JsonProperty("Signature")
  private String signature;

  @JsonProperty("SigningCertURL")
  private String signingCertURL;
}
