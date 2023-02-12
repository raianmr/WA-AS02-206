package tasks.waas02206;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Store {
    record Product(String name, float price, int quantity) {
    }

    // all available products
    static final List<Product> products = new ArrayList<>();
    // maps usernames to passwords
    static final Map<String, String> users = new HashMap<>();
    // maps usernames to carts, which in turn are stored as
    // maps from products to their selected quantities
    static final Map<String, Map<Product, Integer>> carts = new HashMap<>();

    static {
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("user3", "pass3");

        products.add(new Product("Candles", 1000, 20));
        products.add(new Product("Hourglass", 50, 10));
        products.add(new Product("Wrist Watch", 1, 9));
    }

    private Store() {
    }
}
