<%-- 
    Document   : Receiver
    Created on : 9 Oct 2024, 10:05:33
    Author     : Anh Tuan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Five Castles</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Login</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">
        <link rel="stylesheet" href="vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" href="vendors/nice-select/nice-select.css">
        <link rel="stylesheet" href="vendors/nouislider/nouislider.min.css">
        <link rel="stylesheet" href="css/style.css">

        <style>
            .container {
                display: flex;
                justify-content: center; /* Căn giữa theo chiều ngang */
                margin-top: 25px; /* Khoảng cách từ đầu trang */
            }
            .form-container {
                background-color: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
                border: 2px solid #00B6F0;
                display: none;
                width: 100%;
                max-width: 800px;
                margin-top: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            td {
                padding: 10px;
            }

            label {
                font-weight: bold;
            }

            input[type="text"],
            input[type="email"],
            input[type="tel"],
            select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            button {
                background-color: #00B6F0; /* Màu nền của nút */
                color: white; /* Màu chữ của nút */
                border: none; /* Không có đường viền */
                padding: 2px 10px; /* Khoảng cách bên trong nút */
                border-radius: 4px; /* Bo tròn góc của nút */
                cursor: pointer; /* Con trỏ khi di chuột vào nút */
                transition: background-color 0.3s ease; /* Hiệu ứng chuyển màu */
            }
            button:hover {
                background-color: #008CBA; /* Màu nền khi hover */
            }
            .modal {
                display: none; /* Ẩn modal mặc định */
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.2); /* Màu nền mờ */
            }

            .modal-content {
                margin: 15% auto; /* Căn giữa modal */
                padding: 0;
                border: 1px solid #888;
                width: 50%; /* Chiều rộng modal */
                max-width: 500px; /* Chiều rộng tối đa */
            }
            .popup-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.1);
                z-index: 10000;
                pointer-events: all;
            }
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                z-index: 10001;
                width: 1000px;
                height: 500px;
                border: 2px solid #384AEB;
            }
        </style>
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->
        <!--================Cart Contact Area =================-->
        <section class="cart_contact_area section-margin">
            <div>
                <h2 style="font-size: 18px; margin-left: 20%; margin-top: 20px; margin-bottom: 40px; font-weight: bold; color: #0A68FF;">
                    <a href="home" style="color: #0A68FF; text-decoration: none; position: relative;">
                        Aroma
                        <span style="position: absolute; left: 0; bottom: -3px; width: 100%; height: 2px; background-color: #0A68FF; opacity: 0; transition: opacity 0.3s ease;"></span>
                    </a> /
                    <a href="cartcontact" style="color: #0A68FF; text-decoration: none; position: relative;">
                        Cart Contact
                        <span style="position: absolute; left: 0; bottom: -3px; width: 100%; height: 2px; background-color: #0A68FF; opacity: 0; transition: opacity 0.3s ease;"></span>
                    </a> /
                    <span style="color: #0A68FF; text-decoration: none; position: relative;">
                        Receiver
                        <span style="position: absolute; left: 0; bottom: -3px; width: 100%; height: 2px; background-color: #0A68FF; opacity: 0; transition: opacity 0.3s ease;"></span>
                    </span>
                </h2>
                <div class="row" style="background-color: #F7F7F7;">
                    <div class="col-md-1"></div>
                    <div class="col-lg-10">                       
                        <div class="row">
                            <a style="color: #000; width: 100%; font-size: 14px; margin: 0px 10% 20px 10%; display: block;">
                                1. Select a shipping address from the options below:
                            </a>
                        </div>
                        <div class="row">   
                            <c:if test="${listOfReceiver == null || listOfReceiver.isEmpty()}">
                                <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 50vh; width: 100%; text-align: center;">
                                    <i class="fas fa-map-marker-alt" style="font-size: 50px; color: #007BFF;"></i>
                                    <h2 style="color: #0A68FF; margin-top: 20px;">No address available</h2>
                                    <p style="font-size: 16px; color: black;">Please add a new address</p>
                                </div>
                            </c:if>
                            <c:forEach var="c" items="${listOfReceiver}">
                                <div class="col-md-5" style="border-radius: 5px; background-color: #FFFF; padding: 10px; margin: 10px 10px 10px 5%; border: ${c.receiverType == 'Current' ? '2px dashed #69C059' : '1px ridge #007BFF'}; overflow: hidden; word-wrap: break-word;">
                                    <strong style="font-size: 15px; color: black;">${c.receiverName}</strong>
                                    <c:if test="${c.receiverType == 'Current'}">
                                        <span style="position: absolute; font-size: 15px; top: 10px; right: 10px; color: #69C059; padding: 3px 6px; font-weight: bold;">Current</span>
                                    </c:if>
                                    <br>
                                    <a>Gender: ${c.gender}</a><br>
                                    <a>Email: ${c.email}</a><br>
                                    <a>Mobile: ${c.mobile}</a><br>
                                    <a>Address: ${c.address}</a><br>
                                    <form action="receiver" method="post">
                                        <input type="hidden" name="action" value="change">
                                        <input type="hidden" name="recid" value="${c.receiverID}">
                                        <button style="background-color: ${c.receiverType == 'Current' ? '#69C059' : ''};">Deliver to this address</button>                                
                                        <button type="button" style="background-color: ${c.receiverType == 'Current' ? '#69C059' : ''};" onclick="window.location.href = 'receiver?receiverID=${c.receiverID}';">Edit</button> 
                                        <c:if test="${c.receiverType != 'Current'}">
                                            <button type="button" onclick="openModal('${c.receiverID}')">Delete</button>
                                        </c:if>

                                    </form>
                                </div>
                            </c:forEach>
                        </div>                       
                        <p style="margin: 20px 10% 0 10%; color: #000; font-size: 14px;">
                            2. Do you want to ship to a different address? 
                            <button onclick="openForm(); scrollToForm();" style="padding: 0; color: #0A68FF; background-color: #F7F7F7; border: none; cursor: pointer;">
                                Add a new shipping address
                            </button>
                        </p>

                    </div>
                </div>
                <div class="container">
                    <div class="form-container" id="formContainer">
                        <form action="receiver" method="post">
                            <h2 style="margin-bottom: 20px;">Add Receiver Information</h2>
                            <div class="form-content">
                                <table>
                                    <tr>
                                        <td><label for="name">Full Name:</label></td>
                                        <td><input type="text" id="name" name="name" value="${sessionScope.account.fullName}" required></td>
                                    </tr>
                                    <tr>
                                        <td><label for="gender">Gender:</label></td>
                                        <td>
                                            <select id="gender" name="gender" required>
                                                <option value="" disabled="" selected="">Choose Gender</option>
                                                <option value="Male" ${("Male".equals(sessionScope.account.gender) ? "selected" : "")}>Male</option>
                                                <option value="Female" ${("Female".equals(sessionScope.account.gender) ? "selected" : "")}>Female</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <input type="hidden" name="action" value="add">
                                    <tr>
                                        <td><label for="email">Email:</label></td>
                                        <td><input type="email" id="email" name="email" value="${sessionScope.account.email}" required></td>
                                    </tr>
                                    <tr>
                                        <td><label for="mobile">Mobile:</label></td>
                                        <td><input type="tel" id="mobile" name="mobile" value="${sessionScope.account.phoneNumber}" required></td>
                                    </tr>
                                    <tr>
                                        <td><label for="address">Address:</label></td>
                                        <td><input type="text" id="address" name="address" value="${sessionScope.account.address}" required></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" style="text-align: right;">
                                            <button type="submit">Deliver to this address</button>
                                            <button type="button" onclick="cancelForm()">Cancel</button>                                        
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form>
                    </div>
                </div>               
            </div>
        </section>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #1BA8FF; color: #FFFFFF; padding: 10px; border-radius: 5px; display: flex; justify-content: space-between; align-items: center;">
                    <a style="margin: 0;">Delete Receiver Address</a>
                    <span class="nhom" onclick="closeModal()"  style=" font-size: 25px; margin-right: 10px; cursor: pointer;">&times;</span>
                </div>
                <div class="modal-body" style="padding: 10px; background-color: white; border-radius: 5px;">
                    <p style="color: #000; margin-bottom: 25px;">Do you want to delete this receiver address?</p>
                    <div class="col-md-12 form-group" style="display: flex; justify-content: space-between;">
                        <button id="cancelBtn" onclick="closeModal()" style="border: 2px solid #1BA8FF; background-color: #FFFFFF; color: #1BA8FF; padding: 10px 20px; border-radius: 2px; cursor: pointer; font-size: 16px; width: 200px; transition: background-color 0.3s;">No</button>
                        <form action="receiver" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="receiverid" id="receiverId" value="0">
                            <button id="confirmBtn" style="border: 1px solid #FF424E; background-color: #FF424E; color: #FFFFFF; padding: 10px 20px; border-radius: 2px; cursor: pointer; font-size: 16px; width: 200px; transition: background-color 0.3s;">Yes</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="popup-overlay" id="popupOverlay"></div>
        <div class="form-container popup" id="formUpdate">
            <form action="receiver" method="post">
                <h2 style="margin-bottom: 20px;">Edit Receiver Information</h2>
                <div class="form-content">
                    <table>
                        <tr>
                            <td><label for="name">Full Name:</label></td>
                            <td><input type="text" id="name" name="name" value="${rec.receiverName}" required></td>
                        </tr>
                        <tr>
                            <td><label for="gender">Gender:</label></td>
                            <td>
                                <select id="gender" name="gender" required>
                                    <option value="" disabled="" selected="">Choose Gender</option>
                                    <option value="Male" ${"Male".equals(rec.gender) ? "selected" : ""}>Male</option>
                                    <option value="Female" ${"Female".equals(rec.gender) ? "selected" : ""} >Female</option>                                           
                                </select>
                            </td>
                        </tr>
                        <input type="hidden" name="recID" value="${rec.receiverID}">
                        <input type="hidden" name="action" value="update">
                        <tr>
                            <td><label for="email">Email:</label></td>
                            <td><input type="email" id="email" name="email" value="${rec.email}" required></td>
                        </tr>
                        <tr>
                            <td><label for="mobile">Mobile:</label></td>
                            <td><input type="tel" id="mobile" name="mobile" value="${rec.mobile}" required></td>
                        </tr>
                        <tr>
                            <td><label for="address">Address:</label></td>
                            <td><input type="text" id="address" name="address" value="${rec.address}" required></td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: right;">
                                <button type="submit" >Save Changes</button>
                                <button type="button" onclick="closePopup()">Cancel</button>                                        
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->
        <c:if test="${not empty rec}">
            <script>
                document.getElementById("popupOverlay").style.display = "block";
                document.getElementById("formUpdate").style.display = "block";
            </script>
        </c:if>
        <script>
            function openForm() {
                const formContainer = document.getElementById('formContainer');
                const displayStyle = window.getComputedStyle(formContainer).display;

                if (displayStyle === 'none') {
                    formContainer.style.display = 'block'; // Hiển thị form
                    scrollToForm(); // Cuộn đến form khi mở
                }
            }

            function cancelForm() {
                const formContainer = document.getElementById("formContainer");
                formContainer.style.display = "none"; // Ẩn form

                // Cuộn lên đầu trang
                window.scrollTo({
                    top: 50, // Cuộn lên đầu trang
                    behavior: 'smooth' // Hành vi cuộn mượt
                });
            }

            function scrollToForm() {
                const formContainer = document.getElementById('formContainer');
                const windowHeight = window.innerHeight; // Chiều cao của cửa sổ trình duyệt

                // Kiểm tra nếu formContainer đang hiển thị
                if (formContainer.style.display === 'block') {
                    // Tính chiều cao tổng thể của trang
                    const documentHeight = document.documentElement.scrollHeight;

                    // Tính toán vị trí cuộn dựa trên chiều cao tổng thể của trang
                    const fixedScrollPositionFromBottom = 950; // Vị trí cố định từ dưới lên
                    const targetPosition = documentHeight - windowHeight - fixedScrollPositionFromBottom;

                    // Cuộn đến vị trí đã tính toán
                    window.scrollTo({
                        top: targetPosition,
                        behavior: 'smooth'
                    });
                }
            }


            function openModal(receiverId) {
                const modal = document.getElementById("myModal");
                const receiverField = document.getElementById("receiverId");

                if (modal && receiverField) {
                    receiverField.value = receiverId; // Đặt giá trị cho trường receiver
                    modal.style.display = "block"; // Hiển thị modal
                }
            }

            function closeModal() {
                const modal = document.getElementById("myModal");

                if (modal) {
                    modal.style.display = "none"; // Ẩn modal
                }
            }

            function closePopup() {
                document.getElementById("popupOverlay").style.display = "none";
                document.getElementById("formUpdate").style.display = "none";
            }

        </script>
        <script src="vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="vendors/skrollr.min.js"></script>
        <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="vendors/nice-select/jquery.nice-select.min.js"></script>
        <script src="vendors/jquery.ajaxchimp.min.js"></script>
        <script src="vendors/mail-script.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
