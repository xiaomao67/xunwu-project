package com.lizhb.service;

import com.lizhb.entity.User;

/**
 * 用户服务
 * Created by lizhb.
 * Date: 2018/11/7
 * Time: 15:30
 */
public interface IUserService {
    User findUserByName(String userName);
}
