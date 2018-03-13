package com.miaosha.demo.four.controller;

import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.redis.RedisService;
import com.miaosha.common.result.CodeMsg;
import com.miaosha.common.result.Result;
import com.miaosha.common.service.GoodsService;
import com.miaosha.common.service.MiaoshaUserService;
import com.miaosha.common.service.OrderService;
import com.miaosha.common.vo.GoodsVo;
import com.miaosha.common.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    GoodsService goodsService;


    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user,
                                      @RequestParam("orderId") long orderId){
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo vo = new OrderDetailVo();
        vo.setOrderInfo(order);
        vo.setGoodsVo(goods);
        return Result.success(vo);
    }
}
