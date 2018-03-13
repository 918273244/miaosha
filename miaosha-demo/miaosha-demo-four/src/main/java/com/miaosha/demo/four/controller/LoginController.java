package com.miaosha.demo.four.controller;


import com.miaosha.common.redis.RedisService;
import com.miaosha.common.result.Result;
import com.miaosha.common.service.MiaoshaUserService;
import com.miaosha.common.service.UserService;
import com.miaosha.common.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_Login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_Login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //登录
        String token =  miaoshaUserService.login(response, loginVo);
        return Result.success(token);
    }




}
