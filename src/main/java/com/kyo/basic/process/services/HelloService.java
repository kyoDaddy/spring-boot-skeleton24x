package com.kyo.basic.process.services;

import com.kyo.basic.process.vo.req.ReqUserVo;


public interface HelloService {

    ReqUserVo getUser(ReqUserVo info);

    String getHello();
}
