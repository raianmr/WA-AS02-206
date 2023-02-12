package tasks.waas02206;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Store {
    record Product(String name, float price, int quantity) {
    }

    // maps usernames to passwords
    static final Map<String, String> users = new HashMap<>();
    // maps product names to product objects
    static final Map<String, Product> products = new HashMap<>();
    // maps usernames to carts, which in turn are stored as
    // maps from product names to their selected quantities
    static final Map<String, Map<String, Integer>> carts = new HashMap<>();

    static {
        users.put("user1", "pass1");
        users.put("user2", "pass2");
        users.put("user3", "pass3");

        products.put("Candles", new Product("Candles", 1000, 20));
        products.put("Hourglass", new Product("Hourglass", 50, 10));
        products.put("Wrist Watch", new Product("Wrist Watch", 1, 9));
    }

    private Store() {
    }
}
