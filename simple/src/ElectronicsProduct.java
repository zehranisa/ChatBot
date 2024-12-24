public class ElectronicsProduct extends Product {
    private String screenSize;

    public ElectronicsProduct(String name, double price, int stock, String screenSize, int discountRate) {
        super(name, price, stock, discountRate);
        this.screenSize = screenSize;
    }

    public String getScreenSize() {
        return screenSize;
    }

    @Override
    public String productInfo() {
        return super.productInfo() + ", Screen Size: " + screenSize;
    }
}
