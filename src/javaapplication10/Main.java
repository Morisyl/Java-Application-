package javaapplication10;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main {
    private static String tripType; // To store the selected radio button value
    private static String fromLocation, toLocation, FlightNum;
    private static java.util.Date departureDate;
    private static String selectedFlight;
    private static int passengerCount;
    private static String selectedClass;
    private int currentPassenger = 1;
    static Integer numPass = 3;
    private static String bkid;
    String classType;

    public static void main(String[] args) {
        // Step 1: Show JFrame Login
        Login loginFrame = new Login(); 
        loginFrame.setVisible(true);

        // Step 2: On closing Login, show F1
        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                F1 f1Frame = new F1();
                f1Frame.setVisible(true);
                
            }
        });
    }
            public  static void handleF1Actions() {
                Login log= new Login();
                log.setVisible(false);
                F1 f1Frame = new F1();
                f1Frame.setVisible(true);
                
                bkid = log.getTextFieldValue2();
                Main.bkid= bkid;
            
                f1Frame.setVisible(true);
            }
            
             public static String getMybkid() {
        return bkid;
    }

            
    public  static void handleF1Actions1() {
        // Collect trip type on button click
        F1 f1Frame = new F1();
        
        f1Frame.setVisible(false);
            // Step 3: Collect tripType from ButtonGroup
            String tripType= f1Frame.getSelectedButtonText();
            
            
            

            // Step 4: Open F2
            F2 f2Frame = new F2();
            f2Frame.setVisible(true);
            
        
    }

    public static void handleF2Actions() {
        F2 f2Frame = new F2();
        f2Frame.setVisible(false);
            // Step 5: Collect data from F2
            fromLocation = (String) f2Frame.getSelectedItem();
            toLocation = (String) f2Frame.getSelectedItem2();
            departureDate = Date.valueOf("2024-12-24"); ;

            // Step 6: Query database to get flight numbers
            ArrayList<String> flightNumbers = getFlightNumbers(fromLocation, toLocation, departureDate);

            // Step 7: Open F8 and display flight numbers
            F8Frame f8Frame = new F8Frame(flightNumbers);
            f8Frame.setVisible(true);

            // Step 8: Add button on F8 to open F3
            f8Frame.getOpenF3Button().addActionListener(event -> {
                FlightNum = (String) f8Frame.getSelectedItem();
                F3 f3Frame = new F3();
                f3Frame.setVisible(true);
            });
        
    }
    
            public void handleF3Actions() {
                F3 f3Frame = new F3();
                classType= f3Frame.getSelectedButtonText();
                this.classType = classType;
                numPass= f3Frame.getSelectedItem();
                Main.numPass = numPass;
                runPassengerLoop();
                
                f3Frame.dispose();
                
              
            
                
            
            }
            
            public Integer getData(){
        return numPass;}
            public String getData2(){
        return classType;}
            
            private void runPassengerLoop() {
              
        for (currentPassenger = 1; currentPassenger <= 3; currentPassenger++){ 
            try {
        F6 f6 = new F6();
        f6.setVisible(true);
        f6.getOpenF3Button().addActionListener(e -> {
            String name = f6.getTextFieldValue();
            String lastName = f6.getTextFieldValue1();
            String passportNumber = f6.getTextFieldValue2();
            String gender= f6.getSelectedButtonText();
            
             
            
            savePassengerToDatabase(name, lastName, passportNumber, gender);
            f6.dispose();
        });
        
    } catch (Exception e) {
        System.err.println("Error in openF6(): " + e.getMessage());
        e.printStackTrace();}
}
        
    }
            
            private void openF6() {
        F6 f6 = new F6();
        f6.setVisible(true);

        f6.getOpenF3Button().addActionListener(e -> {
            String name = f6.getTextFieldValue();
            String lastName = f6.getTextFieldValue1();
            String passportNumber = f6.getTextFieldValue2();
            String gender= f6.getSelectedButtonText();
            
            savePassengerToDatabase(name, lastName, passportNumber, gender);
            f6.dispose();
        });
    }
            
            public void openF9() {
                
        F9 f9 = new F9();
        f9.setVisible(true);
        

        f9.getButtonNext().addActionListener(e -> {
            f9.dispose();
            openF7();
        });
    }
            
            private static void openF7() {
        F7 f7 = new F7();
        f7.setVisible(true);
    }
            
            private static String getSelectedRadioButton(ButtonGroup group) {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
            
            private void savePassengerToDatabase(String name, String lastName, String passportNumber, String gender) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Flights", "root", "Mori@2020")) {
            String sql = "INSERT INTO passenger (Name,Passport,Gender, lastName,BookingsID ,flightNo, flightClass) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, passportNumber);
            statement.setString(4, gender);
            statement.setString(5, bkid);
            statement.setString(6, selectedFlight);
            statement.setString(7, selectedClass);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<String> getFlightNumbers(String fromLoc, String toLoc, java.util.Date depDate) {
        ArrayList<String> flightNumbers = new ArrayList<>();
        try {
            // Database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Flights", "root", "Mori@2020");
            String query = "SELECT Flight_number FROM flights_info WHERE Departure_location = ? AND Arrival_location = ? AND Departure_day = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, fromLoc);
            stmt.setString(2, toLoc);
            stmt.setDate(3, new java.sql.Date(depDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                flightNumbers.add(rs.getString("Flight_number"));
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flightNumbers;
    }
}