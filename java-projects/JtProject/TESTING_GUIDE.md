# 单元测试使用指南

## 📚 测试类说明

本项目包含以下测试类：

### 1. JtSpringProjectApplicationTests.java
**位置**: `src/test/java/com/jtspringproject/JtSpringProject/JtSpringProjectApplicationTests.java`

**用途**: 集成测试，验证Spring Boot应用能否正常启动

**测试内容**:
- Spring应用上下文加载
- 所有Bean正确注册
- 数据库连接正常
- Hibernate配置正确

### 2. AdminControllerTest.java ⭐ 新增
**位置**: `src/test/java/com/jtspringproject/JtSpringProject/controller/AdminControllerTest.java`

**用途**: 管理员功能单元测试

**测试内容** (20个测试用例):
- ✅ 管理员登录/登出功能
- ✅ 管理员权限验证
- ✅ 商品管理（增删改查）
- ✅ 分类管理（增删改查）
- ✅ 用户管理（查看客户列表）
- ✅ 边界条件测试

### 3. UserControllerTest.java ⭐ 新增
**位置**: `src/test/java/com/jtspringproject/JtSpringProject/controller/UserControllerTest.java`

**用途**: 用户功能单元测试

**测试内容** (20个测试用例):
- ✅ 用户注册功能
- ✅ 用户登录/登出功能
- ✅ 商品浏览功能
- ✅ 用户信息修改
- ✅ 多用户会话管理
- ✅ 边界条件测试

---

## 🚀 运行测试

### 方式1: Maven命令（推荐）

```bash
cd /workspaces/study/java-projects/JtProject

# 运行所有测试
mvn test

# 运行指定测试类
mvn test -Dtest=AdminControllerTest
mvn test -Dtest=UserControllerTest

# 运行单个测试方法
mvn test -Dtest=AdminControllerTest#testAdminLoginWithCorrectCredentials

# 跳过测试（打包时）
mvn package -DskipTests

# 清理后运行测试
mvn clean test
```

### 方式2: IDE运行（VS Code/IntelliJ IDEA）

1. 打开测试文件（如 `AdminControllerTest.java`）
2. 点击类名或方法名旁边的 **▶ Run Test** 按钮
3. 查看测试结果面板

### 方式3: 运行特定分类的测试

```bash
# 只运行controller包下的测试
mvn test -Dtest=*ControllerTest

# 运行所有以Test结尾的类
mvn test -Dtest=*Test
```

---

## 📊 查看测试结果

### 命令行输出
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running AdminControllerTest
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 8.234 s
[INFO] Running UserControllerTest
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.123 s
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 40, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
```

### 详细测试报告

#### 1. 文本报告
```bash
# 查看测试摘要
cat target/surefire-reports/AdminControllerTest.txt
cat target/surefire-reports/UserControllerTest.txt
```

#### 2. XML报告（可被CI/CD工具读取）
```bash
cat target/surefire-reports/TEST-AdminControllerTest.xml
```

#### 3. 使用Maven Surefire插件生成HTML报告
```bash
mvn surefire-report:report
# 报告位置：target/site/surefire-report.html
```

---

## 🔧 测试配置说明

### 配置文件
- **application-test.properties**: 测试环境专用配置
  - 使用H2内存数据库（快速、隔离）
  - 自动创建/删除表（create-drop模式）
  - 减少日志输出（提高测试速度）

- **data.sql**: 测试数据初始化脚本
  - 自动插入测试用户（admin/testuser）
  - 自动插入测试分类和商品
  - 每次测试后自动清除

### 关键注解说明

#### @SpringBootTest
```java
@SpringBootTest  // 启动完整的Spring应用上下文
```
- 加载所有Bean和配置
- 适合集成测试

#### @AutoConfigureMockMvc
```java
@AutoConfigureMockMvc  // 自动配置MockMvc
```
- 无需启动真实Web服务器
- 可以模拟HTTP请求

#### @ActiveProfiles("test")
```java
@ActiveProfiles("test")  // 激活test profile
```
- 加载 `application-test.properties`
- 使用H2内存数据库

#### @Transactional
```java
@Transactional  // 测试后自动回滚
```
- 每个测试方法执行后回滚数据库操作
- 保证测试间互不影响

#### @DisplayName
```java
@DisplayName("测试1：访问管理员登录页面应该成功")
```
- 为测试方法提供可读的描述
- 在测试报告中显示

---

## 📝 测试用例示例

### 示例1: 测试登录成功
```java
@Test
@DisplayName("测试：使用正确的管理员账号密码登录应该成功")
void testAdminLoginWithCorrectCredentials() throws Exception {
    mockMvc.perform(post("/admin/loginvalidate")
            .param("username", "testadmin")
            .param("password", "admin123"))
            .andDo(print())  // 打印请求和响应详情
            .andExpect(status().isOk())  // 期望HTTP 200
            .andExpect(view().name("adminHome"))  // 期望返回adminHome视图
            .andExpect(model().attributeExists("username"));  // Model中应有username
    
    // 断言：登录状态标志应该被设置
    assert AdminController.adminlogcheck == 1 : "管理员登录状态应该为1";
}
```

### 示例2: 测试添加商品
```java
@Test
@DisplayName("测试：管理员应该能添加新商品")
void testAddNewProduct() throws Exception {
    AdminController.adminlogcheck = 1;  // 模拟已登录
    
    mockMvc.perform(post("/admin/products")
            .param("name", "Test Product")
            .param("categoryid", "1")
            .param("price", "100")
            .param("quantity", "10"))
            .andExpect(status().is3xxRedirection())  // 期望重定向
            .andExpect(redirectedUrl("/admin/products"));
    
    // 验证：商品应该被添加到数据库
    var products = productService.getProducts();
    assert products.stream().anyMatch(p -> "Test Product".equals(p.getName()));
}
```

---

## 🐛 调试测试

### 1. 查看详细日志
在 `application-test.properties` 中开启SQL日志：
```properties
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### 2. 使用H2控制台查看数据
测试运行时访问：http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **用户名**: `sa`
- **密码**: (空)

