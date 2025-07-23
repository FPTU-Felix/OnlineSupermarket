package DTO;

import java.util.Date;

public class OrderDTO {

    private int orderId;
    private String customerName;
    private String receiverName;
    private String paymentMethod;
    private String statusName;
    private Date createdOrder;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, String customerName, String receiverName, String paymentMethod, String statusName, Date createdOrder) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.receiverName = receiverName;
        this.paymentMethod = paymentMethod;
        this.statusName = statusName;
        this.createdOrder = createdOrder;
    }

    // Getters
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatusName() {
        return statusName;
    }

    public Date getCreatedOrder() {
        return createdOrder;
    }

    // Setters
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setCreatedOrder(Date createdOrder) {
        this.createdOrder = createdOrder;
    }
}
