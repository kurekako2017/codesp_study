# LocalStack 快速参考

## 🚀 快速启动

### 启动 LocalStack
```bash
docker start localstack
```

### 运行测试
```powershell
cd D:\dev\study\localstack-lab\projects\hello-localstack-java
.\test-localstack.ps1
```

---

## 📋 常用命令

### LocalStack 管理
```bash
# 启动
docker start localstack

# 停止
docker stop localstack

# 重启
docker restart localstack

# 查看状态
docker ps | findstr localstack

# 查看日志
docker logs localstack --tail 50

# 实时日志
docker logs -f localstack
```

### 测试执行
```powershell
# PowerShell (推荐)
.\test-localstack.ps1

# 批处理
execute.bat

# Maven 直接执行
mvn exec:java -Dexec.mainClass="com.example.localstack.App"
```

---

## 📁 重要文件位置

| 文件 | 位置 | 说明 |
|------|------|------|
| 测试脚本 | `test-localstack.ps1` | 自动化测试脚本 |
| 执行脚本 | `execute.bat` | 批处理测试脚本 |
| Java 源码 | `src/main/java/com/example/localstack/App.java` | 测试程序 |
| Maven 配置 | `pom.xml` | 项目依赖配置 |
| 测试日志 | `test-output.log` | 最新测试日志 |
| 执行结果 | `execution-result.txt` | 完整执行输出 |

---

## 🔧 配置信息

### LocalStack
- **Endpoint**: http://localhost:4566
- **容器名**: localstack
- **镜像**: localstack/localstack:latest
- **Health Check**: http://localhost:4566/_localstack/health

### Java 项目
- **Group ID**: com.example
- **Artifact ID**: hello-localstack-java
- **Version**: 0.1.0
- **Java Version**: 11
- **AWS SDK Version**: 2.25.65

### S3 测试
- **Bucket**: hello-localstack-java
- **Key**: hello.txt
- **Endpoint**: http://s3.localhost.localstack.cloud:4566

---

## ✅ 验证清单

- [ ] Docker 正在运行
- [ ] LocalStack 容器已启动
- [ ] Java 11+ 已安装
- [ ] Maven 已安装并配置
- [ ] 端口 4566 未被占用
- [ ] 测试脚本可执行
- [ ] 日志文件可读取

---

## 🐛 常见问题

### LocalStack 无法启动
```bash
docker start localstack
docker ps -a | findstr localstack
```

### 连接超时
检查端口和防火墙:
```bash
netstat -ano | findstr 4566
```

### Maven 依赖下载失败
清理并重试:
```bash
mvn clean
mvn dependency:resolve
```

---

## 📚 相关文档

- [测试总结](LOCALSTACK_TEST_SUMMARY.md) - 完整测试报告
- [运行指南](projects/hello-localstack-java/运行指南.md) - 详细运行说明
- [日志查看](如何查看LocalStack日志.md) - 日志查看指南
- [Java 快速开始](../localstack/JAVA_QUICKSTART.md) - Java 开发指南

---

**最后更新**: 2026-01-02

