package App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BookingPage extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public BookingPage(String departure, String destination, String departureDate, String destinationDate) {
        // Set up the window
        setTitle("Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the top panel
        JLabel titleLabel = new JLabel("Flight Booking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        // Create the center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the center panel
        JLabel departureLabel = new JLabel("Departure: " + departure);
        JLabel destinationLabel = new JLabel("Destination: " + destination);
        JLabel departureDateLabel = new JLabel("Departure Date: " + departureDate);
        JLabel destinationDateLabel = new JLabel("Destination Date: " + destinationDate);

        centerPanel.add(departureLabel);
        centerPanel.add(destinationLabel);
        centerPanel.add(departureDateLabel);
        centerPanel.add(destinationDateLabel);

        // Create the book flight panel
        JPanel bookFlightPanel = new JPanel();
        bookFlightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the book flight panel
        JButton bookFlightButton = new JButton("Book Flight");
        bookFlightButton.setPreferredSize(new Dimension(200, 50));

        bookFlightPanel.add(bookFlightButton);

        // Add action listener to the book flight button
        bookFlightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle book flight button action
                // Perform necessary actions when the flight is booked
                bookFlight(departure, destination, departureDate, destinationDate);
            }
        });

        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bookFlightPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Show the window
        setVisible(true);
    }

    private void bookFlight(String departure, String destination, String departureDate, String destinationDate) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish the database connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare the SQL statement
            String sql = "INSERT INTO booking (departure, destination, departure_date, destination_date) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, departure);
            statement.setString(2, destination);
            statement.setString(3, departureDate);
            statement.setString(4, destinationDate);

            // Execute the SQL statement
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this,
