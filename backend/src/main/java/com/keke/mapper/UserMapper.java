package com.keke.mapper;

import com.keke.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int insertUser(User user);

    User selectUser(@Param("username") String username, @Param("password") String password);

    User selectUserById(@Param("userId") Integer userId);

    int updateUserById(User user);

    List<User> selectAllCustoms();

    User login(@Param("username") String username, @Param("password") String password);

    User selectUserByUsername(@Param("username") String username);

}
