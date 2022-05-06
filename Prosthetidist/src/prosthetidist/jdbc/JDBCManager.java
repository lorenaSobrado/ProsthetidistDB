package prosthetidist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {

	private Connection c = null;

	public JDBCManager() {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/prosthetidist.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Create tables
			this.createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Libraries not loaded");
		}

	}

	public void disconnect() {
		try {
			c.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	// @TODO finish tables
	private void createTables() {
		// Create Tables
		try {
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE Company " + "(Id	INTEGER, " + "Name	TEXT, " + "Email    TEXT UNIQUE, " + "Phone INTEGER, "
					+ "PRIMARY KEY (Id AUTOINCREMENT)" + ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Delivery " + "(Type	TEXT, " + "Price	REAL NOT NULL, " + "Description TEXT, "
					+ "PRIMARY KEY (Type)" + ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Invoice " + "(Id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "DatePurchase	DATE, " + "PaymentMethod    TEXT NOT NULL, "
					+ "Purchase BOOLEAN DEFAULT \"FALSE\", " + "Patient_id INTEGER REFERENCES Patient(Id), " + "Prosthetic_code INTEGER REFERENCES Prosthetic(Code), " 
					+ "Delivery_type TEXT REFERENCES Delivery(Type) " + ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Material " + "(Name TEXT PRIMARY KEY, " + "Price	REAL, " + "Strength	TEXT, " + "Flexibility TEXT, "
					+ "Temperature_resistance TEXT, " + ");";

			stmt.executeUpdate(sql);
			
			sql = "UPDATE Material " + "SET Name = Plastic " + "SET Price = " + 4.99 + " SET Strength = Low " + "SET Flexibility = High " 
					+ "SET Temperature_resistance = Low ";
			
			stmt.executeUpdate(sql);
			
			sql = "UPDATE Material " + "SET Name = Carbon Fiber " + "SET Price = " + 19.99 + " SET Strength = High " + "SET Flexibility = High " 
					+ "SET Temperature_resistance = High ";
			
			stmt.executeUpdate(sql);
			
			sql = "UPDATE Material " + "SET Name = Aluminum " + "SET Price = " + 5.4 + " SET Strength = Low* " + "SET Flexibility = Medium* " 
					+ "SET Temperature_resistance = Low* ";
			
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Measurement " + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, " + "Lengthinees REAL NOT NULL, " 
					+ "Width REAL NOT NULL, " + "Weight	    REAL, " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Patient " + "(Id INTEGER PRIMARY KEY, " + "Name	TEXT, "
			+ "Email	TEXT UNIQUE " + "DOB     DATE, " + "Address  TEXT, " + "Phonenumber	INTEGER UNIQUE, "
			+ "Notes	TEXT, " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Prosthetic " + "(Code INTEGER PRIMARY KEY AUTOINCREMENT, " + "Price REAL NOT NULL, " + "Functionalities	TEXT, " 
					+ "Type TEXT NOT NULL, " + "Model TEXT NOT NULL, " + "Company_id INTEGER REFERENCES Company (Id)," 
					+ "Measurement_id INTEGER REFERENCES Measurements (Id) " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE ProstheticHasMaterials " + "(Prosthetic_code INTEGER REFERENCES Prosthetic(Code), " 
			+ "	Material_name TEXT REFERENCES Material(Name), " + "PRIMARY KEY (Prosthetic_code, Material_name)" + ");";
			
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) { //Shouldn't it be without the "!" ??
				e.printStackTrace();
			}
		}
	}
}
