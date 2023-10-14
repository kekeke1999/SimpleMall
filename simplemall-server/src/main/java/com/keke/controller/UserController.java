package com.keke.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keke.domain.User;
import com.keke.service.UserService;
import com.keke.utils.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse allCustoms(){
        List<User> users = userService.selectAllCustoms();
        return AjaxResponse.success(users);
    }

    @RequestMapping("/customsNum")
    public String customsNum(){
        List<User> users = userService.selectAllCustoms();
        return String.valueOf(users.size());
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AjaxResponse insertUser(@RequestBody User user){
       if (userService.insertUser(user) == 1){
           return AjaxResponse.success();
       }else{
           return AjaxResponse.fail();
       }
    }

    @RequestMapping("/username")
    public String getUsername(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        return user.getUsername();
    }

    @RequestMapping(value = "/user/login", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(@RequestHeader Map<String,Object> he, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) throws JsonProcessingException {
        System.out.println(he);
        System.out.println("username:"+username);
        System.out.println("password"+password);
        return userService.login(username,password,request);
    }

    @RequestMapping(value = "/user/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestHeader Map<String,Object> he) throws JsonProcessingException {
        System.out.println(he);
        HashMap<String,Object> hs=new HashMap<>();
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(hs);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public AjaxResponse selectUserById(HttpServletRequest httpServletRequest){
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        User user2 = userService.selectUserById(user.getUserId());
        return AjaxResponse.success(user2);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public AjaxResponse updateUserById(@RequestParam Integer userId, @RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber, @RequestParam String sex, @RequestParam String intro){
        System.out.println("我来了！");
        User user = new User(userId,username,password,phoneNumber,intro,sex);
        System.out.println("user:"+user);
        if(userService.updateUserById(user) == 1){
            return AjaxResponse.success();
        }else{
            return AjaxResponse.fail();
        }
    }
}
