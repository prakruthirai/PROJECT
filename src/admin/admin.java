package admin;
import javax.swing.*;

import Flight.Flight;
import ViewFlightsPage.ViewFlightsPage;

import java.awt.event.*;
import mainclass.mainclass;
public class admin extends JFrame implements ActionListener {

    // Components of the admin page
    private JLabel welcomeLabel;
    private JButton addFlightButton;
    private JButton viewFlightsButton;
    private JButton logoutButton;

    // Constructor to set up the admin page
    public admin(String username) {

        // Set the title and size of the admin page
        setTitle("Airline Reservation System - Admin Page");
        setSize(400, 300);

        // Create a welcome label to greet the user
        welcomeLabel = new JLabel("Welcome, " + username );
        welcomeLabel.setBounds(50, 20, 300, 30);
        add(welcomeLabel);

        // Create a button to add a new flight
        addFlightButton = new JButton("Add Flight");
        addFlightButton.setBounds(100, 80, 200, 30);
        addFlightButton.addActionListener(this);
        add(addFlightButton);

        // Create a button to view all flights
        viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.setBounds(100, 120, 200, 30);
        viewFlightsButton.addActionListener(this);
        add(viewFlightsButton);

        // Create a button to logout
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 200, 100, 30);
        logoutButton.addActionListener(this);
        add(logoutButton);

        // Set the layout and visibility of the admin page
        setLayout(null);
        setVisible(true);
    }

    // ActionListener to handle button clicks
    public void actionPerformed(ActionEvent e) {

        // Handle addFlightButton clicks
        if (e.getSource() == addFlightButton) {
            new Flight();
            
        }

        // Handle viewFlightsButton clicks
        if (e.getSource() == viewFlightsButton) {
            new ViewFlightsPage();
            
        }

        // Handle logoutButton clicks
        
        if (e.getSource() == logoutButton) {
            // setVisible(true);
            admin obj= new admin(getTitle());
            obj.setVisible(true);
            dispose(); 
            new mainclass();
        }}
        public static void main(String[] args) {
            new admin("Jero");
    }
}
