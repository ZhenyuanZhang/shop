package org.nj.zzy.common.aop.test;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(org.nj.zzy.common.aop.test.TestAop)")
    public void testAop() {
    }

    @Before("testAop()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBefore");
        showArg(joinPoint.getArgs());
        show("kind: " + joinPoint.getKind());
        showSignature(joinPoint.getSignature());
        showSourceLocation(joinPoint.getSourceLocation());
        showStaticPart(joinPoint.getStaticPart());
        show("target: " + joinPoint.getTarget());
        show("this: " + joinPoint.getThis());
        show("longStr: " + joinPoint.toLongString());
        show("shortStr: " + joinPoint.toShortString());
        show("Str: " + joinPoint.toString());
        System.out.println("");
    }

    @Around("testAop()")
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("deAround Before");
        showArg(joinPoint.getArgs());
        show("kind: " + joinPoint.getKind());
        showSignature(joinPoint.getSignature());
        showSourceLocation(joinPoint.getSourceLocation());
        showStaticPart(joinPoint.getStaticPart());
        show("target: " + joinPoint.getTarget());
        show("this: " + joinPoint.getThis());
        show("longStr: " + joinPoint.toLongString());
        show("shortStr: " + joinPoint.toShortString());
        show("Str: " + joinPoint.toString());
        System.out.println("");
        Object result = joinPoint.proceed(joinPoint.getArgs());
        System.out.println("deAround After");
        showArg(joinPoint.getArgs());
        show("kind: " + joinPoint.getKind());
        showSignature(joinPoint.getSignature());
        showSourceLocation(joinPoint.getSourceLocation());
        showStaticPart(joinPoint.getStaticPart());
        show("target: " + joinPoint.getTarget());
        show("this: " + joinPoint.getThis());
        show("longStr: " + joinPoint.toLongString());
        show("shortStr: " + joinPoint.toShortString());
        show("Str: " + joinPoint.toString());
        System.out.println("");
        return result;
    }

    @After("testAop()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("doAfter");
        showArg(joinPoint.getArgs());
        show("kind: " + joinPoint.getKind());
        showSignature(joinPoint.getSignature());
        showSourceLocation(joinPoint.getSourceLocation());
        showStaticPart(joinPoint.getStaticPart());
        show("target: " + joinPoint.getTarget());
        show("this: " + joinPoint.getThis());
        show("longStr: " + joinPoint.toLongString());
        show("shortStr: " + joinPoint.toShortString());
        show("Str: " + joinPoint.toString());
        System.out.println("");
    }

    @AfterReturning(value = "testAop()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("doAfterReturning");
        showArg(joinPoint.getArgs());
        show("kind:" + joinPoint.getKind());
        showSignature(joinPoint.getSignature());
        showSourceLocation(joinPoint.getSourceLocation());
        showStaticPart(joinPoint.getStaticPart());
        show("target: " + joinPoint.getTarget());
        show("this: " + joinPoint.getThis());
        show("longStr: " + joinPoint.toLongString());
        show("shortStr: " + joinPoint.toShortString());
        show("Str: " + joinPoint.toString());
        show(result);
        System.out.println("");
    }

    @AfterThrowing(pointcut = "testAop()", throwing = "e")
    public void deAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("doAfterReturning");
        showArg(joinPoint.getArgs());
        show("kind: " + joinPoint.getKind());
        showSignature(joinPoint.getSignature());
        showSourceLocation(joinPoint.getSourceLocation());
        showStaticPart(joinPoint.getStaticPart());
        show("target: " + joinPoint.getTarget());
        show("this: " + joinPoint.getThis());
        show("longStr: " + joinPoint.toLongString());
        show("shortStr: " + joinPoint.toShortString());
        show("Str: " + joinPoint.toString());
        show(e.getCause());
        System.out.println("");
    }

    private static void show(Object obj) {
        System.out.println(obj);
    }

    private static void showArg(Object[] objs) {
        show("args: " + Arrays.stream(objs).map(String::valueOf).collect(Collectors.joining()));
    }

    private static void showSignature(Signature signature) {
        show("signature: " + signature);
        if (signature == null) {
            return;
        }
        show("signature.declaringType: " + signature.getDeclaringType().getName());
        show("signature.declaringTypeName: " + signature.getDeclaringTypeName());
        show("signature.modifiers: " + signature.getModifiers());
        show("signature.name: " + signature.getName());
    }

    private static void showSourceLocation(SourceLocation sourceLocation) {
        show("sourceLocation: " + sourceLocation);
        if (sourceLocation == null) {
            return;
        }
        try {
            show("sourceLocation.fileName: " + sourceLocation.getFileName());
            show("sourceLocation.line: " + sourceLocation.getLine());
            show("sourceLocation.withinType: " + sourceLocation.getWithinType().getName());
        } catch (UnsupportedOperationException e) {
            show("sourceLocation.fileName: " + e.getMessage());
        }
    }

    private static void showStaticPart(JoinPoint.StaticPart staticPart) {
        if (staticPart == null) {
            return;
        }
        show("staticPart: ");
        show("id: " + staticPart.getId());
        show("kind: " + staticPart.getKind());
        showSignature(staticPart.getSignature());
        showSourceLocation(staticPart.getSourceLocation());
        show("longStr: " + staticPart.toLongString());
        show("shortStr: " + staticPart.toShortString());
        show("Str: " + staticPart.toString());
    }
}
