package com.lizhb.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lizhb.
 * Date: 2018/11/1
 * Time: 15:19
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello xunwu-project.";
    }
}
