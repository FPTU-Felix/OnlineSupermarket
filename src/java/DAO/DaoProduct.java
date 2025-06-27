/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoProduct {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Product> getAllProduct() {
        List<Product> l = new ArrayList<>();
        String sql = "select *from product";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String briefinfor = rs.getString(3);
                String description = rs.getString(4);
                String thumbnail = rs.getString(5);
                String status = rs.getString(6);
                String releaseTime = rs.getString(7);
                int categoryID = rs.getInt(8);
                double price = rs.getDouble(9);
                int quantity = rs.getInt(10);
                int hold = rs.getInt(11);
                Product p = new Product(id, title, briefinfor, description, thumbnail, status, releaseTime, categoryID, price, quantity, hold);
                l.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return l;
    }

    public List<Product> get4Products() {
        List<Product> l = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM product\n"
                + "ORDER BY Quantity DESC\n"
                + "LIMIT 4;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String briefinfor = rs.getString(3);
                String description = rs.getString(4);
                String thumbnail = rs.getString(5);
                String status = rs.getString(6);
                String releaseTime = rs.getString(7);
                int categoryID = rs.getInt(8);
                double price = rs.getDouble(9);
                int quantity = rs.getInt(10);
                int hold = rs.getInt(11);
                Product p = new Product(id, title, briefinfor, description, thumbnail, status, releaseTime, categoryID, price, quantity, hold);
                l.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return l;
    }
    public Product getProductByID(int id) {
        Product p = new Product();
        String sql = "select *from product where ProductID = ?";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt(1);
                String title = rs.getString(2);
                String briefinfor = rs.getString(3);
                String description = rs.getString(4);
                String thumbnail = rs.getString(5);
                String status = rs.getString(6);
                String releaseTime = rs.getString(7);
                int categoryID = rs.getInt(8);
                double price = rs.getDouble(9);
                int quantity = rs.getInt(10);
                int hold = rs.getInt(11);
                p = new Product(iD, title, briefinfor, description, thumbnail, status, releaseTime, categoryID, price, quantity, hold);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return p;
    }

    public static void main(String[] args) {
        DaoProduct d = new DaoProduct();
        System.out.println(d.getProductByID(1));
    }
}
