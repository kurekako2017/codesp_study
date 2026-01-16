# 前端应用示例

> 该目录用于存放企业官网前端项目代码，建议采用 React/Next.js 或 Astro 等现代前端框架。

## 目录结构建议

```
frontend/
├── public/            # 静态资源（图片/字体/favicon）
├── src/               # 源代码
│   ├── components/    # 可复用组件
│   ├── pages/         # 页面
│   ├── styles/        # 样式（全局/主题）
│   ├── utils/         # 工具函数
│   └── api/           # API 调用封装
├── tests/             # 单元与集成测试
└── package.json       # 依赖与脚本
```

## 推荐技术栈
- React / Next.js / Astro
- TypeScript
- Tailwind CSS / styled-components
- Jest / React Testing Library

## 快速启动

```bash
npm install
npm run dev
```

## 相关文档
- [Next.js 官方文档](https://nextjs.org/docs)
- [Astro 官方文档](https://docs.astro.build)
- [React 官方文档](https://react.dev)
