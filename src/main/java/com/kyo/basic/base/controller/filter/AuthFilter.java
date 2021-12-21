package com.kyo.basic.base.controller.filter;

import com.kyo.basic.base.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // check header
        boolean isAuth = checkHeader(request);
        if (!isAuth) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 인증이 부족한 상태
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 인페이스 헤더값 검사
     */
    private boolean checkHeader(HttpServletRequest request) {
        return CommonConstants.REQ_HEADER_VALUE.stream()
                .allMatch(headerNm -> !ObjectUtils.isEmpty(request.getHeader(headerNm)));
    }


}
