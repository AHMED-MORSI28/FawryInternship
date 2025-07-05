package Cart;

import Product.Product;

public class Items {
    private Product product;
    private int     quantity;

    public Items(Product product, int quantity) {
        this.product  = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int     getQuantity() { return quantity; }
    public double  getLineTotal() { return product.getPrice() * quantity; }
}
