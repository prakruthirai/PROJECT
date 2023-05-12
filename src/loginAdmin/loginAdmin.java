package loginAdmin;
import javax.swing.*;

import admin.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginAdmin extends JFrame {

    private JLabel userLabel, passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public loginAdmin() {
        setTitle("Admin Login");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        userLabel = new JLabel("Username:");
        userField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = String.valueOf(passwordField.getPassword());
                // TODO: Add code to validate username and password
                // and check against the database
            }
        });

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    
    loginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            new admin(null);
        }
    });
}

    public static void main(String[] args) {
        new loginAdmin();
    }
}