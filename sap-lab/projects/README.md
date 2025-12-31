# SAP Lab - 项目结构说明

## 目录结构

```
sap-lab/
├── projects/
│   ├── 01-abap-cloud-clean-core/     # ABAP Cloud 和 Clean Core 原则
│   ├── 02-cds-foundation/             # CDS 基础
│   ├── 03-rap-managed-scenario/       # RAP Managed Scenario
│   ├── 04-rap-fiori-elements/         # RAP + Fiori Elements UI
│   ├── 05-rap-unmanaged-custom/       # RAP Unmanaged + 自定义逻辑
│   ├── 06-rap-external-services/      # RAP 消费外部服务
│   ├── 07-cap-model-quick-check/      # CAP 模型快速验证
│   └── hello-sap/                     # 原始示例项目
├── scripts/                            # 启动脚本
│   └── bootstrap.sh
├── docs/                               # 文档
│   └── README.md
├── LEARNING_GUIDE.md                   # 完整学习指南（本文件）
└── README.md                           # 项目说明

```

## 各项目说明

### 📂 01-abap-cloud-clean-core
- **难度**: ⭐ 最低
- **学习时间**: 6 core hours
- **文件**:
  - `README.md` - 项目说明
  - `example.abap` - ABAP 代码示例
  - `.abaplint.json` - 代码检查配置

### 📂 02-cds-foundation
- **难度**: ⭐ 低
- **学习时间**: 6 core hours
- **文件**:
  - `README.md` - 项目说明
  - `ProductMaster.cds` - 主数据 CDS 视图
  - `ProductPricing.cds` - 定价 CDS 视图
  - `NOTES.md` - 学习笔记

### 📂 03-rap-managed-scenario
- **难度**: ⭐ 中等
- **学习时间**: 8-10 core hours
- **文件**:
  - `README.md` - 项目说明
  - `ZProduct.cds` - 产品数据模型
  - `ZProduct_B.cds` - 行为定义
  - `ZProduct_C.abap` - 业务逻辑实现

### 📂 04-rap-fiori-elements
- **难度**: ⭐ 中等
- **学习时间**: 6-8 core hours
- **文件**:
  - `README.md` - 项目说明
  - `ZProductUI.cds` - UI 模型定义
  - `ANNOTATIONS.md` - UI 注解参考

### 📂 05-rap-unmanaged-custom
- **难度**: ⭐ 中高
- **学习时间**: 10-12 core hours
- **文件**:
  - `README.md` - 项目说明
  - `ZOrder_B.cds` - 订单行为定义
  - `ZOrder_C.abap` - 自定义业务逻辑

### 📂 06-rap-external-services
- **难度**: ⭐ 较高
- **学习时间**: 8-10 core hours
- **文件**:
  - `README.md` - 项目说明
  - `ExternalServiceClient.abap` - HTTP 客户端示例
  - `BEST_PRACTICES.md` - 最佳实践

### 📂 07-cap-model-quick-check
- **难度**: ⭐ 中等（可选）
- **学习时间**: 6-8 core hours
- **文件**:
  - `README.md` - 项目说明
  - `db.cds` - 数据模型定义
  - `srv.cds` - 服务定义
  - `VALIDATION_CHECKLIST.md` - 验证检查表

## 🚀 快速开始

### 1. 浏览学习指南
```bash
cat LEARNING_GUIDE.md
```

### 2. 选择学习项目
从 `01-abap-cloud-clean-core` 开始按顺序学习。

### 3. 阅读项目 README
每个项目都有详细的 README.md 说明。

### 4. 查看代码示例
项目中包含实际可运行的代码示例。

### 5. 参考学习资源
使用官方教程链接深入学习。

## 📚 学习资源索引

| 主题 | 链接 | 难度 |
|------|------|------|
| ABAP Cloud | https://developers.sap.com/topics/abap-cloud.html | ⭐ |
| CDS | https://developers.sap.com/tutorials/abap-environment-cds-view.html | ⭐ |
| RAP100 | https://developers.sap.com/mission.sap-fiori-abap-rap100.html | ⭐⭐ |
| HTTP Client | https://developers.sap.com/tutorials/abap-environment-outbound-http-client.html | ⭐⭐⭐ |

## 💬 获取帮助

1. **查看项目 README** - 每个项目都有详细说明
2. **参考官方文档** - 使用提供的链接
3. **查看代码注释** - 示例代码中有详细注释
4. **参考笔记文件** - 许多项目包含学习笔记
5. **SAP 社区论坛** - 搜索常见问题

## 📊 学习进度

建议按照以下时间表学习：

| 周 | 项目 | 预期学习时间 |
|----|------|-----------|
| 第1-2周 | 01-02: ABAP Cloud & CDS | 12-14 小时 |
| 第3-4周 | 03-04: RAP 基础 | 14-18 小时 |
| 第5-6周 | 05-06: RAP 高级 | 18-22 小时 |
| 第7周 | 07: CAP (可选) | 6-8 小时 |

---

**创建日期**: 2025年12月31日  
**最后更新**: 2025年12月31日
