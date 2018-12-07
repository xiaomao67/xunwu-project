package com.lizhb.web.controller.admin;

import com.lizhb.base.APIRespose;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by lizhb.
 * Date: 2018/11/6
 * Time: 10:47
 */
@Controller
public class AdminController {

    @GetMapping("admin/center")
    public String adminCenterPage() {
        System.out.println("admin ... adminCenterPage");
        return "admin/center";
    }

    @GetMapping("admin/welcome")
    public String welcome() {
        System.out.println("admin ... welcome");
        return "admin/welcome";
    }

    @GetMapping("admin/login")
    public String login() {
        System.out.println("admin ... login");
        return "admin/login";
    }

    @GetMapping("admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }

    @PostMapping(value = "admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public APIRespose uploadPhoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return APIRespose.ofStatus(APIRespose.Status.NOT_VALID_PARAM);
        }

        String fileName = file.getOriginalFilename();
        File target = new File("D:\\work\\workspace my\\xunwu-project\\tmp\\" + fileName);
        try {
            file.transferTo(target);
        } catch (IOException e) {
            return APIRespose.ofStatus(APIRespose.Status.INTERNAL_ERROR);
        }
        return APIRespose.ofSuccess(null);
    }
}
