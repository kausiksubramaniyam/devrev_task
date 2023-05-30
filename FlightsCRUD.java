import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

