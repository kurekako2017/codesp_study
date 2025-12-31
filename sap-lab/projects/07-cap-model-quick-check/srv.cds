using { sap.catalog } from '../db/data-model';

/**
 * 产品服务
 */
service CatalogService {

  type ProductInfo {
    id                  : UUID;
    product_name        : String;
    description         : String;
    category            : String;
    price               : Decimal;
    currency            : String;
    stock_quantity      : Integer;
  }

  // 暴露 Products 实体
  entity Products as projection on catalog.Products;
  entity Prices as projection on catalog.Prices;

  // 暴露 Orders 相关实体
  entity Orders as projection on catalog.Orders;
  entity OrderItems as projection on catalog.OrderItems;

  // 只读视图 - 产品列表
  define view ProductList as
    select from catalog.Products {
      id,
      product_name,
      description,
      category,
      price,
      currency,
      stock_quantity
    };

  // 操作 - 获取产品库存
  function getProductStock(product_id : UUID) returns {
    product_id    : UUID;
    stock_qty     : Integer;
  };

  // 操作 - 创建订单
  action createOrder(
    customer_id   : UUID,
    items         : array of {
      product_id  : UUID;
      quantity    : Integer;
    }
  ) returns Orders;
}
