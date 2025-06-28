/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Anh Tuan
 */
public class OrderInfo {

    private int orderID, customerID;
    private String customerName, firstTitle;
    private int otherProducts;
    private double totalCost;
    private int statusID;
    private String statusName, paymentMethod;
    private Date createdOrder;
    private int saleID;
    private String saleName,mobile,receiverAddress,saleNotes;

    public OrderInfo() {
    }

    public OrderInfo(int orderID, int customerID, String customerName, String firstTitle, int otherProducts, double totalCost, int statusID, String statusName, String paymentMethod, Date createdOrder, int saleID, String saleName, String mobile, String receiverAddress, String saleNotes) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.firstTitle = firstTitle;
        this.otherProducts = otherProducts;
        this.totalCost = totalCost;
        this.statusID = statusID;
        this.statusName = statusName;
        this.paymentMethod = paymentMethod;
        this.createdOrder = createdOrder;
        this.saleID = saleID;
        this.saleName = saleName;
        this.mobile = mobile;
        this.receiverAddress = receiverAddress;
        this.saleNotes = saleNotes;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public void setFirstTitle(String firstTitle) {
        this.firstTitle = firstTitle;
    }

    public int getOtherProducts() {
        return otherProducts;
    }

    public void setOtherProducts(int otherProducts) {
        this.otherProducts = otherProducts;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreatedOrder() {
        return createdOrder;
    }

    public void setCreatedOrder(Date createdOrder) {
        this.createdOrder = createdOrder;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getSaleNotes() {
        return saleNotes;
    }

    public void setSaleNotes(String saleNotes) {
        this.saleNotes = saleNotes;
    }

    
}
