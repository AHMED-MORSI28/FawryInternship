import Cart.Cart;
import Customer.Customer;
import Product.Product;
import Service.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    private static void printMenu() {
        System.out.println("----------- Menu -----------");
        System.out.println("1. View cart");
        System.out.println("2. Add item to cart");
        System.out.println("3. Checkout");
        System.out.println("4. View account");
        System.out.println("5. Exit");
        System.out.print("Select option: ");
    }

    private static void printCart(Cart cart) {
        System.out.println("\n--- Current cart ---");
        if (cart.getItems().isEmpty()) {
            System.out.println("(cart is empty)");
        } else {
            for (var li : cart.getItems()) {
                System.out.println(li.getQuantity() + " x "
                        + li.getProduct().getName()
                        + " = " + DF.format(li.getLineTotal()));
            }
            System.out.println("Subtotal: " + DF.format(cart.subTotal()));
        }
        System.out.println("--------------------\n");
    }

    public static void main(String[] args) throws Exception {

        List<Product> catalog = ProductLoader.loadProductsFromCSV("products.csv");
        Inventory inventory   = new Inventory(catalog);

        Customer customer = new Customer("ahmed", 30000);
        Cart cart         = new Cart();
        Scanner in        = new Scanner(System.in);

        boolean running = true;
        while (running) {

            inventory.list();
            printMenu();

            String choice = in.nextLine().trim();

            switch (choice) {

                case "1":   // view cart
                    printCart(cart);
                    break;

                case "2":   // add item
                    try {
                        System.out.print("Enter item number: ");
                        int idx = Integer.parseInt(in.nextLine().trim());

                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(in.nextLine().trim());

                        cart.add(inventory.byNumber(idx), qty);
                        System.out.println("Item added successfully.\n");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;

                case "3":   // checkout
                    try {
                        Checkout.process(customer, cart);
                        cart = new Cart();   // start a fresh cart
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage() + "\n");
                    }
                    break;

                case "4":   // account info
                    System.out.println("\nAccount holder: " + customer.getName());
                    System.out.println("Current balance: " + DF.format(customer.getBalance()) + "\n");
                    break;

                case "5":   // exit
                    running = false;
                    System.out.println("Good‑bye!");
                    break;

                default:
                    System.out.println("Please enter a number between 1 and 5.\n");
            }
        }
    }
}
