package PaymentModule;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentModule extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JLabel cardNumberLabel;
    private JTextField cardNumberTextField;
    private JLabel cvvLabel;
    private JPasswordField cvvPasswordField;
    private JLabel expiryDateLabel;
    private JTextField expiryDateTextField;
    private JButton payButton;

    public PaymentModule() {
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Payment Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountLabel = new JLabel("Amount:");
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        amountTextField = new JTextField(20);
        amountTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardNumberLabel = new JLabel("Card Number:");
        cardNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cardNumberTextField = new JTextField(20);
        cardNumberTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        cvvLabel = new JLabel("CVV:");
        cvvLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cvvPasswordField = new JPasswordField(20);
        cvvPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        expiryDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        expiryDateTextField = new JTextField(20);
        expiryDateTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        payButton = new JButton("Pay");
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle payment processing here
                JOptionPane.showMessageDialog(PaymentModule.this,
                        "Payment processed successfully!",
                        "Payment Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(amountLabel);
        mainPanel.add(amountTextField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(cardNumberLabel);
        mainPanel.add(cardNumberTextField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(cvvLabel);
        mainPanel.add(cvvPasswordField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(expiryDateLabel);
        mainPanel.add(expiryDateTextField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(payButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        PaymentModule paymentModule = new PaymentModule();
    }
}
