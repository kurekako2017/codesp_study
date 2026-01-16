//---------------------------------------------------------------------
// CDS 视图: ZSimpleProduct
// 说明: 简单的产品视图示例 - 演示 CDS 基本语法
// 功能: 展示 CDS 视图的结构和注解
// 注意: 这是一个语法示例，用于学习 CDS 结构
//---------------------------------------------------------------------

// ===== CDS 语法示例 =====
// 这是 CDS 视图的基本结构示例
// 在实际 SAP 环境中，需要：
// 1. 连接到真实的数据库表
// 2. 使用正确的语法：define view Name as select from table

// 示例注解：
// @AbapCatalog.sqlViewName: 'ZSIMPLEPRODUCT' - 指定生成的 SQL 视图名称
// @AccessControl.authorizationCheck: #CHECK - 启用权限检查
// @EndUserText.label: 'Simple Product Example' - UI 显示标签

// 示例字段定义语法：
// key field_name,           // 主键字段
//     field_name,           // 普通字段
//     expression as alias   // 计算字段

// 示例计算字段：
// case when condition then 'value1' else 'value2' end as field_name
// field1 * 0.9 as discount_price

// ===== 完整 CDS 视图示例 =====
/*
// 实际可用的 CDS 视图语法：
@AbapCatalog.sqlViewName: 'ZSIMPLEPRODUCT'
@AbapCatalog.compiler.compareFilter: true
@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Simple Product Example'

define view ZSimpleProduct
  as select from zproducts as products
{
  key products.product_id,
      products.product_name,
      products.category,
      products.price,
      products.created_at,

      // 计算字段示例
      case
        when products.price > 1000 then 'Premium'
        when products.price > 100 then 'Standard'
        else 'Economy'
      end as price_category,

      products.price * 0.9 as discount_price
}
*/

// ===== 使用说明 =====
// 1. 这个文件展示了 CDS 的基本语法结构
// 2. 包含了常用的注解
// 3. 演示了注释的使用方法
// 4. 在实际项目中，需要替换为真实的表和字段
// 5. 注释中的代码是正确的 CDS 语法示例