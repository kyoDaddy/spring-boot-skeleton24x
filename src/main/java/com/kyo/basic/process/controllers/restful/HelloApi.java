package com.kyo.basic.process.controllers.restful;

import com.kyo.basic.config.properties.DaemonProp;
import com.kyo.basic.process.services.HelloService;
import com.kyo.basic.process.vo.base.DaemonVo;
import com.kyo.basic.process.vo.req.ReqUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * HttpMessageConverters
 *      -> HttpMessageConverters는 스프링 프레임워크가 제공하는 인터페이스이고 스프링 MVC의 일부분
 *      -> HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용 (@RequestBody, @ResponseBody와 함께 사용됨)
 *
 * ViewResolve
 *      -> 스프링부트에 등록 되어있는 스프링 웹 MVC의 ContentNegotiatingViewResolver 가 어떤 contentType일 때 어떤 응답을 보내고, Accept header 요청에 의해서 해당 요청에 맞는 응답을 보내는 작업을 알아서 해준다.
 *      -> Accept Header (XML, JSON..)에 따라 뷰 리졸버가 달라진다.
 *
 *
 */
@RestController
public class HelloApi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /* bean 에서 가져오기 */
    @Resource(name="daemon-config")
    private DaemonVo daemonVo;

    /* 자동 주입 */
    private final DaemonProp daemonProp;
    private final HelloService helloService;
    public HelloApi(DaemonProp daemonProp, HelloService helloService) {
        this.daemonProp = daemonProp;
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String index() {
        return "index " + this.daemonProp.getNickName();
    }

    @GetMapping("/hello")
    public String hello() {
        return this.helloService.getHello();
    }

    @GetMapping("/download")
    public ResponseEntity<org.springframework.core.io.Resource> download() throws IOException {
        Path path = Paths.get("C:\\icon_new.gif");
        String contentType = Files.probeContentType(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("icon_new.gif", StandardCharsets.UTF_8)
                .build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        org.springframework.core.io.Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }


}
