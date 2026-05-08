package controller;

import database.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(fullname,email,password,role) VALUES(?,?,?,?)"
            );

            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, "user");

            ps.executeUpdate();

            response.sendRedirect("login.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
