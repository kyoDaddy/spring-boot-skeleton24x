package com.kyo.basic.base.enumeraion;

import lombok.Getter;

@Getter
public enum Result {
	OK(0, "정상처리"),
	FAIL(-1, "실패"),
	INVALID_PARAMETER(-4, "유효하지 않은 입력 정보입니다."),

	ALREADY_EXISTS(1, "이미 존재합니다."),
	NOT_EXISTS(2, "존재하지 않는 리소스입니다."),

	NOT_UNIQUE_NPSN(101, "중복된 uniqueKey 입니다.(DB 내 존재)"),
	NOT_EQUALS_NPSN_MAPPING_ID(102, "전달한 uniqueKey와 mappingId가 매핑되지 않습니다."),

	PERMISSION_DENIED(3, "권한이 없습니다.");

	private final int code;
	private final String message;

	Result(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	public static Result valueOf(int code) {
		Result result = resolve(code);
		if (result == null) {
			throw new IllegalArgumentException("No matching constant for [" + code + "]");
		}
		return result;
	}

	public static Result resolve(int code) {
		for (Result result : values()) {
			if (result.code == code) {
				return result;
			}
		}
		return null;
	}
}
