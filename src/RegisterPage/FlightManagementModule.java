import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class FlightManagementModule extends JPanel {


    private AirlineReservationSystem system;
    private ArrayList<Flight> flights;

    private JTable flightTable;
    private DefaultTableModel tableModel;
    private JButton addFlightButton;
    private JButton editFlightButton;
    private JButton deleteFlightButton;

    public FlightManagementModule(AirlineReservationSystem system) {
        this.system = system;
        flights = new ArrayList<>();

        // Set up the UI elements
        setLayout(new BorderLayout());

        // Set up the table model
        String[] columnNames = {"Flight Number", "Origin", "Destination", "Departure Time", "Arrival Time", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        flightTable = new JTable(tableModel);
        flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set up the scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(flightTable);
        add(scrollPane, BorderLayout.CENTER);

        // Set up the button panel
        JPanel buttonPanel = new JPanel();
        addFlightButton = new JButton("Add Flight");
        editFlightButton = new JButton("Edit Flight");
        deleteFlightButton = new JButton("Delete Flight");
        buttonPanel.add(addFlightButton);
        buttonPanel.add(editFlightButton);
        buttonPanel.add(deleteFlightButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addFlightButton.addActionListener(e -> addFlight());
        editFlightButton.addActionListener(e -> editFlight());
        deleteFlightButton.addActionListener(e -> deleteFlight());
    }

    public void addFlight() {
        Flight flight = new Flight();
        FlightDialog dialog = new FlightDialog(parent, flight);

        if (dialog.showDialog() == JOptionPane.OK_OPTION) {
            flights.add(flight);
            updateTable();
        }
    }

    public void editFlight() {
        int selectedIndex = flightTable.getSelectedRow();

        if (selectedIndex >= 0) {
            Flight flight = flights.get(selectedIndex);
            FlightDialog dialog = new FlightDialog(parent, flight);

            if (dialog.showDialog() == JOptionPane.OK_OPTION) {
                updateTable();
            }
        }
    }

    public void deleteFlight() {
        int selectedIndex = flightTable.getSelectedRow();

        if (selectedIndex >= 0) {
            flights.remove(selectedIndex);
            updateTable();
        }
    }

    public void updateTable() {
        // Clear the table model
        tableModel.setRowCount(0);

        // Add the flights to the table model
        for (Flight flight : flights) {
            Object[] rowData = {flight.getFlightNumber(), flight.getOrigin(), flight.getDestination(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getPrice()};
            tableModel.addRow(rowData);
        }

        // Repaint and revalidate the table
        flightTable.revalidate();
        flightTable.repaint();
    }

    public Component getFlightManagementPanel() {
        return null;
    }
}
