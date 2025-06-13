package util;

import model.Category;
import model.Item;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    public Item parse(String input) throws InvalidItemFormatException {
        Pattern pattern = Pattern.compile("^(\\S+)\\s+(.+?)\\s+at\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);
    
        if (!matcher.matches()) {
            throw new InvalidItemFormatException("Input must match '[quantity] [description] at [price]'");
        }
    
        String quantityStr = matcher.group(1);
        String description = matcher.group(2);
        String priceStr = matcher.group(3);
    
        int quantity;
        try {
            if (quantityStr.contains(".")) {
                throw new InvalidItemFormatException("Quantity must be a whole number.");
            }
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                throw new InvalidItemFormatException("Quantity must be a positive whole number.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidItemFormatException("Quantity must be a whole number.");
        }
    
        BigDecimal price;
        try {
            price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
            throw new InvalidItemFormatException("Price must be a valid decimal number.");
        }
    
        if (price.scale() > 2) {
            throw new InvalidItemFormatException("Price must have at most two decimal places.");
        }
    
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidItemFormatException("Price cannot be negative.");
        }
    
        boolean imported = description.toLowerCase().contains("imported");
    
        String cleanDescription = description.toLowerCase().replace("imported", "").trim();
        Category category = categorize(cleanDescription);
    
        return new Item(description.trim(), price, category, imported);
    }
    
    
    


    private Category categorize(String name) {
        if (name.contains("book")) return Category.BOOK;
        if (name.contains("chocolate") || name.contains("chocolates")) return Category.FOOD;
        if (name.contains("pills") || name.contains("tablet") || name.contains("medicine")) return Category.MEDICAL;
        return Category.OTHER;
    }
}
