@AccessControl.authorizationCheck: #CHECK
@EndUserText.label: 'Product List'
@ObjectModel.usageType.serviceQuality: #HIGH
@ObjectModel.usageType.sizeCategory: #M
@Search.searchable: true

@UI.headerInfo: { 
  typeName: 'Product', 
  typeNamePlural: 'Products' 
}
@UI.presentationVariant: [{ 
  sortOrder: [{ by: 'product_name', direction: #ASC }] 
}]

define root view entity ZProductUI
  as select from ZProduct
{
  @UI.facet: [
    { id: 'ProductInfo', purpose: #STANDARD, label: 'Product Information', position: 10 },
    { id: 'Pricing', purpose: #STANDARD, label: 'Pricing', position: 20 }
  ]
  @UI.lineItem: [ { position: 10 } ]
  @UI.identification: [ { position: 10 } ]
  key product_id,

  @UI.lineItem: [ { position: 20 } ]
  @UI.selectionField: [ { position: 10 } ]
  @UI.identification: [ { position: 20 } ]
  @Search.defaultSearchElement: true
  product_name,

  @UI.lineItem: [ { position: 30 } ]
  @UI.identification: [ { position: 30 } ]
  description,

  @UI.lineItem: [ { position: 40 } ]
  @UI.selectionField: [ { position: 20 } ]
  @UI.identification: [ { position: 40 } ]
  category,

  @UI.lineItem: [ { position: 50 } ]
  @UI.identification: [ { position: 50 }, { facetId: 'Pricing', position: 10 } ]
  price,

  @UI.lineItem: [ { position: 60 } ]
  @UI.identification: [ { position: 60 } ]
  stock_quantity,

  created_at,
  changed_at,
  created_by,
  changed_by
}
