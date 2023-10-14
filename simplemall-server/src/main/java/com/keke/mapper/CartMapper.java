package com.keke.mapper;

import com.keke.domain.Cart;
import com.keke.domain.CartKey;

public interface CartMapper {
    int deleteByPrimaryKey(CartKey key);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(CartKey key);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}