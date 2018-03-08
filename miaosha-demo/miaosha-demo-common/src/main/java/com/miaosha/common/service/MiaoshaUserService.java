package com.miaosha.common.service;

import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface MiaoshaUserService {

    public MiaoshaUser getById(long id);

    public MiaoshaUser getByToken(HttpServletResponse response, String token);

    public boolean login(HttpServletResponse response, LoginVo loginVo);
}
