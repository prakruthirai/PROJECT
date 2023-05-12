package RegisterPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Loginpage.Loginpage;

public class RegisterPage extends JFrame {

    private JLabel usernameLabel, passwordLabel, emailLabel;
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterPage() {
        setTitle("User Registration");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel registerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String email = emailField.getText();
                // Perform user registration logic
                if (registerUser(username, password, email)) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration successful!");
                    // Open the login page
                    new Loginpage();
                    setVisible(false);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Registration failed!");
                }
            }
        });

        registerPanel.add(usernameLabel);
        registerPanel.add(usernameField);
        registerPanel.add(passwordLabel);
        registerPanel.add(passwordField);
        registerPanel.add(emailLabel);
        registerPanel.add(emailField);
        registerPanel.add(registerButton);

        add(registerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private boolean registerUser(String username, String password, String email) {
        try {
            // Perform user registration logic
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ARS", "root", "prakruthirai@02");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO register (username, password, email) VALUES (?, ?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            int rowsAffected = preparedStatement.executeUpdate();
            connection.close();
            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegisterPage();
            }
        });
    }
}
