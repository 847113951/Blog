package com.ylb.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class logAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.ylb.Controller.*.*(..))")
    public void log() {

    }

    @Before(value = "log()")
    public void beforeM(JoinPoint joinPoint) {//请求方法之前执行
        logger.info("----doBefore----");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = attributes.getRequest();

        String url = servletRequest.getRequestURL().toString();
        String ip = servletRequest.getRemoteAddr();
        String clssMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,clssMethod,args);
        logger.info("[doBefore]Request : "+requestLog);

    }

    @AfterReturning(value = "log()", returning = "result")
    public void returnM(Object result) {
        logger.info("----doReturn----");
        logger.info("返回结果[" + result.toString() + "]");
    }

    @After("log()")
    public void afterM() {//请求方法之后执行
        logger.info("------doAfter------");
    }


    @AfterThrowing(value = "log()", throwing = "exception")
    public void exceptionM(Exception exception) {
        logger.info("---doException------");
        logger.info("出异常了，异常信息 [" + exception.getMessage() + "]");
    }

    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        public RequestLog() {
        }
    }

}
