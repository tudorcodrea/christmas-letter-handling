package com.christmas.letter.sender.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "event.queues")
public class AwsQueuesProperties {

  private String letterQueue;


//  SPRING CLOUD AWS SQS CONFIGURATION

//  @Bean
//  public SqsMessagingMessageConverter sqsMessagingMessageConverter(
//      final MessageConverter jackson2MessageConverter ) {
//    // Create converter instance
//    SqsMessagingMessageConverter messageConverter = new SqsMessagingMessageConverter();
//
//    // Configure Type Header
//    messageConverter.setPayloadTypeHeader( "payload-type-header" );
//
//    // Configure Header Mapper
//    SqsHeaderMapper headerMapper = new SqsHeaderMapper();
//    headerMapper.setAdditionalHeadersFunction(
//        ( ( sqsMessage, accessor ) -> accessor.toMessageHeaders() ) );
//    messageConverter.setHeaderMapper( headerMapper );
//
//    // Configure Payload Converter
//    messageConverter.setPayloadMessageConverter( jackson2MessageConverter );
//    return messageConverter;
//  }
//
//  @Bean
//  @Primary
//  public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory(
//      SqsMessagingMessageConverter messageConverter, SqsProperties sqsProperties,
//      ObjectProvider<SqsAsyncClient> sqsAsyncClient,
//      ObjectProvider<AsyncErrorHandler<Object>> asyncErrorHandler,
//      ObjectProvider<ErrorHandler<Object>> errorHandler,
//      ObjectProvider<AsyncMessageInterceptor<Object>> asyncInterceptors,
//      ObjectProvider<MessageInterceptor<Object>> interceptors ) {
//
//    SqsMessageListenerContainerFactory<Object> factory = new SqsMessageListenerContainerFactory<>();
//    factory.configure( sqsContainerOptionsBuilder -> configureContainerOptions( sqsProperties,
//        sqsContainerOptionsBuilder ) );
//    sqsAsyncClient.ifAvailable( factory::setSqsAsyncClient );
//    asyncErrorHandler.ifAvailable( factory::setErrorHandler );
//    errorHandler.ifAvailable( factory::setErrorHandler );
//    interceptors.forEach( factory::addMessageInterceptor );
//    asyncInterceptors.forEach( factory::addMessageInterceptor );
//
//    // Set MessageConverter to the factory or container
//    factory.configure( options -> options.messageConverter( messageConverter ) );
//    return factory;
//  }
//
//  private void configureContainerOptions( SqsProperties sqsProperties,
//                                          ContainerOptionsBuilder<?, ?> options ) {
//    PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
//    mapper.from( sqsProperties.getListener().getMaxConcurrentMessages() )
//        .to( options::maxConcurrentMessages );
//    mapper.from( sqsProperties.getListener().getMaxMessagesPerPoll() )
//        .to( options::maxMessagesPerPoll );
//    mapper.from( sqsProperties.getListener().getPollTimeout() ).to( options::pollTimeout );
//  }


  @Bean
  public MessageConverter jackson2MessageConverter( final ObjectMapper objectMapper ) {
    final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setObjectMapper( objectMapper );
    converter.setSerializedPayloadClass( String.class );
    // set strict content type match to false to enable the listener to handle AWS events
    converter.setStrictContentTypeMatch( false );
    return converter;
  }

}
