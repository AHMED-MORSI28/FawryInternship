package Service;
import Product.Product;
import Product.IsExpire;
import java.util.List;

public class Inventory {

    private List<Product> products;

    public Inventory(List<Product> products) {
        this.products = products;
    }

    public void list() {
        System.out.println("\n--- Inventory ---");
        int i = 1;
        for (Product p : products) {
            String line = i + ") "                       // item number
                    + p.getName()                        // product name
                    + " | $" + String.format("%.2f", p.getPrice())   // price
                    + " | qty: " + p.getQuantity()       // quantity
                    + " | ship: " + (p.isShippable() ? "Y" : "N")    // shipping flag
                    + " | exp: " + (p.getIsExpire() == IsExpire.mayExpire ? "Y" : "N");
            System.out.println(line);
            i++;
        }
        System.out.println("-----------------\n");
    }
    public Product byNumber(int quantitySelected) {
        return products.get(quantitySelected - 1);
    }
}
