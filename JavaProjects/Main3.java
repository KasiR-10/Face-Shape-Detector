package HospitalManagementSystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.*;

public class HospitalAppGUI extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Connection connection;
    private Patient patient;
    private Doctor doctor;

    private JTable dataTable;
    private JScrollPane scrollPane;
    private JLabel titleLabel;

    public HospitalAppGUI() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            patient = new Patient(connection);
            doctor = new Doctor(connection);
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

        setTitle("Hospital Management System");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(245, 245, 245));
        titleLabel = new JLabel("Welcome to the Hospital Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(60, 63, 65));
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton viewPatientsButton = createStyledButton("View Patients", "https://img.icons8.com/fluency-systems-regular/48/000000/user-group-man-man.png");
        JButton addPatientButton = createStyledButton("Add Patient", "https://img.icons8.com/fluency-systems-regular/48/000000/add-user-male.png");
        JButton viewDoctorsButton = createStyledButton("View Doctors", "https://img.icons8.com/fluency-systems-regular/48/000000/medical-doctor.png");
        JButton bookAppointmentButton = createStyledButton("Book Appointment", "https://img.icons8.com/fluency-systems-regular/48/000000/calendar-plus.png");
        JButton viewAppointmentsButton = createStyledButton("View Appointments", "https://img.icons8.com/fluency-systems-regular/48/000000/task-completed.png");

        buttonPanel.add(viewPatientsButton);
        buttonPanel.add(addPatientButton);
        buttonPanel.add(viewDoctorsButton);
        buttonPanel.add(bookAppointmentButton);
        buttonPanel.add(viewAppointmentsButton);

        dataTable = new JTable();
        dataTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dataTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        dataTable.setRowHeight(25);

        scrollPane = new JScrollPane(dataTable);

        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        addPatientButton.addActionListener(e -> addPatient());
        viewPatientsButton.addActionListener(e -> viewPatients());
        viewDoctorsButton.addActionListener(e -> viewDoctors());
        bookAppointmentButton.addActionListener(e -> bookAppointment());
        viewAppointmentsButton.addActionListener(e -> viewAppointments());
    }

    private JButton createStyledButton(String text, String iconUrl) {
        JButton button = new JButton(text);
        try {
            URL url = new URL(iconUrl);
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            button.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setIconTextGap(8);
        button.setMargin(new Insets(5, 10, 5, 10));
        return button;
    }

    private void viewPatients() {
        try {
            titleLabel.setText("Patient List");
            dataTable.setModel(DbUtils.resultSetToTableModel(patient.viewPatients()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching patients: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewDoctors() {
        try {
            titleLabel.setText("Doctor List");
            dataTable.setModel(DbUtils.resultSetToTableModel(doctor.viewDoctors()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching doctors: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAppointments() {
        try {
            titleLabel.setText("Appointment List");
            String query = "SELECT a.id, p.name AS patient_name, d.name AS doctor_name, a.appointment_date " +
                    "FROM appointments a " +
                    "JOIN patients p ON a.patient_id = p.id " +
                    "JOIN doctors d ON a.doctor_id = d.id";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            dataTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching appointments: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPatient() {
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(5);
        JTextField genderField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();

                if (patient.addPatient(name, age, gender)) {
                    JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    viewPatients();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add patient.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid age. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void bookAppointment() {
        JTextField patientIdField = new JTextField(5);
        JTextField doctorIdField = new JTextField(5);
        JTextField dateField = new JTextField(10);
        dateField.setText("YYYY-MM-DD");

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Patient ID:"));
        panel.add(patientIdField);
        panel.add(new JLabel("Doctor ID:"));
        panel.add(doctorIdField);
        panel.add(new JLabel("Appointment Date (YYYY-MM-DD):"));
        panel.add(dateField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Book Appointment", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int patientId = Integer.parseInt(patientIdField.getText());
                int doctorId = Integer.parseInt(doctorIdField.getText());
                String appointmentDate = dateField.getText();

                if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
                    if (checkDoctorAvailability(doctorId, appointmentDate)) {
                        String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                            preparedStatement.setInt(1, patientId);
                            preparedStatement.setInt(2, doctorId);
                            preparedStatement.setString(3, appointmentDate);

                            if (preparedStatement.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(this, "Appointment Booked Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                viewAppointments();
                            } else {
                                JOptionPane.showMessageDialog(this, "Failed to book appointment.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Doctor is not available on this date.", "Availability Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Either Patient or Doctor not found.", "ID Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean checkDoctorAvailability(int doctorId, String appointmentDate) {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalAppGUI gui = new HospitalAppGUI();
            gui.setVisible(true);
        });
    }
}