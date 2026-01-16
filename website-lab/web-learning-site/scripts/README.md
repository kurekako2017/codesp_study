# 自动化脚本示例

> 该目录用于存放企业官网项目的自动化运维、部署、备份等脚本。

## 目录结构建议

```
scripts/
├── setup.sh           # 环境初始化
├── backup.sh          # 数据备份
└── health-check.sh    # 健康检查
```

## 脚本建议
- setup.sh：一键安装依赖、初始化数据库、配置环境变量
- backup.sh：定时备份数据库与重要数据
- health-check.sh：定时检测服务健康状态，自动告警

## 相关资源
- [Bash 脚本教程](https://www.runoob.com/linux/linux-shell.html)
- [CI/CD 实践](https://docs.github.com/en/actions)
