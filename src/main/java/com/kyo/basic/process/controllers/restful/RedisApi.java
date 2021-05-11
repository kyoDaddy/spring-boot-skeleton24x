package com.kyo.basic.process.controllers.restful;

import com.google.gson.Gson;
import com.kyo.basic.process.vo.req.ReqUserVo;
import com.kyo.basic.process.vo.res.ResUserVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("redis")
public class RedisApi {

    private final RedisTemplate<String, String> redisTemplate;
    public RedisApi(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/get/{email}")
    public ResUserVo get(@PathVariable String email) {
        String json = redisTemplate.opsForValue().get(email);
        ResUserVo user = new Gson().fromJson(json, ResUserVo.class);
        return user;
    }

    @PostMapping("/save")
    public ResponseEntity<ReqUserVo> save(HttpServletRequest request, @RequestBody @Valid ReqUserVo user) throws Exception {
        StringBuffer log = (StringBuffer) request.getAttribute("logSb");

        final ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String json = new Gson().toJson(user);
        ops.set(user.getEmail(), json);

        log.append("\t user : " + user + "\r\n");

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
