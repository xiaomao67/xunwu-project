package com.lizhb.security;

import com.lizhb.entity.User;
import com.lizhb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * 自定义认证实现
 * Created by lizhb.
 * Date: 2018/11/6
 * Time: 14:48
 */
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    private IUserService userService;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        User user = userService.findUserByName(userName);
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("user not found");
        }
        if(passwordEncoder.isPasswordValid(user.getPassword(), inputPassword, user.getId())){
            return new PreAuthenticatedAuthenticationToken(user, null, user.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
