package com.ichong.controller;

import com.ichong.annotation.RedissonRateLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: HelloController
 * Description:
 *
 * @author 陈高文
 * @date 2023/5/5 14:39
 */
@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping("/test")
    @RedissonRateLimit(key = "testLimit",timeout = 10000,count = 3,
            interfaceName = "/hello/test",isEnable = true)
    public String testLimit(){
        return "测试接口限流";
    }
}
