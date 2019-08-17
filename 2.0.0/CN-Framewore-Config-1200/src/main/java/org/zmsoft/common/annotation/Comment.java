package org.zmsoft.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
//1指定该注解的作用范围
@Target({ElementType.FIELD,ElementType.TYPE})
//2.指定生命时长  CLASS:编译时有效  Source:写代码时有效  RUntime:运行有效
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {
	String value() default " ";//属性
}

