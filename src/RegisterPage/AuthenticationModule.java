import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticationModule extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AuthenticationModule() {
        // Set up the main window
        setTitle("Airline Reservation System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 1));

        // Add components to the top panel
        JLabel titleLabel = new JLabel("Airline Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.add(titleLabel);

        // Add the top panel to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Create the center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2));

        // Add components to the center panel
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);

        // Add the center panel to the main panel
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Create the bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 1));

        // Add components to the bottom panel
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform authentication and security checks
                if (authenticateUser(username, password)) {
                    // Redirect to the main application
                    App app = new App();
                    app.setVisible(true);
                    dispose();
                } else {
                    // Show error message
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
            }
        });
        bottomPanel.add(loginButton);

        // Add the bottom panel to the main panel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Show the window
        setVisible(true);
    }

    private boolean authenticateUser(String username, String password) {
        // TODO: Implement authentication and security checks
        // This method should validate the username and password against a database or other source
        // of authentication information, and return true if the user is authorized to access the system.
        return true;
    }

    public static void main(String[] args) {
        AuthenticationModule auth = new AuthenticationModule();
    }
}
