package tax;

import model.Category;
import model.Item;

import java.math.BigDecimal;
import java.util.Set;

public class BasicSalesTax implements Tax {
    private static final BigDecimal RATE = new BigDecimal("0.10");
    private static final Set<Category> EXEMPT = Set.of(Category.BOOK, Category.FOOD, Category.MEDICAL);

    @Override
    public BigDecimal calculate(Item item) {
        if (EXEMPT.contains(item.category())) {
            return BigDecimal.ZERO;
        }
        return item.price().multiply(RATE);
    }
}
