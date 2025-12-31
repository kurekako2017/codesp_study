@EndUserText.label: 'Order BO'
define behavior for ZOrder alias Order
{
  // Unmanaged - 手动实现
  use create;
  use read;
  use update;
  use delete;

  // Field control
  field (mandatory) order_number, customer_id, order_date;
  field (read only) order_status, total_amount, created_at, changed_at;

  // Determination
  determination set_order_number on save { create; }
  determination calculate_total on save { create; update; }
  determination set_status on save { create; }

  // Validation
  validation validate_customer on save { create; update; }
  validation validate_line_items on save { create; update; }

  // Action
  action approve result [1] $self;
  action reject_order parameter struct { reason : String; } result [1] $self;

  // Association to line items
  composition [0..*] of ZOrderItem on _items
}
