package com.miaosha.common.service;

import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.vo.GoodsVo;

public interface MiaoshaService {


    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);
}
