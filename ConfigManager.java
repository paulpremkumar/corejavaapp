import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
class ConfigManager{
    private Properties properties;
    public ConfigManager(){
     properties=new Properties();
     try{
  InputStream input=getClass().getClassLoader().getResourceAsStream("dbConfig.properties");
      properties.load(input);
     }catch(IOException e){
       throw new RuntimeException("failed to Load dbConfig.properties",e);
     }
   
    }
    public String getURL(String dbType){
        return properties.getProperty(dbType+".url");
    }
     public String getUser(String dbType){
        return properties.getProperty(dbType+".user");
    }
     public String getPassword(String dbType){
        return properties.getProperty(dbType+".password");
    }

}
