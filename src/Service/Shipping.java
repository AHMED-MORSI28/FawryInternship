package Service;

import Cart.Items;
import java.util.List;

public class Shipping {

    private static final double BASE_FEE    = 30.0;
    private static final double RATE_PER_KG = 50.0;   // = 0.05 per gram

    public static double cost(List<Items> lines) {

        double totalKg = 0.0;
        for (Items li : lines) {
            if (li.getProduct().isShippable()) {
                totalKg += li.getProduct().getWeight() * li.getQuantity();
            }
        }
        return BASE_FEE + (totalKg * RATE_PER_KG);
    }
}
