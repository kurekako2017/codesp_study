package com.jtspringproject.JtSpringProject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jtspringproject.JtSpringProject.models.User;


@Repository
public class userDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
   @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
		List<User>  userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }
    
    @Transactional
	public User saveUser(User user) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(user);
		System.out.println("User added" + user.getId());
        return user;
	}
    
//    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession().
//    }
    @Transactional
    public User getUser(String wwww,String password) {
    	Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
    	query.setParameter("username",wwww);
    	
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

    
 // 使用 @Transactional 注解，表示该方法在一个事务中执行。
 // 如果方法内出现异常，事务会自动回滚，保证数据一致性。
	/*
	 * @Transactional public User getUser(String username, String password) {
	 * 
	 * // 使用 Hibernate 的 HQL 查询语句，查询 CUSTOMER 表中用户名匹配的记录。 // 注意：这里的 "from CUSTOMER"
	 * 通常应是实体类名（例如 "from User"）， // 如果 CUSTOMER 是数据库表名，应确认是否映射正确。 Query query =
	 * sessionFactory.getCurrentSession()
	 * .createQuery("from CUSTOMER where username = :username");
	 * 
	 * // 设置命名参数 ":username" 的具体值 query.setParameter("username", username);
	 * 
	 * try { // 执行查询，返回唯一结果。如果查不到或查出多条，会抛出异常。 User user = (User)
	 * query.getSingleResult();
	 * 
	 * // 打印数据库中取到的密码（调试用，不建议在生产环境打印密码） System.out.println("user.getPassword():" +
	 * user.getPassword());
	 * 
	 * // 比对用户输入的密码是否与数据库一致 if (password.equals(user.getPassword())) { //
	 * 如果密码正确，返回完整的用户对象 return user; } else { // 密码错误，返回一个新的空 User 对象 return new
	 * User(); }
	 * 
	 * } catch (Exception e) { // 捕获查询或类型转换等异常（如用户名不存在时）
	 * System.out.println(e.getMessage());
	 * 
	 * // 出现异常时，返回一个空的 User 对象 User user = new User(); return user; } }
	 */

    
    
	@Transactional
	public boolean userExists(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
		query.setParameter("username",username);
		return !query.getResultList().isEmpty();
	}
}
