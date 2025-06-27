package Model;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String phoneNumber;
    private String address;
    private String avatar;
    private int roleId;
    private String status;
    private Date createdAt;

    public Employee() {
    }

    public Employee(int employeeId, String fullName, String email, String password,
                    String gender, String phoneNumber, String address, String avatar,
                    int roleId, String status, Date createdAt) {
        this.employeeId = employeeId;
        this.fullName   = fullName;
        this.email      = email;
        this.password   = password;
        this.gender     = gender;
        this.phoneNumber= phoneNumber;
        this.address    = address;
        this.avatar     = avatar;
        this.roleId     = roleId;
        this.status     = status;
        this.createdAt  = createdAt;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
}
