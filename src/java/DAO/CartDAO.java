/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CartIteam;
import Model.CartItemMore;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.ProductQty;
import Model.Receiver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class CartDAO extends DBContext {

    public List<Receiver> getAllReceiver(int cusid) {
        List<Receiver> list = new ArrayList<>();
        String sql = "SELECT [ReceiverID]\n"
                + "      ,[CustomerID]\n"
                + "      ,[ReceiverName]\n"
                + "      ,[Email]\n"
                + "      ,[Mobile]\n"
                + "      ,[Gender]\n"
                + "      ,[Address]\n"
                + "      ,[ReceiverType]\n"
                + "      ,[CreatedAt]\n"
                + "      ,[UpdatedAt]\n"
                + "FROM [dbo].[Receiver]\n"
                + "WHERE [CustomerID] = ? AND ([ReceiverType] = 'Current' OR [ReceiverType] = 'History')\n"
                + "ORDER BY  [ReceiverType] ASC;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Receiver c = new Receiver(rs.getInt("ReceiverID"),
                        rs.getInt("CustomerID"),
                        rs.getString("ReceiverName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getString("ReceiverType"),
                        rs.getString("CreatedAt"),
                        rs.getString("UpdatedAt"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public Receiver getCurrentReceiver(int cusid) {

        String sql = "SELECT ReceiverID, CustomerID, ReceiverName, Email, Mobile, Gender, Address, "
                + "ReceiverType, CreatedAt, UpdatedAt "
                + "FROM Receiver "
                + "WHERE CustomerID = ? AND ReceiverType = 'Current' "
                + "LIMIT 1";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Receiver c = new Receiver(
                        rs.getInt("ReceiverID"),
                        rs.getInt("CustomerID"),
                        rs.getString("ReceiverName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getString("ReceiverType"),
                        rs.getString("CreatedAt"),
                        rs.getString("UpdatedAt")
                );
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteReceiverAddress(String receiverid) {
        String sql = "UPDATE Receiver\n"
                + "SET ReceiverType = 'Deleted',\n"
                + "    UpdatedAt = GETDATE()\n"
                + "WHERE ReceiverID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, receiverid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Receiver exitsReceiverAddress(int cusid, String name, String email, String mobile, String gender, String address) {
        String sql = "SELECT ReceiverID\n"
                + "      ,CustomerID\n"
                + "      ,ReceiverName\n"
                + "      ,Email\n"
                + "      ,Mobile\n"
                + "      ,Gender\n"
                + "      ,Address\n"
                + "      ,ReceiverType\n"
                + "      ,CreatedAt\n"
                + "      ,UpdatedAt\n"
                + "  FROM Receiver\n"
                + "  WHERE CustomerID = ?\n"
                + "  AND ReceiverName = ?\n"
                + "  AND Email = ?\n"
                + "  AND Mobile = ?\n"
                + "  AND Gender =?\n"
                + "  AND Address = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
            st.setString(2, name);
            st.setString(3, email);
            st.setString(4, mobile);
            st.setString(5, gender);
            st.setString(6, address);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Receiver c = new Receiver(rs.getInt("ReceiverID"),
                        rs.getInt("CustomerID"),
                        rs.getString("ReceiverName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getString("ReceiverType"),
                        rs.getString("CreatedAt"),
                        rs.getString("UpdatedAt"));

                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void changeCurrentReceiver(int cusid) {
        String sql = "UPDATE Receiver\n"
                + "   SET ReceiverType = 'History',\n"
                + "       UpdatedAt = NOW()\n"
                + " WHERE CustomerID = ? AND ReceiverType = 'Current'";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void changeHistoryReceiver(int receiverID) {
        String sql = "UPDATE Receiver\n"
                + "   SET ReceiverType = 'Current',\n"
                + "       UpdatedAt = NOW()\n"
                + " WHERE ReceiverID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, receiverID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addReceiverAddress(int cusid, String name, String email, String mobile, String gender, String address) {
        String sql = "INSERT INTO Receiver\n"
                + "           (CustomerID\n"
                + "           ,ReceiverName\n"
                + "           ,Email\n"
                + "           ,Mobile\n"
                + "           ,Gender\n"
                + "           ,Address\n"
                + "           ,ReceiverType\n"
                + "           ,CreatedAt\n"
                + "           ,UpdatedAt)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,'Current',NOW(),NOW())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusid);
            st.setString(2, name);
            st.setString(3, email);
            st.setString(4, mobile);
            st.setString(5, gender);
            st.setString(6, address);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Receiver getReceiverByID(String receiverID) {
        String sql = "SELECT ReceiverID\n"
                + "      ,CustomerID\n"
                + "      ,ReceiverName\n"
                + "      ,Email\n"
                + "      ,Mobile\n"
                + "      ,Gender\n"
                + "      ,Address\n"
                + "      ,ReceiverType\n"
                + "      ,CreatedAt\n"
                + "      ,UpdatedAt\n"
                + "  FROM Receiver\n"
                + "  WHERE ReceiverID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, receiverID);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Receiver c = new Receiver(rs.getInt("ReceiverID"),
                        rs.getInt("CustomerID"),
                        rs.getString("ReceiverName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
                        rs.getString("Gender"),
                        rs.getString("Address"),
                        rs.getString("ReceiverType"),
                        rs.getString("CreatedAt"),
                        rs.getString("UpdatedAt"));
                return c;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateReceiverAddress(String recid, String name, String email, String mobile, String gender, String address) {
        String sql = "UPDATE Receiver\n"
                + "   SET ReceiverName = ?\n"
                + "      ,Email = ?\n"
                + "      ,Mobile = ?\n"
                + "      ,Gender = ?\n"
                + "      ,Address = ?\n"
                + "      ,UpdatedAt = GETDATE()\n"
                + " WHERE ReceiverID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, mobile);
            st.setString(4, gender);
            st.setString(5, address);
            st.setString(6, recid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePaymentStatus(int orderID) {
        String sql = "UPDATE Orders\n"
                + "   SET PaymentStatus = 'Paid'\n"
                + " WHERE OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getCartId(int cusId) {
        int cartid = 0;
        String sql = "SELECT CartID\n"
                + "  FROM Cart\n"
                + "  WHERE CustomerID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cartid = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cartid;
    }

    public int getTotalItem(int cartid) {
        int Total = 0;
        String sql = "SELECT SUM(Quantity) AS TotalItems FROM CartItems WHERE CartID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Total = rs.getInt("TotalItems");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Total;
    }

    public List<CartItemMore> getCartItems(int cartId) {
        List<CartItemMore> list = new ArrayList<>();
        String sql = "SELECT CartItems.CartItemID, CartItems.CartID, CartItems.ProductID, CartItems.Title, CartItems.Quantity, CartItems.Thumbnail, CartItems.Price\n"
                + "FROM     CartItems\n"
                + "				  WHERE CartItems.CartID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cartId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartItemMore c = new CartItemMore(rs.getInt("CartItemID"),
                        rs.getInt("CartID"),
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

    public int getSaleID() {
        int SaleID = 0;
        String sql = "SELECT "
                + "    Employee.EmployeeID "
                + "FROM "
                + "    Employee "
                + "LEFT JOIN "
                + "    Orders ON Employee.EmployeeID = Orders.SaleID "
                + "WHERE "
                + "    Employee.RoleID = 2 AND Employee.Status = 'Active' "
                + "GROUP BY "
                + "    Employee.EmployeeID "
                + "ORDER BY "
                + "    COUNT(Orders.OrderID) ASC, Employee.EmployeeID ASC "
                + "LIMIT 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                SaleID = rs.getInt("EmployeeID");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching SaleID: " + e.getMessage());
        }
        return SaleID;
    }

    public Receiver getReceiverInforByCustomerID(int customerID) {
        String sql = "Select *from Receiver where CustomerID = ? AND ReceiverType ='Current'";
        Receiver r = new Receiver();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int receiverID = rs.getInt(1);
                int cusID = rs.getInt(2);
                String receiverName = rs.getString(3);
                String email = rs.getString(4);
                String mobile = rs.getString(5);
                String gender = rs.getString(6);
                String address = rs.getString(7);
                String receiverType = rs.getString(8);
                String createdAt = rs.getString(9);
                String updatedAt = rs.getString(10);
                r = new Receiver(receiverID, cusID, receiverName, email, mobile, gender, address, receiverType, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching SaleID: " + e.getMessage());
        }
        return r;
    }

    public int insertOrder(int cusID, String receiverName, String receiverGender, String receiverEmail, String receiverMobile, String receiverAddress, String receiverNotes, String paymentMethod, int SaleID) {
        int orderId = -1;
        String sql = "INSERT INTO Orders\n"
                + "           (CustomerID\n"
                + "           ,ReceiverName\n"
                + "           ,ReceiverGender\n"
                + "           ,ReceiverEmail\n"
                + "           ,ReceiverMobile\n"
                + "           ,ReceiverAddress\n"
                + "           ,ReceiverNotes\n"
                + "           ,StatusID\n"
                + "           ,PaymentMethod\n"
                + "           ,PaymentStatus\n"
                + "           ,CreatedOrder\n"
                + "           ,SaleID\n"
                + "           ,SaleNotes)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,1,?,?,NOW(),?,NULL)";

        try (
                PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, cusID);
            st.setString(2, receiverName);
            st.setString(3, receiverGender);
            st.setString(4, receiverEmail);
            st.setString(5, receiverMobile);
            st.setString(6, receiverAddress);
            st.setString(7, receiverNotes);
            st.setString(8, paymentMethod);
            st.setString(9, "Unpaid");
            st.setInt(10, SaleID);

            int row = st.executeUpdate();

            if (row > 0) {
                try (ResultSet key = st.getGeneratedKeys()) {
                    if (key.next()) {
                        orderId = key.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return orderId;
    }

    public void insertOrderDetail(List<CartItemMore> cartItems, int orderID) {
        String sql = "INSERT INTO OrderDetails \n"
                + "           (OrderID\n"
                + "           ,ProductID\n"
                + "           ,Title\n"
                + "           ,Quantity\n"
                + "           ,Thumbnail\n"
                + "           ,Price)\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            for (CartIteam c : cartItems) {
                st.setInt(1, orderID);
                st.setInt(2, c.getProductID());
                st.setString(3, c.getTitle());
                st.setInt(4, c.getQuantity());
                st.setString(5, c.getThumbnail());
                st.setDouble(6, c.getPrice());

                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getPaymentMethod(int orderid) {
        String method = "";
        String sql = "SELECT PaymentMethod\n"
                + "  FROM Orders\n"
                + "  WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                method = rs.getString("PaymentMethod");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return method;
    }

    public double getTotalOrder(int orderid) {
        double a = 0;
        String sql = "SELECT SUM(Quantity * Price) AS TotalCost\n"
                + "FROM OrderDetails\n"
                + "WHERE OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getDouble("TotalCost");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    public int getStock(int productId) {
        int stock = 0;
        String sql = "SELECT [Quantity]\n"
                + "  FROM [dbo].[ProductDetails]\n"
                + "  WHERE [ProductID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("Quantity");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return stock;
    }

    public int getHold(int productId) {
        int hold = 0;
        String sql = "SELECT [Hold]\n"
                + "  FROM [dbo].[ProductDetails]\n"
                + "  WHERE [ProductID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                hold = rs.getInt("Hold");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return hold;
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

    public void updateHoldProduct02(List<OrderDetail> orderdetail, List<ProductQty> products) {
        String sql = "UPDATE [dbo].[ProductDetails]\n"
                + "   SET [Hold] = [Hold] - ?\n"
                + " WHERE [ProductID] = ?";

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

    public void updateHoldProduct(List<CartItemMore> cartitem, List<ProductQty> products) {
        String sql = "UPDATE Product\n"
                + "   SET Hold = Hold + ?\n"
                + " WHERE ProductID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (CartIteam c : cartitem) {
                for (ProductQty p : products) {
                    if (c.getProductID() == p.getProductID()) {
                        int cartQuantity = c.getQuantity();
                        int productQuantity = p.getQuantity();

                        if (cartQuantity <= productQuantity) {
                            st.setInt(1, cartQuantity);
                            st.setInt(2, c.getProductID());
                            st.executeUpdate();
                            break;
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public double getFinalPrice(int productID) {
        double price = 0.0;
        String sql = "SELECT CAST(ProductDetails.SellPrice * (1 - Brand.Discount / 100) AS DECIMAL(18, 2)) AS FinalPrice "
                + "FROM Brand "
                + "INNER JOIN Product ON Brand.BrandID = Product.BrandID "
                + "INNER JOIN ProductDetails ON Product.ProductID = ProductDetails.ProductID "
                + "WHERE ProductDetails.ProductID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("FinalPrice");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return price;
    }

    public void updateOrderStatus(int orderid, int statusid) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [StatusID] = ?\n"
                + " WHERE [OrderID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusid);
            st.setInt(2, orderid);
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Order getOrderByID(int orderid) {
        String sql = "SELECT Orders.OrderID, Orders.CustomerID, Orders.ReceiverName, Orders.ReceiverGender, Orders.ReceiverEmail, Orders.ReceiverMobile, Orders.ReceiverAddress, Orders.ReceiverNotes, Orders.StatusID, Status.StatusName, \n"
                + "                  Orders.PaymentMethod, Orders.CreatedOrder, Orders.SaleID, Orders.SaleNotes\n"
                + "FROM     Orders INNER JOIN\n"
                + "                  Status ON Orders.StatusID = Status.StatusID\n"
                + "				  WHERE Orders.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderid);

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

    public List<OrderDetail> getOrderDetailByID(int orderid) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT OrderDetails.OrderDetailID, OrderDetails.OrderID, OrderDetails.ProductID, OrderDetails.Title, OrderDetails.Quantity, OrderDetails.Thumbnail, OrderDetails.Price, OrderDetails.SizeID, Size.SizeName\n"
                + "FROM     OrderDetails INNER JOIN\n"
                + "                  Size ON OrderDetails.SizeID = Size.SizeID\n"
                + "				  WHERE OrderDetails.OrderID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderid);

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

    public Customer getCustomer(int cusid) {

        String sql = "SELECT [CustomerID]\n"
                + "      ,[FullName]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Address]\n"
                + "      ,[Avatar]\n"
                + "      ,[Status]\n"
                + "      ,[CreateAt]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  WHERE [CustomerID] = ?";

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

    public static void main(String[] args) {
        CartDAO d = new CartDAO();
        d.insertOrderDetail(d.getCartItems(1),1);
    }
}
