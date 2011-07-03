package URS;
import java.sql.*;

public class DBConnection {
	String url;
	String dbName;
	String driver;
	String userName;
	String password;
	Connection conn;
	
	public DBConnection(){
	url=null;
	dbName=null;
	userName=null;
	password=null;
	conn=null;	
	}
	
		
	public Connection ConnectToDB(){
	System.out.println("Trying to Connect to MySQL Database");
    conn = null;
    url = "jdbc:mysql://localhost:3306/";
    dbName = "projectdbnew";
    driver = "com.mysql.jdbc.Driver";
    userName = "root"; 
    password = "admin";
    try {
      Class.forName(driver).newInstance();
      conn = DriverManager.getConnection(url+dbName,userName,password);
      System.out.println("Connected to the database");
    }
    //System.out.println("Disconnected from database");
     catch (Exception e) {
      e.printStackTrace();
    }
     return conn;
	}
}
