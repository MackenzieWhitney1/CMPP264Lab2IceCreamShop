package models;
import static helpers.Formatter.formatCurrency;

/**
 * Enum to define possible ice cream extras.
 */
public enum Extra {
    WAFFLE("Waffle Cone/Bowl", Extra.WAFFLE_SIDE_PRICE),
    CHOCOLATE("Chocolate", Extra.NON_WAFFLE_SIDE_PRICE),
    CANDY("Candy", Extra.NON_WAFFLE_SIDE_PRICE),
    FRUIT("Fruit", Extra.NON_WAFFLE_SIDE_PRICE),
    NUTS("Nuts", Extra.NON_WAFFLE_SIDE_PRICE);

    private String label;
    private double price;

    Extra(String label, double price) {
        this.label = label;
        this.price = price;
    }

    // constants for prices
    private static final double WAFFLE_SIDE_PRICE = 1.50;
    private static final double NON_WAFFLE_SIDE_PRICE = 0.5;

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(label);
        sb.append(", ").append(formatCurrency(price));
        return sb.toString();
    }
}
