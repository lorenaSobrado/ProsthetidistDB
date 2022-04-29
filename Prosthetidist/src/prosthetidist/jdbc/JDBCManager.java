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
			+ "	Id	    INTEGER,"
			+ "	Name	TEXT,"
			+ "	Email	TEXT UNIQUE."
			+ " DOB     DATE,"
			+ " Adress  TEXT,"
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
			
			sql = "CREATE TABLE Materials ("
			+ "Name	TEXT,"
			+ "Cost	REAL,"
			+"Strength	TEXT,"
			+"Flexibility TEXT,"
			+"Temperature_resistance TEXT,"
			+"PRIMARY KEY(Name)" 
			+ ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Prosthetics ("
			+"Code	INTEGER,"
			+"Price	REAL,"
			+"Abilities	TEXT,"
			+"Type TEXT,"
			+"Model TEXT,"
			+"Patient_id INTEGER,"
			+"Company_id INTEGER,"
			+"PRIMARY KEY(Code AUTOINCREMENT)," 
			+"FOREIGN KEY (Patient_id) REFERENCES Patient(Id),"//@HELP en el doc db distinto escrito ask
			+"FOREIGN KEY(Company_id) REFERENCES Company(Id)"
			+ ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE PatientBuysProsthetics ("
			+ "	PatientID	INTEGER,"
			+ "	CompanyID	INTEGER,"
			+ "	FOREIGN KEY(CompanyID) REFERENCES Company(Id),"
			+ "	FOREIGN KEY(PatientID) REFERENCES Patient(id),"
			+ "	PRIMARY KEY(PatientID,CompanyID)\r\n"
			+ ");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE PatientContactsCompany ("
			+ "	PatientID	INTEGER,"
			+ "	ProstheticCODE	INTEGER,"
			+ "	FOREIGN KEY(ProstheticCODE) REFERENCES Prosthetics(Code),"
			+ "	FOREIGN KEY(PatientID) REFERENCES Patient(id),"
			+ "	PRIMARY KEY(PatientID,ProstheticCODE)\r\n"
			+ ");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE ProstheticHasMeasurements ("
			+ "	MeasurementSIZE	TEXT,"
			+ "	ProstheticCODE	INTEGER,"
			+ "	FOREIGN KEY(ProstheticCODE) REFERENCES Prosthetics(Code),"
			+ "	FOREIGN KEY(MeasurementSIZE) REFERENCES Measurements(size),"
			+ "	PRIMARY KEY(ProstheticCODE, MeasurementSIZE)\r\n"
			+ ");";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE ProstheticHasMaterials ("
			+ "	ProstheticCODE	INTEGER,"
			+ "	MaterialsNAME	TEXT,"
			+ "	FOREIGN KEY(ProstheticCODE) REFERENCES Prosthetics(Code),"
			+ "	FOREIGN KEY(ProstheticCODE) REFERENCES Materials(name),"
			+ "	PRIMARY KEY(ProstheticCODE, MaterialsNAME)\r\n"
			+ ");";
			stmt.executeUpdate(sql);
			
			
			} catch (SQLException e) {
				// Do not complain if tables already exist
				if (!e.getMessage().contains("already exists")) {
					e.printStackTrace();
				}
			} 
		}
}
		

