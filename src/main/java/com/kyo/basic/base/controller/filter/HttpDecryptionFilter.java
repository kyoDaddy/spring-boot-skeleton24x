package com.kyo.basic.base.controller.filter;


import com.kyo.basic.base.constant.CommonConstants;
import com.kyo.basic.sample.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
public class HttpDecryptionFilter implements Filter {

    private AuthService authService;

    public HttpDecryptionFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * decryption encryted body
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 암호화 미체크 헤더값 확인
        if (ObjectUtils.isEmpty(request.getHeader(CommonConstants.REQ_HEADER_PASS_ENCRYPTION))) {
            RequestDecryptionWrapper requestDecryptionWrapper = new RequestDecryptionWrapper(request, authService);
            filterChain.doFilter(requestDecryptionWrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
