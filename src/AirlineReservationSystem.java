
import javax.swing.*;
import java.awt.*;

public class AirlineReservationSystem {

    private JFrame frame;

    public AirlineReservationSystem() {
        // Create the main window
        frame = new JFrame("Airline Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the modules
        AuthenticationModule authenticationModule = new AuthenticationModule();
        FlightManagementModule flightManagementModule = new FlightManagementModule(this);
        App userInterfaceModule = new App();

        // Add the modules to the main window
        frame.setLayout(new BorderLayout());
        frame.add(userInterfaceModule, BorderLayout.CENTER);

        // Show the main window
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of the AirlineReservationSystem class
        AirlineReservationSystem system = new AirlineReservationSystem();
    }
}

