/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.time.LocalDate;

public class Receiver {
    private int receiverID,userID;
    private String receiverName, email, mobile, gender,address, receiverType, createdAt, updatedAt;
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

    public Receiver(int receiverID, int userID, String receiverName, String email, String mobile, String gender, String address, String receiverType, String createdAt, String updatedAt) {
        this.receiverID = receiverID;
        this.userID = userID;
        this.receiverName = receiverName;
        this.email = email;
    this.mobile = mobile;
        this.gender = gender;
        this.address = address;
        this.receiverType = receiverType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
        }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
