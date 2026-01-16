# 学习示例说明

本目录包含若干用于教学和练习的 CDS 示例文件（存放于 `projects/` 子目录下）。为了方便在本地编辑器中查看和调试，我们在 `projects/02-cds-foundation/` 中包含了用于编辑器解析的 stub 表定义：

- `sap-lab/projects/02-cds-foundation/zproducts.cds` — 学习用的产品表桩（stub）
- `sap-lab/projects/02-cds-foundation/zproduct_prices.cds` — 学习用的产品定价表桩（stub）

注意：这些 stub 仅用于本地编辑器的语法检查与示例展示，不应直接用于生产环境。要在真实 SAP 系统中测试或激活 CDS 视图，请在目标系统中创建对应的真实表或将 CDS 指向存在的表，并在 ABAP 开发工具中激活视图。

快速操作提示：

1. 在 SAP BTP / S/4HANA 上创建真实表并将 CDS `from` 子句指向真实表。
2. 在 Eclipse ADT 或 SAP Business Application Studio 中右键 CDS 文件 -> `Activate`。
3. 使用 ABAP 单元或 OData 服务验证结果。

文件位置：`sap-lab/LEARNING_NOTES.md`