package com.miaoshao.demo.two.dao;

import com.miaoshao.demo.two.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaoshauser where id = #{id}")
    public MiaoshaUser getById(@Param("id")long id);



}
