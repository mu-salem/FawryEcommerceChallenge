package service;

import model.ShippableProduct;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public void shipProducts(Map<ShippableProduct, Integer> shippableItems) {
        if (shippableItems.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;

        for (var entry : shippableItems.entrySet()) {
            ShippableProduct product = entry.getKey();
            int quantity = entry.getValue();
            double productWeight = product.getWeight() * quantity;
            System.out.println(quantity + "x " + product.getName() + " " + (int)(productWeight * 1000) + "g");
            totalWeight += productWeight;
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}
