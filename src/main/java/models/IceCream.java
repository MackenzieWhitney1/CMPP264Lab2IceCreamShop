package models;
import java.util.ArrayList;
import java.util.Objects;

import static helpers.Formatter.formatCurrency;

/**
 * Represents an ice cream item that can be added to an order.
 * If there are more items added to this application, this can extend an Item class.
 */
public class IceCream {
    private Size size;
    private ArrayList<Flavor> scoopFlavors;
    private ArrayList<Extra> extras;
    private double price;

    public IceCream(Size size, ArrayList<Flavor> scoopFlavors, ArrayList<Extra> extras) {
        this.size = size;
        this.scoopFlavors = scoopFlavors;
        this.extras = extras;
        setPrice();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ArrayList<Flavor> getScoopFlavors() {
        return scoopFlavors;
    }

    public void setScoopFlavors(ArrayList<Flavor> scoopFlavors) {
        this.scoopFlavors = scoopFlavors;
    }

    public ArrayList<Extra> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<Extra> extras) {
        this.extras = extras;
    }

    public void setPrice(){
        double sizePrice = size.getPrice();
        double extrasPrice = 0; //initialize as 0
        if(!extras.isEmpty()) {
            for (Extra extra : extras){
                extrasPrice += extra.getPrice();
            }
        }
        this.price = sizePrice + extrasPrice;
    }

    public double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(size.name()).append(" ");
        sb.append(scoopFlavors);
        if(!extras.isEmpty()) {
            sb.append(", Extras: ");
            for(Extra extra: extras){
                sb.append(extra.getLabel());
                sb.append(" ");
            }
        }
        sb.append(" Price: ").append(formatCurrency(price));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IceCream iceCream = (IceCream) o;
        return Double.compare(price, iceCream.price) == 0
                && size == iceCream.size
                && Objects.equals(scoopFlavors, iceCream.scoopFlavors)
                && Objects.equals(extras, iceCream.extras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, scoopFlavors, extras, price);
    }
}
