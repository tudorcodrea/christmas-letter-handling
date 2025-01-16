package com.christmas.letter.sender.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "ProductInfo")
public class LetterMessage {

  @JsonProperty("email")
  private String email;

  @JsonProperty("name")
  private String name;

  @JsonProperty("content")
  private String content;

  @JsonProperty("timestamp")
  private long timestamp;

}
