package com.christmas.letter.sender.model.adapter;

import com.christmas.letter.sender.model.LetterDto;
import com.christmas.letter.sender.model.LetterEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LetterModelAdapter {

  @Mapping(target = "timestamp", expression = "java(System.currentTimeMillis())")
  LetterEntity toEntity(LetterDto model);

//  @AfterMapping // or @BeforeMapping
//  default void fillInTimestamp(@MappingTarget LetterEntity entity) {
//    entity.setTimestamp(System.currentTimeMillis());
//  }

}
