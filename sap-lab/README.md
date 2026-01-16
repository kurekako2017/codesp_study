# SAP 实验室

本文件夹用于在与仓库其余部分隔离的环境中进行 SAP 开发的练习与原型开发。请将 SAP 相关的工具、脚本和示例项目保存在此处。

建议目录布局：

- `projects/`：小型示例应用
- `docs/`：笔记与指南
- `scripts/`：环境准备与辅助脚本

请将依赖项限制在本目录内（例如 `.venv`、本地 SDK），以免影响 `/workspaces/study` 下的其他项目。

当前内容示例：

- `projects/hello-sap`：练习用的入门示例
- `scripts/bootstrap.sh`：可选的环境引导脚本（在存在 requirements 的情况下会创建 `.venv` 并安装依赖）
- `docs/README.md`：快速笔记与链接集合

快速上手：

1. 进入 `sap-lab` 目录，运行 `./scripts/bootstrap.sh`（仅需运行一次）以为快速实验准备 Python 虚拟环境。
2. 打开并编辑 `projects/hello-sap/main.py` 进行迭代开发。
3. 将 SAP 特有的依赖保留在此目录内，避免泄露到其他项目中。

如需帮助或发现问题，请在仓库中提交 Issue 或查看 `docs/` 下的说明文档。
