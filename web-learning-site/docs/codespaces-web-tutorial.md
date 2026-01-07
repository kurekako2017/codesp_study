# 🚀 GitHub Codespaces 网页制作完整教程

## 📖 教程概述

本教程将指导您如何使用 **GitHub Codespaces** 云端开发环境，从零开始构建一个完整的企业官网项目，包含：

- ✨ **前端**：使用 Next.js + Tailwind CSS 构建现代化的响应式网站
- 🔧 **后端**：使用 Express + SQLite 提供 RESTful API 服务
- 📧 **功能**：新闻发布系统、联系表单、数据存储

**为什么选择 Codespaces？**
- ☁️ 无需本地配置，浏览器即可开发
- 🎯 统一的开发环境，避免"在我机器上能跑"的问题
- 💪 强大的云端计算资源
- 🔄 随时随地访问，多设备无缝切换

---

## 🎬 第一部分：准备与启动 Codespace

### 方法一：从现有仓库启动

1. **打开您的 GitHub 仓库**
   - 访问：`https://github.com/你的用户名/你的仓库名`

2. **创建 Codespace**
   ```
   点击绿色按钮 "Code" 
   → 选择 "Codespaces" 标签 
   → 点击 "Create codespace on main"
   ```

3. **等待环境初始化**
   - Codespace 会自动配置容器环境（约 1-2 分钟）
   - 完成后会打开一个完整的 VS Code 编辑器界面

### 方法二：从模板快速开始

1. **使用官方模板**
   ```bash
   访问：https://github.com/codespaces/templates
   选择 "Blank" 模板或 "Node.js" 模板
   点击 "Use this template" → "Open in a codespace"
   ```

2. **首次进入命令**
   ```bash
   # 确认 Node.js 版本
   node --version
   npm --version
   
   # 查看当前目录
   pwd
   ls -la
   ```

### 快速操作指引

```bash
# 克隆已有项目（如果从空白开始）
git clone https://github.com/你的用户名/你的仓库.git
cd 你的仓库

# 或者初始化新项目
mkdir my-company-website
cd my-company-website
git init
```

---

## ⚙️ 第二部分：配置开发环境

### 创建 DevContainer 配置

在项目根目录创建 `.devcontainer/devcontainer.json`：

```bash
mkdir -p .devcontainer
```

编辑 `.devcontainer/devcontainer.json`：

```json
{
  "name": "Company Website Dev Environment",
  "image": "mcr.microsoft.com/devcontainers/javascript-node:18",
  
  "features": {
    "ghcr.io/devcontainers/features/node:1": {
      "version": "18"
    }
  },
  
  "forwardPorts": [3000, 3001, 8080],
  "portsAttributes": {
    "3000": {
      "label": "Frontend (Next.js)",
      "onAutoForward": "notify"
    },
    "3001": {
      "label": "Backend (Express)",
      "onAutoForward": "notify"
    },
    "8080": {
      "label": "Alternative Port",
      "onAutoForward": "silent"
    }
  },
  
  "customizations": {
    "vscode": {
      "extensions": [
        "dbaeumer.vscode-eslint",
        "esbenp.prettier-vscode",
        "bradlc.vscode-tailwindcss",
        "PKief.material-icon-theme",
        "eamodio.gitlens",
        "formulahendry.auto-rename-tag",
        "Prisma.prisma"
      ],
      "settings": {
        "editor.formatOnSave": true,
        "editor.defaultFormatter": "esbenp.prettier-vscode",
        "editor.codeActionsOnSave": {
          "source.fixAll.eslint": true
        },
        "files.autoSave": "afterDelay"
      }
    }
  },
  
  "postCreateCommand": "npm install -g npm@latest && echo '开发环境配置完成！'",
  
  "remoteUser": "node"
}
```

### 配置说明

| 配置项 | 说明 |
|--------|------|
| `image` | 使用 Node.js 18 官方镜像 |
| `forwardPorts` | 自动转发端口到本地浏览器 |
| `extensions` | 自动安装 VS Code 扩展 |
| `formatOnSave` | 保存时自动格式化代码 |
| `postCreateCommand` | 容器创建后执行的命令 |

**应用配置：**
```bash
# 修改配置后，重建容器
按 F1 → 输入 "Codespaces: Rebuild Container"
```

---

## 🎨 第三部分：前端开发（Next.js）

### 步骤 1：创建 Next.js 项目

```bash
# 创建项目（选择 TypeScript, Tailwind CSS, App Router）
npx create-next-app@latest frontend
# 提示选项：
# ✔ Would you like to use TypeScript? Yes
# ✔ Would you like to use ESLint? Yes
# ✔ Would you like to use Tailwind CSS? Yes
# ✔ Would you like to use `src/` directory? No
# ✔ Would you like to use App Router? Yes
# ✔ Would you like to customize the default import alias? No

cd frontend
```

