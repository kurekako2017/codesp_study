# AWS Services Demo for LocalStack

Complete Java application for testing AWS services (DynamoDB, SQS, S3) with LocalStack.

## 📋 Project Overview

This is a standalone Java application that demonstrates how to interact with AWS services using LocalStack for local development and testing.

**Author**: GitHub Copilot AI  
**Created**: 2026-01-02  
**Purpose**: LocalStack AWS services testing and demonstration

## ✨ Features

- ✅ **DynamoDB Testing** - Table creation, item CRUD operations
- ✅ **SQS Testing** - Queue creation, message send/receive/delete
- ✅ **S3 Testing** - Bucket creation, file upload/download
- ✅ **Automatic Logging** - Test results saved to log file
- ✅ **Detailed Comments** - Comprehensive JavaDoc and inline comments
- ✅ **Complete Documentation** - 7 markdown documentation files

## 🚀 Quick Start

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Docker Desktop
- LocalStack running on port 4566

### Run Tests

```bash
# Start LocalStack
docker start localstack

# Run the demo
cd localstack-lab/projects/aws-services-demo
mvn clean compile exec:java
```

### View Results

```bash
# View log file
cat aws-services-test-result.log

# Or on Windows
notepad aws-services-test-result.log
```

## 📊 Test Results

All tests **PASSED** ✓

```
========================================
  Test Summary
========================================
DynamoDB: ✓ PASSED
SQS:      ✓ PASSED
S3:       ✓ PASSED
----------------------------------------
Result: ✓ ALL TESTS PASSED (3/3)
========================================
```

## 📁 Project Structure

```
aws-services-demo/
├── src/main/java/com/example/aws/
│   └── AwsServicesDemo.java          # Main application (single file!)
├── pom.xml                           # Maven configuration
├── run-demo.ps1                      # PowerShell run script
├── aws-services-test-result.log      # Test results log
└── docs/
    ├── ARCHITECTURE.md               # Code architecture
    ├── PROJECT_INFO.md               # Project details
    ├── LOG_FILE_FEATURE.md           # Logging feature
    ├── TEST_RESULTS.md               # Test report
    └── ...                           # More documentation
```

## 🔧 Technologies

- **Language**: Java 11
- **Build Tool**: Maven 3.x
- **AWS SDK**: AWS SDK for Java v2 (2.25.65)
- **LocalStack**: Community Edition
- **Services**: DynamoDB, SQS, S3

## 📖 Documentation

| Document | Description |
|----------|-------------|
| [ARCHITECTURE.md](ARCHITECTURE.md) | Code structure and design patterns |
| [PROJECT_INFO.md](PROJECT_INFO.md) | Project origin and details |
| [LOG_FILE_FEATURE.md](LOG_FILE_FEATURE.md) | Log file functionality |
| [TEST_RESULTS.md](TEST_RESULTS.md) | Detailed test results |
| [UPLOAD_GUIDE.md](UPLOAD_GUIDE.md) | Git upload instructions |

## 🎯 What Does It Test?

### DynamoDB
- Create table with hash key
- Insert item with attributes
- Query item by primary key
- List all tables

### SQS
- Create standard queue
- Send message to queue
- Receive message from queue
- Delete processed message
- List all queues

### S3
- Create bucket
- Upload file (from memory string)
- Download file
- List all buckets

## 💡 Key Features

### Single File Architecture
All code in **one Java class** for simplicity and easy understanding.

### Automatic Log Generation
Test results are automatically saved to `aws-services-test-result.log`:
- Timestamp of execution
- Detailed step-by-step output
- Success/failure summary
- Error stack traces (if any)

### Detailed Comments
Every method and important code block has comprehensive comments explaining:
- What it does
- How it works
- Why it's designed that way

## 🔍 How to Read the Code

The main class `AwsServicesDemo.java` contains:

1. **Configuration** - LocalStack endpoint and region
2. **Client Factories** - Methods to create AWS clients
3. **Test Methods** - One method per service
4. **Main Method** - Orchestrates all tests

```java
main()
  ├─> testDynamoDB()
  ├─> testSQS()
  └─> testS3()
```

## 📝 Sample Output

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

## 🛠️ Configuration

### LocalStack Endpoint
```java
private static final String LOCALSTACK_ENDPOINT = "http://localhost:4566";
```

### AWS Region
```java
private static final Region REGION = Region.US_EAST_1;
```

### Credentials
Uses dummy credentials (`test/test`) - LocalStack doesn't validate them.

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

