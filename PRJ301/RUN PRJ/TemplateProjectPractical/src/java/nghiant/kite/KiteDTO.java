/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiant.kite;

import java.io.Serializable;

/**
 *
 * @author nghia
 */
public class KiteDTO implements Serializable {

    private String id;
    private String name;
    private String size;
    private String color;
    private String level;
    private int outline;
    private boolean status;
    private int quantity;
    private float price;

    public KiteDTO() {
    }

    public KiteDTO(String id, String name, String size, String color, String level, int outline, boolean status, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.level = level;
        this.outline = outline;
        this.status = status;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getOutline() {
        return outline;
    }

    public void setOutline(int outline) {
        this.outline = outline;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
