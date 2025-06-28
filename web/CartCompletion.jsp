<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Five Castles Shop - Cart Completion</title>
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
    </head>
    <body>
        <!--================ Start Header Menu Area =================-->
        <%@include  file="Header.jsp" %>
        <!--================ End Header Menu Area =================-->

        <!-- ================ start banner area ================= -->	
        <section style="margin-top: 50px;">
            <div>
                <div class="row">
                    <div class="col-lg-1">
                    </div>
                    <div class="col-lg-2" style="margin-right: 20px; ">
                        <div class="row">
                            <div class="col-12" style="background-color: #F7F7F7; border-radius: 2px; margin-bottom: 20px; padding: 10px;">
                                
                            </div>
                            <div class="col-12" style="background-color: #F7F7F7; border-radius: 2px; padding: 10px;">
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">        
                        <div style="display: flex; flex-direction: column; height: 500px; border: 1px solid #3180FF; border-radius: 5px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); overflow: hidden; margin-bottom: 10%;">
                            <div style="background-color: #0A68FF; flex: 0 0 20%; color: #FFFFFF; display: flex; align-items: center; justify-content: center; border-radius: 5px;">
                                <h5 style="margin: 0; color: #FFFFFF;">Order placed successfully!</h5>
                            </div>
                            <div style="background-color: #FFFFFF; flex: 1; display: flex; flex-direction: column; border-radius: 5px; padding: 20px;">
                                <div style="display: flex; align-items: flex-start; width: 100%;">
                                    <div style="font-size: 150px; color: #0A68FF; font-weight: bold; margin-top: -10%; margin-left: 20px;">
                                        &#10003;
                                    </div>
                                    <div style="flex-grow: 1; margin-left: 20px;">
                                        <div style="flex-grow: 1; margin-left: 20px; display: flex; justify-content: space-between; align-items: center;">
                                            <h5 style="margin: 0; font-size: 16px;">Payment Method</h5>
                                            <h5 style="margin-right: 10%; font-size: 16px;">${method}</h5>
                                        </div>
                                        <hr style="width: 100%; border: 1px solid #0A68FF; margin: 5px 0;">
                                        <div style="flex-grow: 1; margin-left: 20px; display: flex; justify-content: space-between; align-items: center;">
                                            <h5 style="margin: 0; font-size: 16px;">Total</h5>
                                            <h5 style="margin-right: 10%; font-size: 16px;">${total} $</h5>
                                        </div>
                                    </div>
                                </div>
                                <button onclick="window.location.href = 'home'" style="border: 2px solid #3180FF; background-color: #FFFFFF; padding: 10px; width: 400px; margin-top: 20px; margin-left: 30%;">Back To Home</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--================End Cart Area =================-->

        <!--================ Start footer Area  =================-->	
        <%@include file="Footer.jsp" %>
        <!--================ End footer Area  =================-->
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
