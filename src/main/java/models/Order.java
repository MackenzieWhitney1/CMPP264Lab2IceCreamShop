package models;

import javafx.scene.control.ListView;

import java.util.*;

import static helpers.Calculator.calculateTax;
import static helpers.Formatter.formatCurrency;

/**
 * Represents an order in the application.
 */
public class Order {
    private static double subtotal;
    private static Map<IceCream, Integer> iceCreamCount;

    public Order() {
        iceCreamCount = new HashMap<>();
        subtotal = 0;
    }

    public void addIceCreamToOrder(IceCream iceCream){
        iceCreamCount.put(iceCream, iceCreamCount.getOrDefault(iceCream, 0) + 1);
        calculateSubtotal();
    }

    public void calculateSubtotal(){
        subtotal = 0;
        iceCreamCount.forEach((iceCream, count) -> subtotal += iceCream.getPrice()*count);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public static Map<IceCream, Integer> getIceCreamCount() {
        return iceCreamCount;
    }

    public static void populateListViewWithIceCreamCountMap(ListView<String> listView){
        iceCreamCount.forEach((iceCream, count) -> listView.getItems().add(iceCream.toString() + " x" + count.toString()));
    }

    @Override
    public String toString() {
        double salesTax = calculateTax(subtotal);
        final StringBuilder sb = new StringBuilder("Order Summary \n");
        sb.append("Subtotal: ").append(formatCurrency(subtotal)).append("\n");
        sb.append("Sales Tax: ").append(formatCurrency(salesTax)).append("\n");
        sb.append("Total: ").append(formatCurrency(subtotal+salesTax));
        return sb.toString();
    }
}
