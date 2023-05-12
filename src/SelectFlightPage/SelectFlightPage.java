package SelectFlightPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Booking.Booking;
import App.App;

public class SelectFlightPage extends JFrame {

    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JComboBox<String> travelFromComboBox;
    private JComboBox<String> travelToComboBox;
    private JTable flightTable;

    public SelectFlightPage() {
        // Set up the main window
        setTitle("Flight Search");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLUE);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the top panel
        JLabel titleLabel = new JLabel("Flight Search");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        // Create the center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Create the search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the search panel
        JLabel fromLabel = new JLabel("Departure:");
        fromComboBox = new JComboBox<>();
        JLabel toLabel = new JLabel("Destination:");
        toComboBox = new JComboBox<>();
        JLabel travelFromLabel = new JLabel("Departure Date:");
        travelFromComboBox = new JComboBox<>();
        JLabel travelToLabel = new JLabel("Arrival Date:");
        travelToComboBox = new JComboBox<>();
        JButton searchButton = new JButton("Search");

        searchPanel.add(fromLabel);
        searchPanel.add(fromComboBox);
        searchPanel.add(toLabel);
        searchPanel.add(toComboBox);
        searchPanel.add(travelFromLabel);
        searchPanel.add(travelFromComboBox);
        searchPanel.add(travelToLabel);
        searchPanel.add(travelToComboBox);
        searchPanel.add(searchButton);

        // Populate the combo boxes with data from the database
        populateComboBoxes();

        centerPanel.add(searchPanel, BorderLayout.NORTH);

        // Create the flight table panel
        JPanel flightTablePanel = new JPanel();
        flightTablePanel.setLayout(new BorderLayout());

        // Create the table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Flight Number");
        model.addColumn("Departure");
        model.addColumn("Destination");
        model.addColumn("Departure Date");
        model.addColumn("Arrival Date");

        // Create the flight table
        flightTable = new JTable(model);
        flightTable.setFont(new Font("Arial", Font.PLAIN, 14));
        flightTable.setRowHeight(25);

        // Add the flight table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(flightTable);
        flightTablePanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(flightTablePanel, BorderLayout.CENTER);

        // Create the bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the bottom panel
        JButton bookButton = new JButton("Book");
        JButton backButton = new JButton("Back");

        bottomPanel.add(bookButton);
        bottomPanel.add(backButton);

        // Add action listeners to the buttons
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = flightTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNumber = (String) flightTable.getValueAt(selectedRow, 0); // Get the flight number from the selected row
                    new Booking(); // Open the booking page with the selected flight number
                    dispose(); // Close the flight search page
                    } else {
                    JOptionPane.showMessageDialog(SelectFlightPage.this, "Please select a flight.");
                    }
                    }
                    });
                    backButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose(); // Close the flight search page
                            new App(); // Open the main app page
                        }
                    });
                
                    // Add panels to the main panel
                    mainPanel.add(topPanel, BorderLayout.NORTH);
                    mainPanel.add(centerPanel, BorderLayout.CENTER);
                    mainPanel.add(bottomPanel, BorderLayout.SOUTH);
                
                    // Add the main panel to the frame
                    add(mainPanel);
                
                    // Show the window
                    setVisible(true);
                }
                
                private void populateComboBoxes() {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ARS", "root", "prakruthirai@02");
                
                        // Populate "From" combo box
                        Statement fromStatement = connection.createStatement();
                        ResultSet fromResultSet = fromStatement.executeQuery("SELECT DISTINCT `departure` FROM flights");
                        while (fromResultSet.next()) {
                            String from = fromResultSet.getString("departure");
                            fromComboBox.addItem(from);
                        }
                
                        // Populate "To" combo box
                        Statement toStatement = connection.createStatement();
                        ResultSet toResultSet = toStatement.executeQuery("SELECT DISTINCT `destination` FROM flights");
                        while (toResultSet.next()) {
                            String to = toResultSet.getString("destination");
                            toComboBox.addItem(to);
                        }
                
                        // Populate "Travel From" combo box
                        Statement travelFromStatement = connection.createStatement();
                        ResultSet travelFromResultSet = travelFromStatement.executeQuery("SELECT DISTINCT departure_date FROM flights");
                        while (travelFromResultSet.next()) {
                            String travelFrom = travelFromResultSet.getString("departure_date");
                            travelFromComboBox.addItem(travelFrom);
                        }
                
                        // Populate "Travel To" combo box
                        Statement travelToStatement = connection.createStatement();
                        ResultSet travelToResultSet = travelToStatement.executeQuery("SELECT DISTINCT arrival_date FROM flights");
                        while (travelToResultSet.next()) {
                            String travelTo = travelToResultSet.getString("arrival_date");
                            travelToComboBox.addItem(travelTo);
                        }
                
                        connection.close();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                    }
                }
                
                private void searchFlights() {
                    // Clear the existing table data
                    DefaultTableModel model = (DefaultTableModel) flightTable.getModel();
                    model.setRowCount(0);
                
                    // Retrieve the selected values from the combo boxes
                    String from = (String) fromComboBox.getSelectedItem();
                    String to = (String) toComboBox.getSelectedItem();
                    String travelFrom = (String) travelFromComboBox.getSelectedItem();
                    String travelTo = (String) travelToComboBox.getSelectedItem();
                
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ARS", "root", "prakruthirai@02");
                        Statement statement = connection.createStatement();
                
                        // Construct the SQL query based on the selected values
                        String query = "SELECT * FROM flights WHERE departure = '" + from + "' AND destination = '" + to + "' AND departure_date = '" + travelFrom + "' AND arrival_date = '" + travelTo + "'";
                        ResultSet resultSet = statement.executeQuery(query);
                
                        // Add the flight data to the table model
                        while (resultSet.next()) {
                            String flightNumber = resultSet.getString("flight_number");
                            String departure = resultSet.getString("departure");
                            String destination = resultSet.getString("destination");
                            String departureTime = resultSet.getString("departure_date");
                            String arrivalTime = resultSet.getString("arrival_Date");
                            model.addRow(new Object[]{flightNumber, departure, destination, departureTime, arrivalTime});
                        }
                
                        connection.close();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
                    }
                }
                
                public static void main(String[] args) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new SelectFlightPage();
                        }
                    });
                }}
                
                
