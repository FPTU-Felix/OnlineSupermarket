/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
import Model.CartIteam;
import Model.CartItemMore;
import Model.Customer;
import Model.ProductQty;
import Model.Receiver;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author sontu
 */
public class CartContactController extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartContactController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartContactController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("customer");
        if (cus == null) {
            response.sendRedirect("login");
        } else {
            int cusid = cus.getCustomerId();
            CartDAO d = new CartDAO();
            Receiver currentreceiver = d.getCurrentReceiver(cusid);
            int cartid = d.getCartId(cusid);
            int TotalItems = d.getTotalItem(cartid);
            if (TotalItems == 0) {
                response.sendRedirect("cart");
                return;
            }

            List<CartItemMore> listitems = d.getCartItems(cartid);

            double totalraw = calculateTotal(listitems);
            int totalprice = (int) totalraw;

            request.setAttribute("totalprice", totalprice);
            request.setAttribute("listitems", listitems);
            request.setAttribute("totalitems", TotalItems);
            request.setAttribute("now", currentreceiver);
            request.getRequestDispatcher("CartContact.jsp").forward(request, response);
        }
    }

    private double calculateTotal(List<CartItemMore> cartItems) {
        double total = 0;
        if (cartItems != null) {
            for (CartIteam item : cartItems) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        return total;
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
//        HttpSession session = request.getSession();
//        Customer cus = (Customer) session.getAttribute("customer");
//        int cusid = cus.getCustomerId();
//        CartDAO d = new CartDAO();
//
//        int cartid = d.getCartId(cusid);
//        List<CartItemMore> listitems = d.getCartItems(cartid);
//
//        boolean isStockAvailable = true;
//        boolean notchangeprice = true;
//
//        for (CartIteam c : listitems) {
//            int stock = d.getStock(c.getProductID());
//            int hold = d.getHold(c.getProductID());
//            if (c.getQuantity() + hold > stock) {
//                isStockAvailable = false;
//                break;
//            }
//        }
//
//        double epsilon = 0.01;
//        for (CartIteam c : listitems) {
//            double cartprice = d.getFinalPrice(c.getProductID());
//            if (Math.abs(cartprice - c.getPrice()) > epsilon) {
//                notchangeprice = false;
//                break;
//            }
//        }
//
//        if (isStockAvailable && notchangeprice) {
//            String bankCode = request.getParameter("bankCode");
//            if (bankCode.equalsIgnoreCase("COD")) {
//                CODOrder(request, response);
//            } else {
//                ONLOrder(request, response);
//            }
//        } else {
//            com.google.gson.JsonObject job = new JsonObject();
//            job.addProperty("code", "01");
//            job.addProperty("message", "success");
//            job.addProperty("data", "http://localhost:9999/SWP_Group4/carterror");
//            Gson gson = new Gson();
//            response.getWriter().write(gson.toJson(job));
//        }

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
