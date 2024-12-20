package com.keke.mapper;

import com.keke.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CategoryMapper {

    int deleteCategory(Integer categoryId);

    int insertCategory(String categoryName);

    List<Category> selectAllCategories();

    int updateCategory(Category category);
}
