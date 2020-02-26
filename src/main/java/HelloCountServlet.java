import java.io.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(urlPatterns = "/hello/count")
public class HelloCountServlet extends HttpServlet {
    int count;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        count++;
        String name = request.getParameter("name");
        String resetCount = request.getParameter("reset-count");
        if (resetCount != null) {
            count = 0;
            response.getWriter().println("The count has been reset to 0");
        }
        if (name != null) {
            response.getWriter().println(String.format("Hello, %s count is %d", name, count));
        } else {
            response.getWriter().println(String.format("Hello, count is %d", count));
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello!</h1>");
    }
}