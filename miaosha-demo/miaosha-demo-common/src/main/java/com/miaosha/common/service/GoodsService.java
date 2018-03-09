package com.miaosha.common.service;

import com.miaosha.common.vo.GoodsVo;

import java.util.List;

public interface GoodsService {

    public List<GoodsVo> listGoodsVo();

    public GoodsVo getGoodsVoByGoodsId(long goodsId);

    public void reduceStock(GoodsVo goods);

}
