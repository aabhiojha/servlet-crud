package dev.abhishek.taskmanagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "tasks", value = "/tasks")
public class Tasks extends HttpServlet {
    private String URL = "jdbc:postgresql://localhost:5432/servlet-task";
    private String USER = "postgres";
    private String PASSWORD = "postgres";

    @Override
    public void init() throws ServletException {
        try {
            // create the database connection
            Class.forName("org.postgresql.Driver");
            System.out.println("The PostgreSQL driver loaded");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Map<String, Object>> taskList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("connection created");
            String sql = "select * from tasks;";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Map<String, Object> task = new HashMap<>();

                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Date date = rs.getDate("created_at");
                    System.out.println(id + title + description + status + date);

                    task.put("id", rs.getInt("id"));
                    task.put("title", rs.getString("title"));
                    task.put("description", rs.getString("description"));
                    task.put("status", rs.getString("status"));
                    task.put("created_at", rs.getString("created_at"));

                    taskList.add(task);
                }
            }

            req.setAttribute("tasks", taskList);
            req.getRequestDispatcher("/WEB-INF/views/task-list.jsp")
                    .forward(req, res);

        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String status = req.getParameter("status");

            boolean insert_status = false;

            String sql = """
                    Insert into tasks(title, description, status)
                    values (?, ?, ?);
                    """;
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setString(3, status);
                ps.executeUpdate();
                insert_status = true;
                if (insert_status){
                    System.out.println("print the success diaglogue");
                    res.sendRedirect(req.getContextPath() + "/tasks");
                }
                else System.out.println("print error message");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




