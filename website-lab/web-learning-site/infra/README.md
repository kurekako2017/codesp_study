# 基础设施即代码（IaC）示例

> 该目录用于存放企业官网相关的基础设施配置与自动化脚本。

## 目录结构建议

```
infra/
├── docker/            # Docker 配置
├── terraform/         # Terraform IaC（可选）
├── deployment/        # 部署脚本与配置
└── monitoring/        # 监控配置（Prometheus/Grafana）
```

## 推荐工具
- Docker / Docker Compose
- Terraform
- Prometheus / Grafana
- GitHub Actions / CI/CD

## 相关文档
- [Docker 官方文档](https://docs.docker.com/)
- [Terraform 官方文档](https://developer.hashicorp.com/terraform/docs)
- [Prometheus 官方文档](https://prometheus.io/docs/introduction/overview/)
- [Grafana 官方文档](https://grafana.com/docs/)
