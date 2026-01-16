//---------------------------------------------------------------------
// NOTE: 仅作学习示例 - Do NOT use in production
// CDS 视图: ZProductMaster
// 说明: 产品主数据视图 - 示例用于教学和编辑器解析
// 功能: 展示产品字段与价格等级计算（示例）
// 提示: 为在编辑器中消除“表不存在”错误，本工作区包含对应的 stub 表 `zproducts`。
//---------------------------------------------------------------------

@AbapCatalog.sqlViewName: 'ZPRODUCTMASTER'
@AbapCatalog.compiler.compareFilter: true
@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product Master Data'

// 学习用示例：底层表请参见 sap-lab/projects/02-cds-foundation/zproducts.cds
define view ZProductMaster
  as select from zproducts as products
{
  // ===== 主键字段 =====
  key products.product_id,

  // ===== 基本信息 =====
      products.product_name,
      products.description,
      products.category,

  // ===== 审计字段 =====
      products.created_at,
      products.changed_at,

  // ===== 计算字段: 价格等级 =====
      case
        when products.price > 1000 then 'Premium'
        when products.price > 500 then 'Standard'
        else 'Economy'
      end as price_category
}