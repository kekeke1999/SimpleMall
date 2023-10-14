package com.keke.mapper;

import com.keke.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTempMapper {

    int deleteFromProductTemp(@Param("productId") Integer productId);

    int insertIntoProductTemp(Product product);

    Product selectProductById(Integer productId);

    List<Product> selectTempProductsByUserId(@Param("userId") Integer userId);

    List<Product> searchProduct(@Param("word") String word);

}
