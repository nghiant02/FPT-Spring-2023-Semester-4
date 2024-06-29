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
//    your code here
    private Map<String, Integer> cart;

    public CART() {
    }

    public CART(Map<String, Integer> cart) {
        this.cart = cart;
    }

    public Map<String, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<String, Integer> cart) {
        this.cart = cart;
    }
    public void add(String id){
        if (cart == null) {
            cart = new HashMap<>();
        }
        int quantity = 1;
        if (cart.containsKey(id)) {
            quantity = cart.get(id) + 1;
        }
        cart.put(id, quantity);
    }
    public void delete(String id){
        if (this.cart==null) return;
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
            if (cart.isEmpty()) {
                cart = null;
            }
        }
    }
    public void update(String id, int quantity){
        if (cart == null) {
            return;
        }
        if(quantity == 0) return ;
        if (cart.containsKey(id)) {
            cart.put(id, quantity);
        }
    }
    
}
