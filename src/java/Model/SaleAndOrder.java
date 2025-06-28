/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class SaleAndOrder {
    private int saleID;
    private String saleName;
    private int totalOrder;

    public SaleAndOrder() {
    }

    public SaleAndOrder(int saleID, String saleName, int totalOrder) {
        this.saleID = saleID;
        this.saleName = saleName;
        this.totalOrder = totalOrder;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }
    
    
}
