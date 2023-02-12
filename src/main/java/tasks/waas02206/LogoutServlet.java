package tasks.waas02206;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        var cookies = Arrays.asList(request.getCookies());
        PrintWriter out = response.getWriter();

        cookies.stream()
                .filter(c -> c.getName().equals("username"))
                .findFirst()
                .ifPresent(loginCookie -> loginCookie.setMaxAge(0));

        out.println("<h3> Successfully logged out </h3>");
        var rd = request.getRequestDispatcher("login.jsp");
        rd.include(request, response);
    }
}
