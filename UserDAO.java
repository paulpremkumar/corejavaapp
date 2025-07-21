import java.sql.*;
import java.util.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/attendance?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                users.add(new User(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public OperationResult addUser(User user) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.username);
            ps.setString(2, user.email);
            ps.setString(3, user.password);
             int rows = ps.executeUpdate();

        if (rows > 0) {
            return new OperationResult(true, "User added successfully.");
        } else {
            return new OperationResult(false, "Failed to add user.");
        }
        } catch (SQLIntegrityConstraintViolationException e) {
        // 👇 Check for duplicate email
       if (e.getMessage().contains("Duplicate entry")) {
            return new OperationResult(false, "Email '" + user.email + "' already exists.");
        }
        return new OperationResult(false, "Database constraint violation: " + e.getMessage());
    } catch (SQLException e) {
        e.printStackTrace();
        return new OperationResult(false, "Database error: " + e.getMessage());
    } catch (Exception e) {
            e.printStackTrace();
        return new OperationResult(false, "Unexpected error: " + e.getMessage());
        }
    }

    public boolean deleteUser(Long id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateUser(User user) {
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
        String sql = "UPDATE users SET username=?, email=?, password=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.username);
        ps.setString(2, user.email);
        ps.setString(3, user.password);
        ps.setLong(4, user.id);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}
