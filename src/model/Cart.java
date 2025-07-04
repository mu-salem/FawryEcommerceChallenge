package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("ERROR: Quantity must be positive");
            return;
        }

        if (!product.isInStock(quantity)) {
            System.out.println("ERROR: Not enough stock for " + product.getName());
            return;
        }

        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            subtotal += product.getPrice() * quantity;
        }
        return subtotal;
    }

    public boolean requiresShipping() {
        for (Product product : items.keySet()) {
            if (product instanceof ShippableProduct) {
                return true;
            }
        }
        return false;
    }
}
