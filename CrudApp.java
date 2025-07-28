import java.util.ArrayList;
import java.util.List;
import java.sql.*;

class CrudApp extends DatabaseOperationManager {
    public static void main(String[] arg){
        ConfigManager dbConfig=new ConfigManager();
        String dbUrl=dbConfig.getURL("mysql");
        String dbUser=dbConfig.getUser("mysql");
        String dbPassword=dbConfig.getPassword("mysql");
        System.out.println("dbUrl= "+dbUrl);
        System.out.println("dbUser= "+dbUser);
        System.out.println("dbPassword= "+dbPassword);
          try {
      CrudApp app = new CrudApp();  // create instance to call instance methods
            Connection conn = app.getDBConnection(dbUrl, dbUser, dbPassword);

            app.userAction(conn, "read");
        // your code here
    } catch (SQLException e) {
        e.printStackTrace();
    }
       
      

    }
    public static void userAction(Connection conn,String action)
    {
         CrudApp app=new CrudApp();
        if(action=="create"){
           
         String response= app.createUser(conn,"paulji","paulprem.kumar92@gmail.com","paul@123456.com");
      System.out.println("response : "+response);
        }else if(action=="read"){
  List<UserV1> responseV1= app.readUser(conn);
      System.out.println("response : "+responseV1);
        }else if(action=="update"){
           String updateResponse= app.updateUser(conn,"premji","premji@123.com","premji@gmail.com",7);
         System.out.println("Update user 7="+updateResponse);
        }else if(action=="delete"){
           String deleteResponse=app.deleteUser(conn,7);
            System.out.println("delete user 7="+deleteResponse);
        }
    }
@Override
    public String createUser(Connection conn,String username,String email,String password){
        String query="INSERT INTO users(username,email,password) values(?,?,?)";
        try{
          PreparedStatement stmnt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
          stmnt.setString(1,username);
           stmnt.setString(2,email);
            stmnt.setString(3,password);
             int affectedRows = stmnt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        // Get generated keys (the auto-generated ID)
        try (ResultSet generatedKeys = stmnt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                return String.valueOf(generatedKeys.getInt(1));  // return the generated ID
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }catch(SQLException e){
            return e.getMessage();
        }
        }catch(SQLException e){
            return e.getMessage();
        }
    }
    @Override
    public List<UserV1> readUser(Connection conn) {
     String query="SELECT *FROM users";
     List<UserV1> users=new ArrayList<>();
     try{
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(query);
        while(rs.next()){
            long id=rs.getLong("id");
            String username=rs.getString("username");
            String email=rs.getString("email");
users.add(new UserV1(id,email,username));
        }
     }catch(SQLException e){
       throw new RuntimeException("error");
     }
     return users;
    }

    @Override
    public String updateUser(Connection conn,String username,String password,String email,long id){
        try{
          String query="UPDATE users Set username=?,password=?,email=? where id=?";
          PreparedStatement stmnt = conn.prepareStatement(query);
          stmnt.setString(1,username);
          stmnt.setString(2,password);
          stmnt.setString(3,email);
          stmnt.setLong(4,id);
         int response=stmnt.executeUpdate();
return String.valueOf(response);
        }catch(SQLException e){
          return e.getMessage();
        }
    }
    public String deleteUser(Connection conn,long id){
        try{
            String query="DELETE from users WHERE id=?";
            PreparedStatement stmnt=conn.prepareStatement(query);
            stmnt.setLong(1,id);
            int response = stmnt.executeUpdate();
            return String.valueOf(response);
        }catch(SQLException e){
            return e.getMessage();
        }
    }

}