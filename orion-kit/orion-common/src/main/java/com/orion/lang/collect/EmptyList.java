package com.orion.lang.collect;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 空元素集合
 *
 * @author ljh15
 * @version 1.0.0
 * @since 2020/10/22 14:09
 */
public class EmptyList<E> extends AbstractList<E> implements RandomAccess, Serializable {

    public static final EmptyList<?> EMPTY = new EmptyList<>();

    private static final long serialVersionUID = -4359234034983487L;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(Object obj) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.isEmpty();
    }

    @Override
    public E get(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: 0");
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super E> action) {
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof List) && ((List<?>) o).isEmpty();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length > 0) {
            a[0] = null;
        }
        return a;
    }

}
