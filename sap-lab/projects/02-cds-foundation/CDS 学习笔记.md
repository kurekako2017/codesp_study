## CDS 学习笔记

### 基本语法
```sql
define view entity VIEW_NAME 
  as select from source_table {
    -- 字段定义
    key field1,
        field2,
        -- 计算字段
        cast(field3 as type) as calculated_field
  }
```

### 重要注解
- `@AccessControl.authorizationCheck: #CHECK` - 权限检查
- `@EndUserText.label` - UI 显示标签
- `@Metadata.ignorePropagatedAnnotations` - 忽略传播注解
- `@ObjectModel.readOnly: true` - 只读视图
- `@UI.hidden: true` - 隐藏字段

### 关联（Association）
```sql
association [0..*] to TARGET_VIEW as _target 
  on _target.key = source.key
```

### 计算字段
```sql
cast(condition as type) as field_name
```

## CDS 文件运行环境

CDS（Core Data Services）文件是 SAP ABAP Cloud 和 RAP（RESTful Application Programming）框架中的数据模型定义语言。它们本身不直接"运行"，而是被 SAP 系统编译和激活后，作为数据视图提供服务。

### 1. **SAP ABAP Cloud 环境**
- **主要运行环境**：SAP BTP (Business Technology Platform) 的 ABAP Cloud 实例
- **激活方式**：在 SAP Business Application Studio (BAS) 或 Eclipse ADT 中激活 CDS 视图
- **访问方式**：通过 OData 服务或 ABAP 代码查询

### 2. **本地开发环境**
- **SAP Business Application Studio**：云端 IDE，支持 CDS 开发和测试
- **Eclipse ADT**：本地 IDE，需要连接到 SAP 系统
- **Mock Server**：用于本地测试（在 RAP 项目中）

### 3. **生产环境**
- **SAP S/4HANA Cloud** 或 **SAP BTP ABAP Environment**
- CDS 视图被编译成数据库视图和 OData 服务

### 在当前学习项目中的使用
根据你的工作区结构，这是一个 SAP 学习项目（`sap-lab`），CDS 文件（如 `ProductMaster.cds`）是学习材料：
- **不直接运行**：这些是示例代码，用于学习 CDS 语法
- **测试方式**：需要在真实的 SAP ABAP Cloud 环境中激活和测试
- **学习重点**：理解语法、注解和数据建模概念

### 如何"运行" CDS 文件
1. **在 SAP 系统中激活**：
   ```abap
   -- 在 ABAP Development Tools 中右键激活
   -- 或使用 /IWFND/MAINT_SERVICE 发布为 OData 服务
   ```

2. **查询数据**：
   ```abap
   SELECT * FROM ZProductMaster INTO TABLE @lt_products.
   ```

3. **通过 OData 调用**：
   ```
   GET /sap/opu/odata/sap/ZPRODUCTMASTER_SRV/ProductMasterSet
   ```

如果你想在本地测试，需要设置 SAP ABAP Cloud 环境或使用 SAP 提供的试用系统。当前工作区主要用于学习和代码示例。

## S/4 HANA 2019 兼容性

**可以运行**：S/4 HANA 2019 支持 CDS 视图，但有以下限制：

### 支持的功能
- ✅ 基础 CDS 视图定义
- ✅ 关联（Association）
- ✅ 计算字段
- ✅ 基本注解（如 `@EndUserText.label`）

### 不支持或受限的功能
- ❌ ABAP Cloud 特有的注解（如 `@AccessControl.authorizationCheck: #CHECK`）
- ❌ RAP 框架（Managed/Unmanaged Scenario）
- ❌ 某些高级注解（如 `@Metadata.ignorePropagatedAnnotations`）
- ❌ Fiori Elements UI 集成

### 运行方式
在 S/4 HANA 2019 中：
1. 使用 Eclipse ADT 连接到系统
2. 创建 CDS 视图（使用 `define view` 而非 `define view entity`）
3. 激活视图后可通过 SE16N 或 ABAP 代码查询

### 建议
- 对于学习目的，推荐使用 SAP ABAP Cloud 环境
- S/4 HANA 2019 适合传统 CDS 开发，不适合现代 RAP 应用
