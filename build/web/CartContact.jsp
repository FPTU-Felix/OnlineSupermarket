<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Cart Contact</title>
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
        <link href="/SWP_Group4/assets/jumbotron-narrow.css" rel="stylesheet">      
        <script src="/SWP_Group4/assets/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <%@include file="Header.jsp" %>

        <!--================Cart Contact Area =================-->
        <section class="cart_contact_area section-margin">
            <div>
                <h2 style="font-size: 18px; margin-left: 20%; margin-top: 20px; margin-bottom: 40px; font-weight: bold; color: #0A68FF;">
                    <a href="home" style="color: #0A68FF; text-decoration: none; position: relative;">
                        Online Supermarket
                    </a> /
                    <a href="cartcontact" style="color: #0A68FF; text-decoration: none; position: relative;">
                        Cart Contact
                    </a> 
                </h2>
                <form id="frmCreateOrder" action="cartcompletion" method="post" onsubmit="setNotes()">
                    <input type="hidden" name="name" value="">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <div class="col-md-2" style="margin-right: 20px;">
                            <div class="row">
                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 20px; padding: 10px;"></div>
                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px;"></div>
                            </div>
                        </div>
                        <div class="col-lg-5" style="margin-right: 20px;">
                            <div class="row">
                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 20px; padding: 10px;">
                                    <div style="border-radius: 5px; border: 1px ridge #0A68FF; background-color: #FFFFFF; padding: 20px;">
                                        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                                            <h4 style="font-size: 24px; font-weight: bold; color: #00AB55;">Product List</h4>
                                        </div>
                                        <hr style="border: 1px solid rgba(0, 171, 85, 0.3); margin: 10px 0;">
                                        <c:forEach var="p" items="${listitems}">
                                            <div style="display: flex; align-items: center; padding: 10px 0; border-bottom: 1px solid rgba(255, 182, 193, 0.6);">
                                                <img src="${p.thumbnail}" style="height: 60px; border-radius: 2px; border: 2px solid rgba(255, 182, 193, 0.6); box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);"> 
                                                <div style="margin-left: 15px; flex-grow: 1;"> 
                                                    <div style="font-weight: bold; color: #333;">${p.title}</div> 
                                                    <div style="color: #666;">ID: ${p.productID}</div>
                                                </div>
                                                <div style="text-align: right; margin-left: 20px;">
                                                    <div style="margin-bottom: 5px; color: #333;">Qty: x${p.quantity}</div> 
                                                    <div style="font-weight: bold; color: #00AB55;">${p.price * p.quantity}$</div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="row">
                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 10px; padding: 10px;">
                                    <div class="d-flex justify-content-between">
                                        <span style="color: #000000;">Deliver To:</span>
                                        <a href="receiver" style="color: #0A68FF;">Change</a>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <span>${now.receiverName} <a style="margin: 0 2px; font-size: 20px; color: #0A68FF;">|</a> ${now.mobile}</span>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <span>${now.address}</span>
                                    </div>
                                </div>

                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px; margin-bottom: 20px;"></div>    

                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; padding: 10px; margin-bottom: 20px;">
                                    <label for="notes" style="display: block; color: #000000; margin-bottom: 5px;">Notes:</label>
                                    <textarea id="notes" rows="4" style="width: 100%; border-radius: 4px; padding: 5px;" placeholder="Enter your order notes here...."></textarea>
                                    <input type="hidden" name="notesHidden" id="notesHidden" />
                                </div>

                                <div class="col-12" style="background-color: #FAFAFA; border-radius: 2px; margin-bottom: 10px; padding: 10px;">
                                    <div class="d-flex justify-content-between">
                                        <span style="color: black;">Order:<br>${totalitems} Product</span>
                                        <a href="cart" style=" color: #0A68FF;">Change</a>
                                    </div>
                                    <div style="height: 0.5px; background-color: gray; margin: 10px 0;"></div>
                                    <div class="d-flex justify-content-between" style="border-radius: 2px;">
                                        <span style="color: #000000;">Subtotal:  <br> 
                                            Shipping Fee: <br>
                                            Discount From Voucher:</span>
                                        <span style="margin-right:10px; color: #000000;">${totalprice} VND<br> 
                                            0$<br>
                                            0$</span>
                                    </div>
                                    <div style="height: 0.5px; background-color: gray; margin: 10px 0;"></div>
                                    <div class="d-flex justify-content-between" style="border-radius: 2px;">
                                        <span style="color: black;">Total Cost:</span>
                                        <span style="margin-right:10px;"><h4>${totalprice}$</h4></span>                                    
                                    </div>                                

                                    <div class="col-12 d-flex justify-content-center" style="text-align: center; margin-bottom: 20px;">
                                        <c:choose>
                                            <c:when test="${now == null}">
                                                <button type="button"
                                                        onclick="window.location.href = 'receiver';"
                                                        style="background-color: #00B6F0; color: #FFFFFF; border: none; border-radius: 5px; padding: 10px; width: 350px;">
                                                    Add Receiver Address
                                                </button>                                           
                                            </c:when>
                                            <c:otherwise>                                          
                                                <button type="submit"
                                                        style="background-color: #FF424E; color: #FFFFFF; border: none; border-radius: 5px; padding: 10px; width: 350px;">
                                                    Submit (${totalitems})
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>

        <%@include file="Footer.jsp" %>

        <script>
            function setNotes() {
                var notesValue = document.getElementById('notes').value;
                document.getElementById('notesHidden').value = notesValue;
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
