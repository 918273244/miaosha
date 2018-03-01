package com.basis.web.config.annotation;

import java.lang.annotation.*;

/**
 * 数据源定义
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String name() default DataSource.master;

    static String master = "writeDataSource";
    static String slave = "readDataSource";
    static String slave2 ="historyDataSource";
    static String slave3 ="blackDataSource";
}
