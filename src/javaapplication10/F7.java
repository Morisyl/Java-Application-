/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;


import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class F7 extends JFrame {
    private JTextArea displayArea; // To display booking details
    private JButton confirmButton; // Confirm button
    private double totalPrice; // Total price passed to this frame
    Integer ECONOMY_PRICE = 5000;
    Integer BUSINESS_PRICE = 8000;
    
    // Constructor
    public F7() {
        this.totalPrice = totalPrice; // Set total price
        initComponents(); // Initialize GUI components
    }
    
    private double calculateTotalPrice() {
    try {
        Main main = new Main();
        // Get the number of passengers from the input field
        int passengers = main.getData();
        
        // Get the class type (Economy or Business) from the combo box
        String classType = (String) main.getData2();
        
        // Determine price per passenger based on class type
        double pricePerPassenger = classType.equals("Economy") ? ECONOMY_PRICE : BUSINESS_PRICE;
        
        // Multiply price per passenger by the number of passengers
        double totalPrice = passengers * pricePerPassenger;
        
        return totalPrice; // Return the total calculated price
        
    } catch (NumberFormatException e) {
        // Handle invalid input for the number of passengers
        JOptionPane.showMessageDialog(this, "Please enter a valid number of passengers.", "Error", JOptionPane.ERROR_MESSAGE);
        return 0; // Return 0 if an error occurs
    }
}

    private void initComponents() {
        // Set JFrame properties
        setTitle("Booking Confirmation");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Main main = new Main();
        
        // Create Components
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        totalPrice = main.numPass * BUSINESS_PRICE;
        displayArea.setText("Total Flight Price: $" + totalPrice + "\n\nClick 'Confirm' to finalize booking.");
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        confirmButton = new JButton("Confirm Booking");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                confirmBooking();
            }
        });

        // Layout Setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollPane);
        panel.add(confirmButton);
        
        // Add panel to frame
        add(panel);
    }

    // Method to confirm booking and fetch details from the database
    private void confirmBooking() {
        try {
            // Database connection setup
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/flights", "root", "Mori@2020");
            Login log= new Login();
            // SQL Query to retrieve booking details
            String sql = "SELECT * FROM bookings WHERE booking_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, log.getTextFieldValue2()); // Use logged-in username as the booking ID
            
            ResultSet rs = pstmt.executeQuery();
            
            // Display booking details
            displayArea.setText("Booking Confirmed!\n\nBooking Details:\n");
            while (rs.next()) {
                displayArea.append("Booking ID: " + rs.getString("booking_id") + "\n");
                displayArea.append("Passengers: " + rs.getInt("passengers") + "\n");
                displayArea.append("Class Type: " + rs.getString("class_type") + "\n");
                displayArea.append("Total Price: $" + rs.getDouble("total_price") + "\n");
            }
            
            // Cleanup
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new F7().setVisible(true); // Example: Show with a sample total price
        });
    }
}
