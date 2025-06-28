/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import Model.CartIteam;
import Model.CartItemMore;
import Model.Customer;
import Model.Receiver;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author sontu
 */
public class CartCompletionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("customer");
            if (cus == null) {
                response.sendRedirect("login");
            } else {
                int cusid = cus.getCustomerId();
                String note = request.getParameter("notesHidden");
                // Lấy cartID
                CartDAO cartDAO = new CartDAO();
                int cartId = cartDAO.getCartId(cusid);

                // Lấy danh sách sản phẩm trong giỏ
                List<CartItemMore> cartItems = cartDAO.getCartItems(cartId);
                Receiver r = cartDAO.getReceiverInforByCustomerID(cusid);
                // Tạo đơn hàng mới
                int orderId = cartDAO.insertOrder(cusid, r.getReceiverName(), r.getGender(), r.getEmail(), r.getMobile(), r.getAddress(), note, "COD", cartDAO.getSaleID());

                // Chèn vào bảng order detail
                cartDAO.insertOrderDetail(cartItems, orderId);
                cartDAO.updateHoldProduct(cartItems, cartDAO.getAllProductQuantity());
                // Xóa giỏ hàng sau khi tạo đơn
//                cartDAO.clearCart(cartId);

                // Redirect về trang cảm ơn hoặc đơn hàng thành công
                response.sendRedirect("CartCompletion.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
