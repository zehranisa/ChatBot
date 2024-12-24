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
                listProductNames();
            } else if (question.contains("price")) {
                askForProductPrice(scanner);
            } else if (question.contains("stock")) {
                askForProductStock(scanner);
            } else if (question.contains("color")) {
                askForProductColor(scanner);
            } else if (question.contains("screen size")) {
                askForProductScreenSize(scanner);
            } else if (question.contains("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                suggestKeywords();
            }
        }
        scanner.close();
    }

    private void listProductNames() {
        System.out.println("Available Products: ");
        for (Product product : catalog.getProducts()) {
            System.out.println("- " + product.getName());
        }
    }

    private void askForEmail(Scanner scanner) {
        if (!isEmailVerified) {
            System.out.println("Please enter your email address to view discounted prices:");
            String email = scanner.nextLine();
            if (isValidEmail(email)) {
                isEmailVerified = true;
                System.out.println("Email verified!");
            } else {
                System.out.println("Invalid email address. Please try again.");
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
        System.out.println("4. 'Screen Size' - To learn the screen size of a product");
        System.out.println("5. 'Available Products' - To list all available products");
    }

    private void askForProductPrice(Scanner scanner) {
        System.out.print("Which product would you like to know the price of? ");
        String productName = scanner.nextLine();
        Product product = catalog.findProductByName(productName);

        if (product != null) {
            if (!isEmailVerified) {
                System.out.println("To view the discounted price, please provide your email address.");
                askForEmail(scanner);
            }
            System.out.println("Price: " + product.getPrice() + " TL");
            System.out.println("Discounted Price: " + product.getDiscountedPrice() + " TL");
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

    private void askForProductScreenSize(Scanner scanner) {
        System.out.print("Which product's screen size would you like to know? ");
        String productName = scanner.nextLine();
        Product product = catalog.findProductByName(productName);

        if (product != null && product instanceof ElectronicsProduct) {
            ElectronicsProduct electronicsProduct = (ElectronicsProduct) product;
            System.out.println("The screen size of " + electronicsProduct.getName() + " is " + electronicsProduct.getScreenSize() + ".");
        } else {
            System.out.println("Product not found or the product doesn't have screen size information.");
        }
    }

    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Product("Laptop", 5000, 20, 10));
        catalog.addProduct(new Product("Phone", 3000, 15, 5));
        catalog.addProduct(new AccessoryProduct("Headphones", 500, 30, "White", 0));
        catalog.addProduct(new ElectronicsProduct("TV", 15000, 10, "55 inch", 15));

        ChatBot chatBot = new ChatBot(catalog);
        chatBot.startInteraction();
    }
}
