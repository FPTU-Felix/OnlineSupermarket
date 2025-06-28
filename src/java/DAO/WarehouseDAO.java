/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Attribute;
import Model.Order;
import Model.OrderDetail;
import Model.OrderInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.OrderType;
import Model.ProductQty;
import Model.ProductStock;
import Model.ProductType;
import Model.ProductWarehouse;
import Model.WarehouseProduct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
public class WarehouseDAO extends DBContext {

    public OrderType getOrderForWarehouse() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n")
                .append("    COUNT(CASE WHEN StatusID IN (2,4,5,6) THEN 1 END) AS ProcessOrder, \n")
                .append("    COUNT(CASE WHEN StatusID = 7 THEN 1 END) AS DeliveredOrder, \n")
                .append("    COUNT(CASE WHEN StatusID = 8 THEN 1 END) AS DeliveryFailedOrder \n")
                .append("FROM Orders");  // ✅ MySQL không dùng [Orders]
        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderType c = new OrderType(rs.getInt("ProcessOrder"),
                        rs.getInt("DeliveredOrder"),
                        rs.getInt("DeliveryFailedOrder"));
                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public List<Attribute> getAllStatus() {
        List<Attribute> list = new ArrayList<>();
        String sql = "SELECT StatusID, StatusName FROM `Status`";

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

    public List<OrderInfo> getOrderByWarehouse(String datefrom, String dateto, String cusname, String statusid, String status) {
        List<OrderInfo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("    o.OrderID, ")
                .append("    o.CustomerID,  ")
                .append("    c.FullName AS CustomerName, ")
                .append("    (SELECT od.Title ")
                .append("     FROM OrderDetails od ")
                .append("     WHERE od.OrderID = o.OrderID ")
                .append("     AND od.OrderDetailID = (SELECT MIN(od2.OrderDetailID) ")
                .append("                              FROM OrderDetails od2 ")
                .append("                              WHERE od2.OrderID = o.OrderID)) AS FirstTitle, ")
                .append("    COUNT(od.ProductID) - 1 AS OtherProducts, ")
                .append("    SUM(od.Quantity * od.Price) AS TotalCost, ")
                .append("    o.StatusID, ")
                .append("    s.StatusName, ")
                .append("    o.PaymentMethod, ")
                .append("    o.CreatedOrder, ")
                .append("    o.SaleID, ")
                .append("    e.FullName AS SaleName, ")
                .append("    o.ReceiverMobile, ")
                .append("    o.ReceiverAddress, ")
                .append("    o.SaleNotes ")
                .append("FROM Orders o ")
                .append("INNER JOIN `Status` s ON o.StatusID = s.StatusID ")
                .append("INNER JOIN Customer c ON o.CustomerID = c.CustomerID ")
                .append("INNER JOIN Employee e ON o.SaleID = e.EmployeeID ")
                .append("INNER JOIN OrderDetails od ON o.OrderID = od.OrderID ")
                .append("WHERE o.StatusID IN (2,4,5,6,7,8) ");

        // dynamic filters
        if (datefrom != null && !datefrom.isEmpty()) {
            sql.append("AND o.CreatedOrder >= ? ");
        }
        if (dateto != null && !dateto.isEmpty()) {
            sql.append("AND o.CreatedOrder <= ? ");
        }
        if (cusname != null && !cusname.isEmpty()) {
            sql.append("AND c.FullName LIKE ? ");
        }
        if (statusid != null && !statusid.isEmpty()) {
            sql.append("AND o.StatusID = ? ");
        }
        if (status != null && !status.isEmpty()) {
            switch (status.toLowerCase()) {
                case "process":
                    sql.append("AND o.StatusID IN (2,4,5,6) ");
                    break;
                case "delivered":
                    sql.append("AND o.StatusID = 7 ");
                    break;
                default:
                    sql.append("AND o.StatusID = 8 ");
                    break;
            }
        }

        sql.append("GROUP BY ")
                .append("    o.OrderID, o.CustomerID, c.FullName, o.StatusID, s.StatusName, ")
                .append("    o.PaymentMethod, o.CreatedOrder, o.SaleID, e.FullName, ")
                .append("    o.ReceiverMobile, o.ReceiverAddress, o.SaleNotes ");

        if (status != null && !status.isEmpty()) {
            sql.append("ORDER BY o.StatusID ");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int count = 1;

            if (datefrom != null && !datefrom.isEmpty()) {
                st.setString(count++, datefrom);
            }
            if (dateto != null && !dateto.isEmpty()) {
                st.setString(count++, dateto);
            }
            if (cusname != null && !cusname.isEmpty()) {
                st.setString(count++, "%" + cusname + "%");
            }
            if (statusid != null && !statusid.isEmpty()) {
                st.setString(count++, statusid);
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
            System.out.println("SQL Error: " + e.getMessage());
        }

        return list;
    }

    public List<ProductQty> getAllProductQuantity() {
        List<ProductQty> list = new ArrayList<>();
        String sql = "SELECT ProductID, Quantity, Hold FROM Product";

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

    public void updateQuantityProduct(List<OrderDetail> orderdetail, List<ProductQty> products) {
        String sql = "UPDATE Product\n"
                + "   SET Quantity = Quantity - ?\n"
                + "      ,Hold = Hold - ?\n"
                + " WHERE ProductID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (OrderDetail c : orderdetail) {
                for (ProductQty p : products) {
                    if (c.getProductID() == p.getProductID()) {
                        int orderQuantity = c.getQuantity();
                        st.setInt(1, orderQuantity);
                        st.setInt(2, orderQuantity);
                        st.setInt(3, c.getProductID());
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
        String sql = "SELECT OrderDetailID, OrderID, ProductID, Title, Quantity, Thumbnail, Price\n"
                + "FROM    OrderDetails\n"
                + "WHERE OrderID = ?";

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
                        rs.getDouble("Price")
                );
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

    public List<OrderDetail> getOrderDetailForWarehouse(String orderid) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetails.OrderDetailID, OrderDetails.OrderID, OrderDetails.ProductID, OrderDetails.Title, OrderDetails.Quantity, OrderDetails.Thumbnail, OrderDetails.Price\n"
                + "FROM     OrderDetails\n"
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

    public List<WarehouseProduct> getListProductForWarehouse(String datefrom, String dateto, String title, String status) {
        List<WarehouseProduct> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n"
                + "    Product.ProductID, \n"
                + "    Product.Title, \n"
                + "    Product.Thumbnail, \n"
                + "    SUM(ProductDetails.Quantity) AS TotalQuantity, \n"
                + "    SUM(ProductDetails.Hold) AS TotalHold\n"
                + "FROM \n"
                + "    Product \n"
                + "INNER JOIN \n"
                + "    ProductDetails ON Product.ProductID = ProductDetails.ProductID\n"
                + "WHERE 1=1 ");

        if (datefrom != null && !datefrom.isEmpty()) {
            sql.append(" AND [ReleaseDate] >= ? ");
        }
        if (dateto != null && !dateto.isEmpty()) {
            sql.append(" AND [ReleaseDate] <= ? ");
        }
        if (title != null && !title.isEmpty()) {
            sql.append(" AND [Title] LIKE ? ");
        }
        if (status != null && !status.isEmpty()) {
            if (status.equalsIgnoreCase("low")) {
                sql.append(" GROUP BY \n"
                        + "                     Product.ProductID, \n"
                        + "                     Product.Title, \n"
                        + "                     Product.Thumbnail\n"
                        + "			     HAVING SUM(ProductDetails.Quantity) <= 20 ");

            } else {
                sql.append(" AND Product.FeatureID = 3\n"
                        + "				 GROUP BY \n"
                        + "                     Product.ProductID, \n"
                        + "                     Product.Title, \n"
                        + "                     Product.Thumbnail ");

            }
        } else {
            sql.append(" GROUP BY \n"
                    + "    Product.ProductID, \n"
                    + "    Product.Title, \n"
                    + "    Product.Thumbnail");
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int index = 1;
            if (datefrom != null && !datefrom.isEmpty()) {
                st.setString(index++, datefrom);
            }
            if (dateto != null && !dateto.isEmpty()) {
                st.setString(index++, dateto);
            }
            if (title != null && !title.isEmpty()) {
                st.setString(index++, "%" + title + "%");
            }

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                WarehouseProduct c = new WarehouseProduct(rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getString("Thumbnail"),
                        rs.getInt("TotalQuantity"),
                        rs.getInt("TotalHold"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public ProductWarehouse getProduct(String productid) {
        String sql = "SELECT [ProductID]\n"
                + "      ,[Title]\n"
                + "      ,[Thumbnail]\n"
                + "      ,[ReleaseDate]\n"
                + "  FROM [dbo].[Product]\n"
                + "  WHERE [ProductID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductWarehouse c = new ProductWarehouse(rs.getInt("ProductID"),
                        rs.getString("Title"),
                        rs.getString("Thumbnail"),
                        rs.getDate("ReleaseDate"));

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public List<ProductStock> getProductStock(String productid) {
        List<ProductStock> list = new ArrayList<>();
        String sql = "SELECT Product.Price, Product.Quantity\n"
                + "FROM     Product\n"
                + "WHERE ProductDetails.ProductID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productid);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductStock c = new ProductStock(
                        rs.getDouble("OriginalPrice"),
                        rs.getInt("Quantity"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public void updateProductWarehouse(String quantity, String price, String productid) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [Price] = ?\n"
                + "      ,[Quantity] = [Quantity] + ?\n"
                + " WHERE [ProductID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, price);
            st.setString(2, quantity);
            st.setString(3, productid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getFeatureID(String productID) {
        int a = 0;
        String sql = "SELECT [FeatureID]\n"
                + "  FROM [dbo].[Product]\n"
                + "  WHERE [ProductID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("FeatureID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    public void updateFeature(String productID) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [FeatureID] = 1\n"
                + " WHERE [ProductID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ProductType getProductForWarehouse() {
        String sql = "SELECT \n"
                + "    (SELECT COUNT(*) \n"
                + "     FROM (\n"
                + "         SELECT \n"
                + "             Product.ProductID\n"
                + "         FROM \n"
                + "             Product\n"
                + "         JOIN \n"
                + "             ProductDetails ON Product.ProductID = ProductDetails.ProductID\n"
                + "         GROUP BY \n"
                + "             Product.ProductID\n"
                + "         HAVING \n"
                + "             SUM(ProductDetails.Quantity) <= 20\n"
                + "     ) AS LowStockProducts) AS TotalLow,\n"
                + "\n"
                + "    (SELECT COUNT(*) \n"
                + "     FROM Product \n"
                + "     WHERE Product.FeatureID = 3) AS TotalNew;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductType c = new ProductType(rs.getInt("TotalLow"),
                        rs.getInt("TotalNew"));

                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public static void main(String[] args) {
        WarehouseDAO d = new WarehouseDAO();
        System.out.println(d.getAllStatus().isEmpty());
    }
}
