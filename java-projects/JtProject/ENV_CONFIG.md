# 环境配置说明

## 当前配置（H2本地测试环境）

### 数据库配置
- **数据库**: H2（内存数据库）
- **连接字符串**: `jdbc:h2:mem:ecommjava;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- **用户名**: `sa`
- **密码**: 空
- **Hibernate方言**: `org.hibernate.dialect.H2Dialect`

### 启动应用
```bash
cd /workspaces/study/java-projects/JtProject
mvn spring-boot:run
```

### 访问应用
- **首页**: http://localhost:8080/
- **用户登录**: http://localhost:8080/ (重定向到userLogin)
- **用户注册**: http://localhost:8080/register
- **测试页面**: http://localhost:8080/test
- **H2管理控制台**: http://localhost:8080/h2-console

## 原始配置（已注释，用于连接远程MySQL）

### MySQL 192.168.10.2 配置
```properties
# 原始的MySQL连接配置
#db.driver= com.mysql.cj.jdbc.Driver
#db.url= jdbc:mysql://192.168.10.2:3306/ecommjava?createDatabaseIfNotExist=true
#db.username= root
#db.password= 123456
```

### 切换回MySQL的步骤
1. 编辑 `src/main/resources/application.properties`
2. 取消注释MySQL相关配置
3. 注释掉H2相关配置
4. 在 `HibernateConfiguration.java` 中恢复原密码处理方式
5. 重新启动应用

## 关键文件修改

### 已修改文件
- `src/main/resources/application.properties` - 数据库连接配置
- `src/main/java/com/jtspringproject/JtSpringProject/HibernateConfiguration.java` - 处理空密码
- `pom.xml` - 依赖配置保持不变

## 应用状态
✓ 应用成功启动  
✓ JSP页面正常渲染  
✓ HTTP请求处理正常  
✓ 数据库初始化成功（Hibernate自动建表）

## 可访问的路由
- `GET /` - 首页（跳转到登录页）
- `GET /register` - 用户注册页面
- `GET /test` - 测试页面
- `GET /test2` - 测试页面2
- `GET /admin/login` - 管理员登录页面
