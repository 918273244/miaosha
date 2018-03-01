package com.basis.web.dao;


import com.basis.web.dao.base.BaseMapper;
import com.basis.web.model.WhiteIP;
import com.basis.web.page.WhiteIpPage;

import java.util.List;

public interface WhiteIpMapper extends BaseMapper<WhiteIP, String> {


//    int insert(WhiteIpPage whiteIp);

    int deleteByName(String username);

    /**
     * 获取所有数据
     */
    List<WhiteIP> findAll();

    /**
     * 分页查询
     */
    List<WhiteIP> findByPage(WhiteIpPage whiteIp);

//    WhiteIp selectByName(String username);

}
