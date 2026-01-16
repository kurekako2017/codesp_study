//---------------------------------------------------------------------
// NOTE: 仅作学习示例 - Do NOT use in production
// CDS 视图: ZProductPricing
// 说明: 产品定价视图 - 示例用于教学和编辑器解析
// 功能: 演示多货币价格与折扣计算（示例）
// 提示: 为在编辑器中消除“表不存在”错误，本工作区包含对应的 stub 表 `zproduct_prices`。
//---------------------------------------------------------------------

@AbapCatalog.sqlViewName: 'ZPRODUCTPRICING'
@AbapCatalog.compiler.compareFilter: true
@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product Pricing'

// 学习用示例：底层表请参见 sap-lab/projects/02-cds-foundation/zproduct_prices.cds
define view ZProductPricing
  as select from zproduct_prices as prices
{
  // ===== 复合主键: 同一产品在不同货币下有不同价格 =====
  key prices.product_id,                // 产品 ID (主键部分 1)
  key prices.currency_code,            // 货币代码 如 USD/CNY (主键部分 2)

  // ===== 价格信息 =====
      prices.price,                    // 原始价格（未扣除折扣）
      prices.discount_pct,             // 折扣百分比 (0-100)

  // ===== 计算字段: 净价计算 =====
  // 公式: 净价 = 原始价格 × (1 - 折扣% / 100)
  // 示例: 价格 100 元，折扣 10% -> 净价 = 90 元
      prices.price * (1 - prices.discount_pct / 100) as net_price
}
