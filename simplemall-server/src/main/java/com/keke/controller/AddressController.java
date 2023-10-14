package com.keke.controller;

import com.keke.domain.Address;
import com.keke.domain.User;
import com.keke.service.AddressService;
import com.keke.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/address", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AjaxResponse insertAddress(@RequestParam Boolean defaultStatus, @RequestParam String name,@RequestParam String tel,@RequestParam String post,@RequestParam String province,@RequestParam String city,@RequestParam String region,@RequestParam String detailAddress,HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Integer ds = (defaultStatus == true)?1:0;
        if(ds == 1){
            addressService.setDefaultStatus(user.getUserId());
        }
        Address address = new Address(ds,name,tel,post,province,city,region,detailAddress);
        address.setUserId(user.getUserId());
        if (addressService.insertAddress(address) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/address", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse selectAddress(HttpServletRequest httpServletRequest,@RequestParam("addressId") Integer addressId){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Address address = addressService.selectAddress(user.getUserId(),addressId);
        System.out.println("address:"+address);
        return AjaxResponse.success(addressService.selectAddress(user.getUserId(),addressId));
    }

    @RequestMapping(value = "/address", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public AjaxResponse deleteAddress(@RequestParam("addressId") Integer addressId){
        if (addressService.deleteAddress(addressId) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

    @RequestMapping(value = "/addresses", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse selectAllAddressesByUserId(HttpServletRequest httpServletRequest){
  //  public AjaxResponse selectAllAddressesByUserId(){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        System.out.println("user:"+user);
        List<Address> addressList = addressService.selectAllAddressesByUserId(user.getUserId());
        List<Map> list = new LinkedList<>();
        for (Address add :
                addressList) {
            String address = add.getProvince()+add.getCity()+add.getRegion()+add.getDetailAddress();
            Boolean isDefault = false;
            if(add.getDefaultStatus()==1) {
                isDefault = true;
            }
            Map<String, Object> map = new HashMap<>();
            map.put("id",add.getAddressId());
            map.put("name",add.getName());
            map.put("tel",add.getTel());
            map.put("address",address);
            map.put("isDefault",isDefault);
            list.add(map);
        }
        System.out.println("list:"+list);
        return AjaxResponse.success(list);
    }

    @RequestMapping(value = "/address", method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
    public AjaxResponse updateAddress(@RequestParam Integer addressId, @RequestParam Boolean defaultStatus, @RequestParam String name,@RequestParam String tel,@RequestParam String post,@RequestParam String province,@RequestParam String city,@RequestParam String region,@RequestParam String detailAddress,HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Integer ds = (defaultStatus == true)?1:0;
        if(ds == 1){
            addressService.setDefaultStatus(user.getUserId());
        }
        Address address = new Address(ds,name,tel,post,province,city,region,detailAddress);
        address.setUserId(user.getUserId());
        address.setAddressId(addressId);
        if (addressService.updateAddress(address) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }

}
