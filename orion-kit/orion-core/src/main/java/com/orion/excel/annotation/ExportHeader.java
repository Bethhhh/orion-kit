package com.orion.excel.annotation;

import com.orion.utils.Strings;

import java.lang.annotation.*;

/**
 * Excel 页眉注解
 *
 * @author ljh15
 * @version 1.0.0
 * @since 2020/12/25 18:39
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExportHeader {

    /**
     * 页眉左侧
     */
    String left() default Strings.EMPTY;

    /**
     * 页眉中间
     */
    String center() default Strings.EMPTY;

    /**
     * 页眉右侧
     */
    String right() default Strings.EMPTY;

}
