import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser; // Assuming you're using JDateChooser for date picking

public class FlightSearch {
    // JDBC URL, username, and password of the database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Flights";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mori@2020";

    public static ArrayList<String> getFlightNumbers(JComboBox<String> fromLoc, JComboBox<String> toLoc, JDateChooser departureDate) {
        ArrayList<String> flightNumbers = new ArrayList<>();
        String query = "SELECT flight_number FROM flights_info WHERE Departure_location = ? AND Arrival_location = ? AND Departure_day = ?";

        // Format the date to match the database format (e.g., yyyy-MM-dd)
        java.util.Date date = departureDate.getDate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, fromLoc.getSelectedItem().toString());
            preparedStatement.setString(2, toLoc.getSelectedItem().toString());
            preparedStatement.setDate(3, sqlDate);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Extract data from the result set and add to the ArrayList
            while (resultSet.next()) {
                flightNumbers.add(resultSet.getString("flight_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exception
        }

        return flightNumbers;
    }

    public static void main(String[] args) {
        // Example usage
        JComboBox<String> fromLoc = new JComboBox<>(new String[]{"New York", "Los Angeles", "Chicago"});
        JComboBox<String> toLoc = new JComboBox<>(new String[]{"Miami", "Houston", "Seattle"});
        JDateChooser departureDate = new JDateChooser();

        // Simulate user selection
        fromLoc.setSelectedItem("New York");
        toLoc.setSelectedItem("Miami");
        departureDate.setDate(new java.util.Date()); // Set today's date for testing

        ArrayList<String> flightNumbers = getFlightNumbers(fromLoc, toLoc, departureDate);
        System.out.println("Available flight numbers: " + flightNumbers);
    }
}