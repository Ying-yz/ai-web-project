package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义日志注解
 * 用于标记需要进行日志记录的方法
 */
@Target(ElementType.METHOD) // 规定该注解只能应用在方法上
@Retention(RetentionPolicy.RUNTIME) // 规定该注解在运行时有效，这样可以通过反射获取到
public @interface Log {


}
