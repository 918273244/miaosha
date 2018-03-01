package com.basis.web.config.mybatis;

public class MySQLDialect implements Dialect{
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public boolean supportsLimitOffset() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offsetPlaceholder, int limitPlaceholder, String orderBy, String asc) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(sql);
        if(orderBy != null && !"".equals(orderBy)){
            sqlBuffer.append(" order by ").append(orderBy);
        }
        if(orderBy != null && !"".equals(asc)){
            sqlBuffer.append(" ").append(asc);
        }
        if (limitPlaceholder > 0) {
            int startIndex = (offsetPlaceholder - 1) * limitPlaceholder;
            sqlBuffer.append(" limit ").append(startIndex).append(",").append(limitPlaceholder);
        } else {
            sqlBuffer.append(" limit ").append(limitPlaceholder - 1);
        }
        return sqlBuffer.toString();
    }
}
