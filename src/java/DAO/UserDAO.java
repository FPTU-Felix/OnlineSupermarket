/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Customer;
import Model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.catalina.User;

/**
 *
 * @author FPT
 */
public class UserDAO extends DBContext {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String query = " select * from Customer";

        try {

            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                int customerID = rs.getInt(1);           // CustomerID
                String fullName = rs.getString(2);   // CustomerName
                String email = rs.getString(3);          // Email
                String password = rs.getString(4);       // Password
                String gender = rs.getString(5);         // Gender
                String phoneNumber = rs.getString(6);    // PhoneNumber
                String address = rs.getString(7);        // Address
                String avatar = rs.getString(8);         // Avata
                String status = rs.getString(9);
                Date createAt = rs.getDate(10);      // CreateAt
                // Create a Customer object and add it to the list
                Customer c = new Customer(customerID, fullName, email, password, gender, phoneNumber, address, avatar, status, createAt);
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String query = "SELECT * FROM Employee";  // SQL query to select all employees

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establishing connection
            connection = new DBContext().connection;
            if (connection == null) {
                throw new Exception("Failed to establish a connection.");
            }

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            // Looping through the result set
            while (rs.next()) {
                // Retrieving data from the result set
                int EmployeeID = rs.getInt(1);           // EmployeeID
                String fullName = rs.getString(2);   // FullName
                String email = rs.getString(3);      // Email
                String password = rs.getString(4);   // Password
                String gender = rs.getString(5);     // Gender
                String phoneNumber = rs.getString(6); // PhoneNumber
                String address = rs.getString(7);    // Address
                String avatar = rs.getString(8);      // Avata
                int roleID = rs.getInt(9);           // RoleID
                String status = rs.getString(10);    // Status
                Date createAt = rs.getDate(11);      // CreateAt
                // Create an Employee object and add it to the list
                Employee employee = new Employee(EmployeeID, fullName, email, password, gender, phoneNumber, address, avatar, roleID, status, createAt);
                list.add(employee);
            }
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();

        } finally {
            // Close resources
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
            } catch (Exception e) {
                System.err.println("Failed to close resources: " + e.getMessage());
            }
        }
        return list;  // Returning the list of employees
    }
//public static void main(String[] args) {
//        UserDAO employeeDAO = new UserDAO(); // Replace with your actual DAO class name
//        List<Employee> employees = employeeDAO.getAllEmployees(); // Call the method
//
//        // Check if the list is empty
//        if (employees.isEmpty()) {
//            System.out.println("No employees found.");
//        } else {
//            // Print the details of each employee
//            for (Employee employee : employees) {
//                System.out.println("Employee ID: " + employee.getUserId());
//                System.out.println("Full Name: " + employee.getFullName());
//                System.out.println("Email: " + employee.getEmail());
//                System.out.println("Gender: " + employee.getGender());
//                System.out.println("Phone Number: " + employee.getPhoneNumber());
//                System.out.println("Address: " + employee.getAddress());
//                System.out.println("Avatar: " + employee.getAvata());
//                System.out.println("Role ID: " + employee.getRoleId());
//                System.out.println("Status: " + employee.getStatus());
//                System.out.println("-------------------------------");
//            }
//        }
//    }

