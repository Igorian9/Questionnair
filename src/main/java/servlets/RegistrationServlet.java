package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {

    static Connection connection;


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(Connect.DB_CONNECTION, Connect.DB_USER, Connect.DB_PASSWORD);
            initDB();

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String ageStr = req.getParameter("age");
            String answer1 = req.getParameter("quest1");
            String answer2 = req.getParameter("quest2");
            int age = Integer.parseInt(ageStr);

            setInfo(login, password, age, answer1, answer2);

            resp.sendRedirect("login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static void initDB() throws SQLException {
        Statement st = connection.createStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS Users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, login VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL, age INT NOT NULL, answer1 VARCHAR(120) NOT NULL, answer2 VARCHAR(120) NOT NULL)");
        } finally {
            st.close();
        }
    }

    private synchronized static void setInfo(String login, String password, int age, String answer1, String answer2) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Users (login, password, age, answer1, answer2) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setInt(3, age);
            ps.setString(4, answer1);
            ps.setString(5, answer2);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }
}

