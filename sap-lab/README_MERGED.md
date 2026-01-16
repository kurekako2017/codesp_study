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

---

## 学习示例说明

本目录包含若干用于教学和练习的 CDS 示例文件（存放于 `projects/` 子目录下）。为了方便在本地编辑器中查看和调试，我们在 `projects/02-cds-foundation/` 中包含了用于编辑器解析的 stub 表定义：

- `sap-lab/projects/02-cds-foundation/zproducts.cds` — 学习用的产品表桩（stub）
- `sap-lab/projects/02-cds-foundation/zproduct_prices.cds` — 学习用的产品定价表桩（stub）

注意：这些 stub 仅用于本地编辑器的语法检查与示例展示，不应直接用于生产环境。要在真实 SAP 系统中测试或激活 CDS 视图，请在目标系统中创建对应的真实表或将 CDS 指向存在的表，并在 ABAP 开发工具中激活视图。

快速操作提示：

1. 在 SAP BTP / S/4HANA 上创建真实表并将 CDS `from` 子句指向真实表。
2. 在 Eclipse ADT 或 SAP Business Application Studio 中右键 CDS 文件 -> `Activate`。
3. 使用 ABAP 单元或 OData 服务验证结果。

---

> 备注：已将学习说明也存为 `sap-lab/LEARNING_NOTES.md`，如果你希望将说明直接合并到 `README.md`，我可以继续替换原文件或在其顶部添加链接。