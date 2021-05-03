package com.kyo.basic.process.vo.base;

import com.kyo.basic.process.vo.base.ExceptionEnum;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ExceptionEnum error;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}
