package com.miaoshao.demo.two.controller;

import com.miaoshao.demo.two.redis.RedisService;
import com.miaoshao.demo.two.result.Result;
import com.miaoshao.demo.two.service.MiaoshaUserService;
import com.miaoshao.demo.two.service.UserService;
import com.miaoshao.demo.two.vo.LoginVo;
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
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //登录
        miaoshaUserService.login(response, loginVo);
        return Result.success(true);
    }




}
