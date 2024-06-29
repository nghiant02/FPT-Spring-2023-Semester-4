/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.spring23.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hd
 */
public class CART {

    private Map<String, Product> cart;

    public CART() {
    }

    public CART(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public boolean add(Product p) {
        boolean result = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(p.getProductID())) {
            int currentQuantity = this.cart.get(p.getProductID()).getQuantity();
            p.setQuantity(currentQuantity + p.getQuantity());

        }
        this.cart.put(p.getProductID(), p);
        result = true;
        return result;
    }

    public boolean update(String id, Product p) {
        boolean result = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, p);
                result = true;
            }
        }
        return result;
    }

    public boolean remove(String id) {
        boolean result = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.remove(id);
                result = true;
            }
        }
        return result;
    }
}
