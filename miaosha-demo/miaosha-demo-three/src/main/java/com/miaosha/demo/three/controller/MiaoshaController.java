package com.miaosha.demo.three.controller;

import com.miaosha.common.domain.MiaoshaOrder;
import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.result.CodeMsg;
import com.miaosha.common.service.GoodsService;
import com.miaosha.common.service.MiaoshaService;
import com.miaosha.common.service.OrderService;
import com.miaosha.common.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String do_miaosha(Model model, MiaoshaUser user,
                             @RequestParam("goodsId")long goodsId){
        if(user == null) {
            return "login";
        }

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null){
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";

    }

}
