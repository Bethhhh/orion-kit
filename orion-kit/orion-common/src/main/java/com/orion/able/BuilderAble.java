package com.orion.able;

/**
 * 构建接口
 *
 * @author Li
 * @version 1.0.0
 * @since 2019/11/18 18:09
 */
public interface BuilderAble<T> {

    /**
     * 构建
     *
     * @return T
     */
    T build();

}
