import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersCRUD {

    private Connection connection;

    public UsersCRUD() {
        try {
            connection = DBConnector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPassenger(Users user, String role) {
        try {
            String query = "INSERT INTO Users (userId, userName, userAge, userType, aadharId, phoneNumber, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setInt(3, user.getUserAge());
            statement.setInt(4, user.getUserType());
            statement.setInt(5, user.getAadharId());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Users createUserFromResultSet(ResultSet resultSet) throws SQLException {
        Users user = new Users();
        user.setUserId(resultSet.getString("userId"));
        user.setUserName(resultSet.getString("userName"));
        user.setUserAge(resultSet.getInt("userAge"));
        user.setUserType(resultSet.getInt("userType"));
        user.setAadharId(resultSet.getInt("aadharId"));
        user.setPhoneNumber(resultSet.getString("phoneNumber"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}

