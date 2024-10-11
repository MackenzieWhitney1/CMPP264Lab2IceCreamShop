package helpers;

import java.text.NumberFormat;

// helper to move responsibility from display layer or model classes on formatting
public class Formatter {
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();

    public static String formatCurrency(double value){
        return currency.format(value);
    }
}
