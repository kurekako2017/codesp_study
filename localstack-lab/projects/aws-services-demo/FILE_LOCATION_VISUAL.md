# test-file.txt 存储位置图解

## 📍 简单回答

**test-file.txt 不在本地磁盘，它在 LocalStack 容器的内存中！**

---

## 🎨 可视化说明

```
你的电脑 (Windows)
├─ D:\dev\study\
│  └─ localstack-lab\
│     └─ projects\
│        └─ aws-services-demo\
│           ├─ src\
│           ├─ pom.xml
│           └─ run-demo.ps1
│           
│           ❌ test-file.txt 不在这里！
│
└─ Docker Desktop
   └─ 容器: localstack
      └─ LocalStack 服务 (端口 4566)
         └─ 模拟的 S3 服务 (内存中)
            └─ Bucket: test-bucket-demo
               └─ test-file.txt  ← ✅ 文件在这里！
                  内容: "Hello from LocalStack S3! Timestamp: ..."
```

---

## 🔄 数据流程图

```
┌─────────────────────────────────────────────────────────────┐
│  1. Java 程序运行                                            │
│     AwsServicesDemo.java                                     │
│                                                              │
│     String content = "Hello from LocalStack S3!";           │
│                                                              │
│     ↓ (内存中的字符串)                                       │
└─────────────────────────────────────────────────────────────┘
                           │
                           │ HTTP PUT 请求
                           │ http://localhost:4566
                           │
                           ↓
┌─────────────────────────────────────────────────────────────┐
│  2. LocalStack 容器接收                                      │
│     Docker Container: localstack                             │
│                                                              │
│     ┌──────────────────────────────────────────────┐       │
│     │ S3 模拟服务 (运行在容器内存中)              │       │
│     │                                               │       │
│     │  Bucket: test-bucket-demo                    │       │
│     │    └─ Key: test-file.txt                     │       │
│     │       Content: "Hello from LocalStack S3!"   │       │
│     │       Size: ~80 bytes                        │       │
│     └──────────────────────────────────────────────┘       │
└─────────────────────────────────────────────────────────────┘
                           │
                           │ HTTP GET 请求
                           │ (需要主动下载)
                           │
                           ↓
┌─────────────────────────────────────────────────────────────┐
│  3. 下载到本地 (可选)                                        │
│     aws s3 cp s3://test-bucket-demo/test-file.txt ./        │
│                                                              │
│     ↓                                                        │
│                                                              │
│  D:\dev\study\...\aws-services-demo\test-file.txt           │
│  ← 这时候才会出现在本地                                      │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔍 关键理解点

### 1. 程序没有创建本地文件

```java
// AwsServicesDemo.java 代码
String content = "Hello from LocalStack S3! Timestamp: " + Instant.now();

// 直接从内存字符串上传
s3.putObject(
    PutObjectRequest.builder()
        .bucket("test-bucket-demo")
        .key("test-file.txt")
        .build(),
    RequestBody.fromString(content)  // ← 从内存上传，没有本地文件！
);
```

**没有做这些事**：
- ❌ 没有 `new File("test-file.txt")`
- ❌ 没有 `FileWriter.write()`
- ❌ 没有保存到磁盘

**只做了这个**：
- ✅ 从内存字符串直接上传到 LocalStack

---

### 2. LocalStack 的存储位置

```
容器路径: /var/lib/localstack/
           └─ state/
              └─ (内部数据结构)
```

**特点**：
- 📦 存储在 Docker 容器的文件系统中
- ⚡ 数据在容器运行时保存在内存
- 🔄 容器重启会丢失（除非配置持久化）
- 🚫 不能直接在 Windows 文件浏览器中看到

---

## 💾 如何获取文件

### 方法 1: 使用下载脚本（推荐）

```powershell
cd D:\dev\study\localstack-lab\projects\aws-services-demo
.\download-file.ps1
```

**效果**：
- 从 LocalStack S3 下载文件
- 保存到当前目录
- 显示文件内容

---

### 方法 2: 使用 AWS CLI

```powershell
# 下载到当前目录
aws --endpoint-url=http://localhost:4566 s3 cp s3://test-bucket-demo/test-file.txt ./

# 下载后文件位置
# D:\dev\study\localstack-lab\projects\aws-services-demo\test-file.txt
```

---

### 方法 3: 只查看内容（不下载）

```powershell
# 直接输出到控制台
aws --endpoint-url=http://localhost:4566 s3 cp s3://test-bucket-demo/test-file.txt -

# 输出:
# Hello from LocalStack S3! Timestamp: 2026-01-01T15:17:31.402031300Z
```

---

## 📂 完整路径对比

| 位置 | 路径 | 文件是否存在 |
|------|------|-------------|
| **Java 项目目录** | `D:\dev\study\localstack-lab\projects\aws-services-demo\` | ❌ 不存在 |
| **LocalStack 容器** | `/var/lib/localstack/state/` (内部) | ✅ 存在 |
| **下载后的本地** | `D:\dev\study\...\test-file.txt` | ✅ 下载后存在 |

---

## 🎯 总结表格

| 问题 | 答案 |
|------|------|
| **test-file.txt 在哪里？** | LocalStack 容器的 S3 模拟服务中 |
| **在本地磁盘吗？** | ❌ 默认不在本地磁盘 |
| **如何查看？** | 使用 AWS CLI 或运行下载脚本 |
| **如何获取到本地？** | 执行 `.\download-file.ps1` 或 AWS CLI |
| **为什么找不到？** | 因为它是虚拟文件，存在容器中 |
| **重启后还在吗？** | 看情况：容器停止/重启会保留，删除会丢失 |

---

## 💡 类比理解

**类比**: test-file.txt 就像你手机里的照片

```
❌ 错误理解: 
   "我在电脑上跑了程序，所以文件应该在电脑硬盘上"
   
   就像: "我用手机拍了照片，照片应该在手机外壳上"

✅ 正确理解:
   "程序把数据发送到 LocalStack 容器，数据存在容器的模拟 S3 中"
   
   就像: "照片存在手机内存里，不是存在外壳上"
```

**要获取照片(文件)**：
- 需要打开相册(使用 AWS CLI)
- 或者传输到电脑(下载脚本)

---

## 🚀 快速操作

### 立即下载文件到本地

```powershell
# 1. 进入项目目录
cd D:\dev\study\localstack-lab\projects\aws-services-demo

# 2. 运行下载脚本
.\download-file.ps1

# 3. 文件将出现在当前目录
# 路径: D:\dev\study\localstack-lab\projects\aws-services-demo\test-file.txt
```

---

**创建时间**: 2026-01-02  
**说明版本**: 图解版 1.0

