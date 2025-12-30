package com.jtspringproject.JtSpringProject.services.impl;

import com.jtspringproject.JtSpringProject.dao.CartDao;
import com.jtspringproject.JtSpringProject.models.Cart;
import com.jtspringproject.JtSpringProject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车服务实现类
 */
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartDao cartDao;

    @Override
    public Cart addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    @Override
    public List<Cart> getCarts() {
        return this.cartDao.getCarts();
    }

    @Override
    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    @Override
    public void deleteCart(Cart cart) {
        cartDao.deleteCart(cart);
    }
}
