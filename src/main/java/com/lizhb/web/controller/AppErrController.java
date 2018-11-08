package com.lizhb.web.controller;

import com.lizhb.base.APIRespose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * web错误全局配置
 * Created by lizhb.
 * Date: 2018/11/5
 * Time: 14:40
 */
@Controller
public class AppErrController implements ErrorController {
    private static final String ERROR_PATH="/error";

    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @Autowired
    public AppErrController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * 错误页面处理(text/html)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response){
        int status = response.getStatus();
        switch (status){
            case 301:
                return "301";
            case 404:
                return "404";
            case 500:
                return "500";
        }
        return "index";
    }

    /**
     * 除web页面外的错误处理，如json/XML
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public APIRespose errorApiHandler(HttpServletRequest request){
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        int status = getRequestStatus(request);
        return APIRespose.ofMessage(status, String.valueOf(attr.getOrDefault("message", "error")));
    }

    private int getRequestStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(status != null){
            return status;
        }
        return 500;
    }
}
