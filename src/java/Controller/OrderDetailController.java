package Controller;

import DAO.DaoOrder;
import Model.OrderDetail;
import Model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailController", urlPatterns = {"/OrderDetailController"})
public class OrderDetailController extends HttpServlet {

    private final DaoOrder dao = new DaoOrder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("id"));

        // Lấy chi tiết đơn hàng
        List<OrderDetail> details = dao.getAllProductinOrder(orderId);

        // (Tuỳ chọn) Lấy thông tin đơn hàng chung (receiver, payment, date, v.v.)
        Order order = dao.getAllOrders(null).stream()
                .filter(o -> o.getOrderID() == orderId)
                .findFirst().orElse(null);

        request.setAttribute("order", order);
        request.setAttribute("orderDetails", details);
        request.getRequestDispatcher("OrderDetailPage.jsp").forward(request, response);
    }
}
