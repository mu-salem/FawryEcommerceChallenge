package model;

import interfaces.Shippable;

public class ExpirableShippableProduct extends Product implements Shippable {
    private boolean expired;
    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, double weight, boolean expired) {
        super(name, price, quantity);
        this.weight = weight;
        this.expired = expired;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
