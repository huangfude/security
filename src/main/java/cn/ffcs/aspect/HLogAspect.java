package cn.ffcs.aspect;

import cn.ffcs.controller.BlogController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by MәӧωρaЯsε on 2017/6/26.
 *
 */
@Aspect
@Component
public class HLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(HLogAspect.class);


    @Pointcut("@annotation(cn.ffcs.annotation.ULog)")
    public  void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        logger.info("接受方法：{} 前置日志",method.getName());
    }

}
