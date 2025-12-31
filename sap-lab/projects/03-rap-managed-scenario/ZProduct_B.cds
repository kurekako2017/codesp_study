@EndUserText.label: 'Product BO'
define behavior for ZProduct alias Product
{
  // Create, Read, Update, Delete
  create;
  read;
  update;
  delete;

  // Field validations
  field (mandatory) product_name;
  field (mandatory) category;
  field (mandatory) price;
  field (read only) created_at, created_by, changed_at, changed_by;

  // Determination
  determination set_defaults on save { create; }

  // Validation
  validation check_price_positive on save { create; update; }
}
