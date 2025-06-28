/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CartDAO;
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
public class ReceiverController extends HttpServlet {

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
            out.println("<title>Servlet ReceiverController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReceiverController at " + request.getContextPath() + "</h1>");
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
        CartDAO d = new CartDAO();
        if (cus == null) {
            response.sendRedirect("login");
        } else {
            String receiverID = request.getParameter("receiverID");
            if (receiverID != null && !receiverID.isEmpty()) {
                Receiver rec = d.getReceiverByID(receiverID);
                request.setAttribute("rec", rec);
            }
            int cusid = cus.getCustomerId();

            List<Receiver> list = d.getAllReceiver(cusid);

            Receiver currentreceiver = d.getCurrentReceiver(cusid);
            request.setAttribute("now", currentreceiver);
            request.setAttribute("listOfReceiver", list);
            request.getRequestDispatcher("Receiver.jsp").forward(request, response);
        }
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
        HttpSession session = request.getSession();
        Customer cus = (Customer) session.getAttribute("customer");
        CartDAO d = new CartDAO();

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            String receiverid = request.getParameter("receiverid");
            d.deleteReceiverAddress(receiverid);
            response.sendRedirect("receiver");

        } else if (action.equalsIgnoreCase("add")) {
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");

            Receiver rec = d.exitsReceiverAddress(cus.getCustomerId(), name, email, mobile, gender, address);
            if (rec == null) {
                d.changeCurrentReceiver(cus.getCustomerId());
                d.addReceiverAddress(cus.getCustomerId(), name, email, mobile, gender, address);
                response.sendRedirect("cartcontact");
            } else {
                d.changeCurrentReceiver(cus.getCustomerId());
                d.changeHistoryReceiver(rec.getReceiverID());
                response.sendRedirect("cartcontact");
            }
        } else if (action.equalsIgnoreCase("change")) {
            String recid = request.getParameter("recid");
            int receiverid = Integer.parseInt(recid);

            d.changeCurrentReceiver(cus.getCustomerId());
            d.changeHistoryReceiver(receiverid);
            response.sendRedirect("cartcontact");
        } else if (action.equalsIgnoreCase("update")) {
            String recID = request.getParameter("recID");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");

            Receiver rectemp = d.exitsReceiverAddress(cus.getCustomerId(), name, email, mobile, gender, address);
            if (rectemp == null) {
                d.updateReceiverAddress(recID, name, email, mobile, gender, address);
                response.sendRedirect("receiver");
            } else {
                d.changeCurrentReceiver(cus.getCustomerId());
                d.changeHistoryReceiver(rectemp.getReceiverID());
                response.sendRedirect("cartcontact");
            }          
        }
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
