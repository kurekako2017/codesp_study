package com.jtspringproject.JtSpringProject.services.impl;

import com.jtspringproject.JtSpringProject.models.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.UserDao;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.UserService;

/**
 * 用户服务实现类
 *
 * <p>实现UserService接口，提供用户相关的业务逻辑处理。
 * 包括用户管理、登录验证等功能。</p>
 *
 * <h3>主要功能：</h3>
 * <ul>
 *   <li>用户列表查询</li>
 *   <li>用户添加/更新</li>
 *   <li>用户登录验证</li>
 *   <li>用户名存在性检查</li>
 * </ul>
 *
 * <h3>安全说明：</h3>
 * <p>当前系统使用明文密码，生产环境应实现密码加密功能。</p>
 *
 * @author JT Spring Project Team
 * @version 1.0
 * @see UserService
 * @see UserDao
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    
    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<User> getUsers(){
        logger.info("服务层：获取所有用户");
        try {
            List<User> users = this.userDao.getAllUser();
            logger.info("服务层：成功获取 {} 个用户", users.size());
            return users;
        } catch (Exception e) {
            logger.error("服务层：获取用户列表失败: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 添加或更新用户
     *
     * @param user 用户对象
     * @return 保存后的用户对象
     */
    @Override
    public User addUser(User user) {
        logger.info("服务层：添加/更新用户，用户名: {}", user.getUsername());
        try {
            User savedUser = this.userDao.saveUser(user);
            logger.info("服务层：用户保存成功，ID: {}, 用户名: {}, 角色: {}",
                    savedUser.getId(), savedUser.getUsername(), savedUser.getRole());
            return savedUser;
        } catch (Exception e) {
            logger.error("服务层：保存用户失败，用户名: {}, 错误: {}",
                    user.getUsername(), e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 用户登录验证
     *
     * <p>验证用户名和密码是否匹配。</p>
     *
     * @param username 用户名
     * @param password 密码
     * @return 验证成功返回用户对象，失败返回空User对象
     */
    @Override
    public User checkLogin(String username, String password) {
        logger.info("服务层：用户登录验证，用户名: {}", username);
        try {
            User user = this.userDao.getUser(username, password);
            if (user.getId() > 0) {
                logger.info("服务层：用户登录验证成功，用户名: {}, 角色: {}",
                        username, user.getRole());
            } else {
                logger.warn("服务层：用户登录验证失败，用户名: {}", username);
            }
            return user;
        } catch (Exception e) {
            logger.error("服务层：用户登录验证异常，用户名: {}, 错误: {}",
                    username, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 检查用户名是否已存在
     *
     * <p>用于用户注册时检查用户名是否被占用。</p>
     *
     * @param username 用户名
     * @return 存在返回true，不存在返回false
     */
    @Override
    public boolean checkUserExists(String username) {
        logger.info("服务层：检查用户名是否存在，用户名: {}", username);
        try {
            boolean exists = this.userDao.userExists(username);
            logger.info("服务层：用户名 {} 存在性: {}", username, exists);
            return exists;
        } catch (Exception e) {
            logger.error("服务层：检查用户名失败，用户名: {}, 错误: {}",
                    username, e.getMessage(), e);
            throw e;
        }
    }
}