### 步骤 2：创建主页 (app/page.tsx)

```tsx
export default function HomePage() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-blue-50 to-white">
      {/* 导航栏 */}
      <nav className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16 items-center">
            <div className="text-2xl font-bold text-blue-600">
              🏢 企业官网
            </div>
            <div className="hidden md:flex space-x-8">
              <a href="/" className="text-gray-700 hover:text-blue-600 transition">首页</a>
              <a href="/news" className="text-gray-700 hover:text-blue-600 transition">新闻动态</a>
              <a href="/contact" className="text-gray-700 hover:text-blue-600 transition">联系我们</a>
            </div>
          </div>
        </div>
      </nav>

      {/* Hero 区域 */}
      <section className="py-20 px-4">
        <div className="max-w-7xl mx-auto text-center">
          <h1 className="text-5xl font-bold text-gray-900 mb-6">
            欢迎来到我们的企业
          </h1>
          <p className="text-xl text-gray-600 mb-8 max-w-2xl mx-auto">
            专注于为客户提供高质量的产品与服务，用技术驱动未来
          </p>
          <button className="bg-blue-600 text-white px-8 py-3 rounded-lg font-semibold hover:bg-blue-700 transition">
            了解更多
          </button>
        </div>
      </section>

      {/* 服务展示 */}
      <section className="py-16 px-4 bg-white">
        <div className="max-w-7xl mx-auto">
          <h2 className="text-3xl font-bold text-center mb-12">我们的服务</h2>
          <div className="grid md:grid-cols-3 gap-8">
            {/* 服务卡片 1 */}
            <div className="p-6 border rounded-lg hover:shadow-lg transition">
              <div className="text-4xl mb-4">🚀</div>
              <h3 className="text-xl font-semibold mb-3">快速开发</h3>
              <p className="text-gray-600">
                采用最新技术栈，提供快速高效的开发解决方案
              </p>
            </div>

            {/* 服务卡片 2 */}
            <div className="p-6 border rounded-lg hover:shadow-lg transition">
              <div className="text-4xl mb-4">🎯</div>
              <h3 className="text-xl font-semibold mb-3">精准设计</h3>
              <p className="text-gray-600">
                专业的 UI/UX 设计，打造用户喜爱的产品体验
              </p>
            </div>

            {/* 服务卡片 3 */}
            <div className="p-6 border rounded-lg hover:shadow-lg transition">
              <div className="text-4xl mb-4">💡</div>
              <h3 className="text-xl font-semibold mb-3">创新思维</h3>
              <p className="text-gray-600">
                持续创新，为客户提供领先的技术与商业洞察
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* 页脚 */}
      <footer className="bg-gray-900 text-white py-8 mt-16">
        <div className="max-w-7xl mx-auto px-4 text-center">
          <p>&copy; 2024 企业官网. 保留所有权利.</p>
        </div>
      </footer>
    </div>
  );
}
```

### 步骤 3：创建新闻列表页 (app/news/page.tsx)

```tsx
'use client';

import { useEffect, useState } from 'react';

interface NewsItem {
  id: number;
  title: string;
  summary: string;
  created_at: string;
}

export default function NewsPage() {
  const [news, setNews] = useState<NewsItem[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // 从后端 API 获取新闻列表
    fetch('http://localhost:3001/api/posts')
      .then(res => res.json())
      .then(data => {
        setNews(data);
        setLoading(false);
      })
      .catch(err => {
        console.error('获取新闻失败:', err);
        setLoading(false);
      });
  }, []);

  return (
    <div className="min-h-screen bg-gray-50">
      {/* 导航栏 */}
      <nav className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16 items-center">
            <div className="text-2xl font-bold text-blue-600">
              🏢 企业官网
            </div>
            <div className="hidden md:flex space-x-8">
              <a href="/" className="text-gray-700 hover:text-blue-600 transition">首页</a>
              <a href="/news" className="text-blue-600 font-semibold">新闻动态</a>
              <a href="/contact" className="text-gray-700 hover:text-blue-600 transition">联系我们</a>
            </div>
          </div>
        </div>
      </nav>

      {/* 新闻列表 */}
      <div className="max-w-4xl mx-auto py-12 px-4">
        <h1 className="text-4xl font-bold mb-8">新闻动态</h1>

        {loading ? (
          <div className="text-center py-12">
            <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
            <p className="mt-4 text-gray-600">加载中...</p>
          </div>
        ) : news.length === 0 ? (
          <div className="text-center py-12 bg-white rounded-lg shadow">
            <p className="text-gray-500 text-lg">暂无新闻</p>
          </div>
        ) : (
          <div className="space-y-6">
            {news.map((item) => (
              <article key={item.id} className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition">
                <h2 className="text-2xl font-semibold mb-2 text-gray-900">
                  {item.title}
                </h2>
                <p className="text-gray-600 mb-3">{item.summary}</p>
                <div className="flex justify-between items-center">
                  <span className="text-sm text-gray-500">
                    {new Date(item.created_at).toLocaleDateString('zh-CN')}
                  </span>
                  <button className="text-blue-600 hover:text-blue-800 font-medium">
                    阅读更多 →
                  </button>
                </div>
              </article>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
```

