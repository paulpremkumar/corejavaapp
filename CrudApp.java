class CrudApp {
    public static void main(String[] arg){
        ConfigManager dbConfig=new ConfigManager();
        String dbUrl=dbConfig.getURL("mysql");
         String dbUser=dbConfig.getUser("mysql");
          String dbPassword=dbConfig.getPassword("mysql");
          System.out.println("dbUrl= "+dbUrl);
           System.out.println("dbUser= "+dbUser);
            System.out.println("dbPassword= "+dbPassword);
    }
}