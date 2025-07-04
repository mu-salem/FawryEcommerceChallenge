package service;

import model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutService {
    private static final double SHIPPING_FEE = 30.0;
    private ShippingService shippingService;

    public CheckoutService() {
        this.shippingService = new ShippingService();
    }

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("ERROR: model.Cart is empty");
            return;
        }

        // Check for expired products and out of stock
        Map<Product, Integer> cartItems = cart.getItems();
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int requestedQuantity = entry.getValue();

            // Check if product is expired
            if (product instanceof ExpirableProduct) {
                ExpirableProduct expirableProduct = (ExpirableProduct) product;
                if (expirableProduct.isExpired()) {
                    System.out.println("ERROR: Product " + product.getName() + " is expired");
                    return;
                }
            }

            // Check if product is out of stock
            if (!product.isInStock(requestedQuantity)) {
                System.out.println("ERROR: Product " + product.getName() + " is out of stock");
                return;
            }
        }

        // Calculate subtotal and shipping
        double subtotal = cart.calculateSubtotal();
        double shippingFee = cart.requiresShipping() ? SHIPPING_FEE : 0.0;
        double totalAmount = subtotal + shippingFee;

        // Check customer balance
        if (customer.getBalance() < totalAmount) {
            System.out.println("ERROR: Customer's balance is insufficient");
            return;
        }

        // Process shipping if needed
        Map<ShippableProduct, Integer> shippableItems = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof ShippableProduct) {
                ShippableProduct shipProduct = (ShippableProduct) product;
                shippableItems.put(shipProduct, shippableItems.getOrDefault(shipProduct, 0) + quantity);
            }
        }

        if (!shippableItems.isEmpty()) {
            shippingService.shipProducts(shippableItems);
        }

        // Reduce product quantities
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            product.reduceQuantity(quantity);
        }

        // Deduct amount from customer balance
        customer.deductBalance(totalAmount);

        // Print checkout receipt
        printCheckoutReceipt(cartItems, subtotal, shippingFee, totalAmount, customer.getBalance());
    }

    private void printCheckoutReceipt(Map<Product, Integer> cartItems, double subtotal,
                                      double shippingFee, double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName() + " " + (int)(product.getPrice() * quantity));
        }

        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);

        if (shippingFee > 0) {
            System.out.println("Shipping " + (int)shippingFee);
        }

        System.out.println("Amount " + (int)totalAmount);
        System.out.println("Remaining balance: " + remainingBalance);
    }
}