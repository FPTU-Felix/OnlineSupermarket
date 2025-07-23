/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDAO extends DBContext {

    private final Connection con;
    public static final EmployeeDAO INSTANCE = new EmployeeDAO();

    private EmployeeDAO() {
        this.con = super.connection;
    }

    public Employee login(String email, String password) {
        String sql = "SELECT * FROM Employee "
                + "WHERE Email = ? AND Password = ? AND Status = 'Active'";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setEmployeeId(rs.getInt("EmployeeID"));
                    e.setFullName(rs.getString("FullName"));
                    e.setEmail(rs.getString("Email"));
                    e.setPassword(rs.getString("Password"));
                    e.setGender(rs.getString("Gender"));
                    e.setPhoneNumber(rs.getString("PhoneNumber"));
                    e.setAddress(rs.getString("Address"));
                    e.setAvatar(rs.getString("Avatar"));
                    e.setRoleId(rs.getInt("RoleID"));
                    e.setStatus(rs.getString("Status"));
                    e.setCreatedAt(rs.getTimestamp("CreateAt"));
                    return e;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void banAnUser(int userId) {
        String sql = "UPDATE Employee\n"
                + "SET Status = 'Inactive'\n"
                + "WHERE EmployeeID = ?;";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unbanAnUser(int userId) {
        String sql = "UPDATE Employee\n"
                + "SET Status = 'Active'\n"
                + "WHERE EmployeeID = ?;";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EmployeeDAO.INSTANCE.banAnUser(2);
    }
}
