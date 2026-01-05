# 会社主页前端项目

简单的公司官网静态页面，纯 HTML/CSS/JavaScript 实现。

## 功能特性

- ✅ 响应式设计（PC/平板/手机）
- ✅ 导航菜单（含移动端汉堡菜单）
- ✅ Hero 区块（首屏大图）
- ✅ 公司简介展示
- ✅ 服务介绍卡片
- ✅ 新闻列表
- ✅ 联系表单（含验证）
- ✅ 页脚信息
- ✅ 平滑滚动
- ✅ 现代化 UI 设计

## 快速启动

### 方法 1：直接打开（最简单）
```bash
# 在浏览器中打开
open index.html  # macOS
xdg-open index.html  # Linux
start index.html  # Windows
```

### 方法 2：本地服务器（推荐）
```bash
# Python 3
cd /workspaces/study/web-learning-site/frontend
python3 -m http.server 8000

# 或使用 Node.js
npx serve .

# 然后浏览器访问 http://localhost:8000
```

### 方法 3：VS Code Live Server
1. 安装 Live Server 扩展
2. 右键 `index.html` → "Open with Live Server"

## 文件结构

```
frontend/
├── index.html    # 主页面（包含所有内容）
├── styles.css    # 样式表（响应式+现代设计）
├── script.js     # 交互逻辑（菜单+表单+动画）
└── README.md     # 本文档
```

## 页面结构

- **导航栏**：固定顶部，响应式菜单
- **Hero 区**：首屏大标题与 CTA 按钮
- **会社概要**：公司介绍+信息表格
- **服务**：4 张卡片展示服务项目
- **新闻**：3 条示例新闻
- **联系**：表单（姓名/邮箱/留言）
- **页脚**：公司信息+链接

## 表单处理

当前为演示模式：
- 前端验证（必填项、邮箱格式）
- 控制台输出数据
- 2 秒延迟后显示成功消息

**后续接入后台：**
将 `script.js` 第 45-50 行注释取消，改为实际 API 端点：
```javascript
const response = await fetch('/api/contact', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(formData)
});
```

## 自定义修改

### 修改公司信息
编辑 `index.html`：
- 第 9 行：页面标题
- 第 17 行：导航 Logo
- 第 30-32 行：Hero 文案
- 第 42-58 行：公司表格信息
- 第 155-163 行：页脚联系方式

### 修改颜色主题
编辑 `styles.css` 第 8-17 行的 CSS 变量：
```css
:root {
    --primary-color: #2563eb;  /* 主色 */
    --primary-dark: #1e40af;   /* 主色深色 */
    --text-dark: #1e293b;      /* 文字颜色 */
    ...
}
```

### 添加新闻
复制 `index.html` 第 106-110 行的 `.news-item` 块并修改内容。

## 性能优化建议

1. **图片**：添加实际图片时使用 WebP 格式，并压缩
2. **字体**：当前使用系统字体，速度最快
3. **CSS**：已内联关键 CSS，生产环境可考虑分离
4. **JS**：脚本体积小，可直接内联或异步加载

## Lighthouse 检查

```bash
# 使用 Chrome DevTools
1. 按 F12 打开开发者工具
2. 切换到 "Lighthouse" 标签
3. 选择 "Performance + SEO + Accessibility"
4. 点击 "Generate report"
5. 目标：各项 ≥90 分
```

## 下一步

1. **添加实际内容**：替换文字、添加图片
2. **集成后台**：对接 Strapi/PocketBase 或自建 API
3. **部署**：上传到 Vercel/Netlify（见 ../infra/README.md）
4. **SEO 强化**：添加 sitemap.xml 和 robots.txt
5. **国际化**：支持多语言（中文/日文/英文）

## 浏览器兼容性

- ✅ Chrome/Edge 90+
- ✅ Firefox 88+
- ✅ Safari 14+
- ✅ 移动端浏览器

使用了现代 CSS Grid/Flexbox 和 ES6+ 语法。
