package prosthetidist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
//	public void connect() {
//
//	}

	public void disconnect() {
		try {
			c.close();
			System.out.println("Database connection closed.");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		return c;
	}

	private void createTables() {
		// Create Tables
		try {
			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE Company " + "(id	INTEGER, " + "name	TEXT, " + "email    TEXT UNIQUE NOT NULL, " + "phone INTEGER, "
					+ "PRIMARY KEY (id AUTOINCREMENT)" + ");";

			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Delivery " + "(type	TEXT, " + "price	REAL NOT NULL, " + "description TEXT, "
					+ "PRIMARY KEY (type)" + ");";

			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Delivery " + "(type, price, description) VALUES (?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "Standard");
			prep.setFloat(2, 0f);
			prep.setString(3, "basic");
			prep.executeUpdate();
			
			sql = "INSERT INTO Delivery " + "(type, price, description) VALUES (?,?,?)";
			prep = c.prepareStatement(sql);
			prep.setString(1, "Premium");
			prep.setFloat(2, 10f);
			prep.setString(3, "premium");
			prep.executeUpdate();
			
			sql = "CREATE TABLE Invoice " + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "datePurchase	DATE, " + "creditCard    INTEGER, "
					+ "purchase BOOLEAN DEFAULT FALSE, " + "patient_id INTEGER REFERENCES Patient(id), " + "prosthetic_code INTEGER REFERENCES Prosthetic(code) ON DELETE CASCADE, " 
					+ "delivery_type TEXT REFERENCES Delivery(type) " + ");";
			
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE Material " + "(name TEXT PRIMARY KEY, " + "strength	TEXT, " + "flexibility TEXT, "
					+ "temperatureResistance TEXT " + ");";

			stmt.executeUpdate(sql);
			
			sql = "INSERT INTO Material " + "(name, strength, flexibility, temperatureResistance) VALUES (?,?,?,?)";
			prep = c.prepareStatement(sql);
			prep.setString(1, "Plastic");
			prep.setString(2, "low");
			prep.setString(3, "high");
			prep.setString(4, "low");
			prep.executeUpdate();
			
			sql = "INSERT INTO Material " + "(name, strength, flexibility, temperatureResistance) VALUES (?,?,?,?)";
			prep = c.prepareStatement(sql);
			prep.setString(1, "Carbon Fiber");
			prep.setString(2, "high");
			prep.setString(3, "medium");
			prep.setString(4, "high");
			prep.executeUpdate();
			
			sql = "INSERT INTO Material " + "(name, strength, flexibility, temperatureResistance) VALUES (?,?,?,?)";
			prep = c.prepareStatement(sql);
			prep.setString(1, "Aluminium");
			prep.setString(2, "medium");
			prep.setString(3, "medium");
			prep.setString(4, "low");
			prep.executeUpdate();

			sql = "CREATE TABLE Measurement " + "(id INTEGER PRIMARY KEY AUTOINCREMENT, " + "lengthiness REAL NOT NULL, " 
					+ "width REAL NOT NULL, " + "weight	    REAL " + ");"; 

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Patient " + "(id INTEGER PRIMARY KEY, " + "name	TEXT, "
			+ "email	TEXT UNIQUE NOT NULL, " + "dob     DATE, " + "address  TEXT, " + "phone	INTEGER, "
			+ "notes	TEXT " + ");"; 

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE Prosthetic " + "(code INTEGER PRIMARY KEY AUTOINCREMENT, " + "price REAL, " + "functionalities	TEXT, " 
					+ "type TEXT NOT NULL, " + "model TEXT, " + "company_id INTEGER REFERENCES Company (id) ON DELETE CASCADE," 
					+ "measurement_id INTEGER REFERENCES Measurement (id) " + ");";

			stmt.executeUpdate(sql);

			sql = "CREATE TABLE ProstheticHasMaterials " + "(prosthetic_code INTEGER REFERENCES Prosthetic(code) ON DELETE CASCADE, " 
			+ "	material_name TEXT REFERENCES Material(name) ON DELETE CASCADE, " + "PRIMARY KEY (prosthetic_code, material_name)" + ");";
			
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// Do not complain if tables already exist
			if (!e.getMessage().contains("already exists")) { //Shouldn't it be without the "!" ??
				e.printStackTrace();
			}
		}
	}
}
