package com.kyo.basic.base.config;


import com.kyo.basic.base.constant.UrlConstants;
import com.kyo.basic.base.controller.filter.AuthFilter;
import com.kyo.basic.base.controller.filter.HttpDecryptionFilter;
import com.kyo.basic.sample.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

     private final AuthService authService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * spring-rest-docs 문서 제공 설정 (/docs/index.html)
         */
        registry.addResourceHandler(UrlConstants.DOCS + "/**")
                .addResourceLocations("classpath:/static/docs/");
    }

    @Bean
    public FilterRegistrationBean firstFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new AuthFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns(UrlConstants.INCLUDE_PATH_PATTERNS_AUTH);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean secondFilterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new HttpDecryptionFilter(authService));
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns(UrlConstants.INCLUDE_PATH_PATTERNS_DECRYPTION);

        return registrationBean;
    }


}
