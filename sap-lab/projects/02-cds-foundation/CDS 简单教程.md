# CDS 简单教程

## 什么是 CDS？

Core Data Services (CDS) 是 SAP 的数据建模语言，用于定义数据视图、实体和关联。它是 SAP ABAP Cloud 和 RAP 框架的基础。

## 运行环境

CDS 文件可以在以下环境中运行：

### 1. SAP BTP Trial Account (推荐)
- 免费试用环境
- 支持完整的 ABAP Cloud 功能
- 访问地址：https://account.hanatrial.ondemand.com

### 2. SAP HANA Express Edition (Docker)
- 本地 Docker 环境
- 需要安装 SAP HANA 和 ABAP Development Tools
- 适合开发测试

### 3. SAP Business Application Studio
- 云端 IDE
- 直接连接到 SAP BTP

## 简单 CDS 视图示例

### 1. 创建基础 CDS 视图

```cds
// 定义一个简单的产品视图
@AbapCatalog.sqlViewName: 'ZSIMPLE_PRODUCT'
@AbapCatalog.compiler.compareFilter: true
@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Simple Product View'
define view ZSimpleProduct
  as select from zproducts
{
  key product_id,
      product_name,
      price,
      category
}
```

### 2. 添加计算字段

```cds
@AbapCatalog.sqlViewName: 'ZPRODUCT_CALC'
@AbapCatalog.compiler.compareFilter: true
@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product with Calculation'
define view ZProductWithCalc
  as select from zproducts
{
  key product_id,
      product_name,
      price,
      // 计算折扣价格 (9折)
      price * 0.9 as discount_price,
      // 价格等级
      case
        when price > 1000 then 'Premium'
        when price > 500 then 'Standard'
        else 'Economy'
      end as price_category
}
```

### 3. 创建关联视图

```cds
@AbapCatalog.sqlViewName: 'ZPRODUCT_ASSOC'
@AbapCatalog.compiler.compareFilter: true
@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product with Association'
define view ZProductWithAssoc
  as select from zproducts as p
  association [0..*] to ZProductPricing as _pricing
    on _pricing.product_id = p.product_id
{
  key p.product_id,
      p.product_name,
      p.price,
      _pricing  // 关联到定价表
}
```

## 实践步骤

### 在 SAP BTP Trial 中创建 CDS 视图

1. **登录 SAP BTP Trial**
   - 访问 https://account.hanatrial.ondemand.com
   - 创建或使用现有 subaccount

2. **创建 ABAP Cloud 实例**
   - 在 SAP BTP Cockpit 中创建 "ABAP Environment" 实例
   - 等待实例创建完成

3. **打开 Business Application Studio**
   - 在实例中点击 "SAP Business Application Studio"
   - 创建新项目或打开现有项目

4. **创建 CDS 文件**
   - 在项目中创建 `src/` 文件夹
   - 创建 `.cds` 文件，输入上面的代码

5. **激活 CDS 视图**
   - 右键 CDS 文件 → "Activate"
   - 系统会自动创建数据库视图

6. **测试视图**
   - 使用 ABAP 代码查询：
   ```abap
   SELECT * FROM ZSimpleProduct INTO TABLE @DATA(lt_products).
   ```

   - 或通过 OData 服务访问

## 常用注解

- `@AbapCatalog.sqlViewName`: 指定 SQL 视图名称
- `@AccessControl.authorizationCheck`: 启用权限检查
- `@EndUserText.label`: UI 显示标签
- `@ObjectModel.readOnly`: 只读视图

## 学习资源

- [SAP CDS 官方文档](https://help.sap.com/docs/abap-cloud/abap-core-data-services/)
- [SAP 开发者教程](https://developers.sap.com/tutorials/abap-environment-cds-view.html)
- [ABAP Cloud 学习路径](https://learning.sap.com/learning-journey/developing-with-sap-abap-cloud)

## 注意事项

- CDS 视图名必须以 `Z` 或 `Y` 开头（客户命名空间）
- 激活前确保底层表存在
- 在试用环境中，某些高级功能可能受限