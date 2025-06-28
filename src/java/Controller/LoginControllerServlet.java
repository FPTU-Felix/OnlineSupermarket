package Controller;

import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import Model.Customer;
import Model.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginControllerServlet", urlPatterns = {"/login"})
public class LoginControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userType = request.getParameter("userType");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String redirectPath;

        if ("employee".equals(userType)) {
            Employee emp = EmployeeDAO.INSTANCE.login(email, password);
            if (emp != null) {
                session.setAttribute("employee", emp);
                int roleId = emp.getRoleId();
                if (roleId == 1) {
                    redirectPath = request.getContextPath() + "/admin-home.jsp";
                } else if (roleId == 2) {
                    redirectPath = request.getContextPath() + "/salestaff_dashbroad";
                } else if (roleId == 3) {
                    redirectPath = request.getContextPath() + "/salestaff_dashbroad";
                } else {
                    redirectPath = request.getContextPath() + "/warehouse_dashbroad";
                }
                response.sendRedirect(redirectPath);
                return;
            } else {
                request.setAttribute("errorMessage",
                        "Invalid credentials or employee account not active.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                return;
            }
        } else {
            Customer cust = CustomerDAO.INSTANCE.login(email, password);
            if (cust != null) {
                session.setAttribute("customer", cust);
                redirectPath = request.getContextPath() + "/home";
            } else {
                request.setAttribute("errorMessage",
                        "Invalid credentials or customer account not active.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
                return;
            }
        }
        response.sendRedirect(redirectPath);
    }

    @Override
    public String getServletInfo() {
        return "Handles login for Customer or Employee based on selection.";
    }
}
