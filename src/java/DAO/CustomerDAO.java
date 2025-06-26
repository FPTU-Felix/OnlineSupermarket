/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data access object for Customer. Provides register and login logic (only
 * Active customers can log in).
 *
 * @author Admin
 */
public class CustomerDAO extends DBContext {

    private final Connection con;
    public static CustomerDAO INSTANCE = new CustomerDAO();

    private CustomerDAO() {
        this.con = super.connection;
    }

    public boolean register(Customer customer) {
        String checkSql = "SELECT CustomerID FROM Customer WHERE Email = ?";
        try (PreparedStatement ps = con.prepareStatement(checkSql)) {
            ps.setString(1, customer.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return false;  
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        String insertSql = "INSERT INTO Customer "
                + "(FullName, Email, Password, Gender, PhoneNumber, Address, Status) "
                + "VALUES (?,?,?,?,?,?, 'Active')";
        try (PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());
            ps.setString(4, customer.getGender());
            ps.setString(5, customer.getPhoneNumber());
            ps.setString(6, customer.getAddress());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                return false;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    customer.setCustomerId(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Customer login(String email, String password) {
        String sql = "SELECT * FROM Customer "
                + "WHERE Email = ? AND Password = ? AND Status = 'Active'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setCustomerId(rs.getInt("CustomerID"));
                    c.setFullName(rs.getString("FullName"));
                    c.setEmail(rs.getString("Email"));
                    c.setPassword(rs.getString("Password"));
                    c.setGender(rs.getString("Gender"));
                    c.setPhoneNumber(rs.getString("PhoneNumber"));
                    c.setAddress(rs.getString("Address"));
                    c.setAvatar(rs.getString("Avatar"));
                    c.setStatus(rs.getString("Status"));
                    c.setCreatedAt(rs.getTimestamp("CreateAt"));
                    return c;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
