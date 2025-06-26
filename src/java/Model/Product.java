/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author sontu
 */
public class Product {
    private int id;
    private String title;
    private String briefinfor;
    private String description;
    private String thumbnail;
    private String status;
    private String releaseTime;
    private int categoryID;
    private double price;
    private int quantity;
    private int hold;

    public Product() {
    }

    public Product(int id, String title, String briefinfor, String description, String thumbnail, String status, String releaseTime, int categoryID, double price, int quantity, int hold) {
        this.id = id;
        this.title = title;
        this.briefinfor = briefinfor;
        this.description = description;
        this.thumbnail = thumbnail;
        this.status = status;
        this.releaseTime = releaseTime;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.hold = hold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefinfor() {
        return briefinfor;
    }

    public void setBriefinfor(String briefinfor) {
        this.briefinfor = briefinfor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
