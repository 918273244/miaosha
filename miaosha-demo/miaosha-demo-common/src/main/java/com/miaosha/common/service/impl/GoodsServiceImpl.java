package com.miaosha.common.service.impl;

import com.miaosha.common.dao.GoodsDao;
import com.miaosha.common.domain.MiaoshaGoods;
import com.miaosha.common.service.GoodsService;
import com.miaosha.common.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public void reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }


    @Override
    public void resetStocks(List<GoodsVo> goodsVoList) {
        for(GoodsVo goods : goodsVoList ) {
            MiaoshaGoods g = new MiaoshaGoods();
            g.setGoodsId(goods.getId());
            g.setStockCount(goods.getStockCount());
            goodsDao.resetStock(g);
        }
    }
}
