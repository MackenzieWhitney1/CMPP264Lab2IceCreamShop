package models;
import static helpers.Formatter.formatCurrency;

/**
 * Enum to define possible sizes of ice creams
 */
public enum Size {
    KIDS(1, 3.50),
    SMALL( 1, 5.50),
    DOUBLE(2, 7.50),
    TRIPLE(3, 9.50);

    Size(int numScoops, double price) {
        this.numScoops = numScoops;
        this.price = price;
    }

    private int numScoops;
    private double price;

    public int getNumScoops() {
        return numScoops;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        String properCaseSizeName = name().charAt(0) + name().substring(1).toLowerCase();
        final StringBuilder sb = new StringBuilder(properCaseSizeName).append(", ");
        sb.append(numScoops).append(" scoops. ");
        sb.append(formatCurrency(price));
        return sb.toString();
    }
}
