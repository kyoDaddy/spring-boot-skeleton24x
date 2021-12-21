package com.kyo.basic.sample.controller.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentUtils {

	static OperationRequestPreprocessor getDocumentRequest() {
		return preprocessRequest(
			modifyUris() // 문서상에 표기되는 기본값 변경
				.scheme("https")
				.host("public.api.nexon.com")
				.removePort(),
			prettyPrint());
	}

	static OperationResponsePreprocessor getDocumentResponse() {
		return preprocessResponse(prettyPrint());
	}


	static RestDocumentationResultHandler toDocument(String title) {
		return document(title, getDocumentRequest(), getDocumentResponse());
	}

	static String toJson(Object object) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new IllegalStateException("직렬화 오류");
		}
	}


}
