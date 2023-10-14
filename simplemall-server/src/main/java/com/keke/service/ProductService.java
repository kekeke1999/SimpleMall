package com.keke.service;

import com.keke.domain.Product;
import com.keke.mapper.ProductMapper;
import com.keke.mapper.ProductTempMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductTempMapper productTempMapper;

    public int selectZeroProducts(){
        return productMapper.selectZeroProducts();
    }

    public Product selectProductById(Integer productId){
        return productMapper.selectProductById(productId);
    }

    public int getProductNum(Integer userId){
        return productMapper.getProductNum(userId);
    }
    public int deleteProduct(Integer productId){
        return productMapper.deleteProduct(productId);
    }

    public int insertProduct(Product product){
        return productMapper.insertProduct(product);
    }

    public List<Product> selectProductsByUserId(Integer userId){
        return productMapper.selectProductsByUserId(userId);
    }

    public List<Product> searchProduct(String word){
        return productMapper.searchProduct(word);
    }

    public int updateProduct(Product product){
        return productMapper.updateProduct(product);
    }

    public List<Product> selectProductsByCategory(String category){
        return productMapper.selectProductsByCategory(category);
    }

    public List<Product> selectAllProducts(){
        return productMapper.selectAllProducts();
    }

    public List<Product> selectTempProductsByUserId(Integer userId){
        return productTempMapper.selectTempProductsByUserId(userId);
    }

    public List<Product> searchTempProduct(String word){
        return productTempMapper.searchProduct(word);
    }

    public int offShelf(Integer productId){
        Product product = productMapper.selectProductById(productId);
        if(product != null){
            productMapper.deleteProduct(productId);
            productTempMapper.insertIntoProductTemp(product);
            return 1;
        }else{
            return 0;
        }
    }

    public int onShelf(Integer productId){
        Product product = productTempMapper.selectProductById(productId);
        if(product != null){
            productTempMapper.deleteFromProductTemp(productId);
            productMapper.insertProduct(product);
            return 1;
        }else{
            return 0;
        }
    }

    public int deleteFromProductTemp(Integer productId){
        return productTempMapper.deleteFromProductTemp(productId);
    }

}
