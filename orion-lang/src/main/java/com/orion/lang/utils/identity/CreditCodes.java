package com.orion.lang.utils.identity;

import com.orion.lang.utils.random.Randoms;
import com.orion.lang.utils.regexp.Matches;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 统一社会信用代码工具类
 * <p>
 * 第一部分: 登记管理部门代码1位 (数字或大写英文字母)
 * 第二部分: 机构类别代码1位 (数字或大写英文字母)
 * 第三部分: 登记管理机关行政区划码6位 (数字)
 * 第四部分: 主体标识码 (组织机构代码) 9位 (数字或大写英文字母)
 * 第五部分: 校验码1位 (数字或大写英文字母)
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2020/10/31 21:52
 */
public class CreditCodes {

    private CreditCodes() {
    }

    private static final int[] WEIGHT = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

    private static final char[] BASE_CODE_ARRAY = "0123456789ABCDEFGHJKLMNPQRTUWXY".toCharArray();

    private static final Map<Character, Integer> CODE_INDEX_MAP;

    static {
        CODE_INDEX_MAP = new ConcurrentHashMap<>();
        for (int i = 0; i < BASE_CODE_ARRAY.length; i++) {
            CODE_INDEX_MAP.put(BASE_CODE_ARRAY[i], i);
        }
    }

    /**
     * 是否是有效的统一社会信用代码
     *
     * @param creditCode 统一社会信用代码
     * @return ignore
     */
    public static boolean validCreditCode(String creditCode) {
        if (!Matches.isCreditCode(creditCode)) {
            return false;
        }
        int parityBit = getParityBit(creditCode);
        if (parityBit < 0) {
            return false;
        }
        return creditCode.charAt(17) == BASE_CODE_ARRAY[parityBit];
    }

    /**
     * 获取一个随机的统一社会信用代码
     *
     * @return 统一社会信用代码
     */
    public static String random() {
        StringBuilder buf = new StringBuilder(18);
        for (int i = 0; i < 2; i++) {
            int num = Randoms.randomInt(BASE_CODE_ARRAY.length - 1);
            buf.append(Character.toUpperCase(BASE_CODE_ARRAY[num]));
        }
        for (int i = 2; i < 8; i++) {
            int num = Randoms.randomInt(10);
            buf.append(Character.toUpperCase(BASE_CODE_ARRAY[num]));
        }
        for (int i = 8; i < 17; i++) {
            int num = Randoms.randomInt(BASE_CODE_ARRAY.length - 1);
            buf.append(Character.toUpperCase(BASE_CODE_ARRAY[num]));
        }
        String code = buf.toString();
        return code + BASE_CODE_ARRAY[getParityBit(code)];
    }

    /**
     * 获取校验码
     *
     * @param creditCode 统一社会信息代码
     * @return 获取较验位的值
     */
    public static int getParityBit(String creditCode) {
        int sum = 0;
        Integer codeIndex;
        for (int i = 0; i < 17; i++) {
            codeIndex = CODE_INDEX_MAP.get(creditCode.charAt(i));
            if (codeIndex == null) {
                return -1;
            }
            sum += codeIndex * WEIGHT[i];
        }
        int result = 31 - sum % 31;
        return result == 31 ? 0 : result;
    }

}
