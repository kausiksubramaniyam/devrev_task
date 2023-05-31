import java.sql.*;

public class LoginModule {
    public Integer login(String userName, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnector.getConnection();

            String sql = "SELECT userType FROM Users WHERE userId = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                Integer userType = rs.getInt("userType");
                return userType;
            } else {
                System.out.println("Invalid username or password. Please Try Again or Create a NEW ACCOUNT.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
