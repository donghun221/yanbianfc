package com.peernet.mobile.server.admin.common.message.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.peernet.mobile.server.admin.common.message.cst.FieldDataType;

/**
 * 定义字段文档属性的注解 <br />
 * <i>可用于生成接口文档</i>
 * 
 * @author 宋翔
 * @date 2014-7-24 下午4:14:52
 */
@Target(ElementType.FIELD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDoc
{
    /**
     * 名称（中文名）
     * 
     * @return
     */
    String value();

    /**
     * 描述
     * 
     * @return
     */
    String note() default "";

    /**
     * 数据类型
     * 
     * @return
     */
    String type() default FieldDataType.STRING;

    /**
     * 是否路径参数
     * 
     * @return
     */
    boolean pathVar() default false;

    /**
     * 是否必填
     * 
     * @return
     */
    boolean required() default false;

    /**
     * 长度
     * 
     * @return
     */
    int length() default Integer.MAX_VALUE;

    /**
     * 字段默认值
     * 
     * @return
     */
    String defaultValue();
}
