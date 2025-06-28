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
public class Order {

    private int orderID, customerD;
    private String receiverName, receiverGender, receiverEmail, receiverMobile, receiverAddress, receiverNotes;
    private int statusID;
    private String statusName;
    private String paymentMethod;
    private Date createdOrder;
    private int saleID;
    private String saleNotes;

    public Order() {
    }

    public Order(int orderID, int customerD, String receiverName, String receiverGender, String receiverEmail, String receiverMobile, String receiverAddress, String receiverNotes, int statusID, String statusName, String paymentMethod, Date createdOrder, int saleID, String saleNotes) {
        this.orderID = orderID;
        this.customerD = customerD;
        this.receiverName = receiverName;
        this.receiverGender = receiverGender;
        this.receiverEmail = receiverEmail;
        this.receiverMobile = receiverMobile;
        this.receiverAddress = receiverAddress;
        this.receiverNotes = receiverNotes;
        this.statusID = statusID;
        this.statusName = statusName;
        this.paymentMethod = paymentMethod;
        this.createdOrder = createdOrder;
        this.saleID = saleID;
        this.saleNotes = saleNotes;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerD() {
        return customerD;
    }

    public void setCustomerD(int customerD) {
        this.customerD = customerD;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverGender() {
        return receiverGender;
    }

    public void setReceiverGender(String receiverGender) {
        this.receiverGender = receiverGender;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverNotes() {
        return receiverNotes;
    }

    public void setReceiverNotes(String receiverNotes) {
        this.receiverNotes = receiverNotes;
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

    public String getSaleNotes() {
        return saleNotes;
    }

    public void setSaleNotes(String saleNotes) {
        this.saleNotes = saleNotes;
    }

    
}
