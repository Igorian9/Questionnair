package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    static Connection connection;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(Connect.DB_CONNECTION, Connect.DB_USER, Connect.DB_PASSWORD);

            String login = req.getParameter("login");
            String password = req.getParameter("password");

            String[] buffer = getInfoFromDB(login);

            if (buffer[2].equals(password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user_login", buffer[1]);
                session.setAttribute("user_age", buffer[3]);
                session.setAttribute("user_answerOne", buffer[4]);
                session.setAttribute("user_answerTwo", buffer[5]);
                session.setAttribute("users_count", getCountFromDB());
                resp.sendRedirect("login.jsp");
            } else {
                resp.sendRedirect("error.jsp");
            }
        } catch (SQLException e)

        {
            e.printStackTrace();
        }

    }

    private String[] getInfoFromDB(String login) throws SQLException {
        String[] buffer = new String[6];
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE login = ?");
        try {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {
                    for (int i = 1; i <= 6; i++) {
                        if (i == 1 || i == 4)
                            buffer[i - 1] = String.valueOf(rs.getInt(i));
                        else
                            buffer[i - 1] = rs.getString(i);
                    }
                }
                return buffer;
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    private String getCountFromDB() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(Users.id) FROM Users");
        try {
            ResultSet rs = ps.executeQuery();
            try {
                String res = "";
                while (rs.next()) {
                    res += String.valueOf(rs.getInt(1));
                }
                return res;
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String a = req.getParameter("a");
        HttpSession session = req.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        resp.sendRedirect("login.jsp");
    }
}