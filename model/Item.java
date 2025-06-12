package model;

import java.math.BigDecimal;

public record Item(String name, BigDecimal price, Category category, boolean imported) {}
