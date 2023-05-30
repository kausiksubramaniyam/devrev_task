import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsCRUD {
    private Connection connection;

    public TicketsCRUD() {
        try {
            connection = DBConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bookTicket(Ticket ticket) {
        try {
            String query = "INSERT INTO Tickets (ticketId, flightId, passengerId, ticketCost, seatNumber) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ticket.getTicketId());
            statement.setInt(2, ticket.getFlightNumber());
            statement.setString(3, ticket.getUserId());
            statement.setInt(4, ticket.getTicketCost());
            statement.setInt(5, ticket.getSeatNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllTicketsBookedByUser(String userId){
        ArrayList<Ticket> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tickets WHERE userId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int ticketId = rs.getInt("ticketId");
                int flightNumber = rs.getInt("flightNumber");
                int ticketCost = rs.getInt("ticketCost");
                int seatNumber = rs.getInt("seatNumber");

                Ticket ticket = new Ticket();
                ticket.createTicket(ticketId, flightNumber, userId, ticketCost, seatNumber);

                bookings.add(ticket);
            }
            for(Ticket t:bookings){
                System.out.println(t.getTicketId());
                System.out.println(t.getSeatNumber());
                System.out.println(t.getFlightNumber());
                System.out.println(t.getTicketCost());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Ticket getTicketById(int ticketId) {
        try {
            String query = "SELECT * FROM Tickets WHERE ticketID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ticketId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createTicketFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try {
            String query = "SELECT * FROM Tickets";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tickets.add(createTicketFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    private Ticket createTicketFromResultSet(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTicketId(resultSet.getInt("ticketId"));
        ticket.setFlightNumber(resultSet.getInt("flightId"));
        ticket.setUserId(resultSet.getString("passengerId"));
        ticket.setTicketCost(resultSet.getInt("ticketCost"));
        ticket.setSeatNumber(resultSet.getInt("seatNumber"));
        return ticket;
    }
}

