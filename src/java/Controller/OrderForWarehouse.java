/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.WarehouseDAO;
import Model.Attribute;
import Model.Employee;
import Model.OrderDetail;
import Model.OrderInfo;
import Model.OrderType;
import Model.ProductQty;
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
public class OrderForWarehouse extends HttpServlet {

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
            out.println("<title>Servlet OrderForWarehouse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderForWarehouse at " + request.getContextPath() + "</h1>");
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
        String datefrom = request.getParameter("datefrom");
        String dateto = request.getParameter("dateto");
        String cusnameraw = request.getParameter("cusname");
        String statusfid = request.getParameter("statusfid");
        String status = request.getParameter("status");

        String cusname = "";
        if (cusnameraw != null && !cusnameraw.isEmpty()) {
            cusname = cusnameraw.trim();
        }
        HttpSession session = request.getSession();
        Employee emp = (Employee) session.getAttribute("employee");
        if (emp == null) {
            response.sendRedirect("login");
            return;
        }
        WarehouseDAO d = new WarehouseDAO();
        OrderType ordertype = d.getOrderForWarehouse();
        List<Attribute> liststatus = d.getAllStatus();
        List<OrderInfo> listorder = d.getOrderByWarehouse(datefrom, dateto, cusname, statusfid, status);

        request.setAttribute("datefrom", datefrom);
        request.setAttribute("dateto", dateto);
        request.setAttribute("cusname", cusname);
        request.setAttribute("status", status);
        request.setAttribute("statusfid", statusfid);
        request.setAttribute("listorder", listorder);
        request.setAttribute("liststatus", liststatus);
        request.setAttribute("ordertype", ordertype);
        request.getRequestDispatcher("OrderForWarehouse.jsp").forward(request, response);
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
        String datefrom = request.getParameter("datefrom");
        String dateto = request.getParameter("dateto");
        String cusname = request.getParameter("cusname");
        String statusfid = request.getParameter("statusfid");
        String status = request.getParameter("status");
        String orderid = request.getParameter("orderid");
        String statusid = request.getParameter("statusid");

        WarehouseDAO d = new WarehouseDAO();
        if (6 == Integer.parseInt(statusid)) {
            List<ProductQty> listqty = d.getAllProductQuantity();
            List<OrderDetail> listorder = d.getOrderDetailByID(orderid);
            d.updateQuantityProduct(listorder, listqty);
        }
        d.updateOrderStatus(orderid, statusid);

        StringBuilder url = new StringBuilder("orderforwarehouse");
        boolean hasParam = false;
        if (status != null && !status.isEmpty()) {
            url.append(hasParam ? "&" : "?").append("status=").append(status);
            hasParam = true;
        }
        if (datefrom != null && !datefrom.isEmpty()) {
            url.append(hasParam ? "&" : "?").append("datefrom=").append(datefrom);
            hasParam = true;
        }
        if (dateto != null && !dateto.isEmpty()) {
            url.append(hasParam ? "&" : "?").append("dateto=").append(dateto);
            hasParam = true;
        }
        if (cusname != null && !cusname.isEmpty()) {
            url.append(hasParam ? "&" : "?").append("cusname=").append(cusname);
            hasParam = true;
        }
        if (statusfid != null && !statusfid.isEmpty()) {
            url.append(hasParam ? "&" : "?").append("statusfid=").append(statusfid);
            hasParam = true;
        }
        response.sendRedirect(url.toString());
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
