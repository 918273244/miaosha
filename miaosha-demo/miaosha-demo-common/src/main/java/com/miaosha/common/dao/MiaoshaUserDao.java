package com.miaosha.common.dao;

import com.miaosha.common.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaoshauser where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);



}
