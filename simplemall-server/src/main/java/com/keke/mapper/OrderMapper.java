package com.keke.mapper;

import com.keke.domain.Order;
import com.keke.domain.OrderMore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int closeOrder(Integer orderId);

    int deliverOrder(Integer orderId);

    int insertSelective(Order record);

    List<OrderMore> selectUnpayedOrders();

    List<OrderMore> selectUnpayedOrdersByUserId(Integer userId);

    List<OrderMore> selectUndeliveredOrdersByUserId(Integer userId);

    List<OrderMore> selectFinishedOrdersByUserId(Integer userId);

    List<OrderMore> selectUnconfirmedOrdersByUserId(Integer userId);

    List<OrderMore> searchOrder(@Param("word") String word, @Param("status") Integer status);

    List<OrderMore> selectUndeliveredOrders();

    List<OrderMore> selectUnconfirmedOrders();

    List<OrderMore> selectFinishedOrders();

    OrderMore selectOrder(Integer orderId);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
