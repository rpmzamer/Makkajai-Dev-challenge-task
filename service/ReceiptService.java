package service;

import model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceiptService {
    private final TaxCalculator taxCalculator;
    private final List<Item> items = new ArrayList<>();

    public ReceiptService(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void printReceipt() {
        BigDecimal totalTax = BigDecimal.ZERO;
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Item item : items) {
            BigDecimal tax = taxCalculator.computeTax(item);
            BigDecimal finalPrice = item.price().add(tax);

            System.out.printf("%s: %.2f%n", item.name(), finalPrice);
            totalTax = totalTax.add(tax);
            totalPrice = totalPrice.add(finalPrice);
        }

        System.out.printf("Sales Taxes: %.2f%n", totalTax);
        System.out.printf("Total: %.2f%n", totalPrice);
    }
}
