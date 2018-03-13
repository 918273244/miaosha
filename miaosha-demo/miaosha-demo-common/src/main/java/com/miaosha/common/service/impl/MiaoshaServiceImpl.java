package com.miaosha.common.service.impl;

import com.miaosha.common.domain.MiaoshaOrder;
import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.key.MiaoshaKey;
import com.miaosha.common.redis.RedisService;
import com.miaosha.common.service.GoodsService;
import com.miaosha.common.service.MiaoshaService;
import com.miaosha.common.service.OrderService;
import com.miaosha.common.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MiaoshaServiceImpl implements MiaoshaService{

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    @Override
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存 下订单 写入秒杀订单
        goodsService.reduceStock(goods);

        return orderService.createOrder(user,goods);
    }

    @Override
    public void reset(List<GoodsVo> goodsVoList) {
        goodsService.resetStocks(goodsVoList);
        orderService.deleteOrders();
    }

    @Override
    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        if(order != null) {//秒杀成功
            return order.getOrderId();
        }else{
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(MiaoshaKey.isGoodsOver, ""+goodsId);
    }
}
