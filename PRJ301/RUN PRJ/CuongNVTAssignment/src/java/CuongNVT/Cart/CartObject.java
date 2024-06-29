package CuongNVT.Cart;

import CuongNVT.Book.BookDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public CartObject() {
        items = new HashMap<>();
    }

    public void addBookToCart(String book, int quantity) {
        if (items != null) {
            if (items.containsKey(book)) {
                quantity = items.get(book) + quantity;
            }
            items.put(book, quantity);
        }
    }

    public void removeBookFromCart(String book) {
        if (items != null) {
            if (items.containsKey(book)) {
                items.remove(book);
            }
        }
    }

}
