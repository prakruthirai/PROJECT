package Booking;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import PaymentModule.PaymentModule;

public class Booking extends JFrame {
    private JLabel nameLabel, emailLabel, seatLabel;
    private JTextField nameTextField, emailTextField, seatTextField;
    private JButton bookButton;

    public Booking() {
        // Set the window title
        setTitle("Booking Module");

        // Create the labels
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email:");
        // flightLabel = new JLabel("Flight:");
        seatLabel = new JLabel("Seat:");
        // paymentLabel = new JLabel("Payment:");

        // Create the text fields
        nameTextField = new JTextField(20);
        emailTextField = new JTextField(20);
        // flightTextField = new JTextField(20);
        seatTextField = new JTextField(20);
        // paymentTextField = new JTextField(20);

        // Create the button
        bookButton = new JButton("Book");

        // Add an ActionListener to the button
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the booking process here
                String name = nameTextField.getText();
                String email = emailTextField.getText();
                // String flight = flightTextField.getText();
                String seat = seatTextField.getText();
                // String payment = paymentTextField.getText();

                // Perform validation here and handle any errors
                // ...

                // If validation passes, proceed with booking process
                // ...

                // Display a confirmation message to the user
                JOptionPane.showMessageDialog(Booking.this, "Booking successful!");
            }
        });

        // Set the layout
        setLayout(new GridLayout(6, 2));

        // Add the components to the container
        add(nameLabel);
        add(nameTextField);
        add(emailLabel);
        add(emailTextField);
        // add(flightLabel);
        // add(flightTextField);
        add(seatLabel);
        add(seatTextField);
        // add(paymentLabel);
        // add(paymentTextField);
        add(new JLabel());
        add(bookButton);

        // Set the window size and make it visible
        setSize(400, 250);
        setVisible(true);

        
        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle book button action
                // Redirect to the payment module page or perform necessary actions
                
                // Create an instance of the PaymentModulePage
                PaymentModule paymentPage = new PaymentModule();
                paymentPage.setVisible(true);
                
                // Close the current BookingPage
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        Booking bookingModule = new Booking();
        bookingModule.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Component getBookingPanel() {
        return null;
    }
}
