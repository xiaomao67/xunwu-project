package com.lizhb.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lizhb.
 * Date: 2018/11/6
 * Time: 10:47
 */
@Controller
public class AdminController {

    @GetMapping("admin/center")
    public String adminCenterPage(){
        return "admin/center";
    }

    @GetMapping("admin/welcome")
    public String welcome(){
        return "admin/welcome";
    }

    @GetMapping("admin/login")
    public String login(){
        return "admin/login";
    }
}
