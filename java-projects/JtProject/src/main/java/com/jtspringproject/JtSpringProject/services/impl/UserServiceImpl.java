package com.jtspringproject.JtSpringProject.services.impl;

import com.jtspringproject.JtSpringProject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.UserDao;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.UserService;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<User> getUsers(){
        return this.userDao.getAllUser();
    }
    
    @Override
    public User addUser(User user) {
        return this.userDao.saveUser(user);
    }
    
    @Override
    public User checkLogin(String username, String password) {
        return this.userDao.getUser(username, password);
    }

    @Override
    public boolean checkUserExists(String username) {
        return this.userDao.userExists(username);
    }
}
