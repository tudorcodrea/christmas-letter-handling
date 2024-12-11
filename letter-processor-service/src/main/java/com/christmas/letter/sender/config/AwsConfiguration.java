package com.christmas.letter.sender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AwsConfiguration {

  private static final AwsCredentialsProvider LOCAL_AWS_CREDENTIALS =
      StaticCredentialsProvider.create(AwsBasicCredentials.create("test", "test"));

  @Value("${config.aws.region}")
  private String region;

  @Bean
  public SnsAsyncClient snsAsyncClient() {
    return SnsAsyncClient.builder()
        .region(Region.of(region))
        .credentialsProvider(LOCAL_AWS_CREDENTIALS)
        .build();
  }

  @Bean
  public SqsAsyncClient sqsAsyncClient() {
    return SqsAsyncClient.builder()
        .region(Region.of(region))
        .credentialsProvider(LOCAL_AWS_CREDENTIALS)
        .build();
  }
}
