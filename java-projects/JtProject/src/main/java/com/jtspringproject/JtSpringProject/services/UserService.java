package com.jtspringproject.JtSpringProject.services;

import com.jtspringproject.JtSpringProject.models.User;
import java.util.List;

/**
 * 用户服务接口
 * 定义用户业务逻辑的契约
 */
public interface UserService {
    
    /**
     * 获取所有用户
     * @return 用户列表
     */
    List<User> getUsers();
    
    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加后的用户
     */
    User addUser(User user);
    
    /**
     * 验证用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户对象，如果验证失败返回空对象
     */
    User checkLogin(String username, String password);
    
    /**
     * 检查用户是否存在
     * @param username 用户名
     * @return 用户是否存在
     */
    boolean checkUserExists(String username);
}
