package com.lizhb.entity;

import com.lizhb.ApplicationTests;
import com.lizhb.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lizhb.
 * Date: 2018/11/2
 * Time: 10:06
 */
public class UserRepositoryTest extends ApplicationTests{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){
        User user = userRepository.findOne(1L);
        System.out.println("testFindOne get user: " + user);
    }
}
