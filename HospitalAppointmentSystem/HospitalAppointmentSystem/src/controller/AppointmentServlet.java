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

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String patient = request.getParameter("patient");
        String doctor = request.getParameter("doctor");
        String date = request.getParameter("date");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO appointments(patient_name, doctor_name, appointment_date, status) VALUES(?,?,?,?)"
            );

            ps.setString(1, patient);
            ps.setString(2, doctor);
            ps.setString(3, date);
            ps.setString(4, "Pending");

            ps.executeUpdate();

            response.getWriter().println("Appointment Booked Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
