import java.sql.*;


class dbConnection {
    private Connection connection;
    
    private Statement statement;
    
    private ResultSet resultSet;

    private boolean connectedToDatabase = false;

    private final String url = "jdbc:mysql://localhost:3306/iaf_cert";
      
    private final String user = "root";
    
    private final String pass = "5928";

    // Create a connection to the Database
    public Connection getConnection() {
        try{
            connection = DriverManager.getConnection(url, user, pass);
            connectedToDatabase = true;
            return connection;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    // Perform a select query on the database
    public ResultSet select(String query) throws SQLException {
        connection = getConnection();
        statement = connection.createStatement();
        statement.executeQuery(query);
        resultSet = statement.getResultSet();
        return resultSet;
        
    }
    protected void update(String query) throws SQLException {
        connection = getConnection();
        statement = connection.createStatement();
        statement.execute(query);
    }

    public void insetInto(String query) throws SQLException {
        connection = getConnection();
        statement = connection.createStatement();
        statement.execute(query);
    }

    protected void drop(String query) throws SQLException {
        connection = getConnection();
        statement = connection.createStatement();
        statement.execute(query);
    }


    // Dicconnects from the database
    public void disconnectFromDatabase(){

    if(connectedToDatabase){
     
        try{
               resultSet.close();
               statement.close();
               connection.close();
        }
        catch(Exception Exception){
        } 
        
        finally{
            connectedToDatabase = false;        
        }
    }
        
    }
    
    
    
 
}

