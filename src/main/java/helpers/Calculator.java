package helpers;

// Helper to move responsibility from display layer or model classes on calculations.
public class Calculator {
    final private static double TAX = 0.05;
    public static double calculateTax(double value){
        return value*TAX;
    }
}
