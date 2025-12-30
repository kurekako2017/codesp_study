# JtSpringProject 快速启动指南

## 🚀 最简单的启动方式（推荐）

### 方法1: 点击运行主类 ⭐
1. 打开文件: `src/main/java/com/jtspringproject/JtSpringProject/JtSpringProjectApplication.java`
2. 点击主类上方的 `▶ Run Java` 按钮
3. 等待应用启动（约3-5秒）
4. 访问 http://localhost:8080

### 方法2: Maven命令
```bash
cd /workspaces/study/java-projects/JtProject
mvn spring-boot:run
```

### 方法3: 启动脚本
```bash
cd /workspaces/study/java-projects/JtProject
./start.sh
```

## 📱 访问应用

- **首页**: http://localhost:8080
- **管理员账户**: `admin` / `123`
- **普通用户**: `lisa` / `765`

## 🔄 切换数据库环境

### 默认（H2文件存储 - 数据持久化）
```bash
mvn spring-boot:run
```

### 远程MySQL (192.168.10.2)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=remote
```

### 本地MySQL
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## 📚 完整文档

详细配置说明请查看：[ENV_CONFIG.md](ENV_CONFIG.md)

---

**快速开始**: 直接点击 `JtSpringProjectApplication.java` 的运行按钮即可！
