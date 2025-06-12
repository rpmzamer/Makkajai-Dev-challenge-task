

import model.*;
import service.*;
import tax.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class SalesTaxApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxCalculator taxCalculator = new TaxCalculator(List.of(
                new BasicSalesTax(),
                new ImportDutyTax()
        ));
        ReceiptService receipt = new ReceiptService(taxCalculator);

        System.out.println("Enter items (e.g. '1 imported box of chocolates at 10.00'):");
        System.out.println("Type 'done' to finish.");

        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equalsIgnoreCase("done")) break;

            try {
                Item item = parseItem(line);
                receipt.addItem(item);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Try again.");
            }
        }

        System.out.println("\nYour Receipt:");
        receipt.printReceipt();
    }

    private static Item parseItem(String input) {
        if (!input.contains(" at ")) throw new IllegalArgumentException();

        String[] parts = input.split(" at ");
        String left = parts[0].trim();
        BigDecimal price = new BigDecimal(parts[1].trim());

        // Remove quantity if present
        String[] tokens = left.split(" ", 2);
        String description = tokens.length > 1 ? tokens[1] : tokens[0];

        boolean imported = description.contains("imported");
        String cleanDescription = description.replace("imported", "").trim();

        Category category = categorize(cleanDescription.toLowerCase());

        return new Item(description.trim(), price, category, imported);
    }

    private static Category categorize(String name) {
        if (name.contains("book")) return Category.BOOK;
        if (name.contains("chocolate") || name.contains("chocolates")) return Category.FOOD;
        if (name.contains("pills") || name.contains("tablet") || name.contains("medicine")) return Category.MEDICAL;
        return Category.OTHER;
    }
}
