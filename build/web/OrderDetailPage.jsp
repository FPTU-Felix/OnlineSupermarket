<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.OrderDetail"%>
<%@page import="Model.Order"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    Order order = (Order) request.getAttribute("order");
    List<OrderDetail> details = (List<OrderDetail>) request.getAttribute("orderDetails");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Detail</title>
    </head>
    <body>
        <h2>Order #<%= order.getOrderID() %> - Details</h2>

        <p><strong>Receiver:</strong> <%= order.getReceiverName() %></p>
        <p><strong>Address:</strong> <%= order.getReceiverAddress() %></p>
        <p><strong>Phone:</strong> <%= order.getReceiverMobile() %></p>
        <p><strong>Status:</strong> <%= order.getStatusName() %></p>
        <p><strong>Created On:</strong> <%= order.getCreatedOrder() %></p>

        <h3>Items:</h3>
        <table border="1" cellpadding="10" cellspacing="0">
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Thumbnail</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="d" items="${orderDetails}">
                    <tr>
                        <td>${d.title}</td>
                        <td>${d.quantity}</td>
                        <td><img src="images/${d.thumbnail}" width="60px" /></td>
                        <td>${d.price}</td>
                        <td>${d.price * d.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>
        <a href="OrderOverviewPageController">← Back to Orders</a>
    </body>
</html>
