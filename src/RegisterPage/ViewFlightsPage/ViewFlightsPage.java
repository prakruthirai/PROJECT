package ViewFlightsPage;

import javax.swing.*;

import admin.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewFlightsPage extends JFrame {
    private JTable flightTable;
    private JButton backButton;

    public ViewFlightsPage() {
        setTitle("View Flights");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create a panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Retrieve flights data from the database
        String[][] data = getFlightsData();

        // Create column names
        String[] columnNames = {"Flight Number", "Departure", "Destination", "Departure Date", "Departure Time","Arrival Date", "Arrival Time", "Capacity", "Available Seats", "Price"};

        // Create a table with the flights data
        flightTable = new JTable(data, columnNames);
        flightTable.setFont(new Font("Arial", Font.PLAIN, 14));
        flightTable.setRowHeight(25);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(flightTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a back button
        backButton = new JButton("Back");
        backButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the view flights page
                // Perform any other necessary actions upon clicking the back button
                new admin("Jero");
            }
        });

        // Add the back button to the panel
        panel.add(backButton, BorderLayout.SOUTH);


        // Add the panel to the frame
        add(panel);

        // Show the frame
        setVisible(true);
    }

    private String[][] getFlightsData() {
        String[][] data = null;
    
        try {
            // Establish connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ARS", "root", "prakruthirai@02");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM flights");
    
            // Get the number of columns in the result set
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numCols = metaData.getColumnCount();
    
            // Get the number of rows in the result set
            resultSet.last();
            int numRows = resultSet.getRow();
    
            // Move the result set cursor back to the beginning
            resultSet.beforeFirst();
    
            // Initialize the data array with the correct dimensions
            data = new String[numRows][numCols];
    
            // Populate the data array with the flights data
            int row = 0;
            while (resultSet.next()) {
                for (int col = 0; col < numCols; col++) {
                    data[row][col] = resultSet.getString(col + 1);
                }
                row++;
            }
    
            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    
        return data;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewFlightsPage();
            }
        });
    }
}
