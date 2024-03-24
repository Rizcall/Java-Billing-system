import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class Main {
    private static Map<String, Product> products = new HashMap<>();

    public static void main(String[] args) {
        initializeProducts();

        Scanner scanner = new Scanner(System.in);
        Map<Product, Integer> cart = new HashMap<>();

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addToCart(scanner, cart);
                    break;
                case 3:
                    displayCart(cart);
                    break;
                case 4:
                    generateBill(cart);
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeProducts() {
        products.put("apple", new Product("Apple", 2.5));
        products.put("banana", new Product("Banana", 1.0));
        products.put("orange", new Product("Orange", 1.5));
        products.put("milk", new Product("Milk", 3.0));
        // Add more products as needed
    }

    private static void displayMenu() {
        System.out.println("\nSupermarket Billing System Menu:");
        System.out.println("1. Display Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. Display Cart");
        System.out.println("4. Generate Bill and Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product product : products.values()) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }

    private static void addToCart(Scanner scanner, Map<Product, Integer> cart) {
        System.out.print("Enter the name of the product: ");
        String productName = scanner.next().toLowerCase();
        if (products.containsKey(productName)) {
            Product product = products.get(productName);
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity > 0) {
                cart.put(product, cart.getOrDefault(product, 0) + quantity);
                System.out.println("Product added to cart.");
            } else {
                System.out.println("Invalid quantity.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void displayCart(Map<Product, Integer> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("\nCart Contents:");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getName() + " - $" + product.getPrice() + " x " + quantity);
            }
        }
    }

    private static void generateBill(Map<Product, Integer> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. No bill generated.");
        } else {
            double total = 0.0;
            System.out.println("\nBill Details:");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                double price = product.getPrice() * quantity;
                System.out.println(product.getName() + " - $" + product.getPrice() + " x " + quantity + " = $" + price);
                total += price;
            }
            System.out.println("Total Amount: $" + total);
            System.out.println("Thank you for shopping with us!");
        }
    }
}
