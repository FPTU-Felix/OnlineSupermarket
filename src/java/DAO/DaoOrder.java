/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oreh
 */
public class DaoOrder {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Order> getAllAssignedOrder(int saleId) {
        String sql = """
                     SELECT o.OrderID, o.CustomerID, o.ReceiverName, o.ReceiverGender, o.ReceiverEmail,
                      o.ReceiverMobile, o.ReceiverAddress, o.ReceiverNotes, s.StatusID, o.PaymentMethod,
                      o.PaymentStatus, o.CreatedOrder, o.SaleID, s.StatusName, c.FullName FROM orders o JOIN status s
                     ON o.StatusID = s.StatusID JOIN customer c ON o.CustomerID = c.CustomerID
                     WHERE SaleID = ?
                     ORDER BY o.StatusID ASC, o.CreatedOrder DESC""";
        List<Order> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, saleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiverName = rs.getString(3);
                String receiverGender = rs.getString(4);
                String receiverEmail = rs.getString(5);
                String receiverMobile = rs.getString(6);
                String receiverAddress = rs.getString(7);
                String receiverNotes = rs.getString(8);
                int statusId = rs.getInt(9);
                String paymentMethod = rs.getString(10);
                String PaymentStatus = rs.getString(11);
                Date createdOrder = rs.getDate(12);
                String statusName = rs.getString(14);
                String fullName = rs.getString(15);
                l.add(new Order(orderId, customerId, fullName, receiverName, receiverGender, receiverEmail, receiverMobile, receiverAddress, receiverNotes, statusId, statusName, paymentMethod, createdOrder));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public List<OrderDetail> getOrdersDetails() {
        String sql = """
                     SELECT * FROM orderdetails""";
        List<OrderDetail> l = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderDetailId = rs.getInt(1);
                int orderId = rs.getInt(2);
                int productId = rs.getInt(3);
                String title = rs.getString(4);
                int quantity = rs.getInt(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                l.add(new OrderDetail(orderDetailId, orderId, productId, title, quantity, thumbnail, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public boolean updateOrderStatusByOrderId(int id, int status, String note) {
        String sql = """
                     UPDATE orders
                     SET statusID = ?, `SaleNotes` = ?
                     WHERE OrderID = ?""";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, note);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE CustomerID = ?";
        Customer c;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int customerId = rs.getInt(1);
                    String fullName = rs.getString(2);
                    c = new Customer(customerId, fullName);
                    return c;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
