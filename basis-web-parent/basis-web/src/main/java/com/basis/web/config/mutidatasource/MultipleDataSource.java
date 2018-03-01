package com.basis.web.config.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

    public static final String read="readDataSource";
    public static final String write="writeDataSource";

    public MultipleDataSource()
    {
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return JdbcContextHolder.getJdbcType();
    }
}
