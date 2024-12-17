/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class F9 extends JFrame {

    private JLabel labelMessage;
    private JButton buttonNext;

    public F9() {
        // Set JFrame properties
        setTitle("Passenger Details Confirmation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize components
        labelMessage = new JLabel("Details of all passengers have been taken.", SwingConstants.CENTER);
        labelMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        buttonNext = new JButton("Next");

        // Add components to JFrame
        add(labelMessage, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonNext);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button action
        buttonNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close F9
                openF7(); // Open JFrame F7
            }
        });
    }

    // Method to open JFrame F7 (stub implementation)
    private void openF7() {
        F7 f7 = new F7(); // Replace with actual implementation of F7
        f7.setVisible(true);
    }

    // Setter for labelMessage to dynamically set the passenger count
    public void setLabelMessage(String message) {
        labelMessage.setText(message);
    }

    // Getter for the Next button (if needed in main class)
    public JButton getButtonNext() {
        return buttonNext;
    }

    public static void main(String[] args) {
        // Test the JFrame
        F9 f9 = new F9();
        f9.setLabelMessage("Details of all 5 passengers have been taken."); // Example message
        f9.setVisible(true);
    }
}
