import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightsCRUD {
    private Connection connection;

    public FlightsCRUD() {
        try {
            connection = DBConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFlight(Flights flight) {
        try {
            String query = "INSERT INTO Flights (flightNumber, flightModel, airlineName, fromDestination, toDestination, nextDeparture, seatCount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, flight.getFlightNumber());
            statement.setString(2, flight.getFlightModel());
            statement.setString(3, flight.getAirlineName());
            statement.setString(4, flight.getFromDestination());
            statement.setString(5, flight.getToDestination());
            statement.setObject(6, flight.getNextDeparture());
            statement.setInt(7, flight.getSeatCount());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Flights getFlightById(int flightNumber) {
        try {
            String query = "SELECT * FROM Flights WHERE flightNumber = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, flightNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createFlightFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Flights> getAllFlights() {
        List<Flights> flights = new ArrayList<>();
        try {
            String query = "SELECT * FROM Flights";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flights.add(createFlightFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public void searchFlightsByDate(LocalDateTime searchdate) throws SQLException {
        String query = "SELECT * FROM Flights WHERE nextDeparture = ?";
        PreparedStatement stmt = connection.prepareStatement(query);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = searchdate.format(formatter);

        stmt.setString(1, formattedDateTime);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int flightNumber = rs.getInt("flightNumber");
            String flightModel = rs.getString("flightModel");
            String airlineName = rs.getString("airlineName");
            String fromDestination = rs.getString("fromDestination");
            String toDestination = rs.getString("toDestination");
            LocalDateTime nextDeparture = rs.getTimestamp("nextDeparture").toLocalDateTime();
            int seatCount = rs.getInt("seatCount");

            System.out.println("Flight Number: " + flightNumber);
            System.out.println("Flight Model: " + flightModel);
            System.out.println("Airline Name: " + airlineName);
            System.out.println("From Destination: " + fromDestination);
            System.out.println("To Destination: " + toDestination);
            System.out.println("Next Departure: " + nextDeparture);
            System.out.println("Seat Count: " + seatCount);
            System.out.println("-----------------------------");
        }
    }

    public void searchFlightsBetweenStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        try {
            String sql = "SELECT * FROM Flights WHERE nextDeparture >= ? AND nextDeparture <= ?";
            PreparedStatement stmt = connection.prepareStatement(sql);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedStartDateTime = startDate.format(formatter);
            String formattedEndDateTime = endDate.format(formatter);

            stmt.setString(1, formattedStartDateTime);
            stmt.setString(2, formattedEndDateTime);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int flightNumber = rs.getInt("flightNumber");
                String flightModel = rs.getString("flightModel");
                String airlineName = rs.getString("airlineName");
                String fromDestination = rs.getString("fromDestination");
                String toDestination = rs.getString("toDestination");
                LocalDateTime nextDeparture = rs.getTimestamp("nextDeparture").toLocalDateTime();
                int seatCount = rs.getInt("seatCount");

                System.out.println("Flight Number: " + flightNumber);
                System.out.println("Flight Model: " + flightModel);
                System.out.println("Airline Name: " + airlineName);
                System.out.println("From Destination: " + fromDestination);
                System.out.println("To Destination: " + toDestination);
                System.out.println("Next Departure: " + nextDeparture);
                System.out.println("Seat Count: " + seatCount);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Flights createFlightFromResultSet(ResultSet resultSet) throws SQLException {
        Flights flight = new Flights();
        flight.setFlightNumber(resultSet.getInt("flightNumber"));
        flight.setFlightModel(resultSet.getString("flightModel"));
        flight.setAirlineName(resultSet.getString("airlineName"));
        flight.setFromDestination(resultSet.getString("fromDestination"));
        flight.setToDestination(resultSet.getString("toDestination"));
        flight.setNextDeparture(resultSet.getObject("nextDeparture", LocalDateTime.class));
        flight.setSeatCount(resultSet.getInt("seatCount"));
        return flight;
    }

}

