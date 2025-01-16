package com.christmas.letter.sender.config;

import com.christmas.letter.sender.model.ChannelDetails;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ChannelConfiguration {

  private ConcurrentHashMap<String, ChannelDetails> channelDetailsMap;

  private ChannelConfiguration() {
    channelDetailsMap = new ConcurrentHashMap<>();
  }

  public void addChannelDetails(String topicName, ChannelDetails channelDetails) {
    channelDetailsMap.put(topicName, channelDetails);
  }

  public ChannelDetails getChannelDetails(String topicName) {
    return channelDetailsMap.get(topicName);
  }

  public void removeChannelDetails(String topicName) {
    channelDetailsMap.remove(topicName);
  }

  public boolean isChannelDetailsPresent(String topicName) {
    return channelDetailsMap.containsKey(topicName);
  }
}
