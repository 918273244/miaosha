package com.miaosha.common.service;

import com.miaosha.common.domain.MiaoshaOrder;
import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.vo.GoodsVo;

public interface OrderService {

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId);

    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods);

    public OrderInfo getOrderById(long orderId);

    public void deleteOrders();
}
