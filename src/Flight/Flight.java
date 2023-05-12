package Flight;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import admin.admin;

public class Flight extends JFrame {
    private JTextField flightNumberTextField;
    private JTextField departureTextField;
    private JTextField destinationTextField;
    private JTextField departureDateTextField;
    private JTextField departureTimeTextField;
    private JTextField arrivalDateTextField;
    private JTextField arrivalTimeTextField;
    private JTextField capacityTextField;
    private JTextField availableSeatsTextField;
    private JTextField priceTextField;

    public Flight() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Add New Flight");
        setResizable(false);
        setSize(400, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.add(new JLabel("Flight Number: "));
        flightNumberTextField = new JTextField();
        inputPanel.add(flightNumberTextField);
        inputPanel.add(new JLabel("Departure: "));
        departureTextField = new JTextField();
        inputPanel.add(departureTextField);
        inputPanel.add(new JLabel("Destination: "));
        destinationTextField = new JTextField();
        inputPanel.add(destinationTextField);
        inputPanel.add(new JLabel("Departure Date: "));
        departureDateTextField = new JTextField();
        inputPanel.add(departureDateTextField);
        inputPanel.add(new JLabel("Departure Time: "));
        departureTimeTextField = new JTextField();
        inputPanel.add(departureTimeTextField);
        inputPanel.add(new JLabel("Arrival Date: "));
        arrivalDateTextField = new JTextField();
        inputPanel.add(arrivalDateTextField);
        inputPanel.add(new JLabel("Arrival Time: "));
        arrivalTimeTextField = new JTextField();
        inputPanel.add(arrivalTimeTextField);
        inputPanel.add(new JLabel("Capacity: "));
        capacityTextField = new JTextField();
        inputPanel.add(capacityTextField);
        inputPanel.add(new JLabel("Available Seats: "));
        availableSeatsTextField = new JTextField();
        inputPanel.add(availableSeatsTextField);
        inputPanel.add(new JLabel("Price: "));
        priceTextField = new JTextField();
        inputPanel.add(priceTextField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Flight");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to add new flight to database
                String flightNumber = flightNumberTextField.getText();
                String departure = departureTextField.getText();
                String destination = destinationTextField.getText();
                String departureDate = departureDateTextField.getText();
                String departureTime = departureTimeTextField.getText();
                String arrivalDate = arrivalDateTextField.getText();
                String arrivalTime = arrivalTimeTextField.getText();
                int capacity = Integer.parseInt(capacityTextField.getText());
                int availableSeats = Integer.parseInt(availableSeatsTextField.getText());
                double price = Double.parseDouble(priceTextField.getText());

                boolean success = addFlight(flightNumber, departure, destination, departureDate, departureTime,
                        arrivalDate, arrivalTime, capacity, availableSeats, price);

                if (success) {
                    // Display success message
                    JOptionPane.showMessageDialog(null, "New flight added successfully!");

                    // Clear input fields
                    flightNumberTextField.setText("");
                    departureTextField.setText("");
                    destinationTextField.setText("");
                    departureDateTextField.setText("");
                    departureTimeTextField.setText("");
                    arrivalDateTextField.setText("");
                    arrivalTimeTextField.setText("");
                    capacityTextField.setText("");
                    availableSeatsTextField.setText("");
                    priceTextField.setText("");
                } else {
                    // Display error message
                    JOptionPane.showMessageDialog(null, "Failed to add new flight. Please try again.");
                }
            }
        });
        buttonPanel.add(addButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to go back to the admin page
                dispose(); // Close the current window
                new admin("Jero"); // Open the admin page
            }
        });
    
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
    
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        setContentPane(mainPanel);
        setVisible(true);
    }
    
    private boolean addFlight(String flightNumber, String departure, String destination, String departureDate,
                              String departureTime, String arrivalDate, String arrivalTime, int capacity,
                              int availableSeats, double price) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ARS", "root", "prakruthirai@02");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO flights (flight_number, departure, destination, departure_date, departure_time, " +
                            "arrival_date, arrival_time, capacity, available_seats, price) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    
            preparedStatement.setString(1, flightNumber);
            preparedStatement.setString(2, departure);
            preparedStatement.setString(3, destination);
            preparedStatement.setString(4, departureDate);
            preparedStatement.setString(5, departureTime);
            preparedStatement.setString(6, arrivalDate);
            preparedStatement.setString(7, arrivalTime);
            preparedStatement.setInt(8, capacity);
            preparedStatement.setInt(9, availableSeats);
            preparedStatement.setDouble(10, price);
    
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
    
            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Flight();
            }
        });
    }

    public Object getFlightNumber() {
        return null;
    }

    public Object getDeparture() {
        return null;
    }

    public Object getDestination() {
        return null;
    }

    public Object getDepartureTime() {
        return null;
    }

    public Object getArrivalTime() {
        return null;
    }

    public void setFlightNumber(String string) {
    }

    public void setDeparture(String string) {
    }

    public void setLocation(String string) {
    }

    public void setDepartureTime(String string) {
    }

    public void setArrivalTime(String string) {
    }
}    
