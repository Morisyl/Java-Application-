package javaapplication10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class FlightsInfo{
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Flights"; // Replace 'flights' with your DB name
        String user = "root"; // Replace with your username
        String password = "Mori@2020"; // Replace with your password
                   
        try {
            // Load the MySQL JDBC Driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            System.out.println("Connected to the database!");

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
    public ArrayList<String> getDepartureLocation() {
        String url = "jdbc:mysql://localhost:3306/Flights"; // Replace 'flights' with your DB name
        String user = "root"; // Replace with your username
        String password = "Mori@2020"; // Replace with your password
    
    ArrayList<String> departureLocations = new ArrayList<>(); // Correctly initialize the ArrayList

    try (Connection connection = DriverManager.getConnection(url, user, password);
         Statement statement = connection.createStatement()) {

        // Query to fetch departure location
        String query = "SELECT DISTINCT Departure_location FROM flights_info";

        // Execute the query
        ResultSet resultSet = statement.executeQuery(query);

        // Iterate through the result set and add each location to the ArrayList
        while (resultSet.next()) {
            String location = resultSet.getString("Departure_location");
            departureLocations.add(location);
        }

    } catch (SQLException e) {
        System.out.println("Error while accessing the database!");
        e.printStackTrace();
    }
    
    return departureLocations; // Return the populated ArrayList
}
    
    public ArrayList<String> getArrivalLocation() {
    String url = "jdbc:mysql://localhost:3306/Flights"; // Replace 'flights' with your DB name
        String user = "root"; // Replace with your username
        String password = "Mori@2020"; // Replace with your password

    ArrayList<String> arrivalLocations = new ArrayList<>(); // Correctly initialize the ArrayList

    try (Connection connection = DriverManager.getConnection(url, user, password);
         Statement statement = connection.createStatement()) {

        // Query to fetch departure location
        String query = "SELECT DISTINCT Arrival_location FROM flights_info";

        // Execute the query
        ResultSet resultSet = statement.executeQuery(query);

        // Iterate through the result set and add each location to the ArrayList
        while (resultSet.next()) {
            String location = resultSet.getString("Arrival_location");
            arrivalLocations.add(location);
        }

    } catch (SQLException e) {
        System.out.println("Error while accessing the database!");
        e.printStackTrace();
    }
    
    return arrivalLocations; // Return the populated ArrayList
}
}
