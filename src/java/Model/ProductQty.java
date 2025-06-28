/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class ProductQty {

    private int productID,quantity, hold;

    public ProductQty() {
    }

    public ProductQty(int productID, int quantity, int hold) {
        this.productID = productID;
        this.quantity = quantity;
        this.hold = hold;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }


}
