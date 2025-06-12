package service;

import model.Item;
import tax.Tax;
import util.TaxRounding;

import java.math.BigDecimal;
import java.util.List;

public class TaxCalculator {
    private final List<Tax> taxRules;

    public TaxCalculator(List<Tax> taxRules) {
        this.taxRules = taxRules;
    }

    public BigDecimal computeTax(Item item) {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Tax rule : taxRules) {
            totalTax = totalTax.add(rule.calculate(item));
        }
        return TaxRounding.round(totalTax);
    }
}
