package tasks.waas02206;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String selectedProduct = request.getParameter("product");
        int selectedQuantity = Integer.parseInt(request.getParameter("quantity"));

        var out = response.getWriter();
        var cookies = Arrays.asList(request.getCookies());
        var loginCookie = cookies.stream()
                .filter(c -> c.getName().equals("username"))
                .findFirst().orElse(null);

        if (loginCookie != null) {
            var cart = Store.carts.get(loginCookie.getValue());
            if (cart == null) {
                cart = new HashMap<>();
                cart.put(selectedProduct, selectedQuantity);
            } else {
                cart.put(selectedProduct, cart.get(selectedProduct) + selectedQuantity);
            }
            Store.carts.put(loginCookie.getValue(), cart);


            var rd = request.getRequestDispatcher("ProductServlet");
            rd.include(request, response);

            return;
        }

        out.println("<h1> Invalid session. Please log in again. </h1>");
        var rd = request.getRequestDispatcher("login.jsp");
        rd.include(request, response);
    }
}
