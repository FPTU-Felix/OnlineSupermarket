/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.OrderDTO;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoOrder {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Order> getAllOrderforCustomer(int customerID) {
        String sql = "SELECT \n"
                + "    o.OrderID, \n"
                + "    o.CustomerID, \n"
                + "    o.ReceiverName, \n"
                + "    o.ReceiverGender, \n"
                + "    o.ReceiverEmail, \n"
                + "    o.ReceiverMobile, \n"
                + "    o.ReceiverAddress, \n"
                + "    o.ReceiverNotes,  \n"
                + "    s.StatusID,  \n"
                + "    s.StatusName,\n"
                + "    o.PaymentMethod, \n"
                + "    o.CreatedOrder, \n"
                + "    o.SaleID, \n"
                + "    o.SaleNotes\n"
                + "FROM \n"
                + "    Orders o\n"
                + "JOIN \n"
                + "    Status s ON o.StatusID = s.StatusID\n"
                + "WHERE \n"
                + "    o.CustomerID = ?";
        List<Order> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderiD = rs.getInt(1);
                int customerId = rs.getInt(2);
                String receiverName = rs.getString(3);
                String receiverGender = rs.getString(4);
                String receiverEmail = rs.getString(5);
                String receiverMobile = rs.getString(6);
                String receiverAddress = rs.getString(7);
                String receiverNote = rs.getString(8);
                int statusID = rs.getInt(9);
                String statusName = rs.getString(10);
                String paymentMethod = rs.getString(11);
                Date createdOrder = rs.getDate(12);
                int saleID = rs.getInt(13);
                String saleNote = rs.getString(14);
                Order o = new Order(orderiD, orderiD, receiverName, receiverGender, receiverEmail, receiverMobile, receiverAddress, receiverNote, statusID, statusName, paymentMethod, createdOrder, saleID, saleNote);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateHoldWhenCancel(List<OrderDetail> c, List<Product> p) {
        String sql = "UPDATE Product\n"
                + "SET Hold = Hold - ?\n"
                + "WHERE ProductID = ?";
        try {
            connection = new DBContext().connection;
            PreparedStatement st = connection.prepareStatement(sql);
            for (OrderDetail orderDetail : c) {
                for (Product Product : p) {
                    if (orderDetail.getProductID() == Product.getId()) {
                        int hold = orderDetail.getQuantity();
                        st.setInt(1, hold);
                        st.setInt(2, orderDetail.getProductID());
                        st.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelOrderStatus(int orderID, int statusID) {
        String sql = "update Orders\n"
                + "set StatusID = ?\n"
                + "where OrderID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, statusID);
            ps.setInt(2, orderID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getStatusIDbyStatusName(String statusName) {
        String sql = "select Status.StatusID from Status where Status.StatusName=?";
        int statusID = 0;
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setString(1, statusName);
            while (rs.next()) {
                statusID = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusID;
    }

    public List<OrderDetail> getAllProductinOrder(int orderID) {
        String sql = "SELECT \n"
                + "                                      OrderDetailID, \n"
                + "                                      OrderID, \n"
                + "                                      ProductID, \n"
                + "                                      Title, \n"
                + "                                      Quantity, \n"
                + "                                      Thumbnail, \n"
                + "                                      Price \n"
                + "                                  FROM \n"
                + "                                      OrderDetails\n"
                + "                                  WHERE \n"
                + "                                      OrderID = ?";
        List<OrderDetail> list = new ArrayList<>();
        try {
            connection = new DBContext().connection;
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int orderDetailID = rs.getInt(1);
                int orderiD = rs.getInt(2);
                int productID = rs.getInt(3);
                String title = rs.getString(4);
                int quantity = rs.getInt(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                OrderDetail o = new OrderDetail(orderDetailID, orderiD, productID, title, quantity, thumbnail, price);
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

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
        }
        return null;
    }

    public List<OrderDTO> getAllOrderDTOs(String status) {
        List<OrderDTO> list = new ArrayList<>();
        String sql = """
        SELECT o.OrderID, c.FullName, o.ReceiverName, o.PaymentMethod, s.StatusName, o.CreatedOrder
        FROM Orders o
        JOIN Customer c ON o.CustomerID = c.CustomerID
        JOIN Status s ON o.StatusID = s.StatusID
        """ + (status != null && !status.isEmpty() ? "WHERE s.StatusName = ?" : "") + """
        ORDER BY o.CreatedOrder DESC
    """;

        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            if (status != null && !status.isEmpty()) {
                ps.setString(1, status);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDTO dto = new OrderDTO(
                        rs.getInt("OrderID"),
                        rs.getString("FullName"),
                        rs.getString("ReceiverName"),
                        rs.getString("PaymentMethod"),
                        rs.getString("StatusName"),
                        rs.getDate("CreatedOrder")
                );
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getAllOrders(String status) {
        List<Order> list = new ArrayList<>();
        String sql = """
        SELECT o.OrderID, o.CustomerID, o.ReceiverName, o.ReceiverGender, o.ReceiverEmail,
               o.ReceiverMobile, o.ReceiverAddress, o.ReceiverNotes, s.StatusID, s.StatusName,
               o.PaymentMethod, o.CreatedOrder, o.SaleID, o.SaleNotes
        FROM Orders o
        JOIN Status s ON o.StatusID = s.StatusID
        """ + (status != null && !status.isEmpty() ? "WHERE s.StatusName = ?" : "") + """
        ORDER BY o.CreatedOrder DESC
    """;

        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            if (status != null && !status.isEmpty()) {
                ps.setString(1, status);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("ReceiverName"),
                        rs.getString("ReceiverGender"),
                        rs.getString("ReceiverEmail"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        rs.getString("ReceiverNotes"),
                        rs.getInt("StatusID"),
                        rs.getString("StatusName"),
                        rs.getString("PaymentMethod"),
                        rs.getDate("CreatedOrder"),
                        rs.getInt("SaleID"),
                        rs.getString("SaleNotes")
                );
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DaoOrder d = new DaoOrder();
        DaoProduct da = new DaoProduct();
        d.updateHoldWhenCancel(d.getAllProductinOrder(1), da.getAllProduct());
    }
}
