// Stub table for learning — zproducts
// 用于在编辑器中解析引用，保持为学习用例，不会在生产中使用

@AbapCatalog.sqlViewName: 'ZPRODUCTS'
@AbapCatalog.compiler.compareFilter: true
@EndUserText.label: 'Stub ZPRODUCTS'

define table zproducts {
  key product_id    : abap.char(10);
      product_name  : abap.char(100);
      description   : abap.char(255);
      category      : abap.char(50);
      price         : abap.dec(16,2);
      created_at    : abap.dats;
      changed_at    : abap.tims;
}