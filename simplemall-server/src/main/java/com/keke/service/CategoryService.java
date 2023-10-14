package com.keke.service;

import com.keke.domain.Category;
import com.keke.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public int updateCategory(Category category){
        return categoryMapper.updateCategory(category);
    }

    public int deleteCategory(Integer categoryId){
        return categoryMapper.deleteCategory(categoryId);
    }

    public int insertCategory(String categoryName){
        return categoryMapper.insertCategory(categoryName);
    }

    public List<Category> selectAllCategories(){
        return categoryMapper.selectAllCategories();
    }
}
