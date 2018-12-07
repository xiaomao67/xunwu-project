package com.lizhb.web.controller;

import com.lizhb.base.APIRespose;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lizhb.
 * Date: 2018/11/2
 * Time: 14:57
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
//        model.addAttribute("name", "lizhb");
        return "index";
    }

    @GetMapping("/get")
    @ResponseBody
    public APIRespose get(Model model) {
        return new APIRespose(200, "成功");
    }

    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    public String accessError(){
        return "403";
    }

    @GetMapping("/405")
    public String access2Error(){
        return "403";
    }

    @GetMapping("/500")
    public String internalError(){
        return "500";
    }

    @GetMapping("/logout/page")
    public String logout(){
        return "logout";
    }
}