### 步骤 4：创建联系表单页 (app/contact/page.tsx)

```tsx
'use client';

import { useState } from 'react';

export default function ContactPage() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    message: ''
  });
  const [status, setStatus] = useState<'idle' | 'submitting' | 'success' | 'error'>('idle');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setStatus('submitting');

    try {
      const response = await fetch('http://localhost:3001/api/contacts', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        setStatus('success');
        setFormData({ name: '', email: '', message: '' });
        setTimeout(() => setStatus('idle'), 3000);
      } else {
        setStatus('error');
      }
    } catch (error) {
      console.error('提交失败:', error);
      setStatus('error');
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="min-h-screen bg-gray-50">
      {/* 导航栏 */}
      <nav className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16 items-center">
            <div className="text-2xl font-bold text-blue-600">
              🏢 企业官网
            </div>
            <div className="hidden md:flex space-x-8">
              <a href="/" className="text-gray-700 hover:text-blue-600 transition">首页</a>
              <a href="/news" className="text-gray-700 hover:text-blue-600 transition">新闻动态</a>
              <a href="/contact" className="text-blue-600 font-semibold">联系我们</a>
            </div>
          </div>
        </div>
      </nav>

      {/* 联系表单 */}
      <div className="max-w-2xl mx-auto py-12 px-4">
        <h1 className="text-4xl font-bold mb-8 text-center">联系我们</h1>
        
        <div className="bg-white p-8 rounded-lg shadow-md">
          <form onSubmit={handleSubmit} className="space-y-6">
            {/* 姓名 */}
            <div>
              <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-2">
                姓名 *
              </label>
              <input
                type="text"
                id="name"
                name="name"
                required
                value={formData.name}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入您的姓名"
              />
            </div>

            {/* 邮箱 */}
            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                邮箱 *
              </label>
              <input
                type="email"
                id="email"
                name="email"
                required
                value={formData.email}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="example@email.com"
              />
            </div>

            {/* 留言 */}
            <div>
              <label htmlFor="message" className="block text-sm font-medium text-gray-700 mb-2">
                留言 *
              </label>
              <textarea
                id="message"
                name="message"
                required
                rows={5}
                value={formData.message}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="请输入您的留言..."
              />
            </div>

            {/* 提交按钮 */}
            <button
              type="submit"
              disabled={status === 'submitting'}
              className="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition disabled:bg-gray-400"
            >
              {status === 'submitting' ? '提交中...' : '提交'}
            </button>

            {/* 状态消息 */}
            {status === 'success' && (
              <div className="p-4 bg-green-50 border border-green-200 rounded-lg text-green-800">
                ✅ 提交成功！我们会尽快与您联系。
              </div>
            )}
            {status === 'error' && (
              <div className="p-4 bg-red-50 border border-red-200 rounded-lg text-red-800">
                ❌ 提交失败，请稍后重试。
              </div>
            )}
          </form>
        </div>

        {/* 联系信息 */}
        <div className="mt-12 grid md:grid-cols-3 gap-6 text-center">
          <div className="p-6 bg-white rounded-lg shadow">
            <div className="text-3xl mb-2">📧</div>
            <h3 className="font-semibold mb-1">邮箱</h3>
            <p className="text-gray-600 text-sm">contact@company.com</p>
          </div>
          <div className="p-6 bg-white rounded-lg shadow">
            <div className="text-3xl mb-2">📞</div>
            <h3 className="font-semibold mb-1">电话</h3>
            <p className="text-gray-600 text-sm">+86 123-4567-8900</p>
          </div>
          <div className="p-6 bg-white rounded-lg shadow">
            <div className="text-3xl mb-2">📍</div>
            <h3 className="font-semibold mb-1">地址</h3>
            <p className="text-gray-600 text-sm">北京市朝阳区xxx路</p>
          </div>
        </div>
      </div>
    </div>
  );
}
```

