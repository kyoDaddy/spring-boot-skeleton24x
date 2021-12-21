package com.kyo.basic.base.controller.interceptor;//package com.nexon.sampleapi.base.controller.interceptor;
//
//import com.nexon.sampleapi.base.utils.ConvertUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.MDC;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.AsyncHandlerInterceptor;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Enumeration;
//import java.util.UUID;
//
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class MdcLoggingInterceptor implements HandlerInterceptor {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		// MDC (Mapped Diagnostic Context) : 멀티 클라이언트 환경에서 다른 클라이언트와 값을 구별하여 로그 추적할 수 있도록 제공되는 map
//		String traceId = UUID.randomUUID().toString();
//		MDC.put("traceId", traceId);
//		log.info("request : {} ", request.getRequestURI());
//
//		Enumeration<String> headers = request.getHeaderNames();
//		while (headers.hasMoreElements()) {
//			String name = headers.nextElement();
//			log.info("REQUEST: {}, HEADER {} : {}", request.getRequestURI(), name, request.getHeader(name));
//		}
//
//		MDC.get("1");
//
//		return HandlerInterceptor.super.preHandle(request, response, handler);
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//						   ModelAndView modelAndView) throws Exception {
//		MDC.clear();
//
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//}
