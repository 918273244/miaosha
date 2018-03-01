package com.basis.web.config.mybatis;

import com.basis.web.page.BasePage;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(PageInterceptor.class);

    /*
     * 所用的数据库类型
     */
    private Dialect dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //如果使用的数据不支持偏移，则不处理
        if(!(dialect.supportsLimit() && dialect.supportsLimitOffset())){
            logger.debug("所用数据库不支持偏移");
            return invocation.proceed();
        }
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        //获取绑定sql的对象
        BoundSql boundSql = statementHandler.getBoundSql();
        Object paramObj = boundSql.getParameterObject();
        //判断参数中是否有名字为page的对象,确保page对象只用于查询
        if(paramObj == null|| !(paramObj instanceof BasePage)){
            return invocation.proceed();
        }

        BasePage page = (BasePage)paramObj;
        // 原始的SQL语句
        String sql = boundSql.getSql();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY
                ,new DefaultReflectorFactory());

        //是否查询所有

        if(page.getLoadAllRecord()){
            return invocation.proceed();
        }

        //是否要查询总数
        if(page.getCountTotal()){
            queryAll(sql,invocation,metaObject,page);
        }
        MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
        // 配置文件中SQL语句的ID
        String id = mappedStatement.getId();
        // 改造后带分页查询的SQL语句
        String pageSql = dialect.getLimitString(sql, page.getPage(), page.getCount(),page.getOrder(),page.getAsc());
        //设置回给mybatis去执行
        metaObject.setValue("delegate.boundSql.sql", pageSql);
        logger.debug("{}过滤后的SQL语句为:{}",id,pageSql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialectstr = (String)properties.get("dialectClass");
        try {
            Class<?> dialectClass = Class.forName(dialectstr);
            dialect = (Dialect)dialectClass.newInstance();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void queryAll(String srcSql,Invocation invocation,MetaObject metaObject,BasePage page) throws Throwable{
        // 查询总条数的SQL语句
        String countSql = "select count(*) from (" + srcSql + ") a";
        //获取代理对象的连接
        Connection connection = (Connection)invocation.getArgs()[0];
        //执行查询总条数的sql语句
        PreparedStatement countStatement = connection.prepareStatement(countSql);
        //获取mybatis参数处理类，将实际参数 注入到 sql中
        ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
        parameterHandler.setParameters(countStatement);
        //执行sql
        ResultSet rs = countStatement.executeQuery();
        if(rs.next()) {
            //获取的第一个结果就是总数
            page.setTotal(rs.getInt(1));
        }
    }
}
