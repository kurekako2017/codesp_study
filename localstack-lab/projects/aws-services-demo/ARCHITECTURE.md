# AwsServicesDemo 类架构说明

## 📚 类设计概述

### ✅ 是的，就这一个类！

**AwsServicesDemo.java** 是一个**独立的、自包含的测试类**，包含了：
- ✅ DynamoDB 测试
- ✅ SQS 测试
- ✅ S3 测试
- ✅ 所有必需的辅助方法

**设计理念**: 单一职责 + 高内聚 + 易于理解和运行

---

## 🏗️ 类结构图

```
AwsServicesDemo.java
│
├─ 📌 常量
│  ├─ LOCALSTACK_ENDPOINT = "http://localhost:4566"
│  └─ REGION = Region.US_EAST_1
│
├─ 🚀 入口方法
│  └─ main(String[] args)          ← 程序入口
│     ├─ testDynamoDB()            ← 调用 DynamoDB 测试
│     ├─ testSQS()                 ← 调用 SQS 测试
│     └─ testS3()                  ← 调用 S3 测试
│
├─ 🔧 客户端创建方法
│  ├─ createDynamoDbClient()       ← 创建 DynamoDB 客户端
│  ├─ createSqsClient()            ← 创建 SQS 客户端
│  └─ createS3Client()             ← 创建 S3 客户端
│
└─ 🧪 测试方法
   ├─ testDynamoDB()               ← DynamoDB 完整测试
   ├─ testSQS()                    ← SQS 完整测试
   └─ testS3()                     ← S3 完整测试
```

---

## 🔄 程序执行流程

### 完整调用链路

```
1. JVM 启动
   └─> 2. 执行 main() 方法
       │
       ├─> 3. 测试 DynamoDB
       │   ├─> createDynamoDbClient()      (创建客户端)
       │   └─> testDynamoDB()              (执行测试)
       │       ├─ 创建表
       │       ├─ 插入数据
       │       ├─ 查询数据
       │       └─ 列出表
       │
       ├─> 4. 测试 SQS
       │   ├─> createSqsClient()           (创建客户端)
       │   └─> testSQS()                   (执行测试)
       │       ├─ 创建队列
       │       ├─ 发送消息
       │       ├─ 接收消息
       │       ├─ 删除消息
       │       └─ 列出队列
       │
       └─> 5. 测试 S3
           ├─> createS3Client()            (创建客户端)
           └─> testS3()                    (执行测试)
               ├─ 创建 Bucket
               ├─ 上传文件
               ├─ 下载文件
               └─ 列出 Buckets
```

---

## 🚀 如何独立运行

### 方法 1: 使用 Maven (推荐)

```bash
# 完整编译并运行
mvn clean compile exec:java

# 或指定主类
mvn exec:java -Dexec.mainClass="com.example.aws.AwsServicesDemo"
```

**原理**: Maven 的 `exec-maven-plugin` 插件
- 查找 `pom.xml` 中配置的主类
- 设置 classpath（包含所有依赖）
- 调用 JVM 执行 main() 方法

### 方法 2: 使用 PowerShell 脚本

```powershell
.\run-demo.ps1
```

**脚本内容**:
```powershell
mvn clean compile exec:java -Dexec.mainClass="com.example.aws.AwsServicesDemo"
```

### 方法 3: 直接使用 Java 命令

```bash
# 1. 先编译
javac -cp "target/classes;..." AwsServicesDemo.java

# 2. 运行
java -cp "target/classes;..." com.example.aws.AwsServicesDemo
```

---

## 📦 Maven 配置关键点

### pom.xml 中的关键配置

```xml
<!-- 1. 指定主类 -->
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.5.0</version>
    <configuration>
        <mainClass>com.example.aws.AwsServicesDemo</mainClass>
        <!-- ↑ 指定程序入口点 -->
    </configuration>
</plugin>

<!-- 2. 依赖库 -->
<dependencies>
    <!-- AWS SDK for DynamoDB -->
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>dynamodb</artifactId>
        <version>2.25.65</version>
    </dependency>
    
    <!-- AWS SDK for SQS -->
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>sqs</artifactId>
        <version>2.25.65</version>
    </dependency>
    
    <!-- AWS SDK for S3 -->
    <dependency>
        <groupId>software.amazon.awssdk</groupId>
        <artifactId>s3</artifactId>
        <version>2.25.65</version>
    </dependency>
</dependencies>
```

---

## 🔑 为什么可以独立运行

### 1. 有 main() 方法

```java
public static void main(String[] args) {
    // 程序入口点
}
```

**作用**: JVM 启动时查找并执行这个方法

### 2. 所有依赖都通过 Maven 管理

```
运行时 Classpath:
├─ AwsServicesDemo.class           (编译后的类)
├─ dynamodb-2.25.65.jar            (DynamoDB SDK)
├─ sqs-2.25.65.jar                 (SQS SDK)
├─ s3-2.25.65.jar                  (S3 SDK)
└─ 其他传递依赖 (约 50+ jar 文件)
```

### 3. 自包含设计

```java
// 不需要外部配置文件
private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";
private static final Region REGION = Region.US_EAST_1;

// 所有方法都是 static，不需要实例化
public static void main(String[] args) { ... }
private static void testDynamoDB() { ... }
```

