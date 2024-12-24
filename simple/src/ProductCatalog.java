import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findProductByName(String name) {
        name = name.toLowerCase().trim();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name)) {
                return product;
            }
        }
        return null;
    }

    public Product findProductByColor(String color) {
        color = color.toLowerCase().trim();

        for (Product product : products) {
            if (product instanceof AccessoryProduct) {
                AccessoryProduct accessoryProduct = (AccessoryProduct) product;
                if (accessoryProduct.getColor().toLowerCase().equals(color)) {
                    return accessoryProduct;
                }
            }
        }
        return null;
    }
}
