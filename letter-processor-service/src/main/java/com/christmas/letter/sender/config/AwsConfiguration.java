package com.christmas.letter.sender.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfiguration {

  @Value("${aws.access-key}")
  public String accessKeyId;

  @Value("${aws.secret-key}")
  public String secretKey;

  @Value("${config.aws.region}")
  private String region;

  @Value("${spring.cloud.aws.sns.endpoint}")
  private String endpointUrl;

  @Bean
  public SqsClient sqsClient() {
    var credProvider =
        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretKey));
    return SqsClient.builder()
        .region(Region.of(region))
        .endpointOverride(URI.create(endpointUrl))
        .credentialsProvider(credProvider)
        .build();
  }

  @Bean
  public SnsClient snsClient() {
    var credProvider =
        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretKey));
    return SnsClient.builder()
        .region(Region.of(region))
        .endpointOverride(URI.create(endpointUrl))
        .credentialsProvider(credProvider)
        .build();
  }

  @Bean
  public SnsAsyncClient snsAsyncClient() {
    var credProvider =
        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretKey));
    return SnsAsyncClient.builder()
        .region(Region.of(region))
        .endpointOverride(URI.create(endpointUrl))
        .credentialsProvider(credProvider)
        .build();
  }

  @Bean
  public SqsAsyncClient sqsAsyncClient() {
    var credProvider =
        StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKeyId, secretKey));
    return SqsAsyncClient.builder()
        .region(Region.of(region))
        .endpointOverride(URI.create(endpointUrl))
        .credentialsProvider(credProvider)
        .build();
  }

//  @Bean
//  @Primary
//  public AmazonSQSAsync amazonSQSAsync() {
//    return AmazonSQSAsyncClientBuilder.standard()
//        .withCredentials(credentialsProvider())
//        .withRegion(region)
//        .build();
//  }
//
//  @Bean
//  public AWSCredentialsProvider credentialsProvider() {
//    return new DefaultAWSCredentialsProviderChain();
//  }
//
//  @Bean
//  public QueueMessagingTemplate queueMessagingTemplate() {
//    return new QueueMessagingTemplate(amazonSQSAsync());
//  }
//
//
//  @Bean
//  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
//    SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
//    factory.setAmazonSqs(amazonSQSAsync);
//    factory.setAutoStartup(true);
//    factory.setMaxNumberOfMessages(10);
//    factory.setTaskExecutor(createDefaultTaskExecutor());
//    return factory;
//  }
//
//  protected AsyncTaskExecutor createDefaultTaskExecutor() {
//    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//    threadPoolTaskExecutor.setThreadNamePrefix("SQSExecutor - ");
//    threadPoolTaskExecutor.setCorePoolSize(100);
//    threadPoolTaskExecutor.setMaxPoolSize(100);
//    threadPoolTaskExecutor.setQueueCapacity(2);
//    threadPoolTaskExecutor.afterPropertiesSet();
//    return threadPoolTaskExecutor;
//  }


}
