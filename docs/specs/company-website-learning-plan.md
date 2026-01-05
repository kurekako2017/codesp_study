# 公司官网学习路线图

目标：学习以低成本、高效率快速制作精美且高质量的公司官网，包含一个最小后台（新闻发布）和联系表单（邮件发送与存储），并覆盖域名、邮箱、部署全流程。

## 成功标准
- 全站在宽带环境下加载 <2 秒；Lighthouse 性能/SEO/可访问性均 ≥90 分。
- 后台管理员可登录并发布/编辑/删除新闻，前台正常展示。
- 联系表单能发送邮件并存储提交记录，具备基础防刷/防垃圾能力。
- 域名、SSL、企业邮箱均可用；部署流程可重复（脚本或 CI/CD）。

## 低成本推荐技术栈
- 前端：静态/SSR（Next.js 或 Astro），样式用 Tailwind 或轻量 CSS，尽量少 JS。
- 后端/管理：轻量 Headless CMS（Strapi、PocketBase）或自建 Node/Express+SQLite/Postgres。
- 数据库：演示用 SQLite；上线可选托管 Postgres（如 Supabase/Render 免费层）。
- 认证：邮箱密码 + 会话 Cookie，开启 CSRF 防护。
- 邮件发送：Resend/SendGrid 免费层，或 Zoho Mail/ImprovMX+Gmail 的 SMTP。
- 域名与 DNS：Cloudflare 或 Namecheap 注册，DNS 放 Cloudflare 获取免费 CDN/SSL。
- 部署：前端上 Vercel/Netlify；后端上 Render/Fly.io/railway（免费/低价），开启 HTTPS。

## 最小功能范围
1) 前台站点
- 首页、关于、服务/产品、新闻/博客、联系（表单）、隐私政策。
- SEO 基础：标题/描述/Open Graph、sitemap、robots.txt、schema.org（组织/新闻）。
2) 后台
- 登录页。
- 新闻 CRUD：标题、slug、摘要、正文（Markdown）、封面（可选）、发布时间。
- 联系记录查看/导出（CSV）、搜索/筛选。
- 权限：单管理员即可满足 MVP。
3) 联系表单
- 字段：姓名、邮箱、留言；可选电话/公司。
- 校验 + 频率限制 + 验证码（hCaptcha/Cloudflare Turnstile）。
- 提交后：写入数据库 + 向管理员邮箱发通知。

## 学习路径（顺序）
1) 基础
- HTML 语义化，移动优先响应式，CSS 工具类（Tailwind）或 BEM。
- Git 基础；npm/yarn/pnpm 工作流。
- 使用 Lighthouse/WebPageTest 做自测。
2) 静态/SSR 站点
- 用 Next.js 或 Astro 做简单落地页。
- 加路由、布局、元数据（next-seo/astro-seo），生成 sitemap/robots.txt。
3) 视觉与组件
- 练习 Hero、导航、页脚、卡片、CTA、时间线、FAQ 折叠。
- 建立全局设计令牌（色板、间距、排版），确保可访问的焦点样式。
4) CMS/后端
- 方案 A：Strapi/PocketBase 快速建模，创建 Posts/Contacts 集合。
- 方案 B：自建 Express + SQLite + drizzle/prisma，简单会话登录。
5) 后台 UI
- 路由保护，CRUD 列表与表单，Markdown 编辑（react-markdown 或 tiptap-lite）。
- 上传处理（S3 兼容存储，演示可本地）。
6) 联系流转
- 前后端校验，防刷，SMTP/API 发信，持久化存储。
7) 域名与邮箱
- 购买域名，DNS 切到 Cloudflare，配置前后端的 A/AAAA/CNAME。
- 配置 MX + SPF + DKIM + DMARC（Zoho/ImprovMX+Gmail/Proton）。
8) 部署与 CI
- 前端部署 Vercel/Netlify；后端部署 Render/Fly/railway。
- 环境变量管理、密钥、健康检查、备份、日志/指标。
9) 加固
- 全站 HTTPS、HSTS、CSP、限流、CSRF、输入清理、定期备份。

## 基础设施检查清单
- 已购域名，DNS 由 Cloudflare 托管。
- MX/SPF/DKIM/DMARC 配好，用 mail-tester.com 验证通过。
- 平台自动 SSL，强制 HTTPS 跳转，开启 HSTS。
- 数据库有备份，存储位置记录清楚。
- 监控：存活监测（Better Stack/Uptime Kuma）+ 错误日志（Sentry/Logtail）。

## 成本控制提示
- 优先静态化与 CDN 缓存，减少后台压力。
- 用免费层并设置配额告警；数据库集中单一区域。
- 打包与压缩图片；用系统字体或单一可变字体。

## 练习项目梯度
1) 纯静态落地页 + 联系表单直发 Formspree/Resend（无数据库）。
2) 静态站 + PocketBase 存新闻与联系记录（单二进制后端）。
3) Next.js + 自建 Express API + SQLite + 后台仪表盘 + SMTP 发信。

## 仓库建议结构
- frontend/：Next.js 或 Astro 公网站点。
- backend/：Strapi/PocketBase 或 Express API，含迁移脚本。
- infra/：IaC 或部署笔记（DNS、环境变量、密钥模板）。
- docs/：运行手册（部署、备份/恢复、入门）、检查清单、设计令牌。

## 每日学习节奏（示例）
- 30 分钟：复盘前一天，定小目标。
- 60–90 分钟：实现 1 个组件/功能并提交。
- 20 分钟：测试 + Lighthouse + 无障碍检查。
- 20 分钟：写短文档：变更、风险、下一步。

## 上线前验证清单
- 页面：响应式，无控制台报错，有 404 页。
- 性能：Lighthouse ≥90，图片优化，缓存头设置到位。
- SEO：元信息、sitemap、robots.txt、结构化数据均通过校验。
- 安全：鉴权生效，限流，CSRF/CORS 配置，密钥不入库。
- 后台：新闻增删改查正常，联系记录可查看导出。
- 邮件：SPF/DKIM/DMARC 通过，联系邮件正常送达；如做订阅，带退订信息。
