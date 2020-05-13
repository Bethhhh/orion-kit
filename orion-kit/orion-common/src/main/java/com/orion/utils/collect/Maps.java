package com.orion.utils.collect;

import com.orion.lang.Arg;
import com.orion.lang.ConvertHashMap;
import com.orion.lang.MapEntry;
import com.orion.utils.Arrays1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map工具类
 *
 * @author ljh15
 * @version 1.0.0
 * @date 2020/4/29 16:47
 */
@SuppressWarnings("ALL")
public class Maps {

    private Maps() {
    }

    // --------------- singleton map ---------------

    private static final Map<Object, Map> SINGLETON_MAP = new HashMap<>(4);

    // --------------- new ---------------

    public static <K, V> Map<K, V> newMap() {
        return new HashMap<>(16);
    }

    public static <K, V> Map<K, V> newMap(int capacity) {
        return new HashMap<>(capacity);
    }

    public static <K, V> Map<K, V> newMap(Map<? extends K, ? extends V> m) {
        if (size(m) == 0) {
            return new HashMap<>(16);
        }
        return new HashMap<>(m);
    }

    public static <K, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    public static <K, V> TreeMap<K, V> newTreeMap(Comparator<? super K> comparator) {
        return new TreeMap<>(comparator);
    }

    public static <K, V> TreeMap<K, V> newTreeMap(Map<? extends K, ? extends V> m) {
        if (size(m) == 0) {
            return new TreeMap<>();
        }
        return new TreeMap<>(m);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedMap() {
        return new LinkedHashMap<>(16);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedMap(int capacity) {
        return new LinkedHashMap<>(capacity);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedMap(Map<? extends K, ? extends V> m) {
        if (size(m) == 0) {
            return new LinkedHashMap<>(16);
        }
        return new LinkedHashMap<>(m);
    }

    public static <K, V> ConcurrentHashMap<K, V> newCurrentMap() {
        return new ConcurrentHashMap<>(16);
    }

    public static <K, V> ConcurrentHashMap<K, V> newCurrentMap(int capacity) {
        return new ConcurrentHashMap<>(capacity);
    }

    public static <K, V> ConcurrentHashMap<K, V> newCurrentMap(Map<? extends K, ? extends V> m) {
        if (size(m) == 0) {
            return new ConcurrentHashMap<>(16);
        }
        return new ConcurrentHashMap<>(m);
    }

    public static <K, V> ConvertHashMap<K, V> newConvertMap() {
        return new ConvertHashMap<>(16);
    }

    public static <K, V> ConvertHashMap<K, V> newConvertMap(int capacity) {
        return new ConvertHashMap<>(capacity);
    }

    public static <K, V> ConvertHashMap<K, V> newConvertMap(Map<? extends K, ? extends V> m) {
        if (size(m) == 0) {
            return new ConvertHashMap<>(16);
        }
        return new ConvertHashMap<>(m);
    }

    // --------------- singleton ---------------

    public static <K, V> Map<K, V> singletonMap(Object key) {
        if (SINGLETON_MAP.containsKey(key)) {
            return SINGLETON_MAP.get(key);
        }
        Map<K, V> map = new HashMap<>(16);
        SINGLETON_MAP.put(key, map);
        return map;
    }

    // --------------- function ---------------

    public static <K, V> Map<K, V> of(MapEntry<K, V>... entries) {
        int len = Arrays1.length(entries);
        if (len == 0) {
            return new HashMap<>(16);
        }
        Map<K, V> map = new HashMap<>(len * 4 / 3);
        for (int i = 0; i < len; i++) {
            map.put(entries[i].getKey(), entries[i].getValue());
        }
        return map;
    }

    public static <K, V> Map<K, V> of(Arg.Entry<K, V>... entries) {
        int len = Arrays1.length(entries);
        if (len == 0) {
            return new HashMap<>(16);
        }
        Map<K, V> map = new HashMap<>(len * 4 / 3);
        for (int i = 0; i < len; i++) {
            map.put(entries[i].getKey(), entries[i].getValue());
        }
        return map;
    }

    public static <K, V> Map<K, V> of(Object... kv) {
        if (kv == null) {
            return new HashMap<>(16);
        }
        int c = kv.length / 2;
        int hn = kv.length % 2;
        Map<K, V> res = new HashMap<>(c * 4 / 3);
        for (int i = 0; i < c; i++) {
            res.put(((K) kv[i * 2]), ((V) kv[i * 2 + 1]));
        }
        if (hn == 1) {
            res.put(((K) kv[c * 2]), null);
        }
        return res;
    }

    /**
     * 合并map
     *
     * @param map 合并到的map
     * @param ms  需要合并的map
     * @param <K> ignore
     * @param <V> ignore
     * @return 合并后的map
     */
    public static <K, V> Map<K, V> merge(Map<K, V> map, Map<K, V>... ms) {
        if (map == null) {
            map = new HashMap<>(16);
        }
        if (ms == null) {
            return map;
        }
        for (Map<K, V> m : ms) {
            map.putAll(m);
        }
        return map;
    }

    /**
     * map长度
     *
     * @param m map
     * @return 长度
     */
    public static int size(Map m) {
        return m == null ? 0 : m.size();
    }

    /**
     * map是否为空
     *
     * @param m map
     * @return true为空
     */
    public static boolean isEmpty(Map m) {
        return size(m) == 0;
    }

    /**
     * map是否不为空
     *
     * @param m map
     * @return true不为空
     */
    public static boolean isNotEmpty(Map m) {
        return !isEmpty(m);
    }

    /**
     * map是否全为空
     *
     * @param ms map
     * @return true全为空
     */
    public static boolean isAllEmpty(Map... ms) {
        if (ms == null) {
            return true;
        }
        for (Map m : ms) {
            if (!isEmpty(m)) {
                return false;
            }
        }
        return true;
    }

    /**
     * map是否全不为空
     *
     * @param ms map
     * @return true全不为空, 参数为空false
     */
    public static boolean isNoneEmpty(Map... ms) {
        if (ms == null) {
            return false;
        }
        for (Map m : ms) {
            if (isEmpty(m)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从map随机获取一个元素
     *
     * @param map map
     * @param <K> ignore
     * @param <V> ignore
     * @return 元素
     */
    public static <K, V> MapEntry<K, V> random(Map<K, V> map) {
        int size = size(map);
        if (size == 0) {
            return null;
        } else if (size == 1) {
            return MapEntry.toMapEntry(map.entrySet().iterator().next());
        } else {
            Object randomKey = map.keySet().toArray()[new Random().nextInt(size)];
            return new MapEntry(randomKey, map.get(randomKey));
        }
    }

    /**
     * 将指定元素装配给集合
     *
     * @param map   集合
     * @param value 元素
     * @param <K>   ignore
     * @param <V>   ignore
     */
    public static <K, V> void fill(Map<K, V> map, V value) {
        if (!isEmpty(map)) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                entry.setValue(value);
            }
        }
    }

    public static <K, V> V get(Map<K, V> map, K k) {
        int size = size(map);
        if (size == 0) {
            return null;
        }
        return map.get(k);
    }

    public static <K, V> void set(Map<K, V> map, K k, V v) {
        sets(map, k, v, true);
    }

    public static <K, V> void set(Map<K, V> map, K k, V v, boolean force) {
        sets(map, k, v, force);
    }

    public static <K, V> void setex(Map<K, V> map, K k, V v) {
        sets(map, k, v, false);
    }

    public static <K, V> V getSet(Map<K, V> map, K k, V v) {
        return sets(map, k, v, true);
    }

    private static <K, V> V sets(Map<K, V> map, K k, V v, boolean force) {
        int size = size(map);
        if (size == 0) {
            return null;
        }
        V o = map.get(k);
        if (force) {
            map.put(k, v);
            return o;
        } else {
            if (o == null) {
                map.put(k, v);
            }
            return null;
        }
    }

    /**
     * 获取第一个元素
     *
     * @param m   集合
     * @param <K> ignore
     * @param <V> ignore
     * @return 第一个元素
     */
    public static <K, V> Map.Entry<K, V> getFirst(Map<K, V> m) {
        if (size(m) == 0) {
            return null;
        }
        return Lists.getFirst(m.entrySet());
    }

    /**
     * 获取最后一个元素
     *
     * @param m   集合
     * @param <K> ignore
     * @param <V> ignore
     * @return 最后一个元素
     */
    public static <K, V> Map.Entry<K, V> getLast(Map<K, V> m) {
        if (size(m) == 0) {
            return null;
        }
        return Lists.getLast(m.entrySet());
    }

}