//   public List<User> getAllUsers() {
//    List<User> list = new ArrayList<>();
//    String query = "SELECT " +
//                   "    u.UserID, " +
//                   "    COALESCE(c.FullName, e.FullName) AS FullName, " +
//                   "    COALESCE(c.Email, e.Email) AS Email, " +
//                   "    COALESCE(c.Password, e.Password) AS Password, " +
//                   "    COALESCE(c.Gender, e.Gender) AS Gender, " +
//                   "    COALESCE(c.PhoneNumber, e.PhoneNumber) AS PhoneNumber, " +
//                   "    COALESCE(c.Address, e.Address) AS Address, " +
//                   "    COALESCE(c.Avatar, e.Avatar) AS Avatar, " +
//                   "    COALESCE(e.RoleID, 0) AS RoleID, " +
//                   "    COALESCE(c.Status, e.Status) AS Status " +
//                   "FROM " +
//                   "    [dbo].[User] u " +
//                   "LEFT JOIN [dbo].[Customer] c ON u.UserID = c.UserID " +
//                   "LEFT JOIN [dbo].[Employee] e ON u.UserID = e.UserID " +
//                   "WHERE c.UserID IS NOT NULL OR e.UserID IS NOT NULL;";
//
//     try (Connection connection = new DBContext().connection;  // Use connection field from DBContext
//     PreparedStatement ps = connection.prepareStatement(query);
//         ResultSet rs = ps.executeQuery()) {
//
//        // Iterating through the result set
//        while (rs.next()) {
//            // Extracting common user data
//            int userID = rs.getInt("UserID");
//            String fullName = rs.getString("FullName");
//            String email = rs.getString("Email");
//            String password = rs.getString("Password");
//            String gender = rs.getString("Gender");
//            String phoneNumber = rs.getString("PhoneNumber");
//            String address = rs.getString("Address");
//            String avatar = rs.getString("Avatar");
//            int roleID = rs.getInt("RoleID");
//            String status = rs.getString("Status");
//            
//            // Creating either Customer or Employee based on roleID
//            if (roleID == 0) {
//                // Customer
//                Customer customer = new Customer(userID, fullName, email, password, gender, phoneNumber, address, avatar, status);
//                list.add((User) customer);
//            } else {
//                // Employee
//                Employee employee = new Employee(userID, fullName, email, password, gender, phoneNumber, address, avatar, roleID, status);
//                list.add((User) employee);
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    return list;  // Returning the list of users (both employees and customers)
//}
//    public static void main(String[] args) {
//        UserDAO employeeDAO = new UserDAO();
//        List<Employee> employees = employeeDAO.getAllEmployees();
//
//        // Print the list of employees
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }
//    }
//    public List<Employee> getAllEmployeesWithRoles() {
//    List<Employee> list = new ArrayList<>();
//    String query = "SELECT e.*, r.roleName FROM Employee e JOIN Role r ON e.roleID = r.roleID";  // SQL query to join Employee and Role
//
//    try {
//        // Establishing connection
//        connection = new DBContext().connection;
//        ps = connection.prepareStatement(query);
//        rs = ps.executeQuery();
//
//        while (rs.next()) {
//            int userID = rs.getInt(1);           // EmployeeID
//            String fullName = rs.getString(2);       // FullName
//            String email = rs.getString(3);          // Email
//            String password = rs.getString(4);       // Password
//            String gender = rs.getString(5);         // Gender
//            String phoneNumber = rs.getString(6);    // PhoneNumber
//            String address = rs.getString(7);        // Address
//            String avatar = rs.getString(8);         // Avatar
//            String status = rs.getString(9); 
//            String roleName = rs.getString(10); 
//            int roleID = rs.getInt(11);// 
//                 
//
//            Employee employee = new Employee(userID, fullName, email, password, gender, phoneNumber, address, avatar, status, roleName,roleID);
//            list.add(employee);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    } finally {
//        // Close resources
//    }
//    return list;  // Returning the list of employees
//}
    public void deleteByUserID(int userID) {
        String query = "DELETE FROM Customer WHERE CustomerID = ?";

        try {
            // Establish connection
            connection = new DBContext().connection;

            // Create a PreparedStatement
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID); // Set the UserID parameter

            // Execute the delete operation
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer with UserID " + userID + " was deleted successfully.");
            } else {
                System.out.println("No customer found with UserID " + userID + ".");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
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
    }

    public boolean deleteEmployeeByID(int userID) {
        String query = "DELETE FROM Employee WHERE employeeID = ?";

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userID); // Set the parameter for UserID
            return ps.executeUpdate() > 0; // Return true if rows affected > 0

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of exception
        }
    }

