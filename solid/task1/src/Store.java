import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Single Responsibility Principle
public class Store {
    private int number;
    private Map<Product, Integer> products = new HashMap<>();

    public Store(int number, Product[] products) {
        this.number = number;
        //добавление продуктов на склад, количество продукта на складе генерируется рандомно (1-100)
        for (Product p : products) {
            this.products.put(p, new Random().nextInt(100) + 1);
        }
    }

    public int getNumber() {
        return number;
    }

    public void returnProduct (Product product, int count) {
        int newCount = products.get(product) + count;
        products.put(product, newCount);
    }

    public void sellProduct(Product product, int count) {
        int newCount = products.get(product) - count;
        products.put(product, newCount);
    }

    public boolean hasProduct(Product product, int count) {
        return (products.get(product) >= count);
    }

    public int getProductBalance(Product product) {
        return products.get(product);
    }
}
