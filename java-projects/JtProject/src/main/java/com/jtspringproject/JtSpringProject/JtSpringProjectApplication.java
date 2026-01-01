package com.jtspringproject.JtSpringProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Spring Boot电商应用主入口类
 *
 * <p>该应用是一个基于Spring Boot 2.6.4的电商系统，采用传统的MVC架构</p>
 *
 * <h3>技术栈：</h3>
 * <ul>
 *   <li>Spring Boot 2.6.4 - 核心框架</li>
 *   <li>Hibernate 5 - ORM框架（手动配置，非Spring Data JPA）</li>
 *   <li>JSP - 视图层技术</li>
 *   <li>MySQL 8 - 数据库</li>
 * </ul>
 *
 * <h3>关键架构决策：</h3>
 * <p>排除了Spring Boot的HibernateJpaAutoConfiguration自动配置，
 * 采用手动配置Hibernate SessionFactory的方式，这样可以更灵活地控制事务和会话管理。</p>
 *
 * @author JT Spring Project Team
 * @version 1.0
 * @see com.jtspringproject.JtSpringProject.HibernateConfiguration
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class JtSpringProjectApplication {

	private static final Logger logger = LoggerFactory.getLogger(JtSpringProjectApplication.class);

	/**
	 * 应用程序主入口方法
	 *
	 * @param args 命令行参数
	 */
	public static void main(String[] args) {
		logger.info("========================================");
		logger.info("JT电商系统启动中...");
		logger.info("========================================");

		try {
			SpringApplication.run(JtSpringProjectApplication.class, args);
			logger.info("========================================");
			logger.info("JT电商系统启动成功！");
			logger.info("访问地址: http://localhost:8080");
			logger.info("========================================");
		} catch (Exception e) {
			logger.error("应用启动失败: {}", e.getMessage(), e);
			System.exit(1);
		}
	}

}
