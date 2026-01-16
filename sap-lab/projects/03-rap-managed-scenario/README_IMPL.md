# ZProduct 行为实现指南

目的：为 `ZProduct_B.cds` 中声明的 `implementation in class ZCL_PRODUCT_BO unique;` 提供一个可复制的 ABAP 类实现骨架与在 ADT 中导入/激活的步骤说明，方便在 ABAP 系统中快速验证并修复行为的 determination 与 validation。

快速说明

- 本文档包含：
  - `ZCL_PRODUCT_BO` 类骨架（含 `set_defaults` 与 `check_price_positive` 方法）。
  - 在 ADT/abapGit 中导入并激活对象的步骤。 
  - 常见错误与修复建议。

ABAP 类骨架（复制到 ADT 编辑器）

```abap
*&---------------------------------------------------------------------*
*& Class: ZCL_PRODUCT_BO
*& Purpose: RAP behavior implementation for ZProduct (determination/validation)
*&---------------------------------------------------------------------*
CLASS ZCL_PRODUCT_BO DEFINITION
  INHERITING FROM cl_abap_behavior_handler.

  PUBLIC SECTION.
    CLASS-METHODS class_constructor.

  PRIVATE SECTION.
    METHODS set_defaults FOR DETERMINATION Product~set_defaults
      IMPORTING keys FOR Product.

    METHODS check_price_positive FOR VALIDATION Product~check_price_positive
      IMPORTING keys FOR Product.

ENDCLASS.

CLASS ZCL_PRODUCT_BO IMPLEMENTATION.

  CLASS-METHOD class_constructor.
  ENDMETHOD.

  METHOD set_defaults.
    " 示例：将创建日期与创建人写入要创建的实体
    MODIFY ENTITIES OF zproduct IN LOCAL MODE
      ENTITY product
        UPDATE SET (
          created_at = sy-datum,
          created_by = sy-uname
        )
        WHERE product_id IN ( SELECT product_id FROM table( keys ) ).
  ENDMETHOD.

  METHOD check_price_positive.
    DATA(lt_product) = VALUE zproduct_table( ).

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
```

在 ADT 导入与激活（两种常用方式）

1) 使用 abapGit（推荐）
  - 在 ABAP 系统安装 abapGit（如果尚未安装），创建或选择一个 package。
  - 在 abapGit 中创建新的 repo，填写 GitHub 仓库 URL（例如你当前仓库），拉取相关文件到目标 package。
  - 在 Package 浏览器中找到导入的 CDS、Behavior、Class 对象，逐个 Activate（右键 → Activate）。

2) 手动创建（适合小改动）
  - 在 ADT 新建 CDS View / Behavior：File → New → Other → ABAP Repository Object → Data Definition / Behavior Definition。
  - 将 `ZProduct_B.cds` 内容粘贴并 Save/Activate。若提示 implementation class missing，则：
    - File → New → ABAP Class，创建 `ZCL_PRODUCT_BO`，把上面的类骨架粘贴到类实现部分并 Activate。

常见错误与修复建议

- 错误：`implementation class not found` 或 `Implementation class mismatch`
  - 检查 CDS 中的 `implementation in class <name> unique;`，确保 ABAP 中类名一致（包括前缀 ZCL_ 等）。

- 错误：注解语法或分号错误（如 Mismatched ‘;’）
  - 确保注解行（`@EndUserText.label: '...'` 等）不要单独以分号结束，注解应位于对象定义前且按 CDS 语法书写。

- 错误：方法签名不匹配
  - Behavior 中定义的 determination/validation 名称必须和 ABAP 方法上的 `FOR DETERMINATION Product~name` / `FOR VALIDATION Product~name` 一致。

提交与验证

- 将修改后的对象导入并 Activate 后，查看 ADT Problems 视图，按提示逐项修复。

备注

- 本骨架仅供快速验证与学习，生产环境中应加入完整的错误文本、消息类别、国际化处理，以及更优的事务/并发控制。

---
文档由自动化工具生成以便导入到 ADT；如需我将该类直接加入仓库并提交（便于 abapGit 拉取），回复“生成并提交”。
