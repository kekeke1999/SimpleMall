package com.keke.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keke.domain.User;
import com.keke.mapper.UserMapper;
import com.keke.utils.AjaxResponse;
import com.keke.utils.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user){
        return userMapper.insertUser(user);
    }

    public String login(String username, String password, HttpServletRequest request) throws JsonProcessingException {
        System.out.println("我在登录！");
        User user = userMapper.selectUser(username, password);
        if(user!=null) {
            String token = TokenUtil.sign(username);
            System.out.println(token);
            request.getSession().setAttribute("user",user);
            System.out.println("我设置了session！！！");
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("token",token);
       //     hs.put("token","bearer "+token);
            ObjectMapper objectMapper=new ObjectMapper();
            return objectMapper.writeValueAsString(hs);
          //  return AjaxResponse.success2(token);
        }else {
         //   return AjaxResponse.fail();
            return null;
        }
    }

    public User selectUserById(Integer userId){
        return userMapper.selectUserById(userId);
    }

    public int updateUserById(User user){
        return userMapper.updateUserById(user);
    }

    public List<User> selectAllCustoms(){
        return userMapper.selectAllCustoms();
    }

}
