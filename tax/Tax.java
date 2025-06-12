package tax;

import model.Item;
import java.math.BigDecimal;

public interface Tax {
    BigDecimal calculate(Item item);
}
