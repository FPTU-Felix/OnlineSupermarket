/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Order;
import Model.OrderDetail;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static void main(String[] args) {
        DaoOrder d = new DaoOrder();
        DaoProduct da = new DaoProduct();
       d.updateHoldWhenCancel(d.getAllProductinOrder(1), da.getAllProduct());
    }
}
