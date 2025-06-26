<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container">
            <div class="row justify-content-center align-items-center min-vh-100">
                <div class="col-12 col-md-6 col-lg-5">
                    <div class="card shadow rounded-3">
                        <div class="card-body p-4">
                            <h3 class="card-title text-center mb-4">Sign Up</h3>

                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">${errorMessage}</div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/register" method="post">

                                <div class="form-floating mb-3">
                                    <input type="text"
                                           class="form-control"
                                           id="fullName"
                                           name="fullName"
                                           placeholder="Full Name"
                                           required>
                                    <label for="fullName">Full Name</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input type="email"
                                           class="form-control"
                                           id="email"
                                           name="email"
                                           placeholder="name@example.com"
                                           required>
                                    <label for="email">Email address</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input type="password"
                                           class="form-control"
                                           id="password"
                                           name="password"
                                           placeholder="Password"
                                           required>
                                    <label for="password">Password</label>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label d-block">Gender</label>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="genderMale" value="Male" required>
                                        <label class="form-check-label" for="genderMale">Male</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="Female">
                                        <label class="form-check-label" for="genderFemale">Female</label>
                                    </div>
                                </div>

                                <div class="form-floating mb-3">
                                    <input type="tel"
                                           class="form-control"
                                           id="phoneNumber"
                                           name="phoneNumber"
                                           placeholder="Phone Number"
                                           required>
                                    <label for="phoneNumber">Phone Number</label>
                                </div>

                                <div class="form-floating mb-4">
                                    <textarea class="form-control"
                                              placeholder="Your address"
                                              id="address"
                                              name="address"
                                              style="height:100px"
                                              required></textarea>
                                    <label for="address">Address</label>
                                </div>

                                <button type="submit" class="btn btn-success w-100">
                                    Register
                                </button>
                            </form>
                        </div>
                        <div class="card-footer text-center py-3 bg-white">
                            <small>
                                Already have an account?
                                <a href="${pageContext.request.contextPath}/login" class="text-primary text-decoration-none">
                                    Login here
                                </a>
                            </small>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap 5 JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
