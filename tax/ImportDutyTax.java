package tax;

import model.Item;

import java.math.BigDecimal;

public class ImportDutyTax implements Tax {
    private static final BigDecimal RATE = new BigDecimal("0.05");

    @Override
    public BigDecimal calculate(Item item) {
        if (item.imported()) {
            return item.price().multiply(RATE);
        }
        return BigDecimal.ZERO;
    }
}
