@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product Master Data'
@Metadata.ignorePropagatedAnnotations: true
define view entity ZProductMaster
  as select from zproducts as products
  association [0..*] to ZProductPricing as _pricing on _pricing.product_id = products.product_id
{
  key products.product_id,
      products.product_name,
      products.description,
      products.category,
      products.created_at,
      products.changed_at,
      cast(
        case
          when products.price > 1000
            then 'Premium'
          when products.price > 500
            then 'Standard'
          else 'Economy'
        end
        as zproduct_category
      ) as price_category,
      _pricing
}