//     public static void main(String[] args) {
//        // Create an instance of UserDAO
//        UserDAO userDAO = new UserDAO();
//        
//        // Define the userID to be deleted (Replace with an actual userID for testing)
//        int userID = 3; // Example UserID, change this as needed
//
//        // Call the deleteEmployeeByID method
//        boolean isDeleted = userDAO.deleteCustomerByID(userID);
//
//        // Print the result of the deletion
//        if (isDeleted) {
//            System.out.println("Employee with UserID " + userID + " was successfully deleted.");
//        } else {
//            System.out.println("Failed to delete employee with UserID " + userID + ".");
//        }
//    }
    public Customer getCustomerByID(int CustomerID) {
        Customer customer = null;
        String query = "SELECT * FROM Customer WHERE CustomerID = ?";

        try {
            // Establish connection
            connection = new DBContext().connection;

            // Prepare the SQL query with the customerID parameter
            ps = connection.prepareStatement(query);
            ps.setInt(1, CustomerID);

            // Execute the query
            rs = ps.executeQuery();

            // Check if a result is returned
            if (rs.next()) {
                // Retrieve data for the Customer
                int customerID = rs.getInt(1);           // CustomerID
                String fullName = rs.getString(2);   // CustomerName
                String email = rs.getString(3);      // Email
                String password = rs.getString(4);   // Password
                String gender = rs.getString(5);     // Gender
                String phoneNumber = rs.getString(6); // PhoneNumber
                String address = rs.getString(7);    // Address
                String avatar = rs.getString(8);      // Avata
                String status = rs.getString(9);
                Date createAt = rs.getDate(10);      // CreateAt
                // Create a Customer object
                customer = new Customer(customerID, fullName, email, password, gender, phoneNumber, address, avatar, status, createAt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }

    public Employee getEmployeeByID(String EmployeeID) {
        Employee employee = null;  // Initialize the employee object
        String query = "SELECT [EmployeeID],\n"
                + "      [FullName],\n"
                + "      [Email],\n"
                + "      [Password],\n"
                + "      [Gender],\n"
                + "      [PhoneNumber],\n"
                + "      [Address],\n"
                + "      [Avatar],\n"
                + "      [RoleID],\n"
                + "      [Status],\n"
                + "      [CreateAt]  \n" // Include CreateAt in the query
                + "  FROM [dbo].[Employee]\n"
                + "  WHERE [EmployeeID] = ?";

        // Use try-with-resources to handle resource management
        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, EmployeeID);  // Set the parameter for UserID
            try (ResultSet rs = ps.executeQuery()) {
                // Check if a result is returned
                if (rs.next()) {
                    // Retrieving data from the result set
                    int employeeID = rs.getInt("employeeID");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    String password = rs.getString("Password");
                    String gender = rs.getString("Gender");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String address = rs.getString("Address");
                    String avatar = rs.getString("Avatar");
                    int roleID = rs.getInt("RoleID");
                    String status = rs.getString("Status");
                    Date createAt = rs.getDate("CreateAt");  // Retrieve the CreateAt date

                    // Create an Employee object and assign it to the variable
                    employee = new Employee(employeeID, fullName, email, password, gender, phoneNumber, address, avatar, roleID, status, createAt);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employee;
    }

    public boolean editCustomer(Customer customer) {
        String query = "UPDATE Customer SET FullName = ?, Email = ?, Gender = ?, PhoneNumber = ?, Address = ? WHERE CustomerID = ?";
        boolean rowUpdated = false;

        try {
            // Establish connection
            connection = new DBContext().connection;

            // Prepare the SQL update query
            ps = connection.prepareStatement(query);

            // Set the values from the customer object
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getEmail());

            ps.setString(3, customer.getGender());
            ps.setString(4, customer.getPhoneNumber());
            ps.setString(5, customer.getAddress());

            ps.setInt(6, customer.getCustomerId());  // UserID as a condition in the WHERE clause

            // Execute the update query
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                rowUpdated = true;  // Update was successful
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowUpdated;
    }
//  public static void main(String[] args) {
//        // Create an instance of your DAO (Data Access Object) class
//        UserDAO customerDAO = new UserDAO();
//
//        // Create a sample customer object with the updated information
//        Customer customer = new Customer();
//        customer.setUserId(11);  // Set the UserID of the customer you want to edit
//        customer.setFullName("John Doe Updated");
//        customer.setEmail("john.doe@example.com");
//        customer.setPassword("newpassword123");
//        customer.setGender("Male");
//        customer.setPhoneNumber("1234567890");
//        customer.setAddress("123 New Address, City, Country");
//        customer.setAvatar("/path/to/new/avatar.png");
//
//        // Call the editCustomer method and check the result
//        boolean isUpdated = customerDAO.editCustomer(customer);
//
//        // Print the result
//        if (isUpdated) {
//            System.out.println("Customer details updated successfully.");
//        } else {
//            System.out.println("Failed to update customer details.");
//        }
//    }

    public boolean updateUser(Employee employee) {
        String query = "UPDATE Employee SET FullName = ?, Email = ?,  Gender = ?, PhoneNumber = ?, Address = ?,RoleID = ?, Status = ? WHERE EmployeeID = ?";
        boolean rowUpdated = false;

        // Using try-with-resources for automatic resource management
        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            // Setting parameters from the employee object
            ps.setString(1, employee.getFullName());
            ps.setString(2, employee.getEmail());

            ps.setString(3, employee.getGender());
            ps.setString(4, employee.getPhoneNumber());
            ps.setString(5, employee.getAddress());

            ps.setInt(6, employee.getRoleId()); // Use getRoleId()
            ps.setString(7, employee.getStatus());
            ps.setInt(8, employee.getEmployeeId()); // Use getUserId()

            // Execute the update
            int rowsAffected = ps.executeUpdate();
            rowUpdated = (rowsAffected > 0); // Update successful if rows were affected

        } catch (Exception e) {
            e.printStackTrace(); // Log any exceptions that occur
        }

        return rowUpdated;
    }

    public List<Customer> getCustomersByStatus(String status) {
        List<Customer> list = new ArrayList<>();
        String query = "SELECT * FROM [dbo].[Customer] WHERE Status = ?";

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getString("Status"),
                        rs.getDate("createAt")
                );
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Employee> getAllMarketers() {
        List<Employee> list = new ArrayList<>();
        String query = " select * from Employee where RoleID = 3";

        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt(1);
                String fullName = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String gender = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String address = rs.getString(7);
                String avata = rs.getString(8);
                int roleId = rs.getInt(9);
                String status = rs.getString(10);
                Date createAt = rs.getDate(11);      // CreateAt
                Employee c = new Employee(userID, fullName, email, password, gender, phoneNumber, address, avata, roleId, status, createAt);
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateCustomerInfo(Customer customer) {
        String query = "UPDATE Customer SET FullName=?, PhoneNumber=?, Gender=?, Address=? WHERE CustomerID=?";
        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setString(3, customer.getGender());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, customer.getCustomerId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if the update was successful
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
            return false;
        }
    }

    public int getTotalPosts() {
        int totalPosts = 0;
        String query = "SELECT COUNT(*) FROM Post"; // Assuming you have a Posts table

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) { // Execute the query

            if (rs.next()) {
                totalPosts = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPosts;
    }

    // Method to get total count of products
    public int getTotalProducts() {
        int totalProducts = 0;
        String query = "SELECT COUNT(*) FROM Product"; // Assuming you have a Products table

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) { // Execute the query

            if (rs.next()) {
                totalProducts = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalProducts;
    }

    // Method to get total count of customers
    public int getTotalCustomers() {
        int totalCustomers = 0;
        String query = "SELECT COUNT(*) FROM Customer"; // Assuming you have a Customers table

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) { // Execute the query

            if (rs.next()) {
                totalCustomers = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalCustomers;
    }

    // Method to get total count of feedbacks
    public int getTotalFeedbacks() {
        int totalFeedbacks = 0;
        String query = "SELECT COUNT(*) FROM Feedback"; // Assuming you have a Feedbacks table

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) { // Execute the query

            if (rs.next()) {
                totalFeedbacks = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalFeedbacks;
    }
//      public static void main(String[] args) {
//        UserDAO UserDAO = new UserDAO();
//        
//        int totalPosts = UserDAO.getTotalPosts();
//        System.out.println("Total Posts: " + totalPosts);
//        
//        int totalProducts = UserDAO.getTotalProducts();
//        System.out.println("Total Products: " + totalProducts);
//        
//        int totalCustomers = UserDAO.getTotalCustomers();
//        System.out.println("Total Customers: " + totalCustomers);
//        
//        int totalFeedbacks = UserDAO.getTotalFeedbacks();
//        System.out.println("Total Feedbacks: " + totalFeedbacks);
//    }

    public List<Integer> getNewCustomersTrend() {
        List<Integer> newCustomerCounts = new ArrayList<>();
        String query = "SELECT CAST(CreateAt AS DATE) AS CreatedDate, COUNT(*) AS CustomerCount "
                + "FROM Customer "
                + "WHERE CreateAt >= DATEADD(DAY, -7, GETDATE()) "
                + "GROUP BY CAST(CreateAt AS DATE) "
                + "ORDER BY CreatedDate"; // Order by the grouped date

        try (Connection connection = new DBContext().connection; PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                newCustomerCounts.add(rs.getInt("CustomerCount")); // Retrieve the count
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCustomerCounts;
    }
}

// lay uid vua tao

