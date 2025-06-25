package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    private static final String URL = "jdbc:mysql://localhost:3306/OnlineSupermarket";
    private static final String USER = "root"; // Thay bằng username MySQL của bạn
    private static final String PASSWORD = "123456"; // Thay bằng mật khẩu MySQL của bạn
    
    protected Connection connection;

    public DBContext() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection failed.");
        }
    }
    
    public static void main(String[] args) {
        new DBContext(); // Instantiates DBConnect to test connection
    }
}
