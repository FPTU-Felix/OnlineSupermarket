/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class WarehouseProduct {

    private int productID;
    private String title, thumbnail;
    private int totalQuantity,totalHold;

    public WarehouseProduct() {
    }

    public WarehouseProduct(int productID, String title, String thumbnail, int totalQuantity, int totalHold) {
        this.productID = productID;
        this.title = title;
        this.thumbnail = thumbnail;
        this.totalQuantity = totalQuantity;
        this.totalHold = totalHold;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalHold() {
        return totalHold;
    }

    public void setTotalHold(int totalHold) {
        this.totalHold = totalHold;
    }

    
}
