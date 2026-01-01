package com.jtspringproject.JtSpringProject.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.dao.UserDao;
import com.jtspringproject.JtSpringProject.models.User;

/**
 * 用户数据访问实现类
 *
 * <p>实现UserDao接口，使用Hibernate SessionFactory直接操作数据库。
 * 提供用户的增删改查和认证功能。</p>
 *
 * <h3>主要功能：</h3>
 * <ul>
 *   <li>获取所有用户列表</li>
 *   <li>保存/更新用户</li>
 *   <li>用户登录验证</li>
 *   <li>检查用户名是否存在</li>
 * </ul>
 *
 * <h3>安全说明：</h3>
 * <p>当前密码采用明文存储和比对，生产环境建议使用加密方式（如BCrypt）。</p>
 *
 * @author JT Spring Project Team
 * @version 1.0
 * @see UserDao
 * @see User
 */
@Repository
public class UserDaoImpl implements UserDao {
    
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   
    /**
     * 获取所有用户
     *
     * @return 用户列表
     */
    @Override
    @Transactional
    public List<User> getAllUser() {
        logger.info("获取所有用户");
        try {
            Session session = this.sessionFactory.getCurrentSession();
            List<User> userList = session.createQuery("from CUSTOMER").list();
            logger.info("成功获取 {} 个用户", userList.size());
            return userList;
        } catch (Exception e) {
            logger.error("获取用户列表失败: {}", e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 保存或更新用户
     *
     * @param user 用户对象
     * @return 保存后的用户对象（包含自动生成的ID）
     */
    @Override
    @Transactional
    public User saveUser(User user) {
        logger.info("保存用户: {}", user.getUsername());
        try {
            this.sessionFactory.getCurrentSession().saveOrUpdate(user);
            logger.info("用户保存成功，ID: {}, 用户名: {}", user.getId(), user.getUsername());
            return user;
        } catch (Exception e) {
            logger.error("保存用户失败，用户名: {}, 错误: {}", user.getUsername(), e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 用户登录验证
     *
     * <p>根据用户名查询用户，然后比对密码。如果验证成功返回用户对象，失败返回空User对象。</p>
     *
     * <p><strong>安全警告：</strong>密码采用明文比对，生产环境应使用加密方式。</p>
     *
     * @param username 用户名
     * @param password 密码（明文）
     * @return 验证成功返回用户对象，失败返回空User对象
     */
    @Override
    @Transactional
    public User getUser(String username, String password) {
        logger.info("用户登录验证: {}", username);
        try {
            Query query = sessionFactory.getCurrentSession()
                    .createQuery("from CUSTOMER where username = :username");
            query.setParameter("username", username);

            User user = (User) query.getSingleResult();
            logger.debug("找到用户: {}", username);

            if(password.equals(user.getPassword())) {
                logger.info("用户登录成功: {}, 角色: {}", username, user.getRole());
                return user;
            } else {
                logger.warn("用户密码错误: {}", username);
                return new User();
            }
        } catch(Exception e){
            logger.warn("用户不存在或登录失败: {}, 错误: {}", username, e.getMessage());
            return new User();
        }
    }

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return 存在返回true，不存在返回false
     */
    @Override
    @Transactional
    public boolean userExists(String username) {
        logger.info("检查用户名是否存在: {}", username);
        try {
            Query query = sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(u) FROM CUSTOMER u WHERE u.username = :username");
            query.setParameter("username", username);
            Long count = (Long) query.uniqueResult();
            boolean exists = count != null && count > 0;
            logger.info("用户名 {} 存在: {}", username, exists);
            return exists;
        } catch (Exception e) {
            logger.error("检查用户名失败: {}, 错误: {}", username, e.getMessage(), e);
            throw e;
        }
    }
}
