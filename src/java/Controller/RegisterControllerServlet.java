package Controller;

import DAO.CustomerDAO;
import Model.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="RegisterControllerServlet", urlPatterns={"/register"})
public class RegisterControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName    = request.getParameter("fullName");
        String email       = request.getParameter("email");
        String password    = request.getParameter("password");
        String gender      = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String address     = request.getParameter("address");

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setGender(gender);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);

        boolean registered = CustomerDAO.INSTANCE.register(customer);

        if (registered) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("errorMessage", "Registration failed: email already exists or invalid input.");
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles customer registration";
    }
}
