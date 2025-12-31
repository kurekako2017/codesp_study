@EndUserText.label: 'Order BO Implementation'
CLASS lhc_order DEFINITION INHERITING FROM cl_abap_behavior_handler.

  PUBLIC SECTION.
    CLASS-METHODS class_constructor.

  PRIVATE SECTION.

    METHODS create FOR CREATE Order.
    METHODS read FOR READ Order RESULT RESULT.
    METHODS update FOR UPDATE Order.
    METHODS delete FOR DELETE Order.

    METHODS set_order_number FOR DETERMINATION Order~set_order_number
      IMPORTING keys FOR Order.

    METHODS calculate_total FOR DETERMINATION Order~calculate_total
      IMPORTING keys FOR Order.

    METHODS validate_customer FOR VALIDATION Order~validate_customer
      IMPORTING keys FOR Order.

    METHODS approve FOR ACTION Order~approve
      IMPORTING keys FOR ACTION Order
      RESULT result.

ENDCLASS.

CLASS lhc_order IMPLEMENTATION.

  CLASS-METHOD class_constructor.
  ENDMETHOD.

  METHOD create.
    " 自定义创建逻辑
    INSERT zorders FROM @( VALUE #(
      order_number = cl_uuid_factory=>create_system_uuid( )->create_uuid_x16( )
      ( entity mapping from entry )
    ) ).
  ENDMETHOD.

  METHOD read.
    " 自定义读取逻辑
    SELECT * FROM zorders INTO TABLE @result WHERE order_id IN ( SELECT order_id FROM table( @keys ) ).
  ENDMETHOD.

  METHOD set_order_number.
    MODIFY ENTITIES OF zorder IN LOCAL MODE
      ENTITY order
        UPDATE SET (
          order_number = cl_uuid_factory=>create_system_uuid( )->create_uuid_x16( )
        )
        WHERE order_id IN ( SELECT order_id FROM table( keys ) ).
  ENDMETHOD.

  METHOD calculate_total.
    " 计算订单总额
    READ ENTITIES OF zorder IN LOCAL MODE
      ENTITY order
        FIELDS ( order_id )
        WITH CORRESPONDING #( keys )
      RESULT DATA(lt_orders).

    LOOP AT lt_orders INTO DATA(ls_order).
      SELECT SUM( amount ) AS total_amount
        FROM zorder_items
        WHERE order_id = @ls_order-order_id
        INTO @DATA(ls_total).

      MODIFY ENTITY zorder IN LOCAL MODE
        UPDATE SET (
          total_amount = ls_total-total_amount
        )
        WHERE order_id = @ls_order-order_id.
    ENDLOOP.
  ENDMETHOD.

  METHOD validate_customer.
    READ ENTITIES OF zorder IN LOCAL MODE
      ENTITY order
        FIELDS ( customer_id )
        WITH CORRESPONDING #( keys )
      RESULT DATA(lt_orders).

    LOOP AT lt_orders INTO DATA(ls_order).
      SELECT SINGLE customer_id FROM zcustomers
        WHERE customer_id = @ls_order-customer_id
        INTO @DATA(lv_customer_id).

      IF sy-subrc <> 0.
        APPEND VALUE #(
          %key = ls_order-%key
          %msg = new_message(
            id = 'ZCM_ORDER'
            number = '001'
            severity = if_abap_behv=>mk_severity_error
            v1 = 'Customer not found'
          )
        ) TO reported-order.
      ENDIF.
    ENDLOOP.
  ENDMETHOD.

  METHOD approve.
    MODIFY ENTITIES OF zorder IN LOCAL MODE
      ENTITY order
        UPDATE SET ( order_status = 'APPROVED' )
        WHERE order_id IN ( SELECT order_id FROM table( keys ) ).
  ENDMETHOD.

ENDCLASS.
