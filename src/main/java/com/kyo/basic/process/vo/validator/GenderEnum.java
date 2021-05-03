package com.kyo.basic.process.vo.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    M("남자"),
    F("여자");

    private String description;
}
