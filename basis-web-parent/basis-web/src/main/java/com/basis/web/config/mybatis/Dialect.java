package com.basis.web.config.mybatis;

public interface Dialect {

    /**
     * 是否支持limit语法
     * @return
     */
    public boolean supportsLimit();

    /**
     * 是否支持limit 偏移
     * @return
     */
    public boolean supportsLimitOffset();

    /**
     * 拼接 分页语句 到目标sql
     * @param sql 目标sql
     * @param offsetPlaceholder 偏移起始位置
     * @param limitPlaceholder  偏移量
     * @return
     */
    public String getLimitString(String sql, int offsetPlaceholder, int limitPlaceholder, String orderBy, String asc);
}
