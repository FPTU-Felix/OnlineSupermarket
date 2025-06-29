package Model;

import java.util.Date;

public class Customer {

    private int customerId;
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
    private String address;
    private String avatar;
    private String status;
    private Date createdAt;

    public Customer() {
    }

    public Customer(int customerId, String fullName, String email, String password,
            String gender, String phoneNumber, String address,
            String avatar, String status, Date createdAt) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatar = avatar;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Customer(int customerId, String fullName) {
        this.customerId = customerId;
        this.fullName = fullName;
    }
    
    // getters & setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "customerId=" + customerId
                + ", fullName='" + fullName + '\''
                + ", email='" + email + '\''
                + ", gender='" + gender + '\''
                + ", phoneNumber='" + phoneNumber + '\''
                + ", address='" + address + '\''
                + ", avatar='" + avatar + '\''
                + ", status='" + status + '\''
                + ", createdAt=" + createdAt
                + '}';
    }
}
