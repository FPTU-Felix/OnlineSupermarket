<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container">
            <div class="row justify-content-center align-items-center min-vh-100">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="card shadow rounded-3">
                        <div class="card-body p-4">
                            <h3 class="card-title text-center mb-4">Login</h3>

                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">${errorMessage}</div>
                            </c:if>

                            <form action="${pageContext.request.contextPath}/login" method="post">

                                <!-- User type toggle -->
                                <div class="d-flex justify-content-center mb-3">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" 
                                               name="userType" id="customerRadio" value="customer" checked>
                                        <label class="form-check-label" for="customerRadio">Customer</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" 
                                               name="userType" id="employeeRadio" value="employee">
                                        <label class="form-check-label" for="employeeRadio">Employee</label>
                                    </div>
                                </div>

                                <div class="form-floating mb-3">
                                    <input type="email" class="form-control" id="email" name="email"
                                           placeholder="name@example.com" required>
                                    <label for="email">Email address</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" id="password" name="password"
                                           placeholder="Password" required>
                                    <label for="password">Password</label>
                                </div>

                                <button type="submit" class="btn btn-primary w-100">Login</button>
                            </form>
                        </div>

                        <div class="card-footer text-center py-3 bg-white">
                            <small>
                                Don’t have an account?
                                <a href="${pageContext.request.contextPath}/register" 
                                   class="text-primary text-decoration-none">Sign up</a>
                            </small>
                        </div>

                        <div class="text-center mb-3">
                            <a href="${pageContext.request.contextPath}/Home.jsp" 
                               class="text-primary text-decoration-none">Back To Home</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
