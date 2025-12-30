package com.jtspringproject.JtSpringProject.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtspringproject.JtSpringProject.dao.CategoryDao;
import com.jtspringproject.JtSpringProject.models.Category;
import com.jtspringproject.JtSpringProject.services.CategoryService;

/**
 * 分类服务实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryDao categoryDao;
    
    @Override
    public Category addCategory(String name) {
        return this.categoryDao.addCategory(name);
    }
    
    @Override
    public List<Category> getCategories(){
        return this.categoryDao.getCategories();
    }
    
    @Override
    public Boolean deleteCategory(int id) {
        return this.categoryDao.deletCategory(id);
    }
    
    @Override
    public Category updateCategory(int id, String name) {
        return this.categoryDao.updateCategory(id, name);
    }

    @Override
    public Category getCategory(int id) {
        return this.categoryDao.getCategory(id);
    }
}
