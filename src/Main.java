import model.*;
import service.CheckoutService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create products
        ExpirableShippableProduct cheese = new ExpirableShippableProduct("Cheese", 100, 5, 0.2, false);
        ExpirableShippableProduct biscuits = new ExpirableShippableProduct("Biscuits", 150, 3, 0.7, false);
        ShippableProduct tv = new ShippableProduct("TV", 500, 2, 5.0);
        Product scratchCard = new Product("Mobile scratch card", 50, 10);

        // Create customer
        Customer customer = new Customer("John Doe", 1000);

        // Create cart and add items
        Cart cart = new Cart();

        System.out.println("=== Adding items to cart ===");
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

        System.out.println("\n=== Checkout ===");
        CheckoutService.checkout(customer, cart);

        // Test corner cases
        System.out.println("\n=== Testing corner cases ===");

        // Test empty cart
        Cart emptyCart = new Cart();
        System.out.println("Testing empty cart:");
        CheckoutService.checkout(customer, emptyCart);

        // Test insufficient balance
        Customer poorCustomer = new Customer("Poor Customer", 100);
        Cart expensiveCart = new Cart();
        expensiveCart.add(tv, 1);
        System.out.println("\nTesting insufficient balance:");
        CheckoutService.checkout(poorCustomer, expensiveCart);

        // Test expired product
        ExpirableProduct expiredCheese = new ExpirableProduct("Expired Cheese", 100, 5, true);
        Cart expiredCart = new Cart();
        expiredCart.add(expiredCheese, 1);
        System.out.println("\nTesting expired product:");
        CheckoutService.checkout(customer, expiredCart);

        // Test out of stock
        Product outOfStock = new Product("Out of Stock Item", 100, 0);
        Cart stockCart = new Cart();
        System.out.println("\nTesting out of stock:");
        stockCart.add(outOfStock, 1);
    }
}
