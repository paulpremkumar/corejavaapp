import java.util.List;
import java.sql.*;
abstract class DatabaseOperationManager{
    public Connection getDBConnection(String URL,String USER,String PASSWORD)throws SQLException {
      
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
     
    }

    abstract String createUser(Connection conn,String username,String email,String  password);
    abstract String updateUser(Connection conn,String username,String email,String  password,long id);
     abstract String deleteUser(Connection conn,long id);
    public abstract List<UserV1> readUser(Connection conn);

}