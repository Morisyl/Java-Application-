package javaapplication10;

import javax.swing.*;
import java.sql.*;

public class Signup extends JFrame {
    private JLabel label1;
    private JTextField txt1;
    private JLabel label2;
    private JPasswordField pass;
    private JButton button;
    private static final String url = "jdbc:mysql://localhost:3306/signup";
    private static final String username = "root";
    private static final String password = "Mori@2020";

    public Signup() {
        // Setup the frame
        setTitle("Sign Up page");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize components
        label1 = new JLabel("Username");
        txt1 = new JTextField();
        label2 = new JLabel("Password");
        pass = new JPasswordField();
        button = new JButton("Save");

        // Set where on the dialog box they'll appear
        label1.setBounds(50, 30, 100, 25);
        txt1.setBounds(150, 30, 200, 25);
        label2.setBounds(50, 70, 100, 25);
        pass.setBounds(150, 70, 200, 25);
        button.setBounds(150, 110, 100, 25);

        // Add to the page
        add(label1);
        add(txt1);
        add(label2);
        add(pass);
        add(button);

        // Button action to save user
        button.addActionListener(e -> {
            // Retrieve the username and password
            String username = txt1.getText();
            String password = new String(pass.getPassword());
            
            // Save user to the database
            saveUser(username, password);
            
            //Open the first frame
            dispose();
            new F1().setVisible(true);
        });

        // Make the frame visible
        setLayout(null);
        setVisible(true);
    }

    public static void saveUser(String name, String password) {
        // Query to insert the data into the database
        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";

        // Establish connection
        try (Connection connection = DriverManager.getConnection(url, Signup.username, Signup.password)) {
            // This allows us to safely execute the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);  // Set username
            preparedStatement.setString(2, password);  // Set password

            // Execute the query
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error while saving user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Initialize the SignUp form
        new Signup();
}
}