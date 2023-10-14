package com.keke.mapper;

import com.keke.domain.OrderDetail;
import com.keke.domain.OrderDetailMore;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetailMore> selectOrderDetail(Integer orderId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}
