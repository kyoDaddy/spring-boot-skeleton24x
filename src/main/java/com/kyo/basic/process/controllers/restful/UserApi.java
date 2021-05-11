package com.kyo.basic.process.controllers.restful;

import com.kyo.basic.process.repository.book.UserRepository;
import com.kyo.basic.process.repository.test.TestRepository;
import com.kyo.basic.process.vo.base.SessionVo;
import com.kyo.basic.process.vo.req.ReqUserVo;
import com.kyo.basic.process.vo.res.ResUserVo;
import com.kyo.basic.process.vo.table.book.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserApi {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public TestRepository testRepository;

    @Resource
    private SessionVo sessionVo;


    @PostMapping("/")
    public ReqUserVo getUser(HttpServletRequest request, @RequestBody @Valid ReqUserVo user) {
        StringBuffer log = (StringBuffer) request.getAttribute("logSb");

        UserVo vo1 = userRepository.getUser(null);
        UserVo vo2 = testRepository.selectTestUser();
        log.append(vo1.toString() + " :: " + vo2.toString());

        return user;
    }

    @PostMapping("sign-in")
    public ResponseEntity<ResUserVo> signIn(HttpServletRequest request, @RequestBody @Valid ReqUserVo user) throws Exception {
        StringBuffer log = (StringBuffer) request.getAttribute("logSb");

        ResUserVo resVo = new ResUserVo();
        resVo.setEmail("test@naver.com");
        resVo.setUsername("test");

        sessionVo.setUserId("test@naver.com");
        sessionVo.setUserNm("test");

        return new ResponseEntity<>(resVo, HttpStatus.OK);
    }

    @GetMapping("session")
    public String getSession() {
        return sessionVo.toString();
    }

}
