package cn.lucky.controller;

import cn.lucky.pojo.User;
import cn.lucky.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/doLogin")
    public String doLogin(@RequestParam("username")String username,@RequestParam("password")String password){
        User user= userService.doLogin(username,password);
        System.out.println(user.getJuId());
        String reslt=null;
        if(user!=null){
            if(user.getJuId()==3){
                reslt="user";
            }else if(user.getJuId()==2){
                reslt="user2";
            }else if(user.getJuId()==1){
                reslt="user1";
            }
        }
        return reslt;
    }

    @RequestMapping(value = "/regest",method = RequestMethod.POST,headers = "application/json")
    @ResponseBody
    public Object regist(@RequestBody User user){
        System.out.println(user.getUsername());
        String result=null;
        if(userService.ins(user)){
            result="×¢²á³É¹¦";
        }else{
            result="×¢²áÊ§°Ü";
        }
        return JSON.toJSONString(result);
    }
}
