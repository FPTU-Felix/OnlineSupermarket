package Controller;

import DAO.DaoOrder;
import DTO.OrderDTO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderOverviewPageController", urlPatterns = {"/OrderOverviewPageController"})
public class OrderOverviewPageController extends HttpServlet {

    private final DaoOrder dao = new DaoOrder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy trạng thái nếu có
        String status = request.getParameter("status");

        // Gọi DAO để lấy danh sách đơn hàng theo trạng thái
        List<OrderDTO> orders = dao.getAllOrderDTOs(status);  // Đảm bảo phương thức này đã được tạo trong DaoOrder

        // Truyền sang JSP
        request.setAttribute("orders", orders);
        request.setAttribute("selectedStatus", status);
        request.getRequestDispatcher("OrderOverviewPage.jsp").forward(request, response);
    }
}
