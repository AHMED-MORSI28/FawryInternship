package Cart;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Product.Product;

/**
 * Holds the shopper’s current selections
 * – now checks stock when adding.
 */
public class Cart {

    private final List<Items> lines = new ArrayList<>();

    /** Add product with validation */
    public void add(Product p, int qty) {

        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (qty > p.getQuantity()) {
            throw new IllegalStateException("Requested quantity exceeds available stock");
        }

        // merge with existing line if present
        for (Iterator<Items> it = lines.iterator(); it.hasNext();) {
            Items li = it.next();
            if (li.getProduct().getName().equals(p.getName())) {
                int newQty = li.getQuantity() + qty;
                if (newQty > p.getQuantity()) {
                    throw new IllegalStateException("Requested quantity exceeds available stock");
                }
                it.remove();                       // replace
                lines.add(new Items(p, newQty));
                return;
            }
        }
        lines.add(new Items(p, qty));
    }

    public List<Items> getItems() { return lines; }

    public double subTotal() {
        double total = 0.0;
        for (Items li : lines) total += li.getLineTotal();
        return total;
    }
}
