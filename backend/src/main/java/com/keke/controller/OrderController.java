package com.keke.controller;

import com.keke.domain.*;
import com.keke.service.AddressService;
import com.keke.service.OrderService;
import com.keke.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/finishedOrdersDetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse finishedOrdersDetail(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return AjaxResponse.success(orderService.selectFinishedOrdersByUserId(user.getUserId()));
    }

    @RequestMapping(value = "/oneOrderDetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse orderDetail(@RequestParam Integer orderId){
        OrderAll orderAll = orderService.selectOrder(orderId);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",orderAll.getUserId());
        map.put("orderId",orderAll.getOrderId());
        map.put("name",orderAll.getName());
        map.put("tel",orderAll.getTel());
        map.put("province",orderAll.getProvince());
        map.put("city",orderAll.getCity());
        map.put("region",orderAll.getRegion());
        map.put("detailAddress",orderAll.getDetailAddress());
        map.put("products",orderAll.getProducts());
        map.put("createTime",orderAll.getCreateTime());
        map.put("payTime",orderAll.getPayTime());
        map.put("payWay",orderAll.getPayWay());
        map.put("price",orderAll.getPrice());
     //   System.out.println("orderAll"+orderAll);
        System.out.println("map:"+map);
        return AjaxResponse.success(map);
    }

    @RequestMapping(value = "/unconfirmedOrdersDetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse unconfirmedOrdersDetail(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return AjaxResponse.success(orderService.selectUnconfirmedOrdersByUserId(user.getUserId()));
    }

    @RequestMapping(value = "/unpayedOrdersDetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse unpayedOrdersDetail(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return AjaxResponse.success(orderService.selectUnpayedOrdersByUserId(user.getUserId()));
    }

    @RequestMapping(value = "/undeliveredOrdersDetail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse undeliveredOrdersDetail(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return AjaxResponse.success(orderService.selectUndeliveredOrdersByUserId(user.getUserId()));
    }

    @RequestMapping(value = "/orderProductss", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse orderProductss(){
        return AjaxResponse.success(orderService.selectUndeliveredOrdersByUserId(4));
    }

    @RequestMapping("/unpayedOrders")
    public AjaxResponse getUnpayedOrders(){
        List<OrderMore> list = orderService.selectUnpayedOrders();
        List<UnpayedOrder> listq = new LinkedList<>();
        for (OrderMore ordermore :
                list) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(ordermore.getCreateTime());
            System.out.println("date:"+dateString);
            UnpayedOrder unpayedOrder = new UnpayedOrder(ordermore.getOrderId(),ordermore.getUsername(),ordermore.getUserId(),dateString,ordermore.getPrice(),"未支付");
            listq.add(unpayedOrder);
        }
        return AjaxResponse.success(listq);
    }

    @RequestMapping("/undeliveredOrders")
    public AjaxResponse getUndeliveredOrders(){
        List<OrderMore> list = orderService.selectUndeliveredOrders();
        List<UndeliveredOrder> listu = new LinkedList<>();
        for (OrderMore ordermore :
                list){
            System.out.println("orderMore:"+ordermore);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = formatter.format(ordermore.getCreateTime());
            String payTime = formatter.format(ordermore.getPayTime());
            Address address = addressService.selectAddress(ordermore.getUserId(),ordermore.getAddressId());
            String allAddress = address.getName() + "," + address.getTel() + "," + address.getProvince() + "省（市）," + address.getCity() + "市," +
                    address.getRegion()+"区,"+address.getDetailAddress();
            UndeliveredOrder undeliveredOrder = new UndeliveredOrder(ordermore.getOrderId(),ordermore.getUsername(),ordermore.getUserId(),createTime,ordermore.getPrice(),"未发货",allAddress,payTime,ordermore.getPayWay());
            listu.add(undeliveredOrder);
        }
        return AjaxResponse.success(listu);
    }

    @RequestMapping("/undeliveredNum")
    public String getUndeliveredNum(){
        List<OrderMore> list = orderService.selectUndeliveredOrders();
        return String.valueOf(list.size());
    }

    @RequestMapping("/unpayedNum")
    public String getUnpayedNum(){
        List<OrderMore> list = orderService.selectUnpayedOrders();
        return String.valueOf(list.size());
    }

    @RequestMapping("/unconfirmedOrders")
    public AjaxResponse getUnconfirmedOrders(){
        List<OrderMore> list = orderService.selectUnconfirmedOrders();
        List<UndeliveredOrder> listu = new LinkedList<>();
        for (OrderMore ordermore :
                list){
            System.out.println("orderMore:"+ordermore);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = formatter.format(ordermore.getCreateTime());
            String payTime = formatter.format(ordermore.getPayTime());
            Address address = addressService.selectAddress(ordermore.getUserId(),ordermore.getAddressId());
            String allAddress = address.getName() + "," + address.getTel() + "," + address.getProvince() + "省（市）," + address.getCity() + "市," +
                    address.getRegion()+"区,"+address.getDetailAddress();
            UndeliveredOrder undeliveredOrder = new UndeliveredOrder(ordermore.getOrderId(),ordermore.getUsername(),ordermore.getUserId(),createTime,ordermore.getPrice(),"已发货",allAddress,payTime,ordermore.getPayWay());
            listu.add(undeliveredOrder);
        }
        return AjaxResponse.success(listu);
    }

    @RequestMapping("/searchOrder")
    public AjaxResponse searchOrder(@RequestParam("word") String word, @RequestParam("status") Integer status){
        List<OrderMore> list = orderService.searchOrder(word, status);
        List<UndeliveredOrder> listu = new LinkedList<>();
        for (OrderMore ordermore :
                list){
            System.out.println("orderMore:"+ordermore);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = formatter.format(ordermore.getCreateTime());
            String payTime = "";
            String allAddress ="";
            String payWay="";
            if(ordermore.getPayTime()==null){
                payTime = "";
                allAddress ="";
                payWay="";
            }else{
                payTime = formatter.format(ordermore.getPayTime());
                Address address = addressService.selectAddress(ordermore.getUserId(),ordermore.getAddressId());
                allAddress = address.getName() + "," + address.getTel() + "," + address.getProvince() + "省（市）," + address.getCity() + "市," +
                        address.getRegion()+"区,"+address.getDetailAddress();
                payWay = ordermore.getPayWay();
            }
            String status_word = "";
            switch (status){
                case 1:
                    status_word = "未支付";
                case 2:
                    status_word = "未发货";
                case 3:
                    status_word = "已发货";
                case 4:
                    status_word = "已完成";
            }
            UndeliveredOrder undeliveredOrder = new UndeliveredOrder(ordermore.getOrderId(),ordermore.getUsername(),ordermore.getUserId(),createTime,ordermore.getPrice(),status_word,allAddress,payTime,payWay);
            listu.add(undeliveredOrder);
        }
        return AjaxResponse.success(listu);
    }

    @RequestMapping("/finishedOrders")
    public AjaxResponse getFinishedOrders(){
        List<OrderMore> list = orderService.selectFinishedOrders();
        List<UndeliveredOrder> listu = new LinkedList<>();
        for (OrderMore ordermore :
                list){
            System.out.println("orderMore:"+ordermore);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = formatter.format(ordermore.getCreateTime());
            String payTime = formatter.format(ordermore.getPayTime());
            Address address = addressService.selectAddress(ordermore.getUserId(),ordermore.getAddressId());
            String allAddress = address.getName() + "," + address.getTel() + "," + address.getProvince() + "省（市）," + address.getCity() + "市," +
                    address.getRegion()+"区,"+address.getDetailAddress();
            UndeliveredOrder undeliveredOrder = new UndeliveredOrder(ordermore.getOrderId(),ordermore.getUsername(),ordermore.getUserId(),createTime,ordermore.getPrice(),"订单已完成",allAddress,payTime,ordermore.getPayWay());
            listu.add(undeliveredOrder);
        }
        return AjaxResponse.success(listu);
    }

    @RequestMapping("/orderDetail")
    public AjaxResponse getOrderDetail(@RequestParam Integer orderId){
        return AjaxResponse.success(orderService.selectOrderDetail(orderId));
    }

    @RequestMapping(value = "/closeOrder", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public AjaxResponse closeOrder(@RequestParam Integer orderId){
        orderService.closeOrder(orderId);
        return AjaxResponse.success();
    }

    @RequestMapping(value = "/deliverOrder", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public AjaxResponse deliverOrder(@RequestParam Integer orderId){
        orderService.deliverOrder(orderId);
        return AjaxResponse.success();
    }


}
