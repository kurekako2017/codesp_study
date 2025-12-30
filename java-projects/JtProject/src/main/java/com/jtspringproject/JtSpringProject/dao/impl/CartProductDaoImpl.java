package com.jtspringproject.JtSpringProject.dao.impl;

import com.jtspringproject.JtSpringProject.dao.CartProductDao;
import com.jtspringproject.JtSpringProject.models.CartProduct;
import com.jtspringproject.JtSpringProject.models.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 购物车商品数据访问实现类
 */
@Repository
public class CartProductDaoImpl implements CartProductDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Override
    @Transactional
    public CartProduct addCartProduct(CartProduct cartProduct) {
        this.sessionFactory.getCurrentSession().save(cartProduct);
        return cartProduct;
    }

    @Override
    @Transactional
    public List<CartProduct> getCartProducts() {
        return this.sessionFactory.getCurrentSession().createQuery("from CART_PRODUCT ").list();
    }

    @Override
    @Transactional
    public List<Product> getProductByCartID(Integer cart_id) {
        String sql = "SELECT product_id FROM cart_product WHERE cart_id = :cart_id";
        List<Integer> productIds = this.sessionFactory.getCurrentSession()
                .createNativeQuery(sql)
                .setParameter("cart_id", cart_id)
                .list();

        sql = "SELECT * FROM product WHERE id IN (:product_ids)";
        return this.sessionFactory.getCurrentSession()
                .createNativeQuery(sql, Product.class)
                .setParameterList("product_ids", productIds)
                .list();
    }

    @Override
    @Transactional
    public void updateCartProduct(CartProduct cartProduct) {
        this.sessionFactory.getCurrentSession().update(cartProduct);
    }

    @Override
    @Transactional
    public void deleteCartProduct(CartProduct cartProduct) {
        this.sessionFactory.getCurrentSession().delete(cartProduct);
    }
}
