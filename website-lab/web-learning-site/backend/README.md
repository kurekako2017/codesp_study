# 后端 API 与管理系统示例

> 该目录用于存放企业官网后端项目代码，建议采用 Node.js/Express 或 Nest.js 框架。

## 目录结构建议

```
backend/
├── src/
│   ├── controllers/   # 路由控制器
│   ├── models/        # 数据模型（ORM/ODM）
│   ├── services/      # 业务逻辑
│   ├── middleware/    # 中间件（鉴权/日志/限流）
│   ├── config/        # 配置管理
│   └── utils/         # 工具库
├── migrations/        # 数据库迁移脚本
├── tests/             # API 测试
├── .env.example       # 环境变量模板
└── package.json
```

## 推荐技术栈
- Node.js / Express / Nest.js
- TypeScript
- Prisma / Sequelize / Mongoose
- Jest / Supertest

## 快速启动

```bash
npm install
npm run dev
```

## 相关文档
- [Express 官方文档](https://expressjs.com/)
- [Nest.js 官方文档](https://docs.nestjs.com/)
- [Prisma 官方文档](https://www.prisma.io/docs)