### 步骤 5：启动前端开发服务器

```bash
cd frontend

# 安装依赖（如果还没安装）
npm install

# 启动开发服务器
npm run dev
```

访问：`http://localhost:3000`（Codespaces 会自动转发端口并提供访问链接）

---

## 🔧 第四部分：后端开发（Express + SQLite）

### 步骤 1：创建后端项目结构

```bash
# 回到项目根目录
cd ..

# 创建后端目录
mkdir backend
cd backend

# 初始化 Node.js 项目
npm init -y
```

### 步骤 2：安装依赖

```bash
npm install express cors sqlite3 nodemailer dotenv
npm install --save-dev typescript @types/node @types/express @types/cors ts-node nodemon
```

### 步骤 3：配置 TypeScript

创建 `tsconfig.json`：

```json
{
  "compilerOptions": {
    "target": "ES2020",
    "module": "commonjs",
    "lib": ["ES2020"],
    "outDir": "./dist",
    "rootDir": "./src",
    "strict": true,
    "esModuleInterop": true,
    "skipLibCheck": true,
    "forceConsistentCasingInFileNames": true,
    "resolveJsonModule": true
  },
  "include": ["src/**/*"],
  "exclude": ["node_modules"]
}
```

### 步骤 4：创建主服务文件 (src/index.ts)

```bash
mkdir src
```

创建 `src/index.ts`：

```typescript
import express, { Request, Response } from 'express';
import cors from 'cors';
import sqlite3 from 'sqlite3';
import dotenv from 'dotenv';
import path from 'path';

dotenv.config();

const app = express();
const PORT = process.env.PORT || 3001;

// 中间件
app.use(cors());
app.use(express.json());

// 数据库初始化
const dbPath = path.join(__dirname, '../database.sqlite');
const db = new sqlite3.Database(dbPath);

// 创建表
db.serialize(() => {
  // 新闻文章表
  db.run(`
    CREATE TABLE IF NOT EXISTS posts (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      title TEXT NOT NULL,
      summary TEXT,
      content TEXT,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )
  `);

  // 联系表单表
  db.run(`
    CREATE TABLE IF NOT EXISTS contacts (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT NOT NULL,
      email TEXT NOT NULL,
      message TEXT NOT NULL,
      created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    )
  `);

  // 插入示例数据
  db.get("SELECT COUNT(*) as count FROM posts", (err, row: any) => {
    if (row.count === 0) {
      const samplePosts = [
        {
          title: '公司成立十周年庆典圆满成功',
          summary: '2024年是公司发展的重要里程碑，我们成功举办了十周年庆典活动。',
          content: '详细内容...'
        },
        {
          title: '新产品发布：智能解决方案 2.0',
          summary: '我们很高兴地宣布推出全新的智能解决方案 2.0 版本。',
          content: '详细内容...'
        },
        {
          title: '荣获年度最佳技术创新奖',
          summary: '公司凭借卓越的技术创新能力获得行业权威奖项。',
          content: '详细内容...'
        }
      ];

      const stmt = db.prepare("INSERT INTO posts (title, summary, content) VALUES (?, ?, ?)");
      samplePosts.forEach(post => {
        stmt.run(post.title, post.summary, post.content);
      });
      stmt.finalize();
    }
  });
});

// ==================== API 端点 ====================

// 健康检查
app.get('/health', (req: Request, res: Response) => {
  res.json({ status: 'ok', timestamp: new Date().toISOString() });
});

// 获取新闻列表
app.get('/api/posts', (req: Request, res: Response) => {
  const sql = 'SELECT id, title, summary, created_at FROM posts ORDER BY created_at DESC';
  
  db.all(sql, [], (err, rows) => {
    if (err) {
      console.error('数据库查询错误:', err);
      return res.status(500).json({ error: '获取新闻列表失败' });
    }
    res.json(rows);
  });
});

// 获取单篇新闻
app.get('/api/posts/:id', (req: Request, res: Response) => {
  const { id } = req.params;
  const sql = 'SELECT * FROM posts WHERE id = ?';
  
  db.get(sql, [id], (err, row) => {
    if (err) {
      console.error('数据库查询错误:', err);
      return res.status(500).json({ error: '获取新闻失败' });
    }
    if (!row) {
      return res.status(404).json({ error: '新闻不存在' });
    }
    res.json(row);
  });
});

// 创建新闻（简化版，实际应添加认证）
app.post('/api/posts', (req: Request, res: Response) => {
  const { title, summary, content } = req.body;
  
  if (!title) {
    return res.status(400).json({ error: '标题不能为空' });
  }

  const sql = 'INSERT INTO posts (title, summary, content) VALUES (?, ?, ?)';
  
  db.run(sql, [title, summary, content], function(err) {
    if (err) {
      console.error('数据库插入错误:', err);
      return res.status(500).json({ error: '创建新闻失败' });
    }
    res.status(201).json({
      id: this.lastID,
      message: '新闻创建成功'
    });
  });
});

// 提交联系表单
app.post('/api/contacts', (req: Request, res: Response) => {
  const { name, email, message } = req.body;
  
  // 验证必填字段
  if (!name || !email || !message) {
    return res.status(400).json({ error: '请填写所有必填字段' });
  }

  // 简单的邮箱格式验证
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    return res.status(400).json({ error: '邮箱格式不正确' });
  }

  const sql = 'INSERT INTO contacts (name, email, message) VALUES (?, ?, ?)';
  
  db.run(sql, [name, email, message], function(err) {
    if (err) {
      console.error('数据库插入错误:', err);
      return res.status(500).json({ error: '提交失败' });
    }

    // TODO: 这里可以添加发送邮件通知的逻辑
    console.log(`收到新的联系表单：${name} (${email})`);

    res.status(201).json({
      id: this.lastID,
      message: '提交成功，我们会尽快与您联系'
    });
  });
});

// 获取联系表单列表（管理端使用）
app.get('/api/contacts', (req: Request, res: Response) => {
  const sql = 'SELECT * FROM contacts ORDER BY created_at DESC';
  
  db.all(sql, [], (err, rows) => {
    if (err) {
      console.error('数据库查询错误:', err);
      return res.status(500).json({ error: '获取联系记录失败' });
    }
    res.json(rows);
  });
});

// 启动服务器
app.listen(PORT, () => {
  console.log(`🚀 后端服务器运行在: http://localhost:${PORT}`);
  console.log(`📊 数据库路径: ${dbPath}`);
  console.log(`✅ API 端点：`);
  console.log(`   - GET  /health`);
  console.log(`   - GET  /api/posts`);
  console.log(`   - GET  /api/posts/:id`);
  console.log(`   - POST /api/posts`);
  console.log(`   - POST /api/contacts`);
  console.log(`   - GET  /api/contacts`);
});

