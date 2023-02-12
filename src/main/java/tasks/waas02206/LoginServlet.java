package tasks.waas02206;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String storedPass = Store.users.get(username);
        if (storedPass == null || !storedPass.equals(password)) {
            PrintWriter out = response.getWriter();
            out.println("<h3 align=\"center\"> Wrong credentials </h3>");

            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.include(request, response);

            return;
        }

        Cookie loginCookie = new Cookie("username", username);
        response.addCookie(loginCookie);

        PrintWriter out = response.getWriter();
        out.println("<h3 align=\"center\"> Successfully logged in </h3>");



        RequestDispatcher rd = request.getRequestDispatcher("ProductServlet");
        rd.include(request, response);
    }
}
