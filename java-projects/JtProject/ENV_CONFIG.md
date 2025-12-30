# 环境配置说明

## 环境切换方式（Spring Profile）

本项目配置了多个环境Profile，可以通过指定`spring.profiles.active`快速切换：

### 1. H2本地开发环境（默认）- 数据持久化 ✅ 推荐
```bash
# 默认启动（使用 application.properties 配置）
mvn spring-boot:run

# 或显式指定 local profile
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
- **数据库**: H2（文件存储）
- **数据持久化**: ✅ 是（数据保存在 `./data/ecommjava.mv.db`）
- **自动初始化**: 首次启动时自动执行 `data.sql` 初始化数据
- **适用场景**: Codespaces、本地开发、测试环境

### 2. MySQL远程环境（192.168.10.2）
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=remote
```
- **数据库**: MySQL 8 @ 192.168.10.2:3306
- **用户名/密码**: root/123456
- **适用场景**: 连接到远程MySQL服务器

### 3. MySQL本地环境
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
- **数据库**: MySQL @ localhost:3306
- **用户名/密码**: root/（空密码）
- **适用场景**: 本地安装了MySQL的开发环境

### 4. H2内存模式（数据不持久化）
编辑 `application.properties`，取消注释内存模式配置：
```properties
#db.url= jdbc:h2:mem:ecommjava;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
```
- **数据持久化**: ❌ 否（应用重启数据清空）
- **适用场景**: 临时测试、单元测试

## 数据持久化解决方案

### H2文件模式（当前配置）✅
```properties
db.url=jdbc:h2:file:./data/ecommjava;MODE=MySQL;AUTO_SERVER=TRUE
```
**优点**:
- ✅ 数据重启后保留
- ✅ 数据文件在项目目录 `./data/` 下
- ✅ 可以备份/迁移数据文件
- ✅ 无需外部数据库服务

**数据文件位置**: `/workspaces/study/java-projects/JtProject/data/ecommjava.mv.db`

### 数据初始化机制
应用使用Spring Boot的SQL初始化功能：
1. Hibernate先创建表结构（`hibernate.hbm2ddl.auto=update`）
2. Spring Boot执行 `data.sql` 插入初始数据
3. 配置 `spring.sql.init.continue-on-error=true` 避免重复插入报错

**初始数据**:
- 管理员账户: `admin/123`
- 普通用户: `lisa/765`
- 9个商品分类
- 2个示例商品

## Codespaces vs 本地环境切换

### Codespaces环境
**推荐配置**: 使用默认H2配置
```bash
cd /workspaces/study/java-projects/JtProject
mvn spring-boot:run
```
**特点**:
- ✅ 无需配置外部数据库
- ✅ 数据持久化到Codespace存储
- ✅ 端口自动转发到浏览器
- ⚠️ 注意：Codespace删除后数据也会丢失

### 本地开发环境

**选项1: 使用H2（推荐）**
```bash
git clone <repo>
cd java-projects/JtProject
mvn spring-boot:run
```
- 无需额外配置，开箱即用
- 数据保存在本地 `./data/` 目录

**选项2: 使用本地MySQL**
```bash
# 1. 启动本地MySQL并创建数据库
mysql -u root -p < basedata.sql

# 2. 使用mysql profile启动
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

**选项3: 连接远程MySQL 192.168.10.2**
```bash
# 确保网络可达
ping 192.168.10.2

# 使用remote profile启动
mvn spring-boot:run -Dspring-boot.run.profiles=remote
```

### 环境切换注意事项

**仅需切换启动命令**，无需修改代码：
```bash
# 开发测试 → H2
mvn spring-boot:run

# 连接远程数据库 → MySQL remote
mvn spring-boot:run -Dspring-boot.run.profiles=remote

# 本地MySQL → MySQL local
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

**数据迁移**:
- H2 → MySQL: 使用H2控制台导出SQL，在MySQL中执行
- MySQL → H2: 使用mysqldump导出，修改为H2兼容SQL后导入

## 访问应用

### Web界面
- **首页/登录**: http://localhost:8080/
- **用户注册**: http://localhost:8080/register
- **测试页面**: http://localhost:8080/test
- **管理员登录**: http://localhost:8080/admin/login
- **H2数据库控制台**: http://localhost:8080/h2-console

### H2控制台连接信息
- **JDBC URL**: `jdbc:h2:file:./data/ecommjava`（文件模式）或 `jdbc:h2:mem:ecommjava`（内存模式）
- **用户名**: `sa`
- **密码**: 空

## 配置文件说明

### Profile配置文件
| 文件 | 用途 | 数据库 | 激活方式 |
|------|------|--------|----------|
| `application.properties` | 默认配置 | H2文件 | 默认激活 |
| `application-local.properties` | 本地开发 | H2文件 | `-Dspring.profiles.active=local` |
| `application-remote.properties` | 远程MySQL | MySQL 192.168.10.2 | `-Dspring.profiles.active=remote` |
| `application-mysql.properties` | 本地MySQL | MySQL localhost | `-Dspring.profiles.active=mysql` |
| `application-test.properties` | 测试环境 | 根据需要配置 | `-Dspring.profiles.active=test` |

### 数据初始化文件
- `basedata.sql` - MySQL兼容的初始化脚本（手动执行）
- `data.sql` - Spring Boot自动执行的初始化脚本（H2环境）

## 常见问题

### Q1: 如何清除H2数据库重新初始化？
```bash
# 删除数据文件
rm -rf ./data/

# 重启应用，会自动创建新的数据库并初始化
mvn spring-boot:run
```

### Q2: 数据重复插入报错？
配置了 `spring.sql.init.continue-on-error=true`，重复插入会被忽略。
如果需要重置数据，删除 `./data/` 目录重新启动。

### Q3: Codespaces中数据会丢失吗？
- ✅ Codespace运行期间，数据保存在 `./data/` 目录
- ✅ 提交代码到Git后，需要排除 `./data/` 目录（已在 `.gitignore`）
- ⚠️ Codespace删除后，数据会丢失（建议重要数据导出或使用远程MySQL）

### Q4: 本地和Codespaces切换需要修改代码吗？
**不需要！** 两个环境都使用默认配置即可：
```bash
mvn spring-boot:run
```
代码完全相同，只需要git pull/push同步代码。

## 文件清单

### 核心配置文件（已提交）
- ✅ `src/main/resources/application.properties` - 默认H2配置
- ✅ `src/main/resources/application-local.properties` - 本地H2配置
- ✅ `src/main/resources/application-remote.properties` - 远程MySQL配置
- ✅ `src/main/resources/application-mysql.properties` - 本地MySQL配置
- ✅ `src/main/resources/data.sql` - 自动初始化数据
- ✅ `src/main/java/.../HibernateConfiguration.java` - 空密码处理

### 数据文件（不提交）
- ❌ `./data/ecommjava.mv.db` - H2数据库文件（已在 `.gitignore`）
- ❌ `./data/ecommjava.trace.db` - H2跟踪日志

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
