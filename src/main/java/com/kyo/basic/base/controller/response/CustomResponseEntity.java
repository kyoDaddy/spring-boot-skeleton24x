package com.kyo.basic.base.controller.response;

import com.kyo.basic.base.enumeraion.Result;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponseEntity<T> {
	private int code;
	private String message;
	private T data;

	private String debug;

	public static <T> CustomResponseEntity<T> success(T data) {
		return CustomResponseEntity.<T>builder()
			.code(Result.OK.getCode())
			.message(Result.OK.getMessage())
			.data(data)
			.build();
	}

	public static <T> CustomResponseEntity<T> success() {
		return CustomResponseEntity.<T>builder()
			.code(Result.OK.getCode())
			.message(Result.OK.getMessage())
			.build();
	}

	public static <T> CustomResponseEntity<T> fail(Result result, String debug) {
		return CustomResponseEntity.<T>builder()
			.code(result.getCode())
			.message(result.getMessage())
			.debug(debug)
			.build();
	}

	@Builder
	public CustomResponseEntity(int code, String message, T data, String debug) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.debug = debug;
	}
}
