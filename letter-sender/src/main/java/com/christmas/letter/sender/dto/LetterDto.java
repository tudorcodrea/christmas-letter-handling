package com.christmas.letter.sender.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LetterDto (
        @Email @NotNull(message = "Please use a valid email address!")
        String email,
        @NotBlank(message = "Name should be not empty!") String name,
        @NotBlank(message = "Wishes should be not empty!") String wishes,
        @NotBlank(message = "Please use a valid location!") String location){}