// 优雅关闭
process.on('SIGINT', () => {
  db.close((err) => {
    if (err) {
      console.error('关闭数据库时出错:', err);
    }
    console.log('\n👋 数据库连接已关闭');
    process.exit(0);
  });
});
```

### 步骤 5：配置 package.json

编辑 `package.json`，添加启动脚本：

```json
{
  "name": "backend",
  "version": "1.0.0",
  "description": "企业官网后端 API",
  "main": "dist/index.js",
  "scripts": {
    "dev": "nodemon --watch src --ext ts --exec ts-node src/index.ts",
    "build": "tsc",
    "start": "node dist/index.js",
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "keywords": ["express", "sqlite", "api"],
  "author": "",
  "license": "MIT"
}
```

### 步骤 6：启动后端服务器

```bash
# 开发模式（自动重启）
npm run dev
```

后端服务将运行在 `http://localhost:3001`

### 测试 API

```bash
# 测试健康检查
curl http://localhost:3001/health

# 测试获取新闻列表
curl http://localhost:3001/api/posts

# 测试提交联系表单
curl -X POST http://localhost:3001/api/contacts \
  -H "Content-Type: application/json" \
  -d '{"name":"张三","email":"zhangsan@example.com","message":"测试消息"}'
```

---

## 💡 第五部分：Codespaces 实用技巧

### 1. 端口管理

**查看已转发的端口：**
```
点击 VS Code 底部的 "PORTS" 标签
或按 Ctrl+Shift+P → 输入 "Ports: Focus on Ports View"
```

**修改端口可见性：**
- **Private**：仅您可以访问
- **Public**：任何人都可以通过链接访问（需谨慎使用）

**右键点击端口 → 选择可见性级别**

### 2. 环境变量配置

创建 `.env` 文件（后端目录）：

```bash
cd backend
```

创建 `.env`：

```env
# 服务器配置
PORT=3001
NODE_ENV=development

# 数据库配置
DB_PATH=./database.sqlite

# 邮件配置（使用 Nodemailer）
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SMTP_USER=your-email@gmail.com
SMTP_PASS=your-app-password
ADMIN_EMAIL=admin@company.com

# JWT 密钥（如果添加认证）
JWT_SECRET=your-secret-key-change-in-production
```

**⚠️ 重要：** 将 `.env` 添加到 `.gitignore`

```bash
# 在项目根目录
echo ".env" >> .gitignore
echo "*.sqlite" >> .gitignore
```

### 3. 同时运行前后端

**方法一：使用多个终端**

```bash
# 终端 1：运行后端
cd backend && npm run dev

# 终端 2：运行前端
cd frontend && npm run dev
```

**方法二：使用 concurrently 工具**

在项目根目录：

```bash
npm init -y
npm install --save-dev concurrently
```

编辑根目录的 `package.json`：

```json
{
  "scripts": {
    "dev:frontend": "cd frontend && npm run dev",
    "dev:backend": "cd backend && npm run dev",
    "dev": "concurrently \"npm run dev:backend\" \"npm run dev:frontend\" --names \"API,WEB\" --prefix-colors \"blue,green\""
  }
}
```

启动：

```bash
npm run dev
```

### 4. 快捷键提示

| 快捷键 | 功能 |
|--------|------|
| `Ctrl + \`` | 打开/关闭终端 |
| `Ctrl + Shift + P` | 命令面板 |
| `Ctrl + P` | 快速打开文件 |
| `Ctrl + B` | 切换侧边栏 |
| `Ctrl + Shift + F` | 全局搜索 |
| `F12` | 跳转到定义 |

### 5. 数据持久化

Codespace 的 `/workspaces` 目录会自动持久化，包括：
- 源代码
- 数据库文件
- `node_modules`（会在重启时保留）

**确保数据安全：**
```bash
# 定期提交代码到 Git
git add .
git commit -m "保存当前进度"
git push
```

### 6. 预构建配置（可选）

创建 `.github/workflows/codespaces-prebuild.yml` 加速启动：

```yaml
name: Codespaces Prebuild

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  prebuild:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Setup Node.js
        uses: actions/setup-node@v3
        with:
          node-version: '18'
      - name: Install dependencies
        run: |
          cd frontend && npm install
          cd ../backend && npm install
```

---

## 🚢 第六部分：部署准备

### 1. 提交代码到 GitHub

```bash
# 初始化 Git（如果还没初始化）
git init

# 添加 .gitignore
cat > .gitignore << 'EOF'
node_modules/
.env
*.sqlite
.DS_Store
dist/
.next/
.turbo/
*.log
EOF

# 提交代码
git add .
git commit -m "feat: 完成企业官网前后端开发"

# 创建远程仓库并推送
git remote add origin https://github.com/你的用户名/你的仓库.git
git branch -M main
git push -u origin main
```

### 2. 部署前端到 Vercel

**步骤：**

1. **访问 Vercel**
   ```
   https://vercel.com/
   使用 GitHub 账号登录
   ```

2. **导入项目**
   ```
   点击 "New Project"
   → 选择你的 GitHub 仓库
   → 点击 "Import"
   ```

3. **配置构建设置**
   ```
   Framework Preset: Next.js
   Root Directory: frontend
   Build Command: npm run build
   Output Directory: .next
   ```

4. **环境变量（如需要）**
   ```
   NEXT_PUBLIC_API_URL=https://your-backend.onrender.com
   ```

5. **部署**
   ```
   点击 "Deploy"
   等待构建完成（约 1-3 分钟）
   ```

6. **获取域名**
   ```
   部署成功后会得到类似：
   https://your-project.vercel.app
   ```

### 3. 部署后端到 Render

**步骤：**

1. **访问 Render**
   ```
   https://render.com/
   使用 GitHub 账号登录
   ```

2. **创建新服务**
   ```
   点击 "New +"
   → 选择 "Web Service"
   ```

3. **连接仓库**
   ```
   选择你的 GitHub 仓库
   点击 "Connect"
   ```

4. **配置服务**
   ```
   Name: company-website-api
   Region: Singapore (或最近的区域)
   Branch: main
   Root Directory: backend
   Runtime: Node
   Build Command: npm install && npm run build
   Start Command: npm start
   ```

5. **选择套餐**
   ```
   Free Plan（免费层，足够测试使用）
   ```

6. **环境变量**
   ```
   添加环境变量：
   NODE_ENV=production
   PORT=3001
   SMTP_HOST=smtp.gmail.com
   SMTP_USER=your-email@gmail.com
   ...（其他必要的环境变量）
   ```

7. **部署**
   ```
   点击 "Create Web Service"
   等待构建和部署（约 3-5 分钟）
   ```

8. **更新前端 API 地址**
   ```
   在 Vercel 中添加环境变量：
   NEXT_PUBLIC_API_URL=https://your-backend.onrender.com
   重新部署前端
   ```

### 4. 数据库迁移（生产环境）

对于生产环境，建议从 SQLite 迁移到 PostgreSQL：

**使用 Render 的 PostgreSQL：**

```bash
# 在 Render Dashboard：
1. 创建新的 PostgreSQL 数据库
2. 获取连接字符串
3. 在后端代码中添加 PostgreSQL 支持
```

安装 PostgreSQL 驱动：

```bash
npm install pg
```

### 5. 配置自定义域名（可选）

**Vercel 配置：**
```
进入项目设置 → Domains
添加你的域名（如：www.yourcompany.com）
在域名提供商处添加 CNAME 记录指向 Vercel
```

**Render 配置：**
```
进入服务设置 → Custom Domains
添加 API 子域名（如：api.yourcompany.com）
在域名提供商处添加 CNAME 记录
```

---

## 📚 第七部分：后续学习方向

### 1. 集成 Headless CMS

**推荐选择：**

| CMS | 特点 | 适用场景 |
|-----|------|---------|
| **Strapi** | 开源、功能完整 | 需要完整后台管理 |
| **PocketBase** | 轻量、单文件部署 | 快速原型开发 |
| **Contentful** | 云端托管、免费层 | 不想维护服务器 |

**集成 Strapi 示例：**

```bash
# 创建 Strapi 项目
npx create-strapi-app@latest backend-cms --quickstart

# 启动 Strapi
cd backend-cms
npm run develop
```

访问 `http://localhost:1337/admin` 配置内容类型。

### 2. 添加用户认证

**使用 NextAuth.js：**

```bash
cd frontend
npm install next-auth
```

创建 `app/api/auth/[...nextauth]/route.ts`：

```typescript
import NextAuth from 'next-auth';
import CredentialsProvider from 'next-auth/providers/credentials';

export const authOptions = {
  providers: [
    CredentialsProvider({
      name: 'Credentials',
      credentials: {
        email: { label: "Email", type: "email" },
        password: { label: "Password", type: "password" }
      },
      async authorize(credentials) {
        // 实现认证逻辑
        return { id: '1', email: credentials?.email };
      }
    })
  ],
};

const handler = NextAuth(authOptions);
export { handler as GET, handler as POST };
```

### 3. 添加搜索功能

**使用 Algolia 或 MeiliSearch：**

```bash
# 安装 MeiliSearch 客户端
npm install meilisearch
```

### 4. 配置 CDN 和缓存

**Cloudflare 配置：**
- 添加域名到 Cloudflare
- 启用 Auto Minify（JS/CSS/HTML）
- 配置缓存规则
- 启用 Brotli 压缩

### 5. 监控和分析

**推荐工具：**
- **Vercel Analytics**：前端性能监控
- **Sentry**：错误跟踪
- **Google Analytics**：用户行为分析
- **Uptime Kuma**：服务健康监控

### 6. SEO 优化

```typescript
// app/layout.tsx
export const metadata = {
  title: '企业官网 - 专业的解决方案提供商',
  description: '我们提供高质量的产品与服务',
  keywords: '企业, 解决方案, 服务',
  openGraph: {
    title: '企业官网',
    description: '专业的解决方案提供商',
    url: 'https://yourcompany.com',
    siteName: '企业官网',
    images: ['/og-image.jpg'],
  }
};
```

生成 sitemap：

```bash
npm install next-sitemap
```

---

## ❓ 第八部分：常见问题 FAQ

### Q1: Codespace 会自动保存我的更改吗？

**A:** 是的，Codespace 有多层保存机制：

1. **文件自动保存**：启用了 `files.autoSave: "afterDelay"`
2. **工作区持久化**：`/workspaces` 目录的所有内容都会保存
3. **但建议定期 Git 提交**：防止意外情况

```bash
# 建议每完成一个功能就提交
git add .
git commit -m "feat: 完成xxx功能"
git push
```

### Q2: Codespaces 免费额度是多少？

**A:** GitHub 提供的免费额度：

| 资源 | 免费额度 |
|------|---------|
| **计算时间** | 120 核心小时/月 |
| **存储空间** | 15 GB |

**计算示例：**
- 2核机器：可用 60 小时/月
- 4核机器：可用 30 小时/月

**查看使用情况：**
```
GitHub 头像 → Settings → Billing → Codespaces
```

### Q3: 如何停止或删除 Codespace？

**停止 Codespace：**
```
方法1：关闭浏览器标签页，30分钟后自动停止
方法2：点击左下角 "Codespaces" → "Stop Current Codespace"
方法3：访问 https://github.com/codespaces → 点击相应 Codespace 的 "..." → "Stop"
```

**删除 Codespace：**
```
访问 https://github.com/codespaces
点击要删除的 Codespace 的 "..."
选择 "Delete"
```

### Q4: 端口转发不工作怎么办？

**检查步骤：**

1. **确认服务正在运行**
   ```bash
   # 检查进程
   lsof -i :3000
   lsof -i :3001
   ```

2. **手动添加端口转发**
   ```
   PORTS 标签 → 点击 "Forward a Port"
   输入端口号 → 回车
   ```

3. **检查防火墙设置**
   ```bash
   # Codespace 内部应该没有防火墙限制
   curl http://localhost:3000
   ```

### Q5: 数据库文件会丢失吗？

**A:** 不会，只要文件在 `/workspaces` 目录下：

```bash
# 确认数据库位置
cd backend
ls -la database.sqlite

# 数据库应该在 /workspaces/你的项目名/backend/database.sqlite
pwd
```

**备份建议：**
```bash
# 定期备份数据库
cp database.sqlite database.backup.sqlite

# 或提交到 Git（小数据库可以，大数据库不建议）
git add database.sqlite
git commit -m "backup: 数据库备份"
```

### Q6: 如何在 Codespace 中使用 Git？

**A:** Git 已预装且配置好：

```bash
# 检查 Git 配置
git config --list

# 如需修改用户信息
git config --global user.name "你的名字"
git config --global user.email "your@email.com"

# 基本操作
git status
git add .
git commit -m "更新说明"
git push

# Codespace 会自动处理 GitHub 认证
```

### Q7: 可以安装额外的工具吗？

**A:** 可以，Codespace 基于 Ubuntu：

```bash
# 安装命令行工具
sudo apt-get update
sudo apt-get install -y 工具名

# 示例：安装 PostgreSQL 客户端
sudo apt-get install -y postgresql-client

# 全局安装 npm 包
npm install -g 包名
```

**永久保留工具**：在 `.devcontainer/devcontainer.json` 中添加：

```json
{
  "postCreateCommand": "sudo apt-get update && sudo apt-get install -y postgresql-client"
}
```

### Q8: 前端无法连接后端 API？

**A:** 检查以下几点：

1. **CORS 配置**
   ```typescript
   // backend/src/index.ts
   import cors from 'cors';
   app.use(cors()); // 开发环境允许所有来源
   ```

2. **API 地址**
   ```typescript
   // frontend - 确保使用正确的端口
   fetch('http://localhost:3001/api/posts')
   ```

3. **后端是否运行**
   ```bash
   # 检查后端进程
   lsof -i :3001
   
   # 测试 API
   curl http://localhost:3001/health
   ```

4. **浏览器控制台检查**
   ```
   F12 → Network 标签 → 查看请求状态
   ```

### Q9: Codespace 启动很慢怎么办？

**A:** 优化方法：

1. **使用预构建（Prebuild）**
   - 在仓库设置中启用 Codespaces Prebuilds
   - 可将启动时间从 2-3 分钟缩短到 10-20 秒

2. **减少 postCreateCommand**
   ```json
   // 将耗时命令移到手动执行
   "postCreateCommand": "echo '环境就绪，请手动运行 npm install'"
   ```

3. **选择合适的机器类型**
   - 免费账户：2核4GB（默认）
   - 如需更多资源：升级到 Pro 账户

### Q10: 如何与团队共享 Codespace 配置？

**A:** 提交 `.devcontainer` 配置到仓库：

```bash
# 确保配置文件已提交
git add .devcontainer/devcontainer.json
git commit -m "chore: 添加 DevContainer 配置"
git push

# 团队成员克隆仓库后会自动使用相同配置
```

---

## 🎉 总结

恭喜您完成了 GitHub Codespaces 网页制作教程！您现在已经掌握：

✅ 使用 Codespaces 云端开发环境  
✅ 配置 DevContainer 和开发工具  
✅ 使用 Next.js + Tailwind CSS 构建前端  
✅ 使用 Express + SQLite 构建后端 API  
✅ 部署到 Vercel 和 Render  

### 下一步行动：

1. **实践项目**：基于本教程创建自己的企业官网
2. **添加功能**：集成 CMS、用户认证、搜索功能
3. **优化性能**：配置 CDN、图片优化、SEO
4. **学习进阶**：探索 Strapi、PocketBase、PostgreSQL

### 相关资源：

- 📖 [Next.js 官方文档](https://nextjs.org/docs)
- 📖 [GitHub Codespaces 文档](https://docs.github.com/codespaces)
- 📖 [Tailwind CSS 文档](https://tailwindcss.com/docs)
- 📖 [Express.js 文档](https://expressjs.com/)
- 📖 [公司官网学习路线图](/docs/specs/company-website-learning-plan.md)

**祝您开发愉快！** 🚀
