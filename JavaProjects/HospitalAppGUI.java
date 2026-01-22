package HospitalManagementSystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class HospitalAppGUI extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/HosDat1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private Connection connection;
    private BorderPane mainLayout;

    // Dashboard UI elements
    private Label patientCountLabel;
    private Label doctorCountLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // 1. Create an instance of our new custom LoginDialog
        LoginDialog loginDialog = new LoginDialog();

        // 2. Show the dialog and wait for the user to login or cancel
        Optional<Boolean> result = loginDialog.showAndWait();

        // 3. Check the result:
        //    - result.isPresent() is true if a button was clicked (not closed).
        //    - result.get() is true if the login was successful (we returned true).
        if (result.isPresent() && result.get()) {
            // If login is successful, show the main application window
            showMainWindow(primaryStage);
        } else {
            // If login fails or is cancelled, close the application
            System.out.println("Login cancelled or failed. Exiting.");
            Platform.exit();
        }
    }

    private void showMainWindow(Stage primaryStage) {
        initDatabaseConnection();
        primaryStage.setTitle("Hospital Management System");
        mainLayout = new BorderPane();

        Node navigationPanel = createNavigationPanel();
        mainLayout.setLeft(navigationPanel);
        mainLayout.setCenter(createDashboardView());

        Scene scene = new Scene(mainLayout, 1200, 800);

        // ## THIS IS THE CODE THAT LINKS YOUR CSS FILE ##
        try {
            String cssPath = getClass().getResource("/HospitalManagementSystem/styles.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.err.println("Error: Could not load the CSS file.");
            System.err.println("Please ensure 'styles.css' is in 'src/main/resources/HospitalManagementSystem/'");
        }

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> closeDatabaseConnection());
    }

    private void initDatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            showErrorDialog("Database Connection Failed", "Could not connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void closeDatabaseConnection() {
        if (connection != null) {
            try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private Node createNavigationPanel() {
        VBox navBox = new VBox(10);
        navBox.setPadding(new Insets(15));
        navBox.getStyleClass().add("nav-bar");

        Text appTitle = new Text("Hospital Management System");
        appTitle.getStyleClass().add("app-title");
        navBox.getChildren().add(appTitle);
        VBox.setMargin(appTitle, new Insets(0, 0, 30, 0));

        navBox.getChildren().addAll(
                createNavButton("Dashboard", FontAwesomeSolid.HOME, this::createDashboardView),
                createNavButton("Patients", FontAwesomeSolid.USER_INJURED, this::createPatientsView),
                createNavButton("Doctors", FontAwesomeSolid.USER_MD, this::createDoctorsView),
                createNavButton("Appointments", FontAwesomeSolid.CALENDAR_CHECK, this::createAppointmentsView),
                createNavButton("Billing", FontAwesomeSolid.FILE_INVOICE_DOLLAR, this::createBillingView)
        );
        return navBox;
    }

    private Button createNavButton(String text, FontAwesomeSolid icon, java.util.function.Supplier<Node> viewSupplier) {
        Button button = new Button(text, new FontIcon(icon));
        button.getStyleClass().add("nav-button");
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(e -> mainLayout.setCenter(viewSupplier.get()));
        return button;
    }

    // ---------------------- DASHBOARD (NEW & IMPROVED) -----------------------
    private Node createDashboardView() {
        VBox pane = new VBox(30);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(40));
        pane.getStyleClass().add("dashboard-pane");

        Label title = new Label("Live Info");
        title.getStyleClass().add("dashboard-title");

        // Initialize labels for the cards
        patientCountLabel = new Label("0");
        doctorCountLabel = new Label("0");

        // Patient Card
        VBox patientCard = createDashboardCard("👥 Total Patients", patientCountLabel);

        // Doctor Card
        VBox doctorCard = createDashboardCard("🩺 Active Doctors", doctorCountLabel);

        HBox cards = new HBox(40, patientCard, doctorCard);
        cards.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(title, cards);

        refreshDashboard(); // Load initial data
        return pane;
    }

    private VBox createDashboardCard(String header, Label valueLabel) {
        Label headerLabel = new Label(header);
        headerLabel.getStyleClass().add("card-header");
        valueLabel.getStyleClass().add("card-value");

        VBox card = new VBox(10, headerLabel, valueLabel);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("dashboard-card");
        return card;
    }

    private void refreshDashboard() {
        if (connection == null || patientCountLabel == null || doctorCountLabel == null) {
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            // Get patient count
            try (ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM patients")) {
                if (rs1.next()) {
                    patientCountLabel.setText(String.valueOf(rs1.getInt(1)));
                }
            }
            // Get active doctor count
            try (ResultSet rs2 = stmt.executeQuery("SELECT COUNT(*) FROM doctors WHERE is_active = 1")) {
                if (rs2.next()) {
                    doctorCountLabel.setText(String.valueOf(rs2.getInt(1)));
                }
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Could not load dashboard data.");
        }
    }

    // ---------------------- PATIENTS -----------------------
    private Node createPatientsView() {
        VBox pane = new VBox(15);
        pane.setPadding(new Insets(20));
        pane.getStyleClass().add("view-pane");

        Label title = new Label("Patient Management");
        title.getStyleClass().add("view-title");

        TableView<Patient> table = new TableView<>();
        setupPatientTable(table);

        HBox bottomBar = new HBox(10);
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setPadding(new Insets(10, 0, 0, 0));

        TextField searchField = new TextField();
        searchField.setPromptText("Search by Patient ID...");
        searchField.setPrefWidth(200);

        Button searchButton = new Button("Search");
        searchButton.setGraphic(new FontIcon(FontAwesomeSolid.SEARCH));

        Button clearButton = new Button("Show All");
        clearButton.setGraphic(new FontIcon(FontAwesomeSolid.LIST));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addButton = new Button("Add New Patient", new FontIcon(FontAwesomeSolid.PLUS_CIRCLE));
        addButton.getStyleClass().add("action-button-green");

        bottomBar.getChildren().addAll(searchField, searchButton, clearButton, spacer, addButton);

        pane.getChildren().addAll(title, table, bottomBar);
        VBox.setVgrow(table, Priority.ALWAYS);

        addButton.setOnAction(e -> showAddPatientDialog(table));
        searchButton.setOnAction(e -> {
            try {
                int patientId = Integer.parseInt(searchField.getText());
                searchPatientById(table, patientId);
            } catch (NumberFormatException ex) {
                showErrorDialog("Invalid Input", "Patient ID must be a number.");
            }
        });
        clearButton.setOnAction(e -> loadPatientData(table));

        loadPatientData(table);
        return pane;
    }

    private void setupPatientTable(TableView<Patient> table) {
        TableColumn<Patient, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cd -> cd.getValue().idProperty().asObject());
        TableColumn<Patient, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cd -> cd.getValue().nameProperty());
        TableColumn<Patient, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cd -> cd.getValue().ageProperty().asObject());
        TableColumn<Patient, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(cd -> cd.getValue().genderProperty());
        table.getColumns().addAll(idCol, nameCol, ageCol, genderCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void loadPatientData(TableView<Patient> table) {
        table.setItems(getAllPatients());
    }

    private void searchPatientById(TableView<Patient> table, int patientId) {
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        if (connection == null) return;
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender")));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "No patient found with ID: " + patientId).showAndWait();
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Failed to search for patient.");
        }
        table.setItems(patients);
    }

    private void showAddPatientDialog(TableView<Patient> table) {
        Dialog<Patient> dialog = new Dialog<>();
        dialog.setTitle("Add New Patient");
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField genderField = new TextField();
        genderField.setPromptText("Gender");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(new Label("Gender:"), 0, 2);
        grid.add(genderField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(b -> {
            if (b == saveButtonType) {
                try {
                    if (nameField.getText().trim().isEmpty()) {
                        showErrorDialog("Invalid Input", "Patient name cannot be empty.");
                        return null;
                    }
                    return new Patient(0, nameField.getText(), Integer.parseInt(ageField.getText()), genderField.getText());
                } catch (NumberFormatException e) {
                    showErrorDialog("Invalid Input", "Age must be a valid number.");
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(p -> {
            if (p != null) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)")) {
                    ps.setString(1, p.getName());
                    ps.setInt(2, p.getAge());
                    ps.setString(3, p.getGender());
                    ps.executeUpdate();
                    loadPatientData(table);
                    refreshDashboard(); // Keep dashboard in sync
                } catch (SQLException e) {
                    showErrorDialog("Database Error", "Failed to add patient: " + e.getMessage());
                }
            }
        });
    }

    // ---------------------- DOCTORS -----------------------
    private Node createDoctorsView() {
        VBox pane = new VBox(15);
        pane.setPadding(new Insets(20));
        pane.getStyleClass().add("view-pane");

        Label title = new Label("Doctors List");
        title.getStyleClass().add("view-title");

        TableView<Doctor> table = new TableView<>();
        setupDoctorTable(table);

        pane.getChildren().addAll(title, table);
        VBox.setVgrow(table, Priority.ALWAYS);

        loadDoctorData(table);
        return pane;
    }

    private void setupDoctorTable(TableView<Doctor> table) {
        table.setEditable(true);

        TableColumn<Doctor, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(cd -> cd.getValue().idProperty().asObject());

        TableColumn<Doctor, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cd -> cd.getValue().nameProperty());

        TableColumn<Doctor, String> specializationCol = new TableColumn<>("Specialization");
        specializationCol.setCellValueFactory(cd -> cd.getValue().specializationProperty());

        TableColumn<Doctor, Boolean> activeCol = new TableColumn<>("Active");
        activeCol.setCellValueFactory(cd -> {
            Doctor d = cd.getValue();
            BooleanProperty prop = d.activeProperty();
            prop.addListener((obs, oldV, newV) -> {
                updateDoctorActiveStatus(d.getId(), newV);
                refreshDashboard(); // Keep dashboard in sync
            });
            return prop;
        });
        activeCol.setCellFactory(CheckBoxTableCell.forTableColumn(activeCol));

        table.getColumns().addAll(idCol, nameCol, specializationCol, activeCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void updateDoctorActiveStatus(int doctorId, boolean isActive) {
        if (connection == null) return;
        String sql = "UPDATE doctors SET is_active = ? WHERE doctor_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, isActive);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Could not update doctor status.");
        }
    }

    private void loadDoctorData(TableView<Doctor> table) {
        table.setItems(getAllDoctors());
    }

    // ---------------------- APPOINTMENTS & BILLING (UNCHANGED) -----------------------
    private Node createAppointmentsView() {
        VBox pane = new VBox(20);
        pane.setPadding(new Insets(20));
        pane.getStyleClass().add("view-pane");

        Label title = new Label("Book an Appointment");
        title.getStyleClass().add("view-title");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setMaxWidth(500);

        ComboBox<Patient> patientComboBox = new ComboBox<>(getAllPatients());
        patientComboBox.setPromptText("Select a Patient");
        patientComboBox.setConverter(createPatientConverter());

        ComboBox<Doctor> doctorComboBox = new ComboBox<>(getAllDoctors());
        doctorComboBox.setPromptText("Select a Doctor");
        doctorComboBox.setConverter(createDoctorConverter());

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select a Date");

        formGrid.add(new Label("Patient:"), 0, 0);
        formGrid.add(patientComboBox, 1, 0);
        formGrid.add(new Label("Doctor:"), 0, 1);
        formGrid.add(doctorComboBox, 1, 1);
        formGrid.add(new Label("Date:"), 0, 2);
        formGrid.add(datePicker, 1, 2);

        Button bookButton = new Button("Book Appointment", new FontIcon(FontAwesomeSolid.CHECK_CIRCLE));
        bookButton.getStyleClass().add("action-button-green");
        bookButton.setOnAction(e -> {
            Patient selectedPatient = patientComboBox.getValue();
            Doctor selectedDoctor = doctorComboBox.getValue();
            LocalDate selectedDate = datePicker.getValue();
            if (selectedPatient == null || selectedDoctor == null || selectedDate == null) {
                showErrorDialog("Invalid Input", "Please select a patient, doctor, and date.");
                return;
            }
            bookAppointment(selectedPatient.getId(), selectedDoctor.getId(), selectedDate);
        });

        pane.getChildren().addAll(title, formGrid, bookButton);
        return pane;
    }

    private Node createBillingView() {
        VBox pane = new VBox(20);
        pane.setPadding(new Insets(20));
        pane.getStyleClass().add("view-pane");

        Label title = new Label("Create a Bill");
        title.getStyleClass().add("view-title");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setMaxWidth(500);

        ComboBox<Patient> patientComboBox = new ComboBox<>(getAllPatients());
        patientComboBox.setPromptText("Select a Patient");
        patientComboBox.setConverter(createPatientConverter());

        TextField amountField = new TextField();
        amountField.setPromptText("e.g., 1500.50");

        formGrid.add(new Label("Patient:"), 0, 0);
        formGrid.add(patientComboBox, 1, 0);
        formGrid.add(new Label("Total Amount:"), 0, 1);
        formGrid.add(amountField, 1, 1);

        Button createBillButton = new Button("Create Bill", new FontIcon(FontAwesomeSolid.FILE_INVOICE_DOLLAR));
        createBillButton.getStyleClass().add("action-button-green");
        createBillButton.setOnAction(e -> {
            Patient selectedPatient = patientComboBox.getValue();
            String amountText = amountField.getText();
            if (selectedPatient == null || amountText.trim().isEmpty()) {
                showErrorDialog("Invalid Input", "Please select a patient and enter an amount.");
                return;
            }
            try {
                double amount = Double.parseDouble(amountText);
                createBill(selectedPatient.getId(), amount);
            } catch (NumberFormatException ex) {
                showErrorDialog("Invalid Input", "Amount must be a valid number.");
            }
        });

        pane.getChildren().addAll(title, formGrid, createBillButton);
        return pane;
    }

    // ---------------------- DATA ACCESS & HELPERS -----------------------
    private ObservableList<Patient> getAllPatients() {
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        if (connection == null) return patients;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT patient_id, name, age, gender FROM patients")) {
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender")));
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Failed to load patients.");
        }
        return patients;
    }

    private ObservableList<Doctor> getAllDoctors() {
        ObservableList<Doctor> doctors = FXCollections.observableArrayList();
        if (connection == null) return doctors;
        String sql = "SELECT doctor_id, name, specialization, is_active FROM doctors";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getBoolean("is_active")
                ));
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Failed to load doctors.");
        }
        return doctors;
    }

    private void bookAppointment(int patientId, int doctorId, LocalDate date) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setDate(3, Date.valueOf(date));
            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment booked successfully!").showAndWait();
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Could not book appointment. " + e.getMessage());
        }
    }

    private void createBill(int patientId, double amount) {
        String sql = "INSERT INTO bills (patient_id, bill_date, total_amount) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setDouble(3, amount);
            if (ps.executeUpdate() > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Bill created successfully!").showAndWait();
            }
        } catch (SQLException e) {
            showErrorDialog("Database Error", "Could not create bill. " + e.getMessage());
        }
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private StringConverter<Patient> createPatientConverter() {
        return new StringConverter<>() {
            @Override public String toString(Patient p) { return p == null ? "" : p.getId() + " - " + p.getName(); }
            @Override public Patient fromString(String s) { return null; }
        };
    }

    private StringConverter<Doctor> createDoctorConverter() {
        return new StringConverter<>() {
            @Override public String toString(Doctor d) { return d == null ? "" : d.getName() + " (" + d.getSpecialization() + ")"; }
            @Override public Doctor fromString(String s) { return null; }
        };
    }
}