package com.lizhb.repository;

import com.lizhb.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lizhb.
 * Date: 2018/11/2
 * Time: 9:57
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String userName);
}
