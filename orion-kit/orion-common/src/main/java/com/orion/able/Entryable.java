package com.orion.able;

/**
 * 键值对接口
 *
 * @author Li
 * @version 1.0.0
 * @date 2019/11/18 18:18
 */
public interface Entryable<K, V> {

    K getKey();

    V getValue();

}
