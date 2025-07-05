package service;

import interfaces.Shippable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    private static final double SHIPPING_RATE = 10.0; // 10 per kg

    public static void processShipment(List<Shippable> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");

        // Group items by name and calculate total weight
        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }

        // Print shipment details
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(name);
            System.out.println(count + "x " + name + " " + (weight * 1000) + "g");
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }

    public static double calculateShippingFee(List<Shippable> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight * SHIPPING_RATE;
    }

}
