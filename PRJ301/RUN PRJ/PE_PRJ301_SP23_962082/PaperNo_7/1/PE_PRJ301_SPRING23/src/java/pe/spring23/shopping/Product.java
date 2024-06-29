/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.spring23.shopping;

/**
 *
 * @author hd
 */
public class Product {

    private String productID;
    private String productName;
    private String description;
    private double price;
    private boolean status;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", description=" + description + ", price=" + price + ", status=" + status + '}';
    }

    public Product(String productID, String productName, String description, double price, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    
}
