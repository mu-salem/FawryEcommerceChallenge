package model;

import java.time.LocalDate;

public class Cheese extends Product implements ExpirableProduct, ShippableProduct {
    private LocalDate expiryDate;
    private double weight;

    public Cheese(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}

