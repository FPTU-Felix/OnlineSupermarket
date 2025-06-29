/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Oreh
 */
public class Receiver {

    private int receiverId;
    private Customer customer;
    private String receiverName;
    private String receiverEmail;
    private String mobile;
    private String gender;
    private String address;
    private String receiverType;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Receiver() {
    }

    public Receiver(int receiverId, Customer customer, String receiverName, String receiverEmail, String mobile, String gender, String address, String receiverType, LocalDate createdAt, LocalDate updatedAt) {
        this.receiverId = receiverId;
        this.customer = customer;
        this.receiverName = receiverName;
        this.receiverEmail = receiverEmail;
        this.mobile = mobile;
        this.gender = gender;
        this.address = address;
        this.receiverType = receiverType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

}
