package prosthetidist.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import prosthetidist.ifaces.PatientManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

public class JDBCPatientManager implements PatientManager {
	
	private JDBCManager manager;

	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	//@TODO materials
	
	@Override
	
	public void addPatient(Patient p) {
		
		try {
			String sql = "INSERT INTO Patient (id, name, email, phone, address, notes, dob) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, p.getId());
			prep.setString(2, p.getName());
			prep.setString(3, p.getEmail());
			prep.setInt(4, p.getPhone());
			prep.setString(5, p.getAddress());
			prep.setString(6, p.getNotes());
			prep.setDate(7, Date.valueOf(p.getDob()));
		
			prep.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void designProsthetic (/*String functionalities, String type,*/ Measurement measurement) {
		
		int measurement_id = measurement.getId();
		Prosthetic p= null;
		try {
			String sql= "INSERT INTO Prosthetic (functionalities, type, measurement_id) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, p.getFunctionalities()/*functionalities*/);
			prep.setString(2, p.getType()/*type*/);
			prep.setInt(3, measurement_id);
			//@HELP funciona?
			p.getCompany().setId(null);// why?? 

			prep.executeUpdate();
			//luego esta prosthetic se mete en la db, lo de null se ha metido???????
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Patient getPatientByEmail (String email) {
		
		Patient p = null;
		try {
			String sql = "SELECT * FROM Patient WHERE email=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();
			//get the values
			Integer id= rs.getInt(1);
			String name= rs.getString(2);
			Date dob= rs.getDate(4); //probablemente esto no funcione
			String adress= rs.getString(5);
			Integer phonenumber=rs.getInt(6);
			String notes= rs.getString(7);
			
			p= new Patient (id, name, email, phonenumber, adress, notes, dob.toLocalDate());
			
			

			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}
	
	
	}
	
	

