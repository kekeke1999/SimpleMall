package com.keke.service;

import com.keke.domain.*;
import com.keke.mapper.AddressMapper;
import com.keke.mapper.OrderDetailMapper;
import com.keke.mapper.OrderMapper;
import com.keke.mapper.ProductMapper;
import org.apache.ibatis.annotations.Param;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private AddressMapper addressMapper;


    @Autowired
    private ProductMapper productMapper;

    public List<OrderAll> selectUndeliveredOrdersByUserId(Integer userId){
        List<OrderMore> list = orderMapper.selectUndeliveredOrdersByUserId(userId);
        System.out.println("list:"+list);
        List<OrderAll> order = new LinkedList<>();
        for (OrderMore o :
                list) {
            List<OrderProduct> orderProducts = productMapper.orderProducts(o.getOrderId());
            System.out.println("orderProducts:"+orderProducts);
            Address address = addressMapper.selectAddress(userId, o.getAddressId());
            OrderAll oa = new OrderAll(o.getUserId(),o.getOrderId(),address.getName(),address.getTel(),address.getProvince(),address.getCity(),address.getRegion(),address.getDetailAddress(),orderProducts,o.getCreateTime(),o.getPayTime(),o.getPayWay(),o.getPrice());
            order.add(oa);
        }
        System.out.println("order:"+order);
        return order;
    }

    public OrderAll selectOrder(Integer orderId){
        OrderMore o = orderMapper.selectOrder(orderId);
        System.out.println("orderMore:"+o);
        List<OrderProduct> orderProducts = productMapper.orderProducts(orderId);
        System.out.println("orderProducts:"+orderProducts);
        Address address = addressMapper.selectAddress(o.getUserId(), o.getAddressId());
        OrderAll oa = new OrderAll(o.getUserId(),o.getOrderId(),address.getName(),address.getTel(),address.getProvince(),address.getCity(),address.getRegion(),address.getDetailAddress(),orderProducts,o.getCreateTime(),o.getPayTime(),o.getPayWay(),o.getPrice());
        System.out.print("order:"+oa);
        return oa;
    }

    public List<OrderAll> selectUnconfirmedOrdersByUserId(Integer userId){
        List<OrderMore> list = orderMapper.selectUnconfirmedOrdersByUserId(userId);
        System.out.println("list:"+list);
        List<OrderAll> order = new LinkedList<>();
        for (OrderMore o :
                list) {
            List<OrderProduct> orderProducts = productMapper.orderProducts(o.getOrderId());
            System.out.println("orderProducts:"+orderProducts);
            Address address = addressMapper.selectAddress(userId, o.getAddressId());
            OrderAll oa = new OrderAll(o.getUserId(),o.getOrderId(),address.getName(),address.getTel(),address.getProvince(),address.getCity(),address.getRegion(),address.getDetailAddress(),orderProducts,o.getCreateTime(),o.getPayTime(),o.getPayWay(),o.getPrice());
            order.add(oa);
        }
        System.out.println("order:"+order);
        return order;
    }

    public List<OrderAll> selectUnpayedOrdersByUserId(Integer userId){
        List<OrderMore> list = orderMapper.selectUnpayedOrdersByUserId(userId);
        System.out.println("list:"+list);
        List<OrderAll> order = new LinkedList<>();
        for (OrderMore o :
                list) {
            List<OrderProduct> orderProducts = productMapper.orderProducts(o.getOrderId());
            System.out.println("orderProducts:"+orderProducts);
            Address address = addressMapper.selectAddress(userId, o.getAddressId());
            OrderAll oa = new OrderAll(o.getUserId(),o.getOrderId(),address.getName(),address.getTel(),address.getProvince(),address.getCity(),address.getRegion(),address.getDetailAddress(),orderProducts,o.getCreateTime(),o.getPayTime(),o.getPayWay(),o.getPrice());
            order.add(oa);
        }
        System.out.println("order:"+order);
        return order;
    }

    public List<OrderAll> selectFinishedOrdersByUserId(Integer userId){
        List<OrderMore> list = orderMapper.selectFinishedOrdersByUserId(userId);
        System.out.println("list:"+list);
        List<OrderAll> order = new LinkedList<>();
        for (OrderMore o :
                list) {
            List<OrderProduct> orderProducts = productMapper.orderProducts(o.getOrderId());
            System.out.println("orderProducts:"+orderProducts);
            Address address = addressMapper.selectAddress(userId, o.getAddressId());
            OrderAll oa = new OrderAll(o.getUserId(),o.getOrderId(),address.getName(),address.getTel(),address.getProvince(),address.getCity(),address.getRegion(),address.getDetailAddress(),orderProducts,o.getCreateTime(),o.getPayTime(),o.getPayWay(),o.getPrice());
            order.add(oa);
        }
        System.out.println("order:"+order);
        return order;
    }

    public List<OrderMore> selectUnpayedOrders(){
        return orderMapper.selectUnpayedOrders();
    }

    public List<OrderMore> selectUndeliveredOrders(){
        return orderMapper.selectUndeliveredOrders();
    }

    public List<OrderMore> searchOrder(String word, Integer status){
        return orderMapper.searchOrder(word, status);
    }

    public List<OrderMore> selectUnconfirmedOrders(){
        return orderMapper.selectUnconfirmedOrders();
    }

    public List<OrderMore> selectFinishedOrders(){
        return orderMapper.selectFinishedOrders();
    }

    public List<OrderDetailMore> selectOrderDetail(Integer orderId){
        return orderDetailMapper.selectOrderDetail(orderId);
    }


    public int deliverOrder(Integer orderId){
        return orderMapper.deliverOrder(orderId);
    }

    public int closeOrder(Integer orderId){
        List<OrderDetailMore> list = this.selectOrderDetail(orderId);
        for (OrderDetailMore d :
                list) {
            System.out.println("productId:"+d.getProductId());
            productMapper.updateCount(d.getProductId());
        }
        return orderMapper.closeOrder(orderId);
    }

}
