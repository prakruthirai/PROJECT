package Loginpage;

import javax.swing.*;

import RegisterPage.RegisterPage;
import App.App;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Loginpage extends JFrame {

    private JLabel userLabel, passwordLabel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Loginpage() {
        setTitle("Login");
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
                // Validate the login credentials
                if (validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(Loginpage.this, "Login successful!");
                    // Open the app page
                    new App();
                    setVisible(false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Loginpage.this, "Invalid username or password!");
                    // Open the register page
                    new RegisterPage();
                    setVisible(false);
                    dispose();
                }
            }
        });

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private boolean validateLogin(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ARS", "root", "prakruthirai@02");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM register WHERE username=?");

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set contains any rows
            if (resultSet.next()) {
                String dbUsername = resultSet.getString("username");
                String dbPassword = resultSet.getString("password");

                // Compare the retrieved username and password with the entered credentials
                if (dbUsername.equals(username) && dbPassword.equals(password)) {
                    connection.close();
                    return true;
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        return false;
    }

    public static void main(String[] args) {
        new Loginpage();
    }
}
