import model.*;
import service.CheckoutService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Customer with sufficient balance
        Customer customer = new Customer("Mustafa", 2000.0);

        // Products (name, price, quantity, [expiryDate], weight)
        Cheese cheese = new Cheese("Cheese", 100.0, 10, LocalDate.now().plusDays(7), 0.2);
        Biscuits biscuits = new Biscuits("Biscuits", 150.0, 5, LocalDate.now().plusDays(10), 0.7);
        TV tv = new TV("TV", 500.0, 3, 10.0);
        ScratchCard scratchCard = new ScratchCard("Scratch Card", 50.0, 20);

        // Add items to cart
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
//        cart.add(tv, 3);
//        cart.add(scratchCard, 1);

        // Perform checkout
        CheckoutService checkoutService = new CheckoutService();
        checkoutService.checkout(customer, cart);
    }
}
