package com.miaosha.common.service;

import com.miaosha.common.domain.MiaoshaUser;
import com.miaosha.common.domain.OrderInfo;
import com.miaosha.common.vo.GoodsVo;

import java.awt.image.BufferedImage;
import java.util.List;

public interface MiaoshaService {


    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods);

    public void reset(List<GoodsVo> goodsVoList);

    public long getMiaoshaResult(Long userId, long goodsId);

    public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId);

    public boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode);

    public String createMiaoshaPath(MiaoshaUser user, long goodsId);

    public boolean checkPath(MiaoshaUser user, long goodsId, String path);
}
