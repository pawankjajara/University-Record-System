//package URS;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import snaq.db.ConnectionPool;
//
//public class DBConnectionPool {
//	String url;
//	String dbName;
//	String driver;
//	String userName;
//	String password;
//	Connection conn;
//	long timeout =3000;
//	public static ConnectionPool pool;
//
//	public DBConnectionPool(){
//		url=null;
//		dbName=null;
//		userName=null;
//		password=null;
//		conn=null;	
//	}
//
//
//	public Connection ConnectToDB(){
//		System.out.println("Trying to Connect to MySQL Database");
//		conn = null;
//		url = "jdbc:mysql://localhost:3306/";
//		dbName = "projectdbnew";
//		driver = "com.mysql.jdbc.Driver";
//		userName = "root"; 
//		password = "admin";
//		try {
//			Class c = Class.forName(driver);
//			Driver driver =(Driver)c.newInstance();
//			DriverManager.registerDriver(driver);
//			
//			pool = new ConnectionPool("local",5,10,30,180000,url+dbName,userName,password);
//			conn=pool.getConnection(timeout);
//			
//			if(conn!=null){
//				System.out.println("Connected to the database");
//			}
//			else
//			{
//				System.out.println("Connection Timed Out");
//				System.out.println("NOT  Connected to the database");
//			}
//			//conn = DriverManager.getConnection(url+dbName,userName,password);
//			
//		}
//		catch(SQLException sqlex){
//			System.out.println(sqlex);
//		}
//		
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return conn;
//	}
//}
