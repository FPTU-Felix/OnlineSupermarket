
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Orders For Warehouse Staff</title>       
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.css">
        <!-- Font & Icon (thêm trong <head>) -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <style>
            /* ----- RESET & FONT ----- */
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f2f4f7;
                margin: 0;
                padding: 0;
                color: #212529;
            }

            /* ----- HEADER ----- */
            header {
                background-color: #fff;
                padding: 16px 24px;
                border-bottom: 1px solid #dee2e6;
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            header i, header a {
                font-size: 1.1rem;
            }

            /* ----- STATUS CARDS ----- */
            .status-cards {
                display: flex;
                flex-wrap: wrap;
                gap: 16px;
                margin: 24px 16px;
            }

            .status-card {
                flex: 1;
                min-width: 250px;
                padding: 20px;
                border-radius: 12px;
                color: #fff;
                font-size: 1rem;
                font-weight: 600;
                display: flex;
                align-items: center;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                transition: 0.3s ease;
            }

            .status-card i {
                margin-right: 12px;
                font-size: 1.3rem;
            }

            .status-card:hover {
                transform: translateY(-3px);
            }

            /* CARD MÀU */
            .status-orders     {
                background-color: #007bff;
            }
            .status-delivered  {
                background-color: #20c997;
            }
            .status-failed     {
                background-color: #dc3545;
            }

            /* ----- FILTER FORM ----- */
            .filters {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                gap: 16px;
                background-color: #ffffff;
                padding: 20px;
                margin: 16px;
                border-radius: 12px;
                box-shadow: 0 2px 6px rgba(0,0,0,0.05);
            }

            .filters input,
            .filters select {
                padding: 8px 10px;
                border-radius: 6px;
                border: 1px solid #ccc;
                width: 100%;
            }

            .filters button {
                padding: 8px 16px;
                border: none;
                border-radius: 6px;
                background-color: #007bff;
                color: white;
                font-weight: 600;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .filters button:hover {
                background-color: #0056b3;
            }

            /* ----- TABLE ĐƠN HÀNG ----- */
            .table-container {
                background-color: #fff;
                margin: 16px;
                border-radius: 12px;
                padding: 16px;
                overflow-x: auto;
                box-shadow: 0 2px 6px rgba(0,0,0,0.05);
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            thead {
                background-color: #343a40;
                color: #fff;
            }

            th, td {
                padding: 12px 16px;
                border-bottom: 1px solid #dee2e6;
                text-align: left;
            }

            tbody tr:hover {
                background-color: #f1f3f5;
            }

            .no-results {
                padding: 16px;
                color: #6c757d;
                font-style: italic;
            }

            /* ----- ORDER STATUS ICON & MÀU ----- */
            .order-status {
                display: inline-flex;
                align-items: center;
                padding: 4px 10px;
                border-radius: 8px;
                font-weight: 500;
                font-size: 0.9rem;
                color: #fff;
                width: fit-content;
            }

            .order-status i {
                margin-right: 6px;
            }

            /* Trạng thái màu sắc */
            .pending           {
                background-color: #ffc107;
                color: #212529 !important;
            }
            .confirmed         {
                background-color: #007bff;
            }
            .rejected          {
                background-color: #dc3545;
            }
            .processing        {
                background-color: #17a2b8;
            }
            .packed            {
                background-color: gold;
                color: #000 !important;
            }
            .in-transit        {
                background-color: #6f42c1;
            }
            .delivered         {
                background-color: #20c997;
            }
            .delivery-failed   {
                background-color: #dc3545;
            }
            .completed         {
                background-color: #28a745;
            }

            /* ----- BUTTON CẬP NHẬT TRẠNG THÁI ----- */
            .btn.update-status {
                display: inline-flex;
                align-items: center;
                padding: 6px 12px;
                border-radius: 8px;
                font-weight: 500;
                font-size: 0.9rem;
                transition: background-color 0.3s;
                background-color: #17a2b8;
                color: white;
                border: none;
                cursor: pointer;
            }

            .btn.update-status i {
                margin-right: 6px;
                font-size: 1rem;
            }

            .btn.update-status:hover {
                background-color: #138496;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <%@ include file="WarehouseComponents.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container col-11">
                        <div class="container-fluid" style="margin-top: 20px;">
                            <div class="row">
                                <div class="col-xl-4 col-md-6">
                                    <div class="card text-white mb-4" style="background-color: #007BFF; cursor: pointer;" onclick="window.location.href = 'orderforwarehouse?status=process';">
                                        <div class="card-body">
                                            <i class="fas fa-spinner fa-spin"></i> Orders to Process: <strong>${ordertype.type1}</strong>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-md-6">
                                    <div class="card  text-white mb-4" style="background-color: #20C997; cursor: pointer;" onclick="window.location.href = 'orderforwarehouse?status=delivered';" >
                                        <div class="card-body">
                                            <i class="fas fa-box-open"></i> Delivered Order: <strong>${ordertype.type2}</strong>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-4 col-md-6">
                                    <div class="card text-white mb-4" style="background-color: #DC3545; cursor: pointer;" onclick="window.location.href = 'orderforwarehouse?status=deliveryfailed';">
                                        <div class="card-body">
                                            <i class="fas fa-exclamation-triangle"></i> Delivery Failed Order: <strong>${ordertype.type3}</strong>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Filters Section -->
                        <form action="orderforwarehouse" method="get">
                            <div class="filters">               
                                <div>
                                    <label for="date-from">Date From</label>
                                    <input type="date" name="datefrom" id="date-from" class="form-control" value="${datefrom}">
                                </div>
                                <div>
                                    <label for="date-to">Date To</label>
                                    <input type="date" name="dateto" id="date-to" class="form-control" value="${dateto}">
                                </div>
                                <c:if test="${status != null}">
                                    <input type="hidden" name="status" value="${status}">
                                </c:if>
                                <div>
                                    <label for="customer-name">Customer Name</label>
                                    <input type="text" id="sale-name" name="cusname" class="form-control" value="${cusname}" placeholder="Enter Customer Name">
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
                                    <button type="Reset" style="margin-top: 25px;" id="filter-btn" class="btn btn-primary" onclick="window.location.href = 'orderforwarehouse'">Reset</button>
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
                                    <th>Mobile</th>
                                    <th>Create Date</th>                                               
                                    <th>Receiver Address</th>
                                    <th>Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="l" items="${listorder}">
                                    <tr>
                                        <td><a href="orderdetailforwarehouse?orderid=${l.orderID}">${l.orderID}</a></td>
                                        <td>${l.customerName}</td>
                                        <td>${l.firstTitle} 
                                            <c:if test="${l.otherProducts > 0}">
                                                (+${l.otherProducts} more)
                                            </c:if></td>
                                        <td>${l.totalCost} $</td>
                                        <td>${l.mobile}</td>
                                        <td>${l.createdOrder}</td>
                                        <td>${l.receiverAddress}</td>
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
                                            <div style="display: flex; justify-content: center; gap: 10px;">
                                                <c:choose>
                                                    <c:when test="${l.statusID == 2}">
                                                        <form action="orderforwarehouse" method="post">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="4">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="cusname" value="${cusname}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn update-status" style="background-color: #007BFF; color: white; white-space: nowrap; display: flex; align-items: center; ">
                                                                <i class="fas fa-spinner fa-spin order-status-icon" style="margin-right: 5px;"></i>
                                                                Processing
                                                            </button>
                                                        </form>                                                      
                                                    </c:when>
                                                    <c:when test="${l.statusID == 4}">
                                                        <form action="orderforwarehouse" method="post">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="5">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="cusname" value="${cusname}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn update-status" style="background-color: gold; color: white; white-space: nowrap; display: flex; align-items: center;">
                                                                <i class="fas fa-box order-status-icon" style="margin-right: 5px;"></i>
                                                                Packed
                                                            </button>
                                                        </form>
                                                    </c:when>
                                                    <c:when test="${l.statusID == 5}">
                                                        <form action="orderforwarehouse" method="post">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="6">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="cusname" value="${cusname}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn update-status" style="background-color: #6F42C1; color: white; white-space: nowrap; display: flex; align-items: center; ">
                                                                <i class="fas fa-shipping-fast order-status-icon" style="margin-right: 5px;"></i>
                                                                In Transit
                                                            </button>
                                                        </form>                                                      
                                                    </c:when>
                                                    <c:when test="${l.statusID == 6}">
                                                        <form action="orderforwarehouse" method="post">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="7">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="cusname" value="${cusname}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn update-status" style="background-color: #20C997; color: white; white-space: nowrap; display: flex; align-items: center;">
                                                                <i class="fas fa-box-open order-status-icon" style="margin-right: 5px;"></i>
                                                                Delivered
                                                            </button>
                                                        </form>
                                                        <form action="orderforwarehouse" method="post">
                                                            <input type="hidden" name="orderid" value="${l.orderID}">
                                                            <input type="hidden" name="statusid" value="8">
                                                            <input type="hidden" name="datefrom" value="${datefrom}">
                                                            <input type="hidden" name="dateto" value="${dateto}">
                                                            <input type="hidden" name="cusname" value="${cusname}">
                                                            <input type="hidden" name="statusfid" value="${statusfid}">
                                                            <input type="hidden" name="status" value="${status}">
                                                            <button class="btn update-status" style="background-color: #DC3545; color: white; white-space: nowrap; display: flex; align-items: center;">
                                                                <i class="fas fa-exclamation-triangle order-status-icon" style="margin-right: 5px;"></i>
                                                                Delivery Failed
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
                                                const savedSearch = localStorage.getItem('dataTableSearch');
                                                const savedSort = localStorage.getItem('dataTableSort');
                                                const dataTable = new simpleDatatables.DataTable(orderTable, {
                                                    searchable: true,
                                                    perPage: 5,
                                                    perPageSelect: false,
                                                    columns: [
                                                        {select: 0, sortable: true, searchable: true},
                                                        {select: 1, sortable: true, searchable: true},
                                                        {select: 2, sortable: false, searchable: false},
                                                        {select: 3, sortable: true, searchable: false},
                                                        {select: 4, sortable: true, searchable: true},
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

    </body>
</html>

