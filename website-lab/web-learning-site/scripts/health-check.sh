#!/bin/bash
# 健康检查脚本示例
curl -f http://localhost:5000/health || echo "后端服务异常"
curl -f http://localhost:3000 || echo "前端服务异常"
