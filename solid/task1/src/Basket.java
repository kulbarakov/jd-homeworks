import java.util.HashMap;
import java.util.Map;

//Single Responsibility Principle
public class Basket {
    private Map<Product, Integer> products = new HashMap<>();
    private double sum;
    private Store store;

    public Basket(Store store) {
        this.store = store;
    }

    public void addProduct(Product product, int count) {
        if (products.containsKey(product)) {
            int newCount = products.get(product) + count;
            products.put(product, newCount);
            store.sellProduct(product, count);
        } else {
            products.put(product, count);
            store.sellProduct(product, count);
        }
    }

    public void removeProduct(Product product) {
        store.returnProduct(product, products.get(product));
        products.remove(product);
    }

    public double getSum() {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            sum += (entry.getKey().getPrice() * entry.getValue());
        }
        return sum;
    }

    public int getProductCount(Product product) {
        return products.getOrDefault(product, -1);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void buy() {
        products.clear();
    }

    public void clear() {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            removeProduct(entry.getKey());
        }
    }
}
