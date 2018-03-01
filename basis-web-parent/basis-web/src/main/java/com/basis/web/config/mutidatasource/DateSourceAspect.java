package com.basis.web.config.mutidatasource;

import com.basis.web.config.annotation.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: DateSourceAspect.java
 * @Description: 切面实现读写数据库路由
 */
public class DateSourceAspect {

    /**
     * 缓存
     */
    private static ConcurrentHashMap<String, String> methodIsReadCache = new ConcurrentHashMap<>();

    public Object determineReadOrWriteDB(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Object target = pjp.getTarget();
        String cacheKey = target.getClass().getName() + "." + method.getName();
        String isReadCacheValue = methodIsReadCache.get(cacheKey);
        if (isReadCacheValue == null) {
            // 重新获取方法，否则传递的是接口的方法信息
            Method realMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
            isReadCacheValue = isChoiceReadDB(realMethod);
            methodIsReadCache.put(cacheKey, isReadCacheValue);
        }
        JdbcContextHolder.setJdbcType(isReadCacheValue);
        try {
            return pjp.proceed();
        } finally {
            JdbcContextHolder.clearJdbcType();
        }
    }

    /**
     * 判断是否只读方法
     *
     * @param method 执行方法
     * @return 当前方法是否只读
     */
    private String isChoiceReadDB(Method method) {
        DataSource transactionalAnno = AnnotationUtils.findAnnotation(method, DataSource.class);
        if (transactionalAnno == null) {
            return MultipleDataSource.write;
        }
        return transactionalAnno.name();
    }

}
