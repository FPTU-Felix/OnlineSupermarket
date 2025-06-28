/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class ProductStock {
    private double OriginalPrice;
    private int quantity;

    public ProductStock() {
    }

    public ProductStock(double OriginalPrice, int quantity) {
        this.OriginalPrice = OriginalPrice;
        this.quantity = quantity;
    }

    public double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    
}
