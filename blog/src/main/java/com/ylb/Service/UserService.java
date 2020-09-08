package com.ylb.Service;


import com.ylb.Pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
