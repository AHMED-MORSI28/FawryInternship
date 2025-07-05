package Service;

import Cart.Cart;
import Cart.Items;
import Customer.Customer;
import Product.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Checkout now strips expired lines instead of aborting.
 */
public class Checkout {

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static void process(Customer cust, Cart cart) {

        List<Items> lines = cart.getItems();
        if (lines.isEmpty()) {
            throw new IllegalStateException("Your cart is empty");
        }

        // --- pass 1: find expired + stock issues ---
        List<String> expiredNames = new ArrayList<>();

        for (Iterator<Items> it = lines.iterator(); it.hasNext(); ) {
            Items line = it.next();
            Product p  = line.getProduct();

            if (p.hasExpired()) {
                expiredNames.add(p.getName());
                it.remove();                   // remove from cart
                continue;                      // no further checks on this line
            }

            if (line.getQuantity() > p.getQuantity()) {
                throw new IllegalStateException("Insufficient stock for " + p.getName());
            }
        }

        if (!expiredNames.isEmpty()) {
            System.out.println("Notice: expired items removed from cart -> " + expiredNames);
        }
        if (lines.isEmpty()) {
            throw new IllegalStateException("Your cart is empty after removing expired items");
        }

        // --- pricing ---
        double sub   = cart.subTotal();       // cart now reflects removals
        double ship  = Shipping.cost(lines);
        double total = sub + ship;

        if (cust.getBalance() < total) {
            throw new IllegalStateException("Customer balance too low");
        }

        // --- apply changes ---
        for (Items it : lines) {
            Product p = it.getProduct();
            p.setQuantity(p.getQuantity() - it.getQuantity());
        }
        cust.debit(total);

        // --- receipt ---
        // --- receipt ---
        System.out.println();
        System.out.println("----- Receipt -----");

        double totalKg = 0.0; // <‑‑ accumulate shipping weight

        for (Items li : lines) {
            Product p = li.getProduct();
            double lineTotal = li.getLineTotal();

            if (p.isShippable()) {
                double lineKg = p.getWeight() * li.getQuantity();
                totalKg += lineKg;

                // weight shown only for shippable items
                System.out.println(li.getQuantity() + " x "
                        + p.getName()
                        + " (" + DF.format(lineKg) + " kg)"
                        + " = " + DF.format(lineTotal));
            } else {
                // non‑shippable: no weight shown
                System.out.println(li.getQuantity() + " x "
                        + p.getName()
                        + " = " + DF.format(lineTotal));
            }
        }

        System.out.println("Subtotal: "     + DF.format(sub));
        System.out.println("Total weight: " + DF.format(totalKg) + " kg");
        System.out.println("Shipping: "     + DF.format(ship));
        System.out.println("Total: "        + DF.format(total));
        System.out.println("Balance left: " + DF.format(cust.getBalance()));
        System.out.println("-------------------\n");

    }
}
