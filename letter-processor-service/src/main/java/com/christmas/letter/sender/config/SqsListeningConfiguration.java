package com.christmas.letter.sender.config;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SqsListeningConfiguration {
//    implements MessageListener<String> {
//  private final String id;
//  private final SqsMessageListenerContainerFactory<String> sqsMessageListenerContainerFactory;
//  private final DefaultListenerContainerRegistry defaultListenerContainerRegistry;
//  private final EndpointRegistrar endpointRegistrar;
//  private final MessageListenerContainer<String> messageListenerContainer;
//
//  public SqsListeningConfiguration(
//      SqsMessageListenerContainerFactory<String> sqsMessageListenerContainerFactory,
//      DefaultListenerContainerRegistry defaultListenerContainerRegistry,
//      EndpointRegistrar endpointRegistrar, Properties config)
//      throws NoSuchMethodException {
//    this.endpointRegistrar = endpointRegistrar;
//    this.sqsMessageListenerContainerFactory = sqsMessageListenerContainerFactory;
//    this.defaultListenerContainerRegistry = defaultListenerContainerRegistry;
//
//    var sqsProps = new SqsProperties(config);
//    this.id = sqsProps.getId();
//
//    var sqsEndpoint = buildSqsEndpoint(sqsProps);
//
//    DefaultMessageHandlerMethodFactory handlerMethodFactory = new DefaultMessageHandlerMethodFactory();
//    configureDefaultHandlerMethodFactory(handlerMethodFactory);
//    sqsEndpoint.setHandlerMethodFactory(handlerMethodFactory);
//    sqsEndpoint.setBean(this);
//
//    switch (sqsEndpoint.getAcknowledgementMode()) {
//      case MANUAL -> sqsEndpoint.setMethod(
//          this.getClass().getMethod("onMessage", Message.class, Acknowledgement.class));
//      case ON_SUCCESS, ALWAYS -> sqsEndpoint.setMethod(this.getClass().getMethod(
//          "onMessage", Message.class));
//    }
//
//    this.messageListenerContainer = this.sqsMessageListenerContainerFactory.createContainer(
//        sqsEndpoint);
//  }
//
//  private SqsEndpoint buildSqsEndpoint(SqsProperties props) {
//    return new SqsEndpoint.SqsEndpointBuilder()
//        .id(props.getId())
//        .queueNames(List.of(props.getQueueNames()))
//        .factoryBeanName(props.getFactoryBeanName())
//        .maxConcurrentMessages(props.getMaxConcurrentMessages())
//        .pollTimeoutSeconds(props.getPollTimeoutSeconds())
//        .maxMessagesPerPoll(props.getMaxMessagesPerPoll())
//        .messageVisibility(props.getMessageVisibility())
//        .acknowledgementMode(props.getAcknowledgementMode())
//        .build();
//  }
//
//
//  public void start() {
//
//    var container = this.defaultListenerContainerRegistry.getContainerById(
//        messageListenerContainer.getId());
//    if (Objects.nonNull(container)) {
//      container.start();
//      return;
//    }
//
//    this.defaultListenerContainerRegistry.registerListenerContainer(messageListenerContainer);
//    this.defaultListenerContainerRegistry.getContainerById(messageListenerContainer.getId())
//        .start();
//
//  }
//
//
//  public boolean isRunning() {
//    return this.defaultListenerContainerRegistry.getContainerById(messageListenerContainer.getId())
//        .isRunning();
//  }
//
//
//  public void restart() {
//    this.defaultListenerContainerRegistry.getContainerById(messageListenerContainer.getId()).stop();
//    this.defaultListenerContainerRegistry.getContainerById(messageListenerContainer.getId())
//        .start();
//
//  }
//
//  @Override
//  public void onMessage(Message<String> message) {
//    log.info("Received message: {}", message);
//  }
//
//  public void onMessage(Message<String> message, Acknowledgement acknowledgement) {
//    log.info("Received message with Ack: {}", message);
//    acknowledgement.acknowledge();
//  }
//
//  private CompositeMessageConverter createCompositeMessageConverter() {
//    List<MessageConverter> messageConverters = new ArrayList<>();
//    messageConverters.add(new StringMessageConverter());
//    messageConverters.add(new SimpleMessageConverter());
//    messageConverters.add(
//        createDefaultMappingJackson2MessageConverter(this.endpointRegistrar.getObjectMapper()));
//    this.endpointRegistrar.getMessageConverterConsumer().accept(messageConverters);
//    return new CompositeMessageConverter(messageConverters);
//  }
//
//  private void configureDefaultHandlerMethodFactory(
//      DefaultMessageHandlerMethodFactory handlerMethodFactory) {
//    CompositeMessageConverter compositeMessageConverter = createCompositeMessageConverter();
//
//    List<HandlerMethodArgumentResolver> methodArgumentResolvers = new ArrayList<>(
//        createArgumentResolvers(compositeMessageConverter));
//    this.endpointRegistrar.getMethodArgumentResolversConsumer().accept(methodArgumentResolvers);
//    handlerMethodFactory.setArgumentResolvers(methodArgumentResolvers);
//    handlerMethodFactory.afterPropertiesSet();
//  }
//
//  private List<HandlerMethodArgumentResolver> createArgumentResolvers(
//      MessageConverter messageConverter) {
//    return Arrays.asList(
//        new AcknowledgmentHandlerMethodArgumentResolver(),
//        new BatchAcknowledgmentArgumentResolver(),
//        new HeadersMethodArgumentResolver(),
//        new BatchPayloadMethodArgumentResolver(messageConverter,
//            this.endpointRegistrar.getValidator()),
//        new MessageMethodArgumentResolver(messageConverter),
//        new PayloadMethodArgumentResolver(messageConverter, this.endpointRegistrar.getValidator()));
//  }
//
//  private MappingJackson2MessageConverter createDefaultMappingJackson2MessageConverter(
//      ObjectMapper objectMapper) {
//    MappingJackson2MessageConverter jacksonMessageConverter = new MappingJackson2MessageConverter();
//    jacksonMessageConverter.setSerializedPayloadClass(String.class);
//    jacksonMessageConverter.setStrictContentTypeMatch(false);
//    if (objectMapper != null) {
//      jacksonMessageConverter.setObjectMapper(objectMapper);
//    }
//    return jacksonMessageConverter;
//  }

}
