package com.absentm.spbt.handler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspectHandler {
    private static final Logger logger = LoggerFactory.getLogger(LogAspectHandler.class);

    /**
     * 定义一个切面，拦截 com.absentm.spbt.controllers 包和子包下的所有方法
     * <p>
     * execution() 为表达式主体
     * 第一个 * 号的位置：表示返回值类型， * 表示所有类型
     * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包, com.absentm.spbt.controllers 包, 子包下所有类的方法
     * 第二个 * 号的位置：表示类名， * 表示所有类
     * *(..) ：这个星号表示方法名， * 表示所有的方法，后面括弧里面表示方法的参数，
     * 两个句点表示任何参数
     */
    @Pointcut("execution(* com.absentm.spbt.controllers..*.*(..))")
    public void pointCut() {
    }

    /**
     * annotation 方式是针对某个注解来定义切面 (如， @GetMapping 、 @PostMapping 、 @DeleteMapping 等)
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void annotationCut() {
    }

    /**
     * @param joinPoint JointPoint 对象很有用，可以用它来获取一个签名，然后利用签名可以获取请求的包名、方法名，包括
     *                  参数（通过 joinPoint.getArgs() 获取）等等
     *                  <p>
     *                  Before 注解指定的方法在切面切入目标方法之前执行，可以做一些 log 处理，也可以做一些信息的统计，比如获取用户的请求 url 以及用户的 ip 地址等等
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("====doBefore方法进入了====");

        // 获取签名
        Signature signature = joinPoint.getSignature();

        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();

        // 获取即将执行的方法名
        String funcName = signature.getName();
        logger.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的url和ip
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求url
        String url = request.getRequestURL().toString();

        // 获取请求ip
        String ip = request.getRemoteAddr();
        logger.info("用户请求的url为：{}，ip地址为：{}", url, ip);
    }

    /**
     * 在上面定义的切面方法之后执行该方法
     *
     * @param joinPoint jointPoint
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("====doAfter方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("方法{}已经执行完", method);
    }

    /**
     * 在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     *
     * @param joinPoint joinPoint
     * @param result    result
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();

        logger.info("方法{}执行完毕，返回参数为：{}", classMethod, result);

        // 实际项目中可以根据业务做具体的返回值增强
        logger.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }

    /**
     * 在上面定义的切面方法执行抛异常时，执行该方法
     *
     * @param joinPoint jointPoint
     * @param ex        ex
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();

        // 处理异常的逻辑
        logger.info("执行方法{}出错，异常为：{}", method, ex);
    }
}
