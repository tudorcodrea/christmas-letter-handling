package com.christmas.letter.sender.model.adapter;

import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.LetterEntity;
import com.christmas.letter.sender.model.MessageToChannel;
import com.christmas.letter.sender.model.MessageToChannelDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MessageChannelModelAdapter {

  MessageToChannel toEntity(MessageToChannelDto model);

}
