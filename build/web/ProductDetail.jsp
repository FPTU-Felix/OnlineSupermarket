<%-- 
    Document   : ProductDetail
    Created on : Jun 27, 2025, 9:02:34 AM
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
        <title>Product Detail</title>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/themify-icons/0.1.2/css/themify-icons.css">


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
                        <h2>Shop Detail</h2>
                        <ul class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Shop</a></li>
                            <li class="breadcrumb-item active">Shop Detail </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End All Title Box -->

        <!-- Start Shop Detail  -->
        <div class="shop-detail-box-main">
            <div class="container">
                <c:choose>
                    <c:when test="${ProductDetail.quantity - ProductDetail.hold <= 0}">
                        <div class="row">
                            <div class="col-xl-5 col-lg-5 col-md-6">
                                <div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
                                    <div class="carousel-inner" role="listbox">
                                        <div class="carousel-item active"> <img class="d-block w-100" src="${ProductDetail.thumbnail}" alt="First slide"> </div>
                                        <div class="carousel-item"> <img class="d-block w-100" src="${ProductDetail.thumbnail}" alt="Second slide"> </div>
                                        <div class="carousel-item"> <img class="d-block w-100" src="${ProductDetail.thumbnail}" alt="Third slide"> </div>
                                    </div>
                                    <a class="carousel-control-prev" href="#carousel-example-1" role="button" data-slide="prev"> 
                                        <i class="fa fa-angle-left" aria-hidden="true"></i>
                                        <span class="sr-only">Previous</span> 
                                    </a>
                                    <a class="carousel-control-next" href="#carousel-example-1" role="button" data-slide="next"> 
                                        <i class="fa fa-angle-right" aria-hidden="true"></i> 
                                        <span class="sr-only">Next</span> 
                                    </a>
                                    <ol class="carousel-indicators">
                                        <li data-target="#carousel-example-1" data-slide-to="0" class="active">
                                            <img class="d-block w-100 img-fluid" src="${ProductDetail.thumbnail}" alt="anh1" />
                                        </li>
                                        <li data-target="#carousel-example-1" data-slide-to="1">
                                            <img class="d-block w-100 img-fluid" src="${ProductDetail.thumbnail}" alt="anh2" />
                                        </li>
                                        <li data-target="#carousel-example-1" data-slide-to="2">
                                            <img class="d-block w-100 img-fluid" src="${ProductDetail.thumbnail}" alt="anh3" />
                                        </li>
                                    </ol>
                                </div>
                            </div>
                            <div class="col-xl-7 col-lg-7 col-md-6">
                                <div class="single-product-details">
                                    <h2>${ProductDetail.title}</h2>
                                    <h5> ${ProductDetail.price} VND</h5>
                                    <h4>Avalible:</h4>
                                    <p class="available-stock">${ProductDetail.quantity - ProductDetail.hold}<p>
                                    <h4>Short Description:</h4>
                                    <p>${ProductDetail.description}</p>
                                    <div class="price-box-bar">
                                        <div class="cart-and-bay-btn">
                                            <p class="text-danger"><strong>This item isn't avalible at this time, come back later!</strong></p>
                                        </div>
                                    </div>

                                    <div class="add-to-btn">
                                        <div class="share-bar">
                                            <a class="btn hvr-hover" href="#"><i class="fab fa-facebook" aria-hidden="true"></i></a>
                                            <a class="btn hvr-hover" href="#"><i class="fab fa-google-plus" aria-hidden="true"></i></a>
                                            <a class="btn hvr-hover" href="#"><i class="fab fa-twitter" aria-hidden="true"></i></a>
                                            <a class="btn hvr-hover" href="#"><i class="fab fa-pinterest-p" aria-hidden="true"></i></a>
                                            <a class="btn hvr-hover" href="#"><i class="fab fa-whatsapp" aria-hidden="true"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form action="add_to_cart" method="POST">
                            <div class="row">
                                <input type="hidden" name="productID" value="${ProductDetail.id}">
                                <input type="hidden" name="title" value="${ProductDetail.title}">
                                <input type="hidden" name="image" value="${ProductDetail.thumbnail}">
                                <input type="hidden" name="hold" value="${ProductDetail.hold}">
                                <input type="hidden" name="price" value="${ProductDetail.price}">
                                <div class="col-xl-5 col-lg-5 col-md-6">
                                    <div id="carousel-example-1" class="single-product-slider carousel slide" data-ride="carousel">
                                        <div class="carousel-inner" role="listbox">
                                            <div class="carousel-item active"> <img class="d-block w-100" src="${ProductDetail.thumbnail}" alt="First slide"> </div>
                                            <div class="carousel-item"> <img class="d-block w-100" src="${ProductDetail.thumbnail}" alt="Second slide"> </div>
                                            <div class="carousel-item"> <img class="d-block w-100" src="${ProductDetail.thumbnail}" alt="Third slide"> </div>
                                        </div>
                                        <a class="carousel-control-prev" href="#carousel-example-1" role="button" data-slide="prev"> 
                                            <i class="fa fa-angle-left" aria-hidden="true"></i>
                                            <span class="sr-only">Previous</span> 
                                        </a>
                                        <a class="carousel-control-next" href="#carousel-example-1" role="button" data-slide="next"> 
                                            <i class="fa fa-angle-right" aria-hidden="true"></i> 
                                            <span class="sr-only">Next</span> 
                                        </a>
                                        <ol class="carousel-indicators">
                                            <li data-target="#carousel-example-1" data-slide-to="0" class="active">
                                                <img class="d-block w-100 img-fluid" src="${ProductDetail.thumbnail}" alt="anh1" />
                                            </li>
                                            <li data-target="#carousel-example-1" data-slide-to="1">
                                                <img class="d-block w-100 img-fluid" src="${ProductDetail.thumbnail}" alt="anh2" />
                                            </li>
                                            <li data-target="#carousel-example-1" data-slide-to="2">
                                                <img class="d-block w-100 img-fluid" src="${ProductDetail.thumbnail}" alt="anh3" />
                                            </li>
                                        </ol>
                                    </div>
                                </div>
                                <div class="col-xl-7 col-lg-7 col-md-6">
                                    <div class="single-product-details">
                                        <h2>${ProductDetail.title}</h2>
                                        <h5> ${ProductDetail.price} VND</h5>
                                        <h4>Avalible:</h4>
                                        <p class="available-stock">${ProductDetail.quantity - ProductDetail.hold}<p>
                                        <h4>Short Description:</h4>
                                        <p>${ProductDetail.description}</p>
                                        <ul>
                                            <li>
                                                <div class="form-group quantity-box">
                                                    <label for="qty">Quantity:</label>

                                                    <!-- Nút giảm số lượng -->
                                                    <button onclick="decreaseQty(${ProductDetail.quantity - ProductDetail.hold})"
                                                            class="reduced items-count" type="button">−</button>

                                                    <!-- Ô nhập số lượng -->
                                                    <input type="text" name="qty" id="sst" size="2" maxlength="12" value="1" title="Quantity:"
                                                           class="input-text qty"
                                                           onchange="validateQuantity(this, ${ProductDetail.quantity - ProductDetail.hold})">

                                                    <!-- Nút tăng số lượng -->
                                                    <button onclick="increaseQty(${ProductDetail.quantity - ProductDetail.hold})"
                                                            class="increase items-count" type="button">+</button>
                                                </div>

                                            </li>
                                        </ul>

                                        <div class="price-box-bar">
                                            <div class="cart-and-bay-btn">
                                                <button class="btn hvr-hover" data-fancybox-close="" type="submit">Add to cart</button>
                                            </div>
                                        </div>

                                        <div class="add-to-btn">
                                            <div class="share-bar">
                                                <a class="btn hvr-hover" href="#"><i class="fab fa-facebook" aria-hidden="true"></i></a>
                                                <a class="btn hvr-hover" href="#"><i class="fab fa-google-plus" aria-hidden="true"></i></a>
                                                <a class="btn hvr-hover" href="#"><i class="fab fa-twitter" aria-hidden="true"></i></a>
                                                <a class="btn hvr-hover" href="#"><i class="fab fa-pinterest-p" aria-hidden="true"></i></a>
                                                <a class="btn hvr-hover" href="#"><i class="fab fa-whatsapp" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
                <style>
                    .quantity-box {
                        display: flex;
                        align-items: center;
                        gap: 10px;
                        font-family: Arial, sans-serif;
                        margin-top: 10px;
                    }

                    .quantity-box label {
                        font-weight: bold;
                        margin-right: 10px;
                        white-space: nowrap;
                    }

                    .quantity-box .input-text.qty {
                        width: 60px;
                        height: 40px;
                        text-align: center;
                        font-size: 16px;
                        border: 1px solid #ccc;
                        border-radius: 6px;
                        outline: none;
                        transition: border-color 0.3s;
                    }

                    .quantity-box .input-text.qty:focus {
                        border-color: #007bff;
                    }

                    .quantity-box .items-count {
                        width: 40px;
                        height: 40px;
                        font-size: 22px;
                        font-weight: bold;
                        border: 1px solid #ccc;
                        background-color: #f8f9fa;
                        color: #333;
                        cursor: pointer;
                        border-radius: 6px;
                        transition: all 0.2s ease-in-out;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                    }

                    .items-count:hover {
                        background-color: #ddd;
                        border-color: #999;
                    }

                </style>
                <script>
                    function increaseQty(maxStock) {
                        var result = document.getElementById('sst');
                        var sst = parseInt(result.value, 10);
                        if (!isNaN(sst) && sst < maxStock) {
                            result.value = sst + 1;
                        } else {
                            alert('Cannot select more than available stock!');
                        }
                    }

                    function decreaseQty() {
                        var result = document.getElementById('sst');
                        var sst = parseInt(result.value, 10);
                        if (!isNaN(sst) && sst > 1) {
                            result.value = sst - 1;
                        }
                    }

                    function validateQuantity(input, maxStock) {
                        var value = parseInt(input.value, 10);
                        if (isNaN(value) || value < 1) {
                            input.value = 1;
                        } else if (value > maxStock) {
                            alert('Cannot select more than available stock! The number will be returned 1!');
                            input.value = 1;
                        }
                    }

                    // Nút chọn size (nếu có)
                    document.querySelectorAll('.size-btn').forEach(function (button) {
                        button.addEventListener('click', function () {
                            document.querySelectorAll('.size-btn').forEach(function (btn) {
                                btn.classList.remove('active');
                            });
                            button.classList.add('active');
                            document.getElementById('size-input').value = button.getAttribute('data-size');
                        });
                    });
                </script>

                <div class="row my-5">
                    <div class="card card-outline-secondary my-4">
                        <div class="card-header">
                            <h2>Product Reviews</h2>
                        </div>
                        <div class="card-body">
                            <div class="media mb-3">
                                <div class="mr-2"> 
                                    <img class="rounded-circle border p-1" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2264%22%20height%3D%2264%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%2064%2064%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_160c142c97c%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A10pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_160c142c97c%22%3E%3Crect%20width%3D%2264%22%20height%3D%2264%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2213.5546875%22%20y%3D%2236.5%22%3E64x64%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="Generic placeholder image">
                                </div>
                                <div class="media-body">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
                                    <small class="text-muted">Posted by Anonymous on 3/1/18</small>
                                </div>
                            </div>
                            <hr>
                            <div class="media mb-3">
                                <div class="mr-2"> 
                                    <img class="rounded-circle border p-1" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2264%22%20height%3D%2264%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%2064%2064%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_160c142c97c%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A10pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_160c142c97c%22%3E%3Crect%20width%3D%2264%22%20height%3D%2264%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2213.5546875%22%20y%3D%2236.5%22%3E64x64%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="Generic placeholder image">
                                </div>
                                <div class="media-body">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
                                    <small class="text-muted">Posted by Anonymous on 3/1/18</small>
                                </div>
                            </div>
                            <hr>
                            <div class="media mb-3">
                                <div class="mr-2"> 
                                    <img class="rounded-circle border p-1" src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2264%22%20height%3D%2264%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%2064%2064%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_160c142c97c%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A10pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_160c142c97c%22%3E%3Crect%20width%3D%2264%22%20height%3D%2264%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2213.5546875%22%20y%3D%2236.5%22%3E64x64%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E" alt="Generic placeholder image">
                                </div>
                                <div class="media-body">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
                                    <small class="text-muted">Posted by Anonymous on 3/1/18</small>
                                </div>
                            </div>
                            <hr>
                            <a href="#" class="btn hvr-hover">Leave a Review</a>
                        </div>
                    </div>
                </div>

                <div class="row my-5">
                    <div class="col-lg-12">
                        <div class="title-all text-center">
                            <h1>Featured Products</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet lacus enim.</p>
                        </div>
                        <div class="featured-products-box owl-carousel owl-theme">
                            <c:forEach items="${list4Products}" var="l">
                                <div class="item">
                                    <div class="products-single fix">
                                        <div class="box-img-hover">
                                            <img src="${l.thumbnail}" class="img-fluid" alt="Image">
                                            <div class="mask-icon">
                                                <ul>
                                                    <li><a href="product_detail?id=${l.id}" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="why-text">
                                            <h4>${l.title}</h4>
                                            <h5> ${l.price} VND</h5>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- End Cart -->

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
