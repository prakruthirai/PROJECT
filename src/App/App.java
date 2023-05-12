package App;

import javax.swing.*;

import Booking.Booking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class App extends JFrame {

    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JComboBox<String> travelFromComboBox;
    private JComboBox<String> travelToComboBox;

    public App() {
        // Set up the main window
        setTitle("Airline Reservation System");
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
        JLabel titleLabel = new JLabel("Welcome to Airline Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);

        // Create the center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Create the from and to panel
        JPanel fromToPanel = new JPanel();
        fromToPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // Add components to the from and to panel
        JLabel fromLabel = new JLabel("Departure:");
        fromComboBox = new JComboBox<>();
        JLabel toLabel = new JLabel("Destination:");
        toComboBox = new JComboBox<>();
        JLabel travelFromLabel = new JLabel("Departure Date:");
        travelFromComboBox = new JComboBox<>();
        JLabel travelToLabel = new JLabel("Arrival Date:");
        travelToComboBox = new JComboBox<>();

        fromToPanel.add(fromLabel);
        fromToPanel.add(fromComboBox);
        fromToPanel.add(toLabel);
        fromToPanel.add(toComboBox);
        fromToPanel.add(travelFromLabel);
        fromToPanel.add(travelFromComboBox);
        fromToPanel.add(travelToLabel);
        fromToPanel.add(travelToComboBox);

        // Populate the combo boxes with data from the database
        populateComboBoxes();

        centerPanel.add(fromToPanel, BorderLayout.NORTH);

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
                // Redirect to the book flight page or perform necessary actions
                JOptionPane.showMessageDialog(App.this, "Redirecting to book flight page...");
                new Booking();
                
                   
                        // String username = usernameField.getText();
                        // String password = String.valueOf(passwordField.getPassword());
                        // String email = emailField.getText();
                        // Perform user registration logic
        //                 if (registerUser(username, password, email)) {
        //                     JOptionPane.showMessageDialog(RegisterPage.this, "Registration successful!");
        //                     // Open the login page
        //                     new Loginpage();
        //                     setVisible(false);
        //                     dispose();
        //                 } else {
        //                     JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed!");
        //                 }
        //             }
        //         });
           }
         });

        centerPanel.add(bookFlightPanel, BorderLayout.CENTER);

        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

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
    
            JButton bookFlightButton = new JButton("Book Flight");
    
            bookFlightButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Booking();
                }
            });

    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}