---

## 🎯 类的设计模式

### 1. 工厂方法模式

```java
// 工厂方法：创建 AWS 客户端
private static DynamoDbClient createDynamoDbClient() {
    return DynamoDbClient.builder()
        .endpointOverride(...)
        .region(...)
        .build();
}
```

**好处**:
- 集中管理客户端配置
- 便于修改和维护
- 避免重复代码

### 2. 模板方法模式

```java
// 每个测试方法都遵循相同的模板
private static void testXXX() {
    try (XXXClient client = createXXXClient()) {
        // 1. 创建资源
        // 2. 操作资源
        // 3. 查询资源
        // 4. 列出资源
    } catch (Exception e) {
        // 异常处理
    }
}
```

### 3. 资源管理模式

```java
// try-with-resources: 自动关闭资源
try (DynamoDbClient dynamoDb = createDynamoDbClient()) {
    // 使用资源
} // 自动调用 dynamoDb.close()
```

**好处**:
- 防止资源泄漏
- 代码更简洁
- 异常安全

---

## 📊 方法调用关系图

```
main()
│
├─── testDynamoDB()
│    ├─── createDynamoDbClient()
│    ├─── dynamoDb.createTable()
│    ├─── dynamoDb.putItem()
│    ├─── dynamoDb.getItem()
│    └─── dynamoDb.listTables()
│
├─── testSQS()
│    ├─── createSqsClient()
│    ├─── sqs.createQueue()
│    ├─── sqs.sendMessage()
│    ├─── sqs.receiveMessage()
│    ├─── sqs.deleteMessage()
│    └─── sqs.listQueues()
│
└─── testS3()
     ├─── createS3Client()
     ├─── s3.createBucket()
     ├─── s3.putObject()
     ├─── s3.getObjectAsBytes()
     └─── s3.listBuckets()
```

---

## 🔍 代码组织原则

### 1. 单一职责原则

每个方法只负责一个功能：
- `createDynamoDbClient()` 只负责创建客户端
- `testDynamoDB()` 只负责测试 DynamoDB
- `main()` 只负责调用各个测试方法

### 2. 高内聚低耦合

```java
// 高内聚：相关功能放在一起
testDynamoDB() {
    创建表 + 插入数据 + 查询数据  // 都是 DynamoDB 相关
}

// 低耦合：各个测试方法相互独立
testDynamoDB()  // 不依赖 testSQS()
testSQS()       // 不依赖 testS3()
testS3()        // 不依赖 testDynamoDB()
```

### 3. 易于理解和维护

```java
// 清晰的命名
createDynamoDbClient()  // 一看就知道是创建 DynamoDB 客户端
testDynamoDB()          // 一看就知道是测试 DynamoDB

// 详细的注释
/**
 * 创建 DynamoDB 客户端
 * 
 * <p>配置说明:...
 * @return 配置好的 DynamoDB 客户端实例
 */
```

---

## 💡 为什么不拆分成多个类？

### 当前设计的优点

| 优点 | 说明 |
|------|------|
| **简单** | 只有一个文件，易于理解 |
| **自包含** | 所有代码在一处，不需要跳转 |
| **易于运行** | 一个命令就能运行全部测试 |
| **易于分享** | 复制一个文件即可 |

### 如果拆分会怎样？

**拆分后的结构**:
```
com.example.aws/
├─ AwsServicesDemo.java          (主类)
├─ DynamoDbTest.java             (DynamoDB 测试)
├─ SqsTest.java                  (SQS 测试)
├─ S3Test.java                   (S3 测试)
├─ ClientFactory.java            (客户端工厂)
└─ LocalStackConfig.java         (配置)
```

**优点**: 更符合企业级开发规范  
**缺点**: 对于学习和演示来说过于复杂

---

## 🎓 学习要点

### 1. Java 基础

- ✅ `public static void main(String[] args)` - 程序入口
- ✅ `static` 方法 - 不需要实例化即可调用
- ✅ `try-with-resources` - 自动资源管理
- ✅ 异常处理 - try-catch 块

### 2. AWS SDK 使用

- ✅ Builder 模式 - 构建请求对象
- ✅ 客户端配置 - endpoint、region、credentials
- ✅ 请求-响应模式 - Request -> Client -> Response

### 3. LocalStack 集成

- ✅ 端点覆盖 - `endpointOverride()`
- ✅ 虚拟凭证 - `test/test`
- ✅ 路径风格 - S3 的 `forcePathStyle(true)`

---

## 🚀 总结

| 问题 | 答案 |
|------|------|
| **几个类？** | 只有 1 个类：AwsServicesDemo |
| **为什么？** | 简单、自包含、易于学习和演示 |
| **如何运行？** | `mvn exec:java` 或 `.\run-demo.ps1` |
| **入口在哪？** | `main()` 方法 |
| **依赖管理？** | Maven pom.xml |
| **配置在哪？** | 类常量 + 方法内硬编码 |

---

**设计思想**: "保持简单，一次做好一件事"（KISS 原则）

---

**创建时间**: 2026-01-02  
**文档版本**: 1.0

