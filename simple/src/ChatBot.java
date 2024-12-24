import java.util.Scanner;

public class ChatBot {
    private ProductCatalog catalog;
    private boolean isEmailVerified = false;

    public ChatBot(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public void startInteraction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! How can I assist you?");
        System.out.println("You can ask about the available products by typing 'what products are available?'.");
        System.out.println("Type 'exit' to end the conversation.");

        while (true) {
            System.out.print("User: ");
            String question = scanner.nextLine().toLowerCase();

            if (question.contains("what products")) {
                listProducts();
                askForEmail(scanner);
            } else if (question.contains("price")) {
                askForProductPrice(scanner);
            } else if (question.contains("stock")) {
                askForProductStock(scanner);
            } else if (question.contains("color")) {
                askForProductColor(scanner);
            } else if (question.contains("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                suggestKeywords();
            }
        }
        scanner.close();
    }

    private void listProducts() {
        System.out.println("Available Products: ");
        for (Product product : catalog.getProducts()) {
            System.out.println(product.productInfo());
        }
    }

    private void showDiscountedPrices() {
        if (isEmailVerified) {
            System.out.println("Discounted Prices: ");
            for (Product product : catalog.getProducts()) {
                System.out.println(product.productInfo());
            }
        } else {
            System.out.println("To view discounted prices, you need to verify your email address.");
            askForEmail(new Scanner(System.in));
        }
    }

    private void askForEmail(Scanner scanner) {
        if (!isEmailVerified) {
            System.out.println("Please enter your email address:");
            String email = scanner.nextLine();
            if (isValidEmail(email)) {
                isEmailVerified = true;
                System.out.println("Email verified!");
                showDiscountedPrices();
            } else {
                System.out.println("Invalid email address.");
                askForEmail(scanner);
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    private void suggestKeywords() {
        System.out.println("Sorry, I didn't understand. Here are some keywords that might help:");
        System.out.println("1. 'Product Price' - To learn the price of a product");
        System.out.println("2. 'Stock Information' - To learn the stock status of a product");
        System.out.println("3. 'Color' - To learn the color information of a product");
        System.out.println("4. 'Available Products' - To list all available products");
    }

    private void askForProductPrice(Scanner scanner) {
        System.out.print("Which product would you like to know the price of? ");
        String productName = scanner.nextLine();
        Product product = catalog.findProductByName(productName);

        if (product != null) {
            System.out.println(product.productInfo());
        } else {
            System.out.println("Product not found.");
        }
    }

    private void askForProductStock(Scanner scanner) {
        System.out.print("Which product would you like to know the stock of? ");
        String productName = scanner.nextLine();
        Product product = catalog.findProductByName(productName);

        if (product != null) {
            System.out.println("There are " + product.getStock() + " units of " + product.getName() + " in stock.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void askForProductColor(Scanner scanner) {
        System.out.print("Which product's color would you like to know? ");
        String productName = scanner.nextLine();
        Product product = catalog.findProductByName(productName);

        if (product != null && product instanceof AccessoryProduct) {
            AccessoryProduct accessoryProduct = (AccessoryProduct) product;
            System.out.println("The color of " + accessoryProduct.getName() + " is " + accessoryProduct.getColor() + ".");
        } else {
            System.out.println("Product not found or the product doesn't have color information.");
        }
    }

    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Product("Laptop", 5000, 20, 10));
        catalog.addProduct(new Product("Phone", 3000, 15, 5));
        catalog.addProduct(new AccessoryProduct("Headphones", 500, 30, "White", 0));

        ChatBot chatBot = new ChatBot(catalog);
        chatBot.startInteraction();
    }
}
