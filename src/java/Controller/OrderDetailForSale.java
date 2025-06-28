/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.SaleDAO;
import Model.Attribute;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.OrderDetailEmp;
import Model.ProductQty;
import Model.SaleAndOrder;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author sontu
 */
public class OrderDetailForSale extends HttpServlet {

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
            out.println("<title>Servlet OrderDetailForSale</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetailForSale at " + request.getContextPath() + "</h1>");
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
        String orderid = request.getParameter("orderid");
        SaleDAO d = new SaleDAO();
        List<OrderDetailEmp> listitems = d.getOrderDetailForSale(orderid);
        List<Attribute> liststatus = d.getAllStatus();
        Order order = d.getOrderByID(orderid);
        List<SaleAndOrder> listsale = d.getAllSale();
        Customer cus = d.getCustomerCreateOrder(order.getCustomerD());
        double totalcost = TotalCost(listitems);
        String PaymentStatus = d.getPaymentStauts(orderid);

        request.setAttribute("paymentstatus", PaymentStatus);
        request.setAttribute("totalcost", totalcost);
        request.setAttribute("cus", cus);
        request.setAttribute("listsale", listsale);
        request.setAttribute("order", order);
        request.setAttribute("liststatus", liststatus);
        request.setAttribute("listitems", listitems);
        request.getRequestDispatcher("OrderDetailForSale.jsp").forward(request, response);
    }

    private double TotalCost(List<OrderDetailEmp> listItems) {
        double totalCost = 0.0;

        for (OrderDetailEmp item : listItems) {
            totalCost += item.getPrice() * item.getQuantity();
        }

        return totalCost;
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
        String orderid = request.getParameter("orderid");
        String statusid = request.getParameter("statusid");
        String saleid = request.getParameter("saleid");
        String salenotes = request.getParameter("salenotes");
        SaleDAO d = new SaleDAO();
        Order or = d.getOrderByID(orderid);
        List<OrderDetail> listorder = d.getOrderDetailByID(orderid);
        Customer cus = d.getCustomer(or.getCustomerD());
        if ((saleid != null && !saleid.isEmpty()) || (salenotes != null && !salenotes.isEmpty())) {
            d.updateSaleAndNotes(saleid, salenotes, orderid);
        }
        if (statusid != null && !statusid.isEmpty()) {
            if (3 == Integer.parseInt(statusid) && or.getStatusID() == 1) {
                List<ProductQty> listqty = d.getAllProductQuantity();
                d.updateHoldProduct(listorder, listqty);

            } else if (3 == Integer.parseInt(statusid) && or.getStatusID() == 8) {
                List<ProductQty> listqty = d.getAllProductQuantity();
                d.updateQuantityProduct(listorder, listqty);

            } else if (9 == Integer.parseInt(statusid) && or.getStatusID() == 7) {
                d.updatePaymentStatus(orderid);
            }
            if (or.getPaymentMethod().equals("VNPAY")) {
                String PaymentStatus = d.getPaymentStauts(orderid);
                if (2 == Integer.parseInt(statusid) && PaymentStatus.equals("Unpaid")) {
                    d.updatePaymentStatus(orderid);
                }
            }
            d.updateOrderStatus(orderid, statusid);
        }
        response.sendRedirect("orderdetailforsale?orderid=" + orderid);
    }

    private double calculateTotal(List<OrderDetail> listorder) {
        double total = 0;
        if (listorder != null) {
            for (OrderDetail or : listorder) {
                total += or.getPrice() * or.getQuantity();
            }
        }
        return total;
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
