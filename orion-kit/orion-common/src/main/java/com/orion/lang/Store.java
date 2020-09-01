package com.orion.lang;

import java.io.Serializable;

/**
 * 对象存储
 *
 * @author ljh15
 * @version 1.0.0
 * @date 2020/8/30 23:53
 */
public class Store<T> implements Serializable {

    private static final long serialVersionUID = -885690364340775L;

    private T t;

    public Store() {
    }

    public Store(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public Store<T> set(T t) {
        this.t = t;
        return this;
    }

    @Override
    public String toString() {
        return t == null ? "" : t.toString();
    }

}