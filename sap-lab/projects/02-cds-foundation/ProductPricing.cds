@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product Pricing'
define view entity ZProductPricing
  as select from zproduct_prices as prices
{
  key prices.product_id,
  key prices.currency_code,
      prices.price,
      prices.discount_pct,
      cast(
        prices.price * ( 1 - prices.discount_pct / 100 )
        as decimal(16,2)
      ) as net_price
}
