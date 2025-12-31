@EndUserText.label: 'Product BO Implementation'
CLASS lhc_product DEFINITION INHERITING FROM cl_abap_behavior_handler.

  PUBLIC SECTION.

    CLASS-METHODS class_constructor.

  PRIVATE SECTION.

    METHODS set_defaults FOR DETERMINATION Product~set_defaults
      IMPORTING keys FOR Product.

    METHODS check_price_positive FOR VALIDATION Product~check_price_positive
      IMPORTING keys FOR Product.

ENDCLASS.

CLASS lhc_product IMPLEMENTATION.

  CLASS-METHOD class_constructor.
  ENDMETHOD.

  METHOD set_defaults.
    MODIFY ENTITIES OF zproduct IN LOCAL MODE
      ENTITY product
        UPDATE SET (
          created_at = sy-datum,
          created_by = sy-uname
        )
        WHERE product_id IN ( SELECT product_id FROM table( keys ) ).
  ENDMETHOD.

  METHOD check_price_positive.
    READ ENTITIES OF zproduct IN LOCAL MODE
      ENTITY product
        FIELDS ( price )
        WITH CORRESPONDING #( keys )
      RESULT DATA(lt_product).

    LOOP AT lt_product INTO DATA(ls_product)
      WHERE price < 0.

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
