package com.keke.mapper;

import com.keke.domain.OrderProduct;
import com.keke.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductMapper {

    int selectZeroProducts();

    int updateCount(Integer productId);

    int deleteProduct(@Param("productId") Integer productId);

    int insertProduct(Product product);

    List<Product> selectProductsByUserId(@Param("userId") Integer userId);

    List<Product> searchProduct(@Param("word") String word);

    int updateProduct(Product product);

    List<OrderProduct> orderProducts(Integer orderId);

    List<Product> selectProductsByCategory(String category);

    Product selectProductById(Integer productId);

    List<Product> selectAllProducts();

    int getProductNum(@Param("userId") Integer userId);
}
