<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="DTO.OrderDTO"%>  <%-- Đổi từ Model.Order sang DTO.OrderDTO --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>All Orders Overview</title>
    </head>
    <body>
        <h1>All Orders</h1>

        <!-- Bộ lọc theo trạng thái -->
        <form method="get" action="OrderOverviewPageController">
            <label for="status">Filter by Status:</label>
            <select name="status" onchange="this.form.submit()">
                <option value="">-- All --</option>
                <c:forEach var="s" items="${['Pending','Confirmed','Processing','Packed','Delivered','Completed']}">
                    <option value="${s}" ${s == selectedStatus ? 'selected' : ''}>${s}</option>
                </c:forEach>
            </select>
        </form>

        <br>

        <table border="1" cellpadding="10" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Customer Name</th>
                    <th>Receiver</th>
                    <th>Payment</th>
                    <th>Status</th>
                    <th>Date</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td>${o.orderId}</td>               <%-- camelCase đúng với field trong DTO --%>
                        <td>${o.customerName}</td>
                        <td>${o.receiverName}</td>
                        <td>${o.paymentMethod}</td>
                        <td>${o.statusName}</td>
                        <td>${o.createdOrder}</td>
                        <td><a href="OrderDetailController?id=${o.orderId}">View</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
