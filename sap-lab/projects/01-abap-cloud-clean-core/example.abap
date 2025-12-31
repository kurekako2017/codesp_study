*&---------------------------------------------------------------------*
*& Report ZEXAMPLE_CLEAN_CORE
*&---------------------------------------------------------------------*
*& ABAP Cloud 示例程序 - 遵循 Clean Core 原则
*&---------------------------------------------------------------------*

REPORT zexample_clean_core.

*----------------------------------------------------------------------*
* 数据定义
*----------------------------------------------------------------------*
DATA: lt_products TYPE TABLE OF zproduct_t,
      ls_product  TYPE zproduct_t.

*----------------------------------------------------------------------*
* 业务逻辑 - 分离关注点
*----------------------------------------------------------------------*
CLASS lcl_product_service DEFINITION.
  PUBLIC SECTION.
    METHODS get_products
      RETURNING VALUE(rt_products) TYPE TABLE OF zproduct_t.
    METHODS create_product
      IMPORTING is_product TYPE zproduct_t
      RETURNING VALUE(rv_success) TYPE abap_bool.
ENDCLASS.

CLASS lcl_product_service IMPLEMENTATION.
  METHOD get_products.
    " 获取产品列表业务逻辑
    SELECT * FROM zproducts INTO TABLE rt_products.
  ENDMETHOD.

  METHOD create_product.
    " 创建产品业务逻辑
    INSERT zproducts FROM is_product.
    IF sy-subrc = 0.
      rv_success = abap_true.
    ELSE.
      rv_success = abap_false.
    ENDIF.
  ENDMETHOD.
ENDCLASS.

*----------------------------------------------------------------------*
* 主程序
*----------------------------------------------------------------------*
START-OF-SELECTION.
  DATA(lo_service) = NEW lcl_product_service( ).
  lt_products = lo_service->get_products( ).
  
  WRITE: / 'Product Count:', lines( lt_products ).
