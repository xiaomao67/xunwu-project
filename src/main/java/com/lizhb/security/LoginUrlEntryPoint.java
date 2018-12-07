package com.lizhb.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lizhb.
 * Date: 2018/11/15
 * Time: 13:29
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private PathMatcher pathMatcher = new AntPathMatcher();
    private final Map<String, String> authEntryPointMap;

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);

        authEntryPointMap = new HashMap<>();
        //普通用户登录入口映射
        authEntryPointMap.put("/user/**", "/user/login");
        authEntryPointMap.put("/admin/**", "/admin/login");
    }

    /**
     * 根据请求跳转到指定的页面，父类是默认使用loginFormUrl
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        //根据请求的路径，与已经预置好的路由mapping进行匹配，跳转到相应的login入口
        for(Map.Entry<String, String> authEntry : this.authEntryPointMap.entrySet()){
            if(this.pathMatcher.match(authEntry.getKey(), uri)){
                return authEntry.getValue();
            }
        }

        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}