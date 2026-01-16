# API 接口规范示例

## 新闻接口

### GET /api/news
- 描述：获取新闻列表
- 返回：
```json
[
  { "id": 1, "title": "示例新闻", "content": "这是一个新闻内容示例。" }
]
```

### POST /api/news
- 描述：创建新闻
- 请求体：
```json
{
  "title": "新闻标题",
  "content": "新闻内容"
}
```
- 返回：
```json
{ "message": "新闻已创建" }
```
