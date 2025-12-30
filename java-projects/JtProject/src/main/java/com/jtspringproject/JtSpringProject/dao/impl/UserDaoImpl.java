package com.jtspringproject.JtSpringProject.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.dao.UserDao;
import com.jtspringproject.JtSpringProject.models.User;

/**
 * 用户数据访问实现类
 */
@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   
    @Override
    @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User>  userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }
    
    @Override
    @Transactional
    public User saveUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
        System.out.println("User added" + user.getId());
        return user;
    }
    
    @Override
    @Transactional
    public User getUser(String username, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
        query.setParameter("username", username);
        
        try {
            User user = (User) query.getSingleResult();
            System.out.println("user.getPassword():" + user.getPassword());
            if(password.equals(user.getPassword())) {
                return user;
            }else {        
                return new User();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            User user = new User();
            return user;
        }
    }

    @Override
    @Transactional
    public boolean userExists(String username) {
        Query query = sessionFactory.getCurrentSession()
            .createQuery("SELECT COUNT(u) FROM CUSTOMER u WHERE u.username = :username");
        query.setParameter("username", username);
        Long count = (Long) query.uniqueResult();
        return count != null && count > 0;
    }
}
