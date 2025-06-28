
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

        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f1f3f4;
            }

            .container {
                max-width: 100%;
                margin: 0 auto;
                padding: 20px;
            }

            h3 {
                color: #333;
                text-align: center;
                margin-bottom: 30px;
            }

            .filters {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
                margin-bottom: 20px;
                background-color: #fff;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            .filters div {
                margin-bottom: 10px;
            }

            .table {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                font-size: 1.1em;
                border-collapse: collapse;
            }

            .table thead {
                background-color: #007bff;
                color: white;
            }

            .table th,
            .table td {
                vertical-align: middle;
                text-align: center;
            }

            .table td a {
                color: #007bff;
                text-decoration: none;
            }

            .table td a:hover {
                text-decoration: underline;
            }

            .pagination {
                justify-content: center;
                margin-top: 20px;
            }

            .order-status-icon {
                font-size: 24px; /* Tăng kích thước icon */
                margin-right: 5px;
                transition: transform 0.2s; /* Thêm hiệu ứng chuyển động */
            }

            .order-status {
                display: flex; /* Sử dụng flexbox để căn chỉnh */
                align-items: center;
                padding: 10px; /* Thêm khoảng cách bên trong */
                border-radius: 5px; /* Bo góc */
                transition: background-color 0.3s; /* Thêm hiệu ứng cho nền */
            }

            .order-status:hover {
                background-color: rgba(0, 0, 0, 0.05); /* Hiệu ứng hover cho trạng thái */
            }

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

            .filters div {
                margin-bottom: 10px;
                flex: 1;
                margin-right: 10px;
            }

            .filters div:last-child {
                margin-right: 0;
            }

        </style>
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <%@ include file="SaleComponents.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container col-11">
                        <div class="container-fluid" style="margin-top: 20px;">
                            <div class="row">
                                <div class="col-xl-4 col-md-6">
                                    <div class="card text-white mb-4" style="background-color: #FFA500; cursor: pointer;" onclick="window.location.href = 'orderforsale?status=process';">
                                        <div class="card-body">
                                            <i class="fas fa-hourglass-start"></i> Orders to Process: <strong>${ordertype.type1}</strong>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-md-6">
                                    <div class="card  text-white mb-4" style="background-color: #28A745; cursor: pointer;" onclick="window.location.href = 'orderforsale?status=completed';">
                                        <div class="card-body">
                                            <i class="fas fa-check-double"></i> Completed Orders: <strong>${ordertype.type2}</strong>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-md-6">
                                    <div class="card text-white mb-4" style="background-color: #DC3545; cursor: pointer;" onclick="window.location.href = 'orderforsale?status=reject';">
                                        <div class="card-body">
                                            <i class="fas fa-times-circle"></i> Reject Orders: <strong>${ordertype.type3}</strong>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Filters Section -->
                        <form action="orderforsale" method="get">
                            <div class="filters">               
                                <div>
                                    <label for="date-from">Date From</label>
                                    <input type="date" name="datefrom" id="date-from" class="form-control" value="${datefrom}">
                                </div>
                                <c:if test="${status != null}">
                                    <input type="hidden" name="status" value="${status}">
                                </c:if>
                                <div>
                                    <label for="date-to">Date To</label>
                                    <input type="date" name="dateto" id="date-to" class="form-control" value="${dateto}">
                                </div>
                                <div>
                                    <label for="customer-name">Sales Name</label>
                                    <input type="text" id="sale-name" name="salename" class="form-control" value="${salename}" placeholder="Enter Sale Name">
                                </div>
                                <div>
                                    <label for="status">Status</label>
                                    <select name="statusfid" id="status" class="form-control">
                                        <option value="">All</option>
                                        <c:forEach var="s" items="${liststatus}">
                                            <option ${statusfid == s.attributeID ? "selected" : ""} value="${s.attributeID}">${s.attributeName}</option>
                                        </c:forEach>                          
                                    </select>
                                </div>
                                <div>
                                    <button type="Submit" style="margin-top: 25px; margin-right: 10px;" id="filter-btn" class="btn btn-primary">Filter</button>                
                                    <button type="Reset" style="margin-top: 25px;" id="filter-btn" class="btn btn-primary" onclick="window.location.href = 'orderforsale'">Reset</button>
                                </div>                
                            </div>
                        </form>                   
                        <table class="table table-bordered" id="orderTable">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Customer</th>
                                    <th>Product(s)</th>
                                    <th>Total Cost</th>
                                    <th>Payment Method</th>
                                    <th>Create Date</th>                                               
                                    <th>Sale Name</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="l" items="${listorder}">
                                    <tr>
                                        <td><a href="orderdetailforsale?orderid=${l.orderID}">${l.orderID}</a></td>
                                        <td>${l.customerName}</td>
                                        <td>${l.firstTitle} 
                                            <c:if test="${l.otherProducts > 0}">
                                                (+${l.otherProducts} more)
                                            </c:if></td>
                                        <td>${l.totalCost}</td>
                                        <td>${l.paymentMethod}</td>
                                        <td>${l.createdOrder}</td>
                                        <td>${l.saleName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${l.statusID == 1}">
                                                    <div class="order-status pending">
                                                        <i class="fas fa-hourglass-start order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 2}">
                                                    <div class="order-status confirmed">
                                                        <i class="fas fa-thumbs-up order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 3}">
                                                    <div class="order-status rejected">
                                                        <i class="fas fa-times-circle order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 4}">
                                                    <div class="order-status processing">
                                                        <i class="fas fa-spinner fa-spin order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 5}">
                                                    <div class="order-status packed">
                                                        <i class="fas fa-box order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 6}">
                                                    <div class="order-status in-transit">
                                                        <i class="fas fa-shipping-fast order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 7}">
                                                    <div class="order-status delivered">
                                                        <i class="fas fa-box-open order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 8}">
                                                    <div class="order-status delivery-failed">
                                                        <i class="fas fa-exclamation-triangle order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>
                                                <c:when test="${l.statusID == 9}">
                                                    <div class="order-status completed">
                                                        <i class="fas fa-check-double order-status-icon"></i>
                                                        <span>${l.statusName}</span>
                                                    </div>
                                                </c:when>                                               
                                            </c:choose>
                                        </td>
                                        <td>
                                            <div style="display: flex; flex-direction: row; justify-content: center; white-space: nowrap;">
                                                <c:choose>
                                                    <c:when test="${l.statusID == 1}">
                                                        <form action="orderforsale" method="post" style="margin-right: 5px;">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="2">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="salename" value="${salename}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn btn-success btn-sm update-status" style="white-space: nowrap; white-space: nowrap; display: flex; align-items: center;">
                                                                <i class="fas fa-thumbs-up order-status-icon"></i>
                                                                Confirmed
                                                            </button>
                                                        </form>       
                                                        <form action="orderforsale" method="post" style="margin-right: 5px;">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="3">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="salename" value="${salename}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn btn-danger btn-sm update-status" style="white-space: nowrap; white-space: nowrap; display: flex; align-items: center;" id="cancel2">
                                                                <i class="fas fa-times-circle order-status-icon"></i>
                                                                Rejected
                                                            </button>
                                                        </form>
                                                    </c:when>
                                                    <c:when test="${l.statusID == 7}">
                                                        <form action="orderforsale" method="post" style="margin-right: 5px;">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="9">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="salename" value="${salename}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn btn-sm update-status" style="background-color: #28A745; color: #FFFFFF; white-space: nowrap; display: flex; align-items: center;" id="cancel1">
                                                                <i class="fas fa-check-double order-status-icon"></i>
                                                                Completed
                                                            </button>
                                                        </form>
                                                    </c:when>
                                                    <c:when test="${l.statusID == 8 }">
                                                        <form action="orderforsale" method="post" style="margin-right: 5px;">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="3">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="salename" value="${salename}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn btn-danger btn-sm update-status" style="white-space: nowrap; white-space: nowrap; display: flex; align-items: center;" id="cancel2">
                                                                <i class="fas fa-times-circle order-status-icon"></i>
                                                                Rejected
                                                            </button>
                                                        </form>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <script src="js/scripts.js"></script>
        <script>
                                        window.addEventListener('DOMContentLoaded', event => {
                                            const orderTable = document.getElementById('orderTable');
                                            if (orderTable) {
                                                const savedPage = localStorage.getItem("currentPage");
                                                const dataTable = new simpleDatatables.DataTable(orderTable, {
                                                    searchable: true,
                                                    perPage: 5,
                                                    perPageSelect: false,
                                                    columns: [
                                                        {select: 0, sortable: true, searchable: true},
                                                        {select: 1, sortable: true, searchable: true},
                                                        {select: 2, sortable: false, searchable: false},
                                                        {select: 3, sortable: true, searchable: false},
                                                        {select: 4, sortable: false, searchable: false},
                                                        {select: 5, sortable: true, searchable: false},
                                                        {select: 6, sortable: true, searchable: true},
                                                        {select: 7, sortable: true, searchable: false},
                                                        {select: 8, sortable: false, searchable: false},
                                                    ],
                                                    labels: {
                                                        noRows: "No results found.",
                                                        info: "",
                                                    }
                                                });

                                                if (savedPage) {
                                                    dataTable.page(parseInt(savedPage, 10));
                                                }

                                                dataTable.on("datatable.page", function (page) {
                                                    localStorage.setItem("currentPage", page);
                                                });
                                            }
                                        });
        </script>
        <script>
            document.getElementById("cancel1").onclick = function (event) {
                this.style.pointerEvents = "none";
            };
        </script>
        <script>
            document.getElementById("cancel2").onclick = function (event) {
                this.style.pointerEvents = "none";
            };
        </script>
    </body>
</html>

