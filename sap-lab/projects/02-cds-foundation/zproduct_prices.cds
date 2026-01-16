// Stub table for learning — zproduct_prices
// 用于在编辑器中解析引用，保持为学习用例，不会在生产中使用

@AbapCatalog.sqlViewName: 'ZPRODUCTPRICES'
@AbapCatalog.compiler.compareFilter: true
@EndUserText.label: 'Stub ZPRODUCTPRICES'

define table zproduct_prices {
  key product_id    : abap.char(10);
  key currency_code : abap.char(3);
      price         : abap.dec(16,2);
      discount_pct  : abap.dec(5,2);
}