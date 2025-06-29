<%-- 
    Document   : salesstaff-home
    Created on : Jun 28, 2025, 3:30:06 PM
    Author     : Oreh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sales Staff Orders Review</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <style>
            .table tbody tr.highlight td {
                background-color: #007fff;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Nav Bar Placeholder</a>
                </div>
            </nav>

            <div class="row">
                <div class="col-2">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action active">Order Details</a>
                    </div>
                </div>
                <div class="col-10">
                    <h4 class="text-center mb-3">Order List</h4>
                    <div class="text-end mb-2">
                        <button type="button" class="review-action btn btn-success me-2" data-bs-toggle="modal" data-bs-target="#review-order-action" data-id="2">
                            Approve
                        </button>
                        <button type="button" class="review-action btn btn-danger me-2" data-bs-toggle="modal" data-bs-target="#review-order-action" data-id="3">
                            Reject
                        </button>
                    </div>
                    <table id="order-table" class="table table-bordered table-striped table-hover text-center">
                        <thead class="table-light">
                            <tr class="fs-7">
                                <th>Order ID</th>
                                <th>Customer</th>
                                <th>Receiver</th>
                                <th>Order Details</th>
                                <th>Status</th>
                                <th>Payment Method</th>
                                <th>Create Date</th>
                            </tr>
                        </thead>
                        <tbody id="order-body">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="modal fade" id="view-order-details" aria-labelledby="modal-title" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title" id="modal-title">Order Details</h2>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="table table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th>Title</th>
                                        <th>Quantity</th>
                                        <th>Thumbnail</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody id="order-details-body">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="view-order-receiver" aria-labelledby="modal-title" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title" id="modal-title">Receiver Details</h2>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table>
                                <thead>
                                </thead>
                                <tbody id="order-receiver-body">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="review-order-action" aria-labelledby="modal-title" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title" id="modal-title">Confirm Action</h2>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid border-bottom">
                            <div class="row"><input type="text" class="form-control"></div>
                            <div class="row">
                                <div class="col-xs-8"><h6>Are you sure you want to submit?</h6></div>
                                <div class="col text-end">
                                    <button type="button" id="update-order-status" class="btn btn-success me-2">
                                        Yes
                                    </button>
                                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal" aria-label="close">
                                        No
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            const selectedOrders = [];
            let selectedAction = 0;

            $('#order-table').on('click', 'tbody tr', function (event) {
                if ($(event.target).closest('button').length > 0) {
                    return;
                }

                $(this).toggleClass('highlight');
                let orderId = $(this).find('td:first').text().trim();
                let index = selectedOrders.indexOf(orderId);
                if (index === -1) {
                    selectedOrders.push(orderId);
                } else {
                    selectedOrders.splice(index, 1);
                }
                console.log(selectedOrders);
            });

            $('.review-action').on('click', function () {
                selectedAction = $(this).data('id');
            });

            $('#review-order-action .btn-success').on('click', function () {
                let note = $('#review-order-action input').val().trim();
                sendOrderAction(selectedAction, note);
                $('#review-order-action').modal('hide');
            });

            function sendOrderAction(action, note) {
                if (selectedOrders.length === 0) {
                    alert("No orders selected.");
                } else {
                    console.log(action);
                    console.log(selectedOrders);
                    console.log(note);
                    fetch('orders/api/v1', {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            action: action,
                            orderIds: selectedOrders,
                            note: note
                        })
                    }).then(response => {
                        console.log("Raw response:", response);
                        if (!response.ok) {
                            throw new Error(`HTTP error: ${response.status}`);
                        }
                        return response.text(); // or response.json() if your server returns JSON
                    }).then(data => {
                        console.log("Parsed response data:", data);
                        alert(`${action} request sent successfully`);
                        window.location.replace(window.location.href); // move this here
                    }).catch(error => {
                        console.error("Error in fetch operation:", error);
                        alert("Failed to send request. See console for details.");
                    });
                }
            }
        </script>
        <script src='js/salesstaff.order.js'></script>
        <script src='js/salesstaff.order-detail.js'></script>
        <script src='js/salesstaff.order-receiver.js'></script>
    </body> 
</html>
