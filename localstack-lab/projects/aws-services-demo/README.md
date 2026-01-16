# 本地 LocalStack 的 AWS 服务演示

这是一个完整的 Java 应用，用于在本地使用 LocalStack 测试 AWS 服务（DynamoDB、SQS、S3）。

## 📋 项目概览

该项目是一个独立的 Java 演示程序，展示如何在本地开发和测试中使用 LocalStack 与 AWS 服务交互。

**作者**：GitHub Copilot AI  
**创建时间**：2026-01-02  
**用途**：LocalStack AWS 服务测试与演示

## ✨ 功能亮点

- ✅ **DynamoDB 测试** — 表创建、项的增删改查
- ✅ **SQS 测试** — 队列创建、消息发送/接收/删除
- ✅ **S3 测试** — 存储桶创建、文件上传/下载
- ✅ **自动日志** — 测试结果保存到日志文件
- ✅ **详细注释** — 完整的 JavaDoc 与行内注释
- ✅ **完整文档** — 多个 Markdown 文档说明

## 🚀 快速开始

### 前置条件

- Java 11 或更高
- Maven 3.6 或更高
- Docker Desktop
- LocalStack 在 4566 端口运行

### 运行演示

```bash
# 启动 LocalStack（示例）
docker start localstack

# 运行演示程序
cd localstack-lab/projects/aws-services-demo
mvn clean compile exec:java
```

### 查看结果

```bash
# 查看日志文件
cat aws-services-test-result.log

# 或在 Windows 上
notepad aws-services-test-result.log
```

## 📊 测试结果

所有测试 **通过** ✓

```
========================================
  测试摘要
========================================
DynamoDB: ✓ 通过
SQS:      ✓ 通过
S3:       ✓ 通过
----------------------------------------
结果: ✓ 所有测试通过 (3/3)
========================================
```

## 📁 项目结构

```
aws-services-demo/
├── src/main/java/com/example/aws/
│   └── AwsServicesDemo.java          # 主程序（单文件示例）
├── pom.xml                           # Maven 配置
├── run-demo.ps1                      # PowerShell 运行脚本
├── aws-services-test-result.log      # 测试结果日志
└── docs/
    ├── ARCHITECTURE.md               # 代码架构说明
    ├── PROJECT_INFO.md               # 项目详情
    ├── LOG_FILE_FEATURE.md           # 日志功能说明
    ├── TEST_RESULTS.md               # 测试报告
    └── ...                           # 更多文档
```

## 🔧 使用技术

- **语言**：Java 11
- **构建工具**：Maven 3.x
- **AWS SDK**：AWS SDK for Java v2 (2.25.65)
- **LocalStack**：社区版
- **覆盖服务**：DynamoDB、SQS、S3

## 📖 文档索引

| 文档 | 说明 |
|------|------|
| [ARCHITECTURE.md](ARCHITECTURE.md) | 代码结构与设计模式 |
| [PROJECT_INFO.md](PROJECT_INFO.md) | 项目来源与详细信息 |
| [LOG_FILE_FEATURE.md](LOG_FILE_FEATURE.md) | 日志功能说明 |
| [TEST_RESULTS.md](TEST_RESULTS.md) | 详细测试结果 |
| [UPLOAD_GUIDE.md](UPLOAD_GUIDE.md) | Git 上传说明 |

## 🎯 测试覆盖内容

### DynamoDB
- 创建带哈希键的表
- 插入带属性的项
- 按主键查询项
- 列出所有表

### SQS
- 创建标准队列
- 发送消息到队列
- 从队列接收消息
- 删除已处理的消息
- 列出所有队列

### S3
- 创建存储桶
- 上传文件（内存字符串）
- 下载文件
- 列出所有存储桶

## 💡 关键特性说明

### 单文件架构
示例代码集中在 **一个 Java 类** 中，便于理解和学习。

### 自动日志生成
测试结果会自动写入 `aws-services-test-result.log`：
- 执行时间戳
- 逐步详细输出
- 成功/失败汇总
- 错误堆栈（如有）

### 详细注释
每个方法和关键代码块均提供详尽注释，说明：
- 功能说明
- 工作原理
- 设计原因

## 🔍 如何阅读代码

主类 `AwsServicesDemo.java` 包含：

1. **配置** — LocalStack 端点和区域
2. **客户端工厂** — 创建 AWS 客户端的方法
3. **测试方法** — 每个服务对应一个测试方法
4. **主方法** — 协调所有测试执行

```java
main()
  ├─> testDynamoDB()
  ├─> testSQS()
  └─> testS3()
```

## 📝 示例输出

```
[1] Testing DynamoDB...
  - Creating DynamoDB table: TestTable
  ✓ Table created successfully
  - Putting item into table
  ✓ Item inserted successfully
  - Getting item from table
  ✓ Item retrieved: {id=..., name=...}
  ✓ Tables: [TestTable]
[1] DynamoDB Test: ✓ SUCCESS

[2] Testing SQS...
  - Creating SQS queue: test-queue
  ✓ Queue created: http://sqs...
  ✓ Message sent, ID: ...
  ✓ Message received: Hello LocalStack!
  ✓ Message deleted
[2] SQS Test: ✓ SUCCESS

[3] Testing S3...
  - Creating S3 bucket: test-bucket-demo
  ✓ Bucket created successfully
  ✓ File uploaded successfully
  ✓ File downloaded: Hello from LocalStack S3!
[3] S3 Test: ✓ SUCCESS
```

## 🛠️ 配置项

### LocalStack 端点
```java
private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";
```

### AWS 区域
```java
private static final Region REGION = Region.US_EAST_1;
```

### 凭证
使用虚拟凭证（`test/test`），LocalStack 不会验证凭证有效性。

## 📚 Learn More

- [AWS SDK for Java v2 Documentation](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/)
- [LocalStack Documentation](https://docs.localstack.cloud)
- [DynamoDB Developer Guide](https://docs.aws.amazon.com/dynamodb/)
- [SQS Developer Guide](https://docs.aws.amazon.com/sqs/)
- [S3 Developer Guide](https://docs.aws.amazon.com/s3/)

## 🤝 Contributing

This is a demonstration project created for learning purposes. Feel free to:
- Use it as a reference
- Extend it with more AWS services
- Improve the documentation
- Report issues

## 📄 License

This project is for educational purposes.

## 🙏 Acknowledgments

- Created with GitHub Copilot AI
- Tested with LocalStack Community Edition
- Uses AWS SDK for Java v2

---

**Created**: 2026-01-02  
**Author**: GitHub Copilot AI  
**Purpose**: LocalStack AWS Services Testing Demo

