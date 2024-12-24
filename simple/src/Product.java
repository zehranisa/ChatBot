public class Product {
    private String name;
    private double price;
    private int stock;
    private int discountRate;

    public Product(String name, double price, int stock, int discountRate) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.discountRate = discountRate;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountedPrice() {
        return price * (1 - discountRate / 100.0);
    }

    public String productInfo() {
        return name + " - Price: " + price + " TL, Discounted Price: " + getDiscountedPrice() + " TL, Stok: " + stock + " adet";
    }
}
