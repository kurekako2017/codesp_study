// 新闻相关 API 示例
const express = require('express');
const router = express.Router();

// GET /api/news
router.get('/', (req, res) => {
  res.json([
    { id: 1, title: '示例新闻', content: '这是一个新闻内容示例。' }
  ]);
});

// POST /api/news
router.post('/', (req, res) => {
  // 这里应有数据校验与存储逻辑
  res.status(201).json({ message: '新闻已创建' });
});

module.exports = router;
