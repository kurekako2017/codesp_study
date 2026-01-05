# 企业官网学习与实战项目

目标：在低成本条件下快速搭建精美公司主页，包含后台新闻发布、联系表单（邮件发送+存储），并覆盖域名、邮箱、部署全流程。

## 目录结构
- frontend/：公网站点（建议 Next.js 或 Astro，可先做静态页再接后台）
- backend/：轻量后台/接口（可选 Strapi/PocketBase 或 Express+SQLite）
- infra/：域名、DNS、部署与环境变量说明，后续可放 IaC
- docs/：运行手册、检查清单、设计规范与迭代笔记

## 最小功能
- 前台：主页、关于、服务/产品、新闻列表与详情、联系表单、隐私政策
- 后台：登录、新闻 CRUD、联系记录查看/导出
- 联系流转：表单校验+防刷，存库并邮件通知

## 推荐工具（可替换）
- 托管：前端 Vercel/Netlify；后端 Render/Fly/railway（免费层）
- 数据库：演示用 SQLite，上线可 Postgres（Supabase/Render）
- 邮件：Resend/SendGrid 免费层，或 Zoho/ImprovMX+Gmail SMTP
- DNS/证书：Cloudflare 提供 CDN+SSL

## 建议起步步骤
1) 在 frontend/ 做一个静态落地页，跑通 Lighthouse 自测（90+）。
2) 在 backend/ 选择 Strapi/PocketBase 或 Express+SQLite，建 posts、contacts 两个集合/表。
3) 对接前台：新闻列表/详情从后端读取；联系表单提交到后端并邮件通知。
4) 在 infra/ 记录域名、DNS、环境变量、部署步骤，逐步上线到免费托管。

更多细节与学习路线参见 docs/specs/company-website-learning-plan.md。
