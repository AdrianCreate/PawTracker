package DatabaseConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
	
	
	//private static final String MYDB = "jdbc:mysql://localhost:3306/paw_tracker";
	 //private static final String USERNAME = "root";
	 //private static final String PASSWORD = "";
	
	private static String MYDB;
	private static String USERNAME;
	private static String PASSWORD;
	static {
		String filePath = "C:\\Users\\adi13\\eclipse-workspace\\PawTracker\\src\\main\\webapp\\WEB-INF\\fisiere\\properties.txt";
		BufferedReader bReader = null;
		FileReader fReader = null;
		try {
			fReader = new FileReader(filePath);
			bReader = new BufferedReader(fReader);
			MYDB = bReader.readLine();
			USERNAME = bReader.readLine();
			PASSWORD = bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 public DBConnection() {
		 
	 }
	 
	  static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("Error loading MySQL JDBC driver", e);
	        }
	    }
	  
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(MYDB, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return con;
	}
	
	  public void closeConnection(Connection connection) throws SQLException {
	        if (connection != null) {
	        	connection.close();
	        }
	  }
	  
	  public void closeResSet(ResultSet resSet) throws SQLException {
		  if(resSet != null) {
			  resSet.close();
		  }
	  }
	  
	  public void closeStatement(Statement state) throws SQLException {
		  if(state != null) {
			  state.close();
		  }
	  }
	  
	  public void closeResources(Connection connection, Statement state, ResultSet resSet) {
		  try {
			  closeResSet(resSet);
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } finally {
			  try {
				  closeStatement(state);
			  } catch (SQLException e) {
				  e.printStackTrace();
			  } finally {
				  try {
					  closeConnection(connection);
				  } catch (SQLException e) {
					  e.printStackTrace();
				  }
			  }
		  }
	  }
	}
