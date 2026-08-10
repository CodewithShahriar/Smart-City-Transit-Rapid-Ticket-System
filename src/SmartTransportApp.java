import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// --- 1. Encapsulation: Smart Card with PIN & Account Security ---
class SmartCard {
    private String cardId;
    private double balance;
    private String pin;

    public SmartCard(String cardId, double initialBalance, String pin) {
        this.cardId = cardId;
        this.balance = initialBalance;
        this.pin = pin;
    }

    public String getCardId() { return cardId; }
    public double getBalance() { return balance; }

    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public boolean recharge(double amount, String inputPin) {
        if (verifyPin(inputPin) && amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean deduct(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}

// --- 2. Real-World Route & Station Logic ---
class StationNetwork {
    private static final Map<String, Integer> metroStations = new HashMap<>();

    static {
        
        metroStations.put("Uttara North", 0);
        metroStations.put("Uttara Center", 2);
        metroStations.put("Pallabi", 5);
        metroStations.put("Mirpur 10", 8);
        metroStations.put("Agargaon", 12);
        metroStations.put("Farmgate", 15);
        metroStations.put("Motijheel", 20);
    }

    public static String[] getStations() {
        return metroStations.keySet().toArray(new String[0]);
    }

    public static double calculateDistance(String start, String end) {
        if (metroStations.containsKey(start) && metroStations.containsKey(end)) {
            return Math.abs(metroStations.get(start) - metroStations.get(end));
        }
        return 0;
    }
}

// --- 3. Abstraction & Inheritance ---
abstract class Vehicle {
    private String type;
    public Vehicle(String type) { this.type = type; }
    public String getType() { return type; }
    public abstract double calculateFare(double distance);
}

class Bus extends Vehicle {
    public Bus() { super("AC City Bus"); }
    @Override
    public double calculateFare(double distance) {
        return Math.max(20.0, distance * 5.0); 
    }
}

class MetroRail extends Vehicle {
    public MetroRail() { super("Rapid Metro Rail"); }
    @Override
    public double calculateFare(double distance) {
        return 20.0 + (distance * 3.5); 
    }
}

// --- 4. Realistic Visual GUI ---
public class SmartTransportApp extends JFrame {
    private SmartCard userCard = new SmartCard("MRT-883921", 150.0, "1234"); 

    private JLabel balanceLabel, cardIdLabel;
    private JComboBox<String> startStationBox, endStationBox, transportBox;
    private JTextArea outputArea;

    public SmartTransportApp() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        setTitle("Smart City Transit System - Real World Network");
        setSize(520, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Color primaryColor = new Color(26, 37, 48);
        Color accentColor = new Color(41, 128, 185);
        Color successColor = new Color(39, 174, 96);

        // --- TOP PANEL: Digital Metro Pass ---
        JPanel cardPanel = new JPanel(new GridLayout(3, 1, 3, 3));
        cardPanel.setBackground(primaryColor);
        cardPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("METRO PASS & RAPID TRANSIT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);

        cardIdLabel = new JLabel("Card ID: " + userCard.getCardId(), SwingConstants.CENTER);
        cardIdLabel.setForeground(new Color(189, 195, 199));

        balanceLabel = new JLabel("Available Balance: ৳" + String.format("%.2f", userCard.getBalance()), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        balanceLabel.setForeground(new Color(46, 204, 113));

        cardPanel.add(titleLabel);
        cardPanel.add(cardIdLabel);
        cardPanel.add(balanceLabel);

        // --- CENTER PANEL: Route & Booking Inputs ---
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 12));
        formPanel.setBorder(new EmptyBorder(10, 25, 10, 25));

        String[] stations = StationNetwork.getStations();
        startStationBox = new JComboBox<>(stations);
        endStationBox = new JComboBox<>(stations);
        endStationBox.setSelectedIndex(4); // Default to Agargaon

        String[] vehicles = {"Rapid Metro Rail", "AC City Bus"};
        transportBox = new JComboBox<>(vehicles);

        JButton buyTicketBtn = new JButton("Book Journey");
        buyTicketBtn.setBackground(accentColor);
        buyTicketBtn.setForeground(Color.WHITE);
        buyTicketBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JButton rechargeBtn = new JButton("Recharge Pass");
        rechargeBtn.setBackground(successColor);
        rechargeBtn.setForeground(Color.WHITE);
        rechargeBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        formPanel.add(new JLabel("Transport Mode:"));
        formPanel.add(transportBox);
        formPanel.add(new JLabel("From Station:"));
        formPanel.add(startStationBox);
        formPanel.add(new JLabel("To Station:"));
        formPanel.add(endStationBox);
        formPanel.add(buyTicketBtn);
        formPanel.add(rechargeBtn);

        // --- BOTTOM PANEL: Ticket & Status Output ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 25, 15, 25));

        outputArea = new JTextArea(9, 30);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        outputArea.setText("--- Select Stations & Click 'Book Journey' ---");

        bottomPanel.add(new JLabel("Live Terminal Output:"), BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(cardPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---
        buyTicketBtn.addActionListener(e -> processJourney());
        rechargeBtn.addActionListener(e -> processRecharge());
    }

    private void processJourney() {
        String from = (String) startStationBox.getSelectedItem();
        String to = (String) endStationBox.getSelectedItem();

        if (from.equals(to)) {
            outputArea.setText("\n  [!] Invalid Journey: Origin and Destination cannot be the same.");
            return;
        }

        double distance = StationNetwork.calculateDistance(from, to);
        String selectedVehicle = (String) transportBox.getSelectedItem();
        Vehicle vehicle = selectedVehicle.contains("Metro") ? new MetroRail() : new Bus();
        
        double fare = vehicle.calculateFare(distance);

        if (userCard.deduct(fare)) {
            updateBalance();
            long ticketId = (long) (Math.random() * 90000000L) + 10000000L;
            outputArea.setText(
                "===========================================\n" +
                "         TRANSIT PASS DIGITAL TICKET       \n" +
                "===========================================\n" +
                " Ticket ID  : #" + ticketId + "\n" +
                " Service    : " + vehicle.getType() + "\n" +
                " Route      : " + from + " -> " + to + "\n" +
                " Distance   : " + distance + " KM\n" +
                " Total Fare : ৳" + String.format("%.2f", fare) + "\n" +
                " Gate Status: GATE OPEN [Gate 02]\n" +
                "==========================================="
            );
        } else {
            outputArea.setText(
                "\n  [X] INSUFFICIENT BALANCE!\n" +
                "  Required Fare: ৳" + String.format("%.2f", fare) + "\n" +
                "  Current Balance: ৳" + String.format("%.2f", userCard.getBalance()) + "\n" +
                "  Please recharge your pass."
            );
        }
    }

    private void processRecharge() {
        JTextField amountField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        Object[] message = {
            "Amount (৳):", amountField,
            "Card PIN :", pinField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Recharge Pass", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String pin = new String(pinField.getPassword());

                if (userCard.recharge(amount, pin)) {
                    updateBalance();
                    outputArea.setText("\n  [+] RECHARGE SUCCESSFUL\n  Added: ৳" + amount + "\n  New Balance: ৳" + userCard.getBalance());
                } else {
                    outputArea.setText("\n  [X] RECHARGE FAILED: Invalid PIN or Invalid Amount!");
                }
            } catch (NumberFormatException ex) {
                outputArea.setText("\n  [!] Error: Enter a valid numeric amount.");
            }
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Available Balance: ৳" + String.format("%.2f", userCard.getBalance()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartTransportApp().setVisible(true));
    }
}