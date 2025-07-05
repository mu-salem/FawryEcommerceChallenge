package service;

import interfaces.Shippable;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;

import java.util.List;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        // Check if cart is empty
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return;
        }

        // Check for expired or out of stock products
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (product.isExpired()) {
                System.out.println("Error: " + product.getName() + " is expired");
                return;
            }

            if (!product.isInStock(item.getQuantity())) {
                System.out.println("Error: " + product.getName() + " is out of stock");
                return;
            }
        }

        // Calculate costs
        double subtotal = cart.getSubtotal();
        List<Shippable> shippableItems = cart.getShippableItems();
        double shippingFee = ShippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFee;

        // Check if customer can afford it
        if (!customer.canAfford(totalAmount)) {
            System.out.println("Error: Insufficient balance. Need: " + totalAmount +
                    ", Available: " + customer.getBalance());
            return;
        }

        // Process shipment if needed
        if (!shippableItems.isEmpty()) {
            ShippingService.processShipment(shippableItems);
        }

        // Update product quantities
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }

        // Deduct amount from customer balance
        customer.deductBalance(totalAmount);

        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                    " " + (int) item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int) subtotal);
        if (shippingFee > 0) {
            System.out.println("Shipping " + (int) shippingFee);
        }
        System.out.println("Amount " + (int) totalAmount);
        System.out.println("Customer balance after payment: " + customer.getBalance());
        System.out.println("END.");

        // Clear cart after successful checkout
        cart.clear();
    }
}