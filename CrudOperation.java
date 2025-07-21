import java.sql.*;

public class CrudOperation {

    private static final String URL = "jdbc:mysql://localhost:3306/attendance?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Connected to the database!");

            // Create a user
            createUser(conn, "Alice", "alice@example.com", "secret123");

            // Read users
            readUsers(conn);

            // Update user
            updateUserEmail(conn, 1L, "alice.new@example.com");

            // Delete user
            deleteUser(conn, 1L);

        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
    }

    // CREATE
    public static void createUser(Connection conn, String username, String email, String password) throws SQLException {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
            System.out.println("✅ User created!");
        }
    }

    // READ
    public static void readUsers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("📄 Users in database:");
            while (rs.next()) {
                System.out.printf("ID: %d, Username: %s, Email: %s%n",
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"));
            }
        }
    }

    // UPDATE
    public static void updateUserEmail(Connection conn, long id, String newEmail) throws SQLException {
        String sql = "UPDATE users SET email = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newEmail);
            stmt.setLong(2, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Email updated!" : "⚠️ No user found.");
        }
    }

    // DELETE
    public static void deleteUser(Connection conn, long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "🗑️ User deleted!" : "⚠️ No user found.");
        }
    }
}
