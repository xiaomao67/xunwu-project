package com.lizhb.service.user;

import com.lizhb.entity.Role;
import com.lizhb.entity.User;
import com.lizhb.repository.RoleRepository;
import com.lizhb.repository.UserRepository;
import com.lizhb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhb.
 * Date: 2018/11/7
 * Time: 15:31
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if(user == null){
            return null;
        }

        List<Role> roles = roleRepository.findByUserId(user.getId());
        if(roles ==null || roles.isEmpty()){
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE"+role.getName())));
        user.setAuthorityList(authorities);
        return user;

    }
}
