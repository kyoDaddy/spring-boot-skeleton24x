package com.kyo.basic.process.services.impl;

import com.kyo.basic.process.services.HelloService;
import com.kyo.basic.process.vo.base.ExceptionEnum;
import com.kyo.basic.process.vo.req.ReqUserVo;
import com.kyo.basic.process.vo.base.ApiException;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public ReqUserVo getUser(ReqUserVo info) {
        return null;
    }

    @Override
    public String getHello() {
        throw new ApiException(ExceptionEnum.SECURITY_01);
    }
}
