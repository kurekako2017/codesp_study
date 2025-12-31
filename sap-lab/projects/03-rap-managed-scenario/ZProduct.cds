@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product'
define root view entity ZProduct
  as select from zproducts
{
  key product_id,
      product_name,
      description,
      category,
      price,
      stock_quantity,
      created_at,
      changed_at,
      created_by,
      changed_by
}
