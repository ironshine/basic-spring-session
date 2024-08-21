package com.sparta.basicspringsession.dto;

import lombok.Getter;

@Getter
public class MemberSaveResponseDto {
    private final String name;

    public MemberSaveResponseDto(String name) {
        this.name = name;
    }
}
