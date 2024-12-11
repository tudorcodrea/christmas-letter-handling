package com.christmas.letter.sender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Letter {
    String email;
    String name;
    List<String> wishes;
    String location;
}