### 3. 在测试中添加断点
在IDE中设置断点，以调试模式运行测试

### 4. 打印请求响应详情
```java
.andDo(print())  // 打印完整的请求和响应信息
```

---

## ✅ 测试最佳实践

### 1. 测试命名
- 使用描述性的方法名
- 使用 `@DisplayName` 提供中文描述

### 2. 测试独立性
- 每个测试应该独立运行
- 不依赖其他测试的执行顺序
- 使用 `@BeforeEach` 准备测试数据

### 3. 断言明确
```java
assert condition : "失败时的错误消息";
```

### 4. 测试覆盖
- ✅ 正常流程（Happy Path）
- ✅ 边界条件
- ✅ 异常情况
- ✅ 权限验证

---

## 📈 测试覆盖率

### 生成覆盖率报告（使用JaCoCo）

1. 在 `pom.xml` 中添加JaCoCo插件（已配置）

2. 运行测试并生成报告：
```bash
mvn clean test jacoco:report
```

3. 查看报告：
```bash
open target/site/jacoco/index.html
```

---

## 🎯 快速开始

### 1. 首次运行测试
```bash
cd /workspaces/study/java-projects/JtProject
mvn clean test
```

### 2. 只运行新增的测试
```bash
mvn test -Dtest=AdminControllerTest,UserControllerTest
```

### 3. 查看测试结果
```bash
cat target/surefire-reports/*.txt
```

### 4. 在IDE中运行
1. 打开 `AdminControllerTest.java`
2. 点击类名旁的 ▶ 运行全部测试
3. 或点击单个方法旁的 ▶ 运行单个测试

---

## 📞 常见问题

### Q1: 测试失败提示找不到视图？
**A**: 确保JSP文件在 `src/main/webapp/views/` 目录下，且已被复制到 `target/classes/META-INF/resources/views/`

### Q2: 测试时数据库连接失败？
**A**: 检查 `application-test.properties` 配置，确保使用H2内存数据库

### Q3: 测试间数据相互影响？
**A**: 确保测试类上有 `@Transactional` 注解，每个测试后会自动回滚

### Q4: 如何跳过测试直接打包？
**A**: 使用 `mvn package -DskipTests`

### Q5: 测试运行很慢怎么办？
**A**: 
- 关闭SQL日志输出
- 使用 `-Dtest=` 只运行需要的测试
- 考虑使用 `@WebMvcTest` 替代 `@SpringBootTest`（更轻量）

---

## 🎉 总结

现在你有：
- ✅ **40个测试用例**（AdminController 20个 + UserController 20个）
- ✅ **完整的测试配置**（application-test.properties）
- ✅ **自动化测试数据**（data.sql）
- ✅ **详细的注释说明**（每个测试都有中文注释）
- ✅ **多种运行方式**（Maven命令 + IDE运行）

开始测试吧！🚀
