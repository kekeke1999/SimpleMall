package com.keke.mapper;

import com.keke.domain.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    int deleteAddress(Integer addressId);

    int insertAddress(Address address);

    List<Address> selectAddressesByUserId(Integer userId);

    int updateAddress(Address address);

    Address selectAddress(@Param("userId") Integer userId, @Param("addressId") Integer addressId);

    int setDefaultStatus(@Param("userId") Integer userId);

}
