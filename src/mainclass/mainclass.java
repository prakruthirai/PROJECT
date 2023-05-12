package mainclass;
import Loginpage.Loginpage;
import RegisterPage.RegisterPage;
import loginAdmin.loginAdmin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainclass {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JPanel controlPanel;

    public mainclass(){
        prepareGUI();
    }

    public static void main(String[] args){
        mainclass mainApp = new mainclass();
        mainApp.showMainPage();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Airline Reservation System");
        mainFrame.setSize(500,500);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    private void showMainPage(){
        headerLabel.setText("Welcome to Airline Reservation System");

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        JButton adminButton = new JButton("Admin");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Loginpage();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new loginAdmin();
            }
        });

        controlPanel.add(loginButton);
        controlPanel.add(registerButton);
        controlPanel.add(adminButton);

        mainFrame.setVisible(true);
    }
}
