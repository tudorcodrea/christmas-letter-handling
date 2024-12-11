package com.christmas.letter.sender.model.adapter;

import com.christmas.letter.sender.model.LetterEntity;
import com.christmas.letter.sender.model.LetterMessage;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LetterModelAdapter {

  @Mapping(target = "timestamp", expression = "java(System.currentTimeMillis())")
  LetterEntity toEntity(LetterMessage model);

//  @AfterMapping // or @BeforeMapping
//  default void fillInTimestamp(@MappingTarget LetterEntity entity) {
//    entity.setTimestamp(System.currentTimeMillis());
//  }

}
