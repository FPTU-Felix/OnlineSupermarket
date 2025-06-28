<%-- 
    Document   : OrderForSales
    Created on : 15 Oct 2024, 21:16:52
    Author     : Anh Tuan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Orders For Sale</title>       
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.css">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <style>
            .pending {
                color: #FFA500;
            }

            .confirmed {
                color: #28A745;
            }

            .rejected {
                color: #DC3545;
            }

            .processing {
                color: #007BFF;
            }

            .packed {
                color: #FFD700;
            }

            .in-transit {
                color: #6F42C1;
            }

            .delivered {
                color: #20C997;
            }

            .delivery-failed{
                color: #DC3545;
            }

            .completed {
                color: #28A745;
            }

            .body {
                font-family: 'Arial', sans-serif;
                background-color: #f1f3f4;
                padding: 30px;
            }
            .container {
                max-width: 1200px;
                margin: auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            h3 {
                text-align: center;
                color: #333;
                margin-bottom: 20px;
            }
            .order-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .order-header h4 {
                margin: 0;
                font-size: 1.2em;
            }
            .order-header .status {
                color: #28a745;
                font-weight: bold;
                font-size: 1em;
            }
            .info-section {
                display: flex;
                justify-content: space-between;
                flex-wrap: wrap;
                margin-bottom: 30px;
            }
            .info-box {
                flex: 1;
                padding: 20px;
                border: 1px solid #ced4da;
                border-radius: 8px;
                margin-bottom: 20px;
                background-color: #f9f9f9;
                min-width: 300px;
                margin-right: 10px;
                transition: box-shadow 0.3s ease;
            }
            .info-box:hover {
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
            }
            .info-box:last-child {
                margin-right: 0;
            }
            .info-box h5 {
                font-size: 1.1em;
                margin-bottom: 10px;
                color: #333;
                display: flex;
                align-items: center;
                gap: 10px;
            }
            .info-box p {
                margin: 5px 0;
                font-size: 1em;
            }
            .notes-box textarea {
                width: 100%;
                height: 100px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                padding: 10px;
                font-size: 1em;
            }
            .btn-save {
                margin-top: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                font-size: 1em;
                transition: background-color 0.3s ease, transform 0.3s ease;
            }

            .btn-save:hover {
                background-color: #0056b3;
                transform: translateY(-2px);
            }

            .btn-save:active {
                transform: translateY(0);
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <%@ include file="SaleComponents.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
                        <div class="order-header">
                            <div>
                                <p><a href="orderforsale" style="
                                      display: inline-block;
                                      padding: 8px 16px;
                                      background-color: #007bff;
                                      color: white;
                                      text-decoration: none;
                                      border-radius: 4px;
                                      transition: background-color 0.3s ease;
                                      " onmouseover="this.style.backgroundColor = '#0056b3'"
                                      onmouseout="this.style.backgroundColor = '#007bff'">
                                        ← Back to Orders
                                    </a></p>
                                <h4>Order ID: #${order.orderID}</h4>
                                <p>${order.createdOrder}</p>
                            </div>
                            <span class="status">
                                <c:choose>
                                    <c:when test="${order.statusID == 1}">
                                        <div class="order-status pending">
                                            <i class="fas fa-hourglass-start order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 2}">
                                        <div class="order-status confirmed">
                                            <i class="fas fa-thumbs-up order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 3}">
                                        <div class="order-status rejected">
                                            <i class="fas fa-times-circle order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 4}">
                                        <div class="order-status processing">
                                            <i class="fas fa-spinner fa-spin order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 5}">
                                        <div class="order-status packed">
                                            <i class="fas fa-box order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 6}">
                                        <div class="order-status in-transit">
                                            <i class="fas fa-shipping-fast order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 7}">
                                        <div class="order-status delivered">
                                            <i class="fas fa-box-open order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 8}">
                                        <div class="order-status delivery-failed">
                                            <i class="fas fa-exclamation-triangle order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${order.statusID == 9}">
                                        <div class="order-status completed">
                                            <i class="fas fa-check-double order-status-icon"></i>
                                            <span>${order.statusName}</span>
                                        </div>
                                    </c:when>                                               
                                </c:choose>
                            </span>
                        </div>

                        <!-- Customer, Order Info, Receiver Info -->
                        <div class="info-section">
                            <!-- Customer Info -->
                            <div class="info-box">
                                <h5><i class="fas fa-user"></i> Customer</h5>
                                <p>Customer ID: ${cus.customerID}</p>
                                <p>Full Name: ${cus.fullName}</p>
                                <p>Email: ${cus.email}</p>
                                <p>Mobile: ${cus.phoneNumber}</p>
                            </div>

                            <!-- Order Info -->
                            <div class="info-box">
                                <h5><i class="fas fa-box"></i> Order Info</h5>
                                <p>Payment Method: ${order.paymentMethod}</p>
                                <p>Total Cost: ${totalcost} $</p>
                                <p>Payment Status:</p>
                                <div style="display: flex; align-items: center;">
                                    <p style="margin: 0; padding-right: 10px;">Status:</p>
                                    <form method="post" action="orderdetailforsale" style="display: inline-flex;">
                                        <select name="statusid" onchange="this.form.submit()" style="padding: 6px 10px; font-size: 1em; border-radius: 5px; border: 1px solid #ced4da; background-color: #fff; color: #333; cursor: pointer; transition: border-color 0.3s;">
                                            <c:forEach items="${liststatus}" var="s">
                                                <option value="${s.attributeID}"                                        
                                                        ${s.attributeID == 4 || s.attributeID == 5 || s.attributeID == 6 || s.attributeID == 7 || s.attributeID == 8 ? 'disabled' : ''}
                                                        ${order.statusID == 1 && s.attributeID == 9 ? 'disabled' : ''}
                                                        ${order.statusID == 2 && (s.attributeID == 1 || s.attributeID == 3 || s.attributeID == 9)? 'disabled' : ''}
                                                        ${order.statusID == 3 && (s.attributeID == 1 || s.attributeID == 2 || s.attributeID == 9)? 'disabled' : ''}
                                                        ${order.statusID == 9 && (s.attributeID == 1 || s.attributeID == 2 || s.attributeID == 3)? 'disabled' : ''}
                                                        ${s.attributeID == order.statusID ? 'selected disabled' : ''}         
                                                        >
                                                    ${s.attributeName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="orderid" value="${order.orderID}" />
                                    </form>
                                </div>
                                    <hr style="border: 1px solid #DC3545;">
                                        <p >Customer Notes: ${order.receiverNotes != null && !order.receiverNotes.isEmpty() ? order.receiverNotes : 'N/A'}</p>
                            </div>

                            <!-- Receiver Info -->
                            <div class="info-box">
                                <h5><i class="fas fa-shipping-fast"></i> Receiver Info</h5>
                                <p>Full Name: ${order.receiverName}</p>
                                <p>Gender: ${order.receiverGender}</p>
                                <p>Email: ${order.receiverEmail}</p>
                                <p>Mobile: ${order.receiverMobile}</p>
                                <p>Address: ${order.receiverAddress}</p>
                            </div>
                            <!-- Notes -->
                            <div class="info-box notes-box col-md-5">
                                <h5><i class="fas fa-sticky-note"></i>Sale Notes</h5>
                                <form action="orderdetailforsale"  method="POST">
                                    <input type="hidden" name="orderid" value="${order.orderID}" />
                                    <c:if test="${sessionScope.account.roleId == 4 && order.statusID != 3}">
                                        <label for="saleName">Sale Name:</label>
                                        <select name="saleid" id="saleName"  style="padding: 6px 10px; font-size: 1em; border-radius: 5px; border: 1px solid #ced4da; background-color: #fff; color: #333; cursor: pointer; transition: border-color 0.3s;">
                                            <c:forEach var="p" items="${listsale}">
                                                <option value="${p.saleID}" ${order.saleID == p.saleID ? 'selected' : ''}>${p.saleName} (${p.totalOrder})</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${sessionScope.account.roleId == 2}">
                                        <input type="hidden" name="saleid" value="${order.saleID}" >
                                    </c:if>
                                    <input type="hidden" name="orderid" value="${order.orderID}" />
                                    <textarea name="salenotes" placeholder="Type some note..." >${order.saleNotes}</textarea>
                                    <button type="submit" class="btn-save">Save</button>
                                </form>
                            </div>
                            <div class="info-box col-md-7">
                                <div class="table-responsive" style="flex: 1; margin-left: 10px;">
                                    <table class="table table-bordered" 
                                           style="border-collapse: separate; border-spacing: 0; border-top-left-radius: 10px; border-top-right-radius: 10px;">
                                        <thead>
                                            <tr>
                                                <th style="border-top-left-radius: 10px;">Thumbnail</th>
                                                <th>Title</th>
                                                <th>Category</th>
                                                <th>Price</th>
                                                <th>Qty</th>
                                                <th style="border-top-right-radius: 10px;">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="l" items="${listitems}">s
                                                <tr >
                                                    <td><img src="${l.thumbnail}" alt="Product Thumbnail" class="img-thumbnail" style="width: 55px; height: auto;"></td>
                                                    <td>${l.title}</td>
                                                    <td>${l.categoryName}</td>
                                                    <td>${l.price}$</td>
                                                    <td>${l.quantity}</td>
                                                    <td>${l.price * l.quantity}$</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div><script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="js/scripts.js"></script>        
    </body>
</html>