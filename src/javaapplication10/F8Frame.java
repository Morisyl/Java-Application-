/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class F8Frame extends JFrame {
    private JComboBox<String> flightNumberComboBox;
    private JButton openF3Button;

    public F8Frame(ArrayList<String> flightNumbers) {
        // Frame Configuration
        setTitle("Available Flights");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel to hold components
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        add(panel, BorderLayout.CENTER);

        // Label
        JLabel flightNumberLabel = new JLabel("Select Flight Number:");
        flightNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(flightNumberLabel);

        // ComboBox for flight numbers
        flightNumberComboBox = new JComboBox<>();
        for (String flightNumber : flightNumbers) {
            flightNumberComboBox.addItem(flightNumber);
        }
        panel.add(flightNumberComboBox);

        // Button to open F3
        openF3Button = new JButton("Open F3");
        panel.add(openF3Button);
    }

    
     public  String getSelectedItem() {
        Object selectedItem = flightNumberComboBox.getSelectedItem();
        return selectedItem.toString();
     }
    
    public JButton getOpenF3Button() {
        return openF3Button;
    }
}
