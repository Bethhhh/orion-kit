package com.orion.lang.define.builder;

import com.orion.lang.able.Buildable;
import com.orion.lang.utils.Strings;
import com.orion.lang.utils.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 字符串构建器
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/2/10 10:20
 */
public class StringJoiner implements Buildable<String> {

    private String prefix;

    private String suffix;

    private String symbol;

    private Predicate<String> filter;

    private Function<String, String> wrapper;

    private final List<String> modifiers;

    public StringJoiner() {
        this.modifiers = new ArrayList<>();
    }

    public StringJoiner(String symbol) {
        this(symbol, null, null);
    }

    public StringJoiner(String symbol, String prefix, String suffix) {
        this.symbol = symbol;
        this.prefix = prefix;
        this.suffix = suffix;
        this.modifiers = new ArrayList<>();
    }

    public static StringJoiner of() {
        return new StringJoiner();
    }

    public static StringJoiner of(String symbol) {
        return new StringJoiner(symbol);
    }

    public static StringJoiner of(Supplier<String> symbolSupplier) {
        Valid.notNull(symbolSupplier, "symbol supplier is null");
        return new StringJoiner(symbolSupplier.get());
    }

    public static StringJoiner of(String symbol, String prefix, String suffix) {
        return new StringJoiner(symbol, prefix, suffix);
    }

    /**
     * 设置标识符
     *
     * @param symbol symbol
     * @return this
     */
    public StringJoiner symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    /**
     * 设置标识符
     *
     * @param symbolSupplier symbol
     * @return this
     */
    public StringJoiner symbol(Supplier<String> symbolSupplier) {
        Valid.notNull(symbolSupplier, "symbol supplier is null");
        this.symbol = symbolSupplier.get();
        return this;
    }

    /**
     * 设置前缀
     *
     * @param prefix prefix
     * @return this
     */
    public StringJoiner prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * 设置前缀
     *
     * @param prefixSupplier prefix
     * @return this
     */
    public StringJoiner prefix(Supplier<String> prefixSupplier) {
        Valid.notNull(prefixSupplier, "prefix supplier is null");
        this.prefix = prefixSupplier.get();
        return this;
    }

    /**
     * 设置后缀
     *
     * @param suffix suffix
     * @return this
     */
    public StringJoiner suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    /**
     * 设置后缀
     *
     * @param suffixSupplier suffix
     * @return this
     */
    public StringJoiner suffix(Supplier<String> suffixSupplier) {
        Valid.notNull(suffixSupplier, "suffix supplier is null");
        this.suffix = suffixSupplier.get();
        return this;
    }

    /**
     * 跳过null
     *
     * @return this
     */
    public StringJoiner skipNull() {
        addFilter(Objects::nonNull);
        return this;
    }

    /**
     * 跳过空串
     *
     * @return this
     */
    public StringJoiner skipEmpty() {
        addFilter(Strings::isNotEmpty);
        return this;
    }

    /**
     * 跳过空串
     *
     * @return this
     */
    public StringJoiner skipBlank() {
        addFilter(Strings::isNotBlank);
        return this;
    }

    /**
     * 过滤
     *
     * @param filter 过滤器
     * @return this
     */
    public StringJoiner filter(Predicate<String> filter) {
        addFilter(filter);
        return this;
    }

    /**
     * 添加过滤器
     *
     * @param filter 过滤器
     */
    private void addFilter(Predicate<String> filter) {
        Valid.notNull(filter, "filter is null");
        if (this.filter == null) {
            this.filter = filter;
        }
        this.filter = this.filter.and(filter);
    }

    /**
     * 包装器
     *
     * @param wrapper wrapper
     * @return this
     */
    public StringJoiner wrapper(Function<String, String> wrapper) {
        Valid.notNull(wrapper, "wrapper is null");
        this.wrapper = wrapper;
        return this;
    }

    public StringJoiner with(String s) {
        modifiers.add(s);
        return this;
    }

    public StringJoiner with(Supplier<String> supplier) {
        Valid.notNull(supplier, "supplier is null");
        modifiers.add(supplier.get());
        return this;
    }

    @Override
    public String build() {
        StringBuilder builder = new StringBuilder();
        if (prefix != null) {
            builder.append(prefix);
        }
        boolean add = false;
        for (String modifier : modifiers) {
            if (filter != null) {
                if (!filter.test(modifier)) {
                    continue;
                }
            }
            if (wrapper != null) {
                modifier = wrapper.apply(modifier);
            }
            builder.append(modifier);
            if (symbol != null) {
                builder.append(symbol);
                add = true;
            }
        }
        if (add) {
            builder.deleteCharAt(builder.length() - 1);
        }
        if (suffix != null) {
            builder.append(suffix);
        }
        return builder.toString();
    }

}
