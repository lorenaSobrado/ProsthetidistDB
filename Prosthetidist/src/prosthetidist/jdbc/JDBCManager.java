package prosthetidist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {

		private Connection c= null;
	
		public JDBCManager() {
			try {
				// Open database connection
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:./db/prosthetidist.db");
				c.createStatement().execute("PRAGMA foreign_keys=ON");
				System.out.println("Database connection opened.");
				// Create tables
				//this.createTables();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Libraries not loaded");
			}
			
			
		}
		
		private void createTables() {
			// Create Tables
			try {
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE Patient ("
			+ "	Id	    INTEGER"
			+ "	Name	TEXT"
			+ "	Email	TEXT"
			+ " DOB     DATE"
			+ " Adress  TEXT"
			+ "	Phonenumber	INTEGER UNIQUE,"
			+ "	Notes	TEXT,"
			+ "	PRIMARY KEY (Id AUTOINCREMENT)"
			+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Measurements ("
			+ "	Size	TEXT,"
			+ "	Lengthiness	REAL,"
			+ "	Width	REAL,"
			+ "	Weight	    REAL,"
			+ "	PRIMARY KEY (Size)"
			+ ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Company ("
			+ " Id	INTEGER,"
			+ " Name	TEXT,"
		    + " Email	TEXT,"
			+ " Phone INTEGER,"
			+ " PRIMARY KEY(Id AUTOINCREMENT)" 
			+ ");";
			
			stmt.executeUpdate(sql);
		
			sql = "CREATE TABLE Delivery ("
					+ " Id	INTEGER,"
					+"Type	TEXT,"
					+"Price	REAL,"
					+"Description TEXT,"
					+"PRIMARY KEY(Id AUTOINCREMENT)" 
					+ ");";

			stmt.executeUpdate(sql);
			/*
			sql = "CREATE TABLE examines ("
			+ "	dogId	INTEGER,"
			+ "	vetId	INTEGER,"
			+ "	FOREIGN KEY(dogId) REFERENCES dogs(id) ON DELETE CASCADE,"
			+ "	FOREIGN KEY(vetId) REFERENCES vets(id) ON DELETE CASCADE,"
			+ "	PRIMARY KEY(dogId,vetId)\r\n"
			+ ");";
			stmt.executeUpdate(sql);
			*/
			
			} catch (SQLException e) {
				// Do not complain if tables already exist
				if (!e.getMessage().contains("already exists")) {
					e.printStackTrace();
				}
			} 
		}
}
		

