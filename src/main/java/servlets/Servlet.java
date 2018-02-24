package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "Servlet", urlPatterns = "/quest")
public class Servlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String param = req.getParameter("param");
        RequestDispatcher rd = null;
        if (param.equals("reg")) {
            rd = req.getRequestDispatcher("reg.jsp");

        }
        if (param.equals("login")) {
            rd = req.getRequestDispatcher("login.jsp");

        }
        try {
            rd.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
