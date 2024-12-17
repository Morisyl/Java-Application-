package javaapplication10;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton SignUpButton;
    private static final String db_url = "jdbc:mysql://localhost:3306/signup";
    private static final String db_username = "root";
    private static final String db_password = "Mori@2020";
 
    public Login() {
        // Set up the JFrame
        setTitle("Login Page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");

        SignUpButton = new JButton("Sign Up");
        // Layout the components
        setLayout(null);
        usernameLabel.setBounds(50, 30, 100, 25);
        usernameField.setBounds(150, 30, 200, 25);
        passwordLabel.setBounds(50, 70, 100, 25);
        passwordField.setBounds(150, 70, 200, 25);
        loginButton.setBounds(150, 110, 100, 25);
        SignUpButton.setBounds(250, 110, 100, 25);
        
        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(SignUpButton);
        
        // Add action listener to the login button
        loginButton.addActionListener((ActionEvent e) -> {
            // Validate login credentials
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (validateLogin(username, password)) {
                // Login is successful, open the next page
                JOptionPane.showMessageDialog(null, "Login Successful!");
                
                Main main = new Main();
                
                main.handleF1Actions();  // Open next page
                 // Close the login page
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        });
        
        SignUpButton.addActionListener((ActionEvent e) -> {
            //Collect user credentials
            dispose();
            new Signup().setVisible(true);
        });
    }

    
    public String getTextFieldValue2() {
    if (usernameField != null) {
        return usernameField.getText().trim(); // Returns trimmed text to remove leading/trailing spaces
    } else {
        return null; // Handles cases where textField is null
}}
    // Method to validate login credentials against the database
    private boolean validateLogin(String username, String password) {
        try (Connection conn = DriverManager.getConnection(db_url, db_username, db_password)){
            String query = "SELECT * FROM users WHERE name = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // User found in the database
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());

        }
        return false;
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
}}