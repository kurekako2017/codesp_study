namespace sap.catalog;

using { 
  cuid,
  managed 
} from '@sap/cds/common';

/**
 * Product 实体
 */
entity Products : cuid, managed {
  product_name        : String(255) not null;
  description         : String(1000);
  category            : String(100) not null;
  price               : Decimal(15, 2) not null;
  currency            : String(3) default 'USD';
  stock_quantity      : Integer default 0;
  is_active           : Boolean default true;
  
  compositions composition [0..*] of Prices on prices.product
}

/**
 * 价格信息
 */
entity Prices : cuid, managed {
  product             : Association to Products;
  currency            : String(3);
  amount              : Decimal(15, 2) not null;
  discount_percent    : Decimal(5, 2) default 0;
  effective_from      : Date;
  effective_to        : Date;
}

/**
 * 客户信息
 */
entity Customers : cuid, managed {
  customer_name       : String(255) not null;
  email               : String(255);
  phone               : String(20);
  address             : String(500);
  city                : String(100);
  country             : String(100);
  orders              : composition [0..*] of Orders on orders.customer
}

/**
 * 订单
 */
entity Orders : cuid, managed {
  customer            : Association to Customers;
  order_number        : String(20) unique not null;
  order_date          : Date not null;
  total_amount        : Decimal(15, 2);
  status              : String(20) default 'PENDING';
  items               : composition [0..*] of OrderItems on items.order
}

/**
 * 订单项
 */
entity OrderItems : cuid, managed {
  order               : Association to Orders;
  product             : Association to Products;
  quantity            : Integer not null;
  unit_price          : Decimal(15, 2) not null;
  line_amount         : Decimal(15, 2) not null;
}
