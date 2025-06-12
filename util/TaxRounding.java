package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxRounding {
    private static final BigDecimal ROUNDING_FACTOR = new BigDecimal("0.05");

    public static BigDecimal round(BigDecimal amount) {
        return amount.divide(ROUNDING_FACTOR, 0, RoundingMode.UP)
                     .multiply(ROUNDING_FACTOR);
    }
}
