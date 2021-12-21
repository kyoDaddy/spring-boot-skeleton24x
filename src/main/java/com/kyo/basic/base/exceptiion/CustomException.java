package com.kyo.basic.base.exceptiion;

import com.kyo.basic.base.enumeraion.Result;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	private final Result result;
	private final String debug;

	public CustomException(Result result, String debug) {
		this.result = result;
		this.debug = debug;
	}
}
