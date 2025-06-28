<%-- 
    Document   : Cart
    Created on : Jun 26, 2025, 11:01:30 PM
    Author     : sontu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <!-- Basic -->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Site Metas -->
        <title>Cart</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/custom.css">

        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>
        <!-- Start Main Top -->
        <%@include file="Header.jsp" %>
        <!-- End Main Top -->

        <!-- Start Top Search -->
        <div class="top-search">
            <div class="container">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-search"></i></span>
                    <input type="text" class="form-control" placeholder="Search">
                    <span class="input-group-addon close-search"><i class="fa fa-times"></i></span>
                </div>
            </div>
        </div>
        <!-- End Top Search -->

        <!-- Start All Title Box -->
        <div class="all-title-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Cart</h2>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Shop</a></li>
                            <li class="breadcrumb-item active">Cart</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->

        <!-- Start Cart  -->
        <div class="cart-box-main">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="table-main table-responsive">
                            <c:if test="${empty listCartNow}">
                                <div class="empty-cart-message">
                                    <p>Your Cart is Empty now. Go Shopping!</p>
                                    <a class="gray_btn" href="allproduct">Go Shopping!</a>
                                </div>
                            </c:if>

                            <c:if test="${!empty listCartNow}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Images</th>
                                            <th>Product Name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th>Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listCartNow}" var="l">
                                            <tr>
                                                <td class="thumbnail-img">
                                                    <a href="#"><img class="img-fluid" src="${l.thumbnail}" alt="img" /></a>
                                                </td>
                                                <td class="name-pr"><a href="#">${l.title}</a></td>
                                                <td class="price-pr"><p class="price">${l.price}</p></td>
                                                <td class="form-group quantity-box">
                                                    <div class="product_count">
                                                        <button onclick="updateQuantity(this, -1);" class="reduced items-count" type="button">-</button>
                                                        <input type="text" name="qty" class="input-text qty" maxlength="12" value="${l.quantity}" title="Quantity:">
                                                        <button onclick="updateQuantity(this, 1);" class="increase items-count" type="button">+</button>
                                                    </div>
                                                </td>
                                                <td class="total-pr"><p class="total">${l.price * l.quantity}</p></td>
                                                <td class="remove-pr">
                                                    <a href="deletecart?service=deleteItem&cartItemID=${l.cartItemID}" onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?');">
                                                        <i class="fas fa-times"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="4" class="text-right"><strong>Subtotal</strong></td>
                                            <td colspan="2"><strong><span id="subtotal">0.00</span> VND</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="row my-5">
                    <div class="col-12 d-flex shopping-box">
                        <a href="cartcontact" class="ml-auto btn hvr-hover">Process to checkout</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- CSS fix click + style -->
        <style>
            .empty-cart-message {
                text-align: center;
                font-size: 36px;
                font-weight: bold;
                color: #333;
                margin-top: 50px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 80vh;
            }

            .empty-cart-message a {
                display: inline-block;
                margin-top: 20px;
                padding: 15px 30px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                font-size: 18px;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }

            .empty-cart-message a:hover {
                background-color: #0056b3;
            }

            .product_count {
                display: flex;
                align-items: center;
                gap: 5px;
            }

            .product_count input.qty {
                width: 50px;
                text-align: center;
                height: 32px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            .product_count button {
                width: 32px;
                height: 32px;
                background-color: #eee;
                border: 1px solid #ccc;
                font-size: 18px;
                font-weight: bold;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.2s ease;
            }

            .product_count button:hover {
                background-color: #ddd;
            }
        </style>

        <!-- Script tăng/giảm và tính tổng -->
        <script>
            function updateQuantity(button, change) {
                const row = button.closest('tr');
                const qtyInput = row.querySelector('.qty');
                const priceText = row.querySelector('.price').textContent.replace(/[^\d.]/g, '');
                const price = parseFloat(priceText);
                let qty = parseInt(qtyInput.value);

                if (!isNaN(qty)) {
                    qty += change;
                    if (qty < 1)
                        qty = 1;
                    qtyInput.value = qty;

                    const total = (price * qty).toFixed(2);
                    row.querySelector('.total').textContent = total;

                    calculateSubtotal();
                }
            }

            function calculateSubtotal() {
                const totals = document.querySelectorAll('.total');
                let subtotal = 0;
                totals.forEach(el => {
                    const val = parseFloat(el.textContent.replace(/[^\d.]/g, ''));
                    if (!isNaN(val))
                        subtotal += val;
                });
                document.getElementById('subtotal').textContent = subtotal.toFixed(2);
            }

            window.onload = calculateSubtotal;
        </script>


        <!-- Start Instagram Feed  -->
        <div class="instagram-box">
            <div class="main-instagram owl-carousel owl-theme">
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-01.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-02.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-03.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-04.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-05.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-06.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-07.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-08.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-09.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <div class="ins-inner-box">
                        <img src="images/instagram-img-05.jpg" alt="" />
                        <div class="hov-in">
                            <a href="#"><i class="fab fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Instagram Feed  -->


        <!-- Start Footer  -->
        <%@include file="Footer.jsp" %>
        <!-- End Footer  -->

        <!-- Start copyright  -->
        <div class="footer-copyright">
            <p class="footer-company">All Rights Reserved. &copy; 2018 <a href="#">ThewayShop</a> Design By :
                <a href="https://html.design/">html design</a></p>
        </div>
        <!-- End copyright  -->

        <a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

        <!-- ALL JS FILES -->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- ALL PLUGINS -->
        <script src="js/jquery.superslides.min.js"></script>
        <script src="js/bootstrap-select.js"></script>
        <script src="js/inewsticker.js"></script>
        <script src="js/bootsnav.js."></script>
        <script src="js/images-loded.min.js"></script>
        <script src="js/isotope.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/baguetteBox.min.js"></script>
        <script src="js/form-validator.min.js"></script>
        <script src="js/contact-form-script.js"></script>
        <script src="js/custom.js"></script>
    </body>

</html>
