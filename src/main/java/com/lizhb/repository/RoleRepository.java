package com.lizhb.repository;

import com.lizhb.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 角色数据DAO
 * Created by lizhb.
 * Date: 2018/11/7
 * Time: 16:01
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByUserId(Long userId);
}
