package com.hospital.ui;

import com.hospital.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class HospitalUI extends JFrame {

    JTextField nameField, ageField, contactField;
    JTextField doctorField, dateField;

    public HospitalUI() {
        setTitle("Hospital Appointment System");
        setSize(400, 400);
        setLayout(new GridLayout(8,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Name:"));
        nameField = new JTextField(); add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField(); add(ageField);

        add(new JLabel("Contact:"));
        contactField = new JTextField(); add(contactField);

        add(new JLabel("Doctor Name:"));
        doctorField = new JTextField(); add(doctorField);

        add(new JLabel("Date:"));
        dateField = new JTextField(); add(dateField);

        JButton saveBtn = new JButton("Save");
        JButton viewBtn = new JButton("View");

        add(saveBtn);
        add(viewBtn);

        saveBtn.addActionListener(e -> saveAppointment());
        viewBtn.addActionListener(e -> viewAppointments());

        setVisible(true);
    }

    void saveAppointment() {
        try {
            Connection con = DBConnection.getConnection();

            String pQuery = "INSERT INTO patients(name, age, contact) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(pQuery, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, nameField.getText());
            ps.setInt(2, Integer.parseInt(ageField.getText()));
            ps.setString(3, contactField.getText());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int patientId = 0;
            if (rs.next()) patientId = rs.getInt(1);

            String aQuery = "INSERT INTO appointments(patient_id, doctor_name, appointment_date) VALUES (?, ?, ?)";
            PreparedStatement ps2 = con.prepareStatement(aQuery);

            ps2.setInt(1, patientId);
            ps2.setString(2, doctorField.getText());
            ps2.setString(3, dateField.getText());
            ps2.executeUpdate();

            JOptionPane.showMessageDialog(this, "Saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void viewAppointments() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT p.name, a.doctor_name, a.appointment_date " +
                           "FROM patients p JOIN appointments a ON p.id = a.patient_id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            StringBuilder data = new StringBuilder();

            while (rs.next()) {
                data.append("Patient: ").append(rs.getString("name"))
                    .append(" | Doctor: ").append(rs.getString("doctor_name"))
                    .append(" | Date: ").append(rs.getString("appointment_date"))
                    .append("\n");
            }

            JOptionPane.showMessageDialog(this, data.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}