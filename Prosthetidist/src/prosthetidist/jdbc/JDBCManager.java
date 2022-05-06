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
			String sql = "CREATE TABLE Company " + "(id	INTEGER, " + "name	TEXT, " + "email    TEXT UNIQUE, " + "phone INTEGER, "
					+ "PRIMARY KEY (id AUTOINCREMENT)" + ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Delivery " + "(type	TEXT, " + "price	REAL NOT NULL, " + "description TEXT, "
					+ "PRIMARY KEY (type)" + ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Invoice " + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "datePurchase	DATE, " + "paymentMethod    TEXT NOT NULL, "
					+ "purchase BOOLEAN DEFAULT \"FALSE\", " + "patient_id INTEGER REFERENCES Patient(id), " + "prosthetic_code INTEGER REFERENCES Prosthetic(code), " 
					+ "delivery_type TEXT REFERENCES Delivery(type) " + ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Material " + "(name TEXT PRIMARY KEY, " + "price	REAL, " + "strength	TEXT, " + "flexibility TEXT, "
					+ "temperature_resistance TEXT, " + ");";

			stmt.executeUpdate(sql);
			
			sql = "UPDATE Material " + "SET name = Plastic " + "SET price = " + 4.99 + " SET strength = Low " + "SET flexibility = High " 
					+ "SET temperature_resistance = Low ";
			
			stmt.executeUpdate(sql);
			
			sql = "UPDATE Material " + "SET name = Carbon Fiber " + "SET price = " + 19.99 + " SET strength = High " + "SET flexibility = High " 
					+ "SET temperature_resistance = High ";
			
			stmt.executeUpdate(sql);
			
			sql = "UPDATE Material " + "SET name = Aluminum " + "SET price = " + 5.4 + " SET strength = Low* " + "SET flexibility = Medium* " 
					+ "SET temperature_resistance = Low* ";
			
			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Measurement " + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "lengthinees REAL NOT NULL, " 
					+ "width REAL NOT NULL, " + "weight	    REAL, " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Patient " + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name	TEXT, "
			+ "email	TEXT UNIQUE " + "dob     DATE, " + "address  TEXT, " + "phonenumber	INTEGER UNIQUE, "
			+ "notes	TEXT, " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Prosthetic " + "(code INTEGER PRIMARY KEY AUTOINCREMENT, " + "price REAL, " + "functionalities	TEXT, " 
					+ "type TEXT NOT NULL, " + "model TEXT, " + "company_id INTEGER REFERENCES Company (id)," 
					+ "measurement_id INTEGER REFERENCES Measurement (id) " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE ProstheticHasMaterials " + "(prosthetic_code INTEGER REFERENCES Prosthetic(code), " 
			+ "	material_name TEXT REFERENCES Material(name), " + "PRIMARY KEY (prosthetic_code, material_name)" + ");";
			
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) { //Shouldn't it be without the "!" ??
				e.printStackTrace();
			}
		}
	}
}
