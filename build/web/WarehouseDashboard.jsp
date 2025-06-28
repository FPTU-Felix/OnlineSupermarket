<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - WareHouse</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body class="sb-nav-fixed">
        <div id="layoutSidenav">
            <%@ include file="WarehouseComponents.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <!-- Order Statistics -->
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
                        <!-- Revenue Statistics -->
                        <div class="row">

                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Revenue by Category
                                    </div>

                                    <div class="card-body">
                                         Safely handle the revenue attribute, providing a default value of 0 if it's null 
                                        <h5 style="color: green;">Total Revenue: $<c:out value="${totalRevenue}"/></h5>

                                        <canvas id="categoryRevenueChart" width="50 %" height="42"></canvas> <!-- Canvas for the chart -->
                                    </div>
                                </div>
                            </div>

                            <!-- Customer Statistics -->
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-users me-1"></i>
                                        Customers
                                    </div>
                                    <div class="card-body">
                                        <p>Newly Registered: ${newCustomers}</p>
                                        <p>Newly Bought: ${newlyBoughtCustomers}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Feedback Statistics -->
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="row">
                                    <div class="col-xl-12">
                                        <div class="card mb-4">
                                            <div class="card-header">
                                                <i class="fas fa-star me-1"></i>
                                                Feedback Statistics
                                            </div>
                                            <div class="card-body">
                                                <canvas id="feedbackChart"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Order Trend -->
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-line me-1"></i>
                                        Order Trend (Last 7 Days)
                                    </div>
                                    <div class="card-body">
                                        <!-- Date range inputs -->
                                        <div class="row mb-3">
                                            <div class="col-md-6">
                                                <label for="startDate" class="form-label">Start Date</label>
                                                <input type="date" id="startDate" class="form-control">
                                            </div>
                                            <div class="col-md-6">
                                                <label for="endDate" class="form-label">End Date</label>
                                                <input type="date" id="endDate" class="form-control">
                                            </div>
                                        </div>
                                        <canvas id="orderTrendChart" width="100%" height="40"></canvas>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </main>               
            </div>
        </div>                                   
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
