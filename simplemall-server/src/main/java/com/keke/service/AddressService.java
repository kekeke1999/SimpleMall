package com.keke.service;

import com.keke.domain.Address;
import com.keke.mapper.AddressMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public int deleteAddress(Integer addressId){
        return addressMapper.deleteAddress(addressId);
    }

    public int insertAddress(Address address){
        return addressMapper.insertAddress(address);
    }

    public List<Address> selectAllAddressesByUserId(Integer userId){
        return addressMapper.selectAddressesByUserId(userId);
    }

    public int updateAddress(Address address){
        return addressMapper.updateAddress(address);
    }

    public Address selectAddress( Integer userId, Integer addressId){
        return addressMapper.selectAddress(userId, addressId);
    }

    public int setDefaultStatus(Integer userId){
        return addressMapper.setDefaultStatus(userId);
    }
}
