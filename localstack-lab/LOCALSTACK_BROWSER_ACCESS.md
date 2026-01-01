# LocalStack 浏览器访问指南

## ❌ 错误的做法

```
http://localhost:4566  ← 这个地址在浏览器中会显示空白或错误！
```

**原因**: 4566 端口是 **API 端点**，不是 Web 界面！

---

## ✅ 正确的访问方法

### 方法 1: 健康检查端点 (推荐)

**在浏览器中访问**:
```
http://localhost:4566/_localstack/health
```

**显示内容示例**:
```json
{
  "services": {
    "dynamodb": "available",
    "sqs": "available", 
    "s3": "available"
  },
  "version": "3.0.0"
}
```

**说明**: 这个端点显示所有 AWS 服务的状态

---

### 方法 2: 系统信息端点

**在浏览器中访问**:
```
http://localhost:4566/_localstack/info
```

**显示内容示例**:
```json
{
  "version": "3.0.0",
  "edition": "community",
  "is_license_activated": false
}
```

---

### 方法 3: 使用 AWS CLI

**在命令行中执行**:

```powershell
# 查看 S3 buckets
aws --endpoint-url=http://localhost:4566 s3 ls

# 查看 DynamoDB 表
aws --endpoint-url=http://localhost:4566 dynamodb list-tables

# 查看 SQS 队列
aws --endpoint-url=http://localhost:4566 sqs list-queues
```

---

### 方法 4: 使用 LocalStack Web UI

**访问地址**:
```
https://app.localstack.cloud
```

**说明**:
- 需要 LocalStack 账户
- 需要配置 Auth Token
- 可以图形化管理资源

---

## 🔍 快速测试命令

### PowerShell 测试
```powershell
# 测试健康检查端点
Invoke-WebRequest -Uri http://localhost:4566/_localstack/health -UseBasicParsing

# 测试系统信息
Invoke-WebRequest -Uri http://localhost:4566/_localstack/info -UseBasicParsing
```

### 使用 curl
```bash
# 健康检查
curl http://localhost:4566/_localstack/health

# 系统信息
curl http://localhost:4566/_localstack/info
```

---

## 📊 可访问的端点列表

| 端点 | 说明 | 浏览器访问 |
|------|------|-----------|
| `http://localhost:4566` | API 根路径 | ❌ 空白 |
| `http://localhost:4566/_localstack/health` | 健康检查 | ✅ 可以 |
| `http://localhost:4566/_localstack/info` | 系统信息 | ✅ 可以 |
| `http://localhost:4566/_localstack/init` | 初始化脚本 | ✅ 可以 |
| `https://app.localstack.cloud` | Web UI | ✅ 可以 |

---

## 💡 理解 API 端点

### 什么是 API 端点？

API 端点就像一个**服务器的电话号码**，不是网页地址。

**类比**:
- ❌ **Web 界面** = 餐厅门面（可以进去看菜单）
- ✅ **API 端点** = 餐厅电话（只能打电话点餐）

### LocalStack 4566 端口的作用

```
http://localhost:4566  ← 这是 AWS SDK 调用的地址
                          不是给人看的网页
```

**正确使用方式**:
```java
// Java 代码中使用
S3Client s3 = S3Client.builder()
    .endpointOverride(URI.create("http://localhost:4566"))
    .build();
```

---

## 🎯 实际操作示例

### 示例 1: 在浏览器中查看服务状态

1. 打开浏览器
2. 输入地址: `http://localhost:4566/_localstack/health`
3. 按回车
4. 看到 JSON 响应

**预期结果**:
```json
{
  "services": {
    "dynamodb": "available",
    "sqs": "available",
    "s3": "available"
  }
}
```

---

### 示例 2: 使用 PowerShell 测试

```powershell
# 1. 启动 PowerShell
# 2. 执行命令
$response = Invoke-RestMethod -Uri http://localhost:4566/_localstack/health
$response | ConvertTo-Json

# 3. 查看结果
```

---

### 示例 3: 运行 Java 测试程序

```powershell
cd D:\dev\study\localstack-lab\projects\aws-services-demo
.\run-demo.ps1
```

**程序会自动**:
1. 连接到 `http://localhost:4566`
2. 创建 DynamoDB 表
3. 创建 SQS 队列
4. 创建 S3 Bucket

---

## 🔧 故障排查

### 问题 1: 浏览器显示空白

**原因**: 直接访问了 API 根路径  
**解决**: 访问 `/_localstack/health` 端点

---

### 问题 2: 显示 "Unable to connect"

**原因**: LocalStack 未启动  
**解决**:
```powershell
docker start localstack
```

---

### 问题 3: 显示 404 错误

**原因**: 端点路径错误  
**解决**: 检查 URL 拼写

正确的端点:
- ✅ `/_localstack/health`
- ❌ `/localstack/health` (少了下划线)

---

## 📱 在不同工具中访问

### 在浏览器中
```
http://localhost:4566/_localstack/health
```

### 在 Postman 中
```
GET http://localhost:4566/_localstack/health
```

### 在 curl 中
```bash
curl http://localhost:4566/_localstack/health
```

### 在 PowerShell 中
```powershell
Invoke-WebRequest http://localhost:4566/_localstack/health
```

### 在 Java 代码中
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("http://localhost:4566/_localstack/health"))
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

---

## 🎓 总结

| 访问目的 | 使用什么 |
|---------|---------|
| 查看服务状态 | `/_localstack/health` |
| 查看系统信息 | `/_localstack/info` |
| 管理资源 | LocalStack Web UI |
| 编程调用 | AWS SDK + endpoint override |
| 命令行操作 | AWS CLI + `--endpoint-url` |

---

**重要提示**: 
- ❌ `http://localhost:4566` 不能直接浏览
- ✅ `http://localhost:4566/_localstack/health` 可以浏览
- 💡 API 端点是给程序用的，不是给人看的

---

**创建时间**: 2026-01-02  
**文档版本**: 1.0

