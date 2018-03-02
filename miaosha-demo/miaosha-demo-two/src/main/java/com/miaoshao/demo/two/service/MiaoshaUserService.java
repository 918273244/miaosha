package com.miaoshao.demo.two.service;

import com.miaoshao.demo.two.domain.MiaoshaUser;
import com.miaoshao.demo.two.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface MiaoshaUserService {

    public MiaoshaUser getById(long id);

    public MiaoshaUser getByToken(HttpServletResponse response, String token);

    public boolean login(HttpServletResponse response, LoginVo loginVo);
}
