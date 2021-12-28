package com.example.oauth2.sso.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
public class MainController {

    @RequestMapping({"/index","/",""})
//    获取登录用户信息
    public String index(Map<String,Object> map){
        Object principal =
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.put("username", principal);
        if(principal != null && principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            map.put("username", userDetails.getUsername());
            System.out.println(userDetails.getUsername());
        }

        return "index";
    }

}
