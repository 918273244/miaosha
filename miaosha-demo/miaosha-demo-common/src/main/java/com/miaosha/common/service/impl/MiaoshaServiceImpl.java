package com.miaosha.common.service.impl;

import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.service.GoodsService;
import com.miaosha.common.service.MiaoshaService;
import com.miaosha.common.service.OrderService;
import com.miaosha.common.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaServiceImpl implements MiaoshaService{

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Override
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);

        return orderService.createOrder(user,goods);
    }
}
