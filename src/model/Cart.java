package model;

import interfaces.Shippable;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be positive");
            return;
        }

        if (!product.isInStock(quantity)) {
            System.out.println("Error: Not enough " + product.getName() + " in stock");
            return;
        }

        items.add(new CartItem(product, quantity));
        System.out.println("Added " + quantity + "x " + product.getName() + " to cart");
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof Shippable) {
                // Add each individual item based on quantity
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add((Shippable) item.getProduct());
                }
            }
        }
        return shippableItems;
    }

    public void clear() {
        items.clear();
    }
}
