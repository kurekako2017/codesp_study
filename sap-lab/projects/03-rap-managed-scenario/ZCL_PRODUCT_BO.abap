*&---------------------------------------------------------------------*
*& ABAP 类: ZCL_PRODUCT_BO
*& 说明: ZProduct 行为实现（Determination / Validation 骨架）
*& 目的: 提供可由 abapGit 或 ADT 导入的类文件，便于在 ABAP 系统中激活并完成业务逻辑实现
*&---------------------------------------------------------------------*

@EndUserText.label: 'ZProduct Behavior Implementation'
CLASS ZCL_PRODUCT_BO DEFINITION INHERITING FROM cl_abap_behavior_handler.

  PUBLIC SECTION.
    CLASS-METHODS class_constructor.

  PRIVATE SECTION.
    " Determination: set_defaults - 在创建时填充默认审计字段
    METHODS set_defaults FOR DETERMINATION Product~set_defaults
      IMPORTING keys FOR Product.

    " Validation: check_price_positive - 验证价格为正
    METHODS check_price_positive FOR VALIDATION Product~check_price_positive
      IMPORTING keys FOR Product.

ENDCLASS.

*---------------------------------------------------------------------*
* 实现
*---------------------------------------------------------------------*
CLASS ZCL_PRODUCT_BO IMPLEMENTATION.

  CLASS-METHOD class_constructor.
    " 可在此处进行类级别初始化（如注册 helper）
  ENDMETHOD.

  METHOD set_defaults.
    " 示例实现：为将要创建的实体设置默认的创建时间/创建人
    MODIFY ENTITIES OF zproduct IN LOCAL MODE
      ENTITY product
        UPDATE SET (
          created_at = sy-datum,
          created_by = sy-uname
        )
        WHERE product_id IN ( SELECT product_id FROM table( keys ) ).
  ENDMETHOD.

  METHOD check_price_positive.
    " 读取要验证的实体，检查 price 字段
    READ ENTITIES OF zproduct IN LOCAL MODE
      ENTITY product
        FIELDS ( price )
        WITH CORRESPONDING #( keys )
      RESULT DATA(lt_read).

    LOOP AT lt_read INTO DATA(ls_product) WHERE price < 0.
      APPEND VALUE #(
        %key = ls_product-%key
        %msg = new_message(
          id = 'ZCM_PRODUCT'
          number = '001'
          severity = if_abap_behv=>mk_severity_error
          v1 = 'Price must be positive'
        )
      ) TO reported-product.
    ENDLOOP.
  ENDMETHOD.

ENDCLASS.
