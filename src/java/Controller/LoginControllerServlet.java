package Controller;

import DAO.CustomerDAO;
import Model.Customer;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = CustomerDAO.INSTANCE.login(email, password);

        if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect(request.getContextPath() + "/Home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid email, password, or account not active.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles customer login";
    }
}
