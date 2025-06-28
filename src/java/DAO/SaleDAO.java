/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Attribute;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.OrderDetailEmp;
import Model.OrderInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.OrderType;
import Model.ProductQty;
import Model.SaleAndOrder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class SaleDAO extends DBContext {

    public Customer getCustomer(int cusid) {

        String sql = "SELECT CustomerID\n"
                + "      ,FullName\n"
                + "      ,Email\n"
                + "      ,Password\n"
                + "      ,Gender\n"
                + "      ,PhoneNumber\n"
                + "      ,Address\n"
                + "      ,Avatar\n"
                + "      ,Status\n"
                + "      ,CreateAt\n"
                + "  FROM Customer\n"
                + "  WHERE CustomerID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer a = new Customer(rs.getInt("CustomerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getString("Status"),
                        rs.getDate("CreateAt"));

                return a;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public OrderType getOrderForSale(int roleid, int empid) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n")
                .append("    COUNT(CASE WHEN StatusID IN (1,7,8) THEN 1 END) AS ProcessOrder,\n")
                .append("    COUNT(CASE WHEN StatusID = 9 THEN 1 END) AS CompletedOrder,\n")
                .append("    COUNT(CASE WHEN StatusID = 3 THEN 1 END) AS RejectOrder\n")
                .append("FROM Orders\n");

        // Nếu là sale role (roleid == 2), thì lọc theo SaleID
        if (roleid == 2) {
            sql.append("WHERE SaleID = ?");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());

            if (roleid == 2) {
                st.setInt(1, empid);
            }

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new OrderType(
                        rs.getInt("ProcessOrder"),
                        rs.getInt("CompletedOrder"),
                        rs.getInt("RejectOrder")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in getOrderForSale: " + e.getMessage());
        }

        return null;
    }

    public List<Attribute> getAllStatus() {
        List<Attribute> list = new ArrayList<>();
        String sql = "SELECT StatusID\n"
                + "      ,StatusName\n"
                + "  FROM Status";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Attribute c = new Attribute(rs.getInt("StatusID"),
                        rs.getString("StatusName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public List<OrderInfo> getOrderBySale(int roleid, int saleid, String datefrom, String dateto, String salename, String statusfilter, String status) {
        List<OrderInfo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n")
                .append("    Orders.OrderID, \n")
                .append("    Orders.CustomerID,  \n")
                .append("    Customer.FullName AS CustomerName,\n")
                .append("    (SELECT Title \n")
                .append("     FROM OrderDetails \n")
                .append("     WHERE OrderID = Orders.OrderID \n")
                .append("     ORDER BY OrderDetailID ASC \n")
                .append("     LIMIT 1) AS FirstTitle,\n")
                .append("    COUNT(OrderDetails.ProductID) - 1 AS OtherProducts, \n")
                .append("    SUM(OrderDetails.Quantity * OrderDetails.Price) AS TotalCost,\n")
                .append("    Orders.StatusID, \n")
                .append("    Status.StatusName, \n")
                .append("    Orders.PaymentMethod, \n")
                .append("    Orders.CreatedOrder, \n")
                .append("    Orders.SaleID, \n")
                .append("    Employee.FullName AS SaleName,\n")
                .append("    Orders.ReceiverMobile,\n")
                .append("    Orders.ReceiverAddress,\n")
                .append("    Orders.SaleNotes\n")
                .append("FROM Orders \n")
                .append("INNER JOIN Status ON Orders.StatusID = Status.StatusID \n")
                .append("INNER JOIN Customer ON Orders.CustomerID = Customer.CustomerID \n")
                .append("INNER JOIN Employee ON Orders.SaleID = Employee.EmployeeID \n")
                .append("INNER JOIN OrderDetails ON Orders.OrderID = OrderDetails.OrderID\n")
                .append("WHERE 1=1 ");

        if (roleid == 2) {
            sql.append("AND Orders.SaleID = ? ");
        }

        if (datefrom != null && !datefrom.isEmpty()) {
            sql.append("AND Orders.CreatedOrder >= ? ");
        }

        if (dateto != null && !dateto.isEmpty()) {
            sql.append("AND Orders.CreatedOrder <= ? ");
        }

        if (salename != null && !salename.isEmpty()) {
            sql.append("AND Employee.FullName LIKE ? ");
        }

        if (statusfilter != null && !statusfilter.isEmpty()) {
            sql.append("AND Orders.StatusID = ? ");
        }

        if (status != null && !status.isEmpty()) {
            if (status.equalsIgnoreCase("process")) {
                sql.append("AND Orders.StatusID IN (1,7,8) ");
            } else if (status.equalsIgnoreCase("completed")) {
                sql.append("AND Orders.StatusID = 9 ");
            } else {
                sql.append("AND Orders.StatusID = 3 ");
            }
        }

        sql.append("GROUP BY \n")
                .append("    Orders.OrderID, \n")
                .append("    Orders.CustomerID,  \n")
                .append("    Customer.FullName,\n")
                .append("    Orders.StatusID, \n")
                .append("    Status.StatusName, \n")
                .append("    Orders.PaymentMethod, \n")
                .append("    Orders.CreatedOrder, \n")
                .append("    Orders.SaleID, \n")
                .append("    Employee.FullName,\n")
                .append("    Orders.ReceiverMobile,\n")
                .append("    Orders.ReceiverAddress,\n")
                .append("    Orders.SaleNotes ");

        if (status != null && !status.isEmpty()) {
            sql.append(" ORDER BY Orders.StatusID ");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int count = 1;

            if (roleid == 2) {
                st.setInt(count++, saleid);
            }

            if (datefrom != null && !datefrom.isEmpty()) {
                st.setString(count++, datefrom);
            }

            if (dateto != null && !dateto.isEmpty()) {
                st.setString(count++, dateto);
            }

            if (salename != null && !salename.isEmpty()) {
                st.setString(count++, "%" + salename + "%");
            }

            if (statusfilter != null && !statusfilter.isEmpty()) {
                st.setString(count++, statusfilter);
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderInfo c = new OrderInfo(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("FirstTitle"),
                        rs.getInt("OtherProducts"),
                        rs.getDouble("TotalCost"),
                        rs.getInt("StatusID"),
                        rs.getString("StatusName"),
                        rs.getString("PaymentMethod"),
                        rs.getDate("CreatedOrder"),
                        rs.getInt("SaleID"),
                        rs.getString("SaleName"),
                        rs.getString("ReceiverMobile"),
                        rs.getString("ReceiverAddress"),
                        rs.getString("SaleNotes")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in getOrderBySale: " + e.getMessage());
        }

        return list;
    }

    public List<ProductQty> getAllProductQuantity() {
        List<ProductQty> list = new ArrayList<>();
        String sql = "SELECT ProductID\n"
                + "      ,Quantity\n"
                + "      ,Hold\n"
                + "  FROM Product";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductQty c = new ProductQty(
                        rs.getInt("ProductID"),
                        rs.getInt("Quantity"),
                        rs.getInt("Hold"));

                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public void updateHoldProduct(List<OrderDetail> orderdetail, List<ProductQty> products) {
        String sql = "UPDATE Product\n"
                + "   SET Hold = Hold - ?\n"
                + " WHERE ProductID = ? ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (OrderDetail c : orderdetail) {
                for (ProductQty p : products) {
                    if (c.getProductID() == p.getProductID()) {
                        int orderQuantity = c.getQuantity();
                        st.setInt(1, orderQuantity);
                        st.setInt(2, c.getProductID());
                        st.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateQuantityProduct(List<OrderDetail> orderdetail, List<ProductQty> products) {
        String sql = "UPDATE Product\n"
                + "   SET Quantity = Quantity + ?\n"
                + " WHERE ProductID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (OrderDetail c : orderdetail) {
                for (ProductQty p : products) {
                    if (c.getProductID() == p.getProductID()) {
                        int orderQuantity = c.getQuantity();
                        st.setInt(1, orderQuantity);
                        st.setInt(2, c.getProductID());
                        st.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<OrderDetail> getOrderDetailByID(String orderid) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetails.OrderDetailID, OrderDetails.OrderID, OrderDetails.ProductID, OrderDetails.Title, OrderDetails.Quantity, OrderDetails.Thumbnail, OrderDetails.Price\n"
                + "FROM     OrderDetails INNER JOIN\n"
                + "				  WHERE OrderDetails.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail c = new OrderDetail(rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getInt("Quantity"),
                        rs.getString("Thumbnail"),
                        rs.getDouble("Price"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public void updateOrderStatus(String orderid, String statusid) {
        String sql = "UPDATE Orders\n"
                + "   SET StatusID = ?\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, statusid);
            st.setString(2, orderid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<OrderDetailEmp> getOrderDetailForSale(String orderid) {
        List<OrderDetailEmp> list = new ArrayList<>();

        String sql = "SELECT od.OrderDetailID, od.OrderID, od.ProductID, od.Title, "
                + "od.Quantity, od.Thumbnail, od.Price, c.CategoryName "
                + "FROM OrderDetails od "
                + "INNER JOIN Product p ON od.ProductID = p.ProductID "
                + "INNER JOIN Category c ON p.CategoryID = c.CategoryID "
                + "WHERE od.OrderID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, orderid);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    OrderDetailEmp c = new OrderDetailEmp(
                            rs.getInt("OrderDetailID"),
                            rs.getInt("OrderID"),
                            rs.getInt("ProductID"),
                            rs.getString("Title"),
                            rs.getInt("Quantity"),
                            rs.getString("Thumbnail"),
                            rs.getDouble("Price"),
                            rs.getString("CategoryName")
                    );
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // hoặc log lỗi
        }

        return list;
    }

    public Order getOrderByID(String orderid) {
        String sql = "SELECT Orders.OrderID, Orders.CustomerID, Orders.ReceiverName, Orders.ReceiverGender, Orders.ReceiverEmail, Orders.ReceiverMobile, Orders.ReceiverAddress, Orders.ReceiverNotes, Orders.StatusID, Status.StatusName, \n"
                + "                  Orders.PaymentMethod, Orders.CreatedOrder, Orders.SaleID, Orders.SaleNotes\n"
                + "FROM     Orders INNER JOIN\n"
                + "                  Status ON Orders.StatusID = Status.StatusID\n"
                + "				  WHERE Orders.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order c = new Order(rs.getInt("OrderID"),
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

                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<SaleAndOrder> getAllSale() {
        List<SaleAndOrder> list = new ArrayList<>();
        String sql = "SELECT "
                + "    e.EmployeeID, "
                + "    e.FullName, "
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrder "
                + "FROM Employee e "
                + "LEFT JOIN Orders o ON o.SaleID = e.EmployeeID AND o.StatusID IN (1, 7, 8) "
                + "WHERE e.RoleID = 2 AND e.Status = 'Active' "
                + "GROUP BY e.EmployeeID, e.FullName "
                + "ORDER BY e.EmployeeID";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SaleAndOrder c = new SaleAndOrder(
                        rs.getInt("EmployeeID"),
                        rs.getString("FullName"),
                        rs.getInt("TotalOrder")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return list;
    }

    public Customer getCustomerCreateOrder(int customerid) {

        String sql = "SELECT CustomerID\n"
                + "      ,FullName\n"
                + "      ,Email\n"
                + "      ,Password\n"
                + "      ,Gender\n"
                + "      ,PhoneNumber\n"
                + "      ,Address\n"
                + "      ,Avatar\n"
                + "      ,Status\n"
                + "      ,CreateAt\n"
                + "  FROM Customer\n"
                + "  WHERE CustomerID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Customer a = new Customer(rs.getInt("CustomerID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avatar"),
                        rs.getString("Status"),
                        rs.getDate("CreateAt"));

                return a;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public void updateSaleAndNotes(String saleid, String salenotes, String orderid) {
        String sql = "UPDATE Orders\n"
                + "   SET SaleID = ?\n"
                + "      ,SaleNotes = ?\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, saleid);
            st.setString(2, salenotes);
            st.setString(3, orderid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePaymentStatus(String orderID) {
        String sql = "UPDATE Orders\n"
                + "   SET PaymentStatus = 'Paid'\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getPaymentStauts(String orderID) {
        String status = "";
        String sql = "SELECT PaymentStatus\n"
                + "  FROM Orders\n"
                + "  WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                status = rs.getString("PaymentStatus");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }

    public static void main(String[] args) {
        SaleDAO s = new SaleDAO();
        System.out.println(s.getAllSale().isEmpty());
    }
}
