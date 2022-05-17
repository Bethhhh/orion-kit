package com.orion.utils.unit;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 长度单位
 * <p>
 * 默认舍入模式: {@link RoundingMode#FLOOR}
 * 默认舍入精度: 2
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/2/25 18:27
 */
public enum LengthUnit {

    /**
     * 毫米
     */
    MM {
        @Override
        public BigDecimal toMillimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u;
        }

        @Override
        public BigDecimal toCentimeter(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode);
        }

        @Override
        public BigDecimal toDecimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode)
                    .divide(TEN, roundingMode);
        }

        @Override
        public BigDecimal toMetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode)
                    .divide(HUNDRED, roundingMode);
        }

        @Override
        public BigDecimal toKilometre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode)
                    .divide(HUNDRED, roundingMode)
                    .divide(THOUSAND, roundingMode);
        }
    },

    /**
     * 厘米
     */
    CM {
        @Override
        public BigDecimal toMillimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(TEN);
        }

        @Override
        public BigDecimal toCentimeter(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u;
        }

        @Override
        public BigDecimal toDecimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode);
        }

        @Override
        public BigDecimal toMetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(HUNDRED, roundingMode);
        }

        @Override
        public BigDecimal toKilometre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(HUNDRED, roundingMode)
                    .divide(THOUSAND, roundingMode);
        }
    },

    /**
     * 分米
     */
    DM {
        @Override
        public BigDecimal toMillimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(HUNDRED);
        }

        @Override
        public BigDecimal toCentimeter(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(TEN);
        }

        @Override
        public BigDecimal toDecimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u;
        }

        @Override
        public BigDecimal toMetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode);
        }

        @Override
        public BigDecimal toKilometre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(TEN, roundingMode)
                    .divide(THOUSAND, roundingMode);
        }
    },

    /**
     * 米
     */
    M {
        @Override
        public BigDecimal toMillimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(HUNDRED)
                    .multiply(TEN);
        }

        @Override
        public BigDecimal toCentimeter(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(HUNDRED);
        }

        @Override
        public BigDecimal toDecimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(TEN);
        }

        @Override
        public BigDecimal toMetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u;
        }

        @Override
        public BigDecimal toKilometre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .divide(THOUSAND, roundingMode);
        }
    },

    /**
     * 千米
     */
    KM {
        @Override
        public BigDecimal toMillimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(TEN)
                    .multiply(HUNDRED)
                    .multiply(THOUSAND);
        }

        @Override
        public BigDecimal toCentimeter(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(HUNDRED)
                    .multiply(THOUSAND);
        }

        @Override
        public BigDecimal toDecimetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(TEN)
                    .multiply(THOUSAND);
        }

        @Override
        public BigDecimal toMetre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u.setScale(scale, roundingMode)
                    .multiply(THOUSAND);
        }

        @Override
        public BigDecimal toKilometre(BigDecimal u, int scale, RoundingMode roundingMode) {
            return u;
        }
    };

    private static final BigDecimal TEN = BigDecimal.valueOf(10);

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    private static final BigDecimal THOUSAND = BigDecimal.valueOf(1000);

    /**
     * 默认舍入模式
     */
    private static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.FLOOR;

    /**
     * 默认舍入精度
     */
    private static final int DEFAULT_SCALE = 2;

    public BigDecimal toMillimetre(long u) {
        return this.toMillimetre(BigDecimal.valueOf(u), DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    public BigDecimal toMillimetre(BigDecimal u) {
        return this.toMillimetre(u, DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    /**
     * ? -> MM
     *
     * @param u            unit
     * @param scale        scale
     * @param roundingMode roundingMode
     * @return MM
     */
    public abstract BigDecimal toMillimetre(BigDecimal u, int scale, RoundingMode roundingMode);

    public BigDecimal toCentimeter(long u) {
        return this.toCentimeter(BigDecimal.valueOf(u), DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    public BigDecimal toCentimeter(BigDecimal u) {
        return this.toCentimeter(u, DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    /**
     * ? -> CM
     *
     * @param u            unit
     * @param scale        scale
     * @param roundingMode roundingMode
     * @return CM
     */
    public abstract BigDecimal toCentimeter(BigDecimal u, int scale, RoundingMode roundingMode);

    public BigDecimal toDecimetre(long u) {
        return this.toDecimetre(BigDecimal.valueOf(u), DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    public BigDecimal toDecimetre(BigDecimal u) {
        return this.toDecimetre(u, DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    /**
     * ? -> DM
     *
     * @param u            unit
     * @param scale        scale
     * @param roundingMode roundingMode
     * @return DM
     */
    public abstract BigDecimal toDecimetre(BigDecimal u, int scale, RoundingMode roundingMode);

    public BigDecimal toMetre(long u) {
        return this.toMetre(BigDecimal.valueOf(u), DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    public BigDecimal toMetre(BigDecimal u) {
        return this.toMetre(u, DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    /**
     * ? -> M
     *
     * @param u            unit
     * @param scale        scale
     * @param roundingMode roundingMode
     * @return M
     */
    public abstract BigDecimal toMetre(BigDecimal u, int scale, RoundingMode roundingMode);

    public BigDecimal toKilometre(long u) {
        return this.toKilometre(BigDecimal.valueOf(u), DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    public BigDecimal toKilometre(BigDecimal u) {
        return this.toKilometre(u, DEFAULT_SCALE, DEFAULT_ROUND_MODE);
    }

    /**
     * ? -> KM
     *
     * @param u            unit
     * @param scale        scale
     * @param roundingMode roundingMode
     * @return KM
     */
    public abstract BigDecimal toKilometre(BigDecimal u, int scale, RoundingMode roundingMode);

}
