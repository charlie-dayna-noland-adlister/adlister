import java.io.*;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet({"/hello", "/count"})
public class HelloServlet extends HttpServlet {
    private int count;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String uri = request.getRequestURI();

        String name = request.getParameter("name");
        if(uri.equals("/count")) {
            String resetCount = request.getParameter("reset-count");
            if (resetCount != null) {
                count = 0;
                response.getWriter().println("The count has been reset to 0");
            } else {
                count++;
                response.getWriter().println(String.format("Hello, count is %d", count));
            }
            if (name != null) {
                response.getWriter().println(String.format("Hello, %s", name));
            } else {
                response.getWriter().println("Hello, World!");
            }

        }
        if (name != null) {
            response.getWriter().println(String.format("Hello, %s", name));
        } else {
            response.getWriter().println("Hello, World!");
        }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello!</h1>");
    }
}
