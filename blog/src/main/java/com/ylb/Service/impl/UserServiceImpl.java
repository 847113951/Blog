package com.ylb.Service.impl;

import com.ylb.Mapper.UserMapper;
import com.ylb.Pojo.Example.UserExample;
import com.ylb.Pojo.User;
import com.ylb.Service.UserService;
import com.ylb.Util.MD5;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {

        User user = userMapper.checkUser(username, MD5.encrypByMd5(password));
        if (user == null) {
            System.out.println(1);
            return null;
        }
        return user;
    }
}
