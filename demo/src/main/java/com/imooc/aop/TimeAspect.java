package com.imooc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    /**
     * 第一个*表示返回值可以是所有
     * com.imooc.controller.UserController.*(..) 表示UserController下的所有方法，方法的参数也不限制
     * @param pjp
     * @return
     */
    @Around("execution(* com.imooc.controller.UserController.*(..))")
    public Object handlerTimeConsuming(ProceedingJoinPoint pjp) {
        Object o = null;
        try {
            long start = System.currentTimeMillis();
            Object[] args = pjp.getArgs();
            for (Object arg : args) {
                System.out.println(arg);
            }
            o =pjp.proceed();
            System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return o;
    }
}
