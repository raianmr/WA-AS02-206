package tasks.waas02206;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        var out = response.getWriter();
        out.println("""
                <br>
                <form method="post" action="LogoutServlet">
                <input type="submit" value="Logout">
                </form>
                """);

        out.println("<br>");
        out.println("<form method=\"post\" action=\"OrderServlet\">");
        out.println("<select name=\"product\">");

        for (var productName : Store.products.keySet()) {
            out.println("<option value=\"%s\">%s</option>".formatted(productName, Store.products.get(productName)));
        }
        out.println("</select >");
        out.println("<input type=\"number\" name=\"quantity\" min=\"1\" required>");
        out.println("<input type=\"submit\" value=\"Order\">");
        out.println("</form >");

        var cookies = Arrays.asList(request.getCookies());
        var loginCookie = cookies.stream().filter(c -> c.getName().equals("username")).findFirst().orElse(null);
        if (loginCookie != null) {
            var cart = Store.carts.get(loginCookie.getValue());
            if (cart == null) {
                cart = new HashMap<>();
            }
            for (var productName : cart.keySet()) {
                out.println("<li> %s %s </li>".formatted(productName, Store.products.get(productName)));
            }
        }
    }
}
