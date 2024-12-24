public class AccessoryProduct extends Product {
    private String color;

    public AccessoryProduct(String name, double price, int stock, String color, int discountRate) {
        super(name, price, stock, discountRate);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String productInfo() {
        return super.productInfo() + ", Color: " + color;
    }
}
