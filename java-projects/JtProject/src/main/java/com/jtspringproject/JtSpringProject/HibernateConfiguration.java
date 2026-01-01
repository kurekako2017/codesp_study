package com.jtspringproject.JtSpringProject;
 
import java.util.Properties;
 
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hibernate手动配置类
 *
 * <p>该配置类负责手动配置Hibernate SessionFactory、数据源和事务管理器。
 * 由于排除了Spring Boot的Hibernate自动配置，所有Hibernate相关配置都在此类中完成。</p>
 *
 * <h3>配置内容：</h3>
 * <ul>
 *   <li>数据源配置 - 支持MySQL 8和H2数据库</li>
 *   <li>Hibernate SessionFactory配置 - 包括实体扫描、方言等</li>
 *   <li>事务管理器配置 - 基于Hibernate的声明式事务</li>
 * </ul>
 *
 * <h3>关键特性：</h3>
 * <ul>
 *   <li>支持空密码（用于H2数据库）</li>
 *   <li>从application.properties读取配置参数</li>
 *   <li>启用声明式事务管理（@Transactional）</li>
 * </ul>
 *
 * @author JT Spring Project Team
 * @version 1.0
 * @see org.springframework.transaction.annotation.EnableTransactionManagement
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);

    @Value("${db.driver}")
    private String DRIVER;
 
    @Value("${db.password}")
    private String PASSWORD;
 
    @Value("${db.url}")
    private String URL;
 
    @Value("${db.username}")
    private String USERNAME;
 
    @Value("${hibernate.dialect}")
    private String DIALECT;
 
    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;
 
    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;
 
    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;
 
    /**
     * 配置数据源Bean
     *
     * <p>创建并配置DriverManagerDataSource，支持MySQL和H2数据库。
     * 对于H2数据库，支持空密码配置。</p>
     *
     * @return 配置好的数据源对象
     */
    @Bean
    public DataSource dataSource() {
        logger.info("正在配置数据源...");
        logger.debug("数据库驱动: {}", DRIVER);
        logger.debug("数据库URL: {}", URL);
        logger.debug("数据库用户名: {}", USERNAME);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);

        // 处理H2数据库的空密码情况
        if (PASSWORD != null && !PASSWORD.trim().isEmpty()) {
            dataSource.setPassword(PASSWORD);
            logger.debug("数据库密码已设置");
        } else {
            logger.debug("数据库密码为空（H2数据库模式）");
        }

        logger.info("数据源配置完成");
        return dataSource;
    }
 
    /**
     * 配置Hibernate SessionFactory Bean
     *
     * <p>创建并配置LocalSessionFactoryBean，包括：</p>
     * <ul>
     *   <li>设置数据源</li>
     *   <li>扫描实体类包</li>
     *   <li>配置Hibernate属性（方言、SQL显示、DDL自动生成等）</li>
     * </ul>
     *
     * @return 配置好的SessionFactory对象
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        logger.info("正在配置Hibernate SessionFactory...");

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);

        logger.debug("实体扫描包路径: {}", PACKAGES_TO_SCAN);
        logger.debug("Hibernate方言: {}", DIALECT);
        logger.debug("显示SQL: {}", SHOW_SQL);
        logger.debug("DDL自动生成策略: {}", HBM2DDL_AUTO);

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
        sessionFactory.setHibernateProperties(hibernateProperties);
 
        logger.info("Hibernate SessionFactory配置完成");
        return sessionFactory;
    }
 
    /**
     * 配置Hibernate事务管理器Bean
     *
     * <p>创建并配置HibernateTransactionManager，用于管理基于Hibernate的声明式事务。
     * 配合@Transactional注解使用，实现方法级别的事务控制。</p>
     *
     * @return 配置好的事务管理器对象
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        logger.info("正在配置Hibernate事务管理器...");

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        logger.info("Hibernate事务管理器配置完成");
        return transactionManager;
    }   
}
