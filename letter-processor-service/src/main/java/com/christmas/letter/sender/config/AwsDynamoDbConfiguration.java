package com.christmas.letter.sender.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.christmas.letter.sender.repository")
public class AwsDynamoDbConfiguration {

  @Value("${config.aws.dynamodb.url}")
  private String amazonDynamoDBEndpoint;

  @Value("${config.aws.dynamodb.access-key}")
  private String amazonAWSAccessKey;

  @Value("${config.aws.dynamodb.secret-key}")
  private String amazonAWSSecretKey;

  @Value("${config.aws.region}")
  private String region;

  private AWSCredentialsProvider awsDynamoDBCredentials() {
    return new AWSStaticCredentialsProvider(
        new BasicAWSCredentials(amazonAWSAccessKey, amazonDynamoDBEndpoint));
  }

  @Primary
  @Bean
  public DynamoDBMapperConfig dynamoDBMapperConfig() {
    return DynamoDBMapperConfig.DEFAULT;
  }

  @Bean
  @Primary
  public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
                                       DynamoDBMapperConfig config) {
    return new DynamoDBMapper(amazonDynamoDB, config);
  }

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {

    return AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region))
        .withCredentials(awsDynamoDBCredentials()).build();
  }


//  @Bean
//  public AmazonDynamoDB amazonDynamoDB() {
//
//    var credProvider =
//        StaticCredentialsProvider.create(AwsBasicCredentials.create(amazonAWSSecretKey, amazonAWSAccessKey));
//
//    AmazonDynamoDB amazonDynamoDB
//        = AmazonDynamoDBClientBuilder.standard()
//        .withEndpointConfiguration(
//            new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region))
//        .withCredentials(credProvider)
//        .build();
//    return amazonDynamoDB;
//  }

//  @Bean
//  public AmazonDynamoDB amazonDynamoDB() {
//    AmazonDynamoDB amazonDynamoDB
//        = new AmazonDynamoDBClient(amazonAWSCredentials());
//
//    if (!StringUtils.hasText(amazonDynamoDBEndpoint)) {
//      amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
//    }
//
//    return amazonDynamoDB;
//  }
//
//  @Bean
//  public AWSCredentials amazonAWSCredentials() {
//    return new BasicAWSCredentials(
//        amazonAWSAccessKey, amazonAWSSecretKey);
//  }
}
