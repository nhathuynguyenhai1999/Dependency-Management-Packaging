package com.cg.case_shopping.control;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy giá trị từ ô tìm kiếm
        String keyword = request.getParameter("kw");
        if (keyword != null && !keyword.isEmpty()){
            // Thực hiện tìm kiếm sản phẩm trong cơ sở dữ liệu dựa trên keyword
            // Ví dụ: In ra keyword để kiểm tra
            System.out.println("Found the searching letter" + keyword);
        }else {
            // Nếu không có keyword, có thể xử lý hiển thị tất cả sản phẩm hoặc thông báo lỗi
            System.out.println("No found the searching word");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu GET ở đây
        // Ví dụ: Hiển thị một trang HTML đặc biệt
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Search Form</title></head><body>");
        out.println("<h1>Search Form</h1>");
        out.println("<form action='home?action=search' method='post'>");
        out.println("<input type='text' name='txt'>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form>");
        out.println("</body></html>");
    }

}
