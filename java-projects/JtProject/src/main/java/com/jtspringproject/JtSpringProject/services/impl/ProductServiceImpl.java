package com.jtspringproject.JtSpringProject.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.ProductDao;
import com.jtspringproject.JtSpringProject.models.Product;
import com.jtspringproject.JtSpringProject.services.ProductService;

/**
 * 商品服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDao productDao;
    
    @Override
    public List<Product> getProducts(){
        return this.productDao.getProducts();
    }
    
    @Override
    public Product addProduct(Product product) {
        return this.productDao.addProduct(product);
    }
    
    @Override
    public Product getProduct(int id) {
        return this.productDao.getProduct(id);
    }

    @Override
    public Product updateProduct(int id, Product product){
        product.setId(id);
        return this.productDao.updateProduct(product);
    }
    
    @Override
    public boolean deleteProduct(int id) {
        return this.productDao.deletProduct(id);
    }
}
