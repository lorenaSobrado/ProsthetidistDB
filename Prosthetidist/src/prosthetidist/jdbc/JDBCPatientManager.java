package prosthetidist.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PatientIdException;
import prosthetidist.ifaces.PatientManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Material;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

public class JDBCPatientManager implements PatientManager {

	private JDBCManager manager;

	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}

	@Override

	public void addPatient(Patient p) throws SQLException, PatientIdException {
		Patient patientExists = this.getPatientById(p.getId());
		if(patientExists != null) {
			throw new PatientIdException();
		}

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
	}

	public void designProsthetic(String functionalities, String type, Measurement measurement) {
		int measurement_id = measurement.getId();
		try {
			String sql = "INSERT INTO Prosthetic (functionalities, type, measurement_id) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, functionalities);
			prep.setString(2, type);
			prep.setInt(3, measurement_id);
			prep.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Patient getPatientByEmail(String email) {

		Patient p = null;
		try {
			String sql = "SELECT * FROM Patient WHERE email = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();
			// get the values
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			Date dob = rs.getDate("dob");
			String address = rs.getString("address");
			Integer phone = rs.getInt("phone");
			String notes = rs.getString("notes");

			p = new Patient(id, name, email, phone, address, notes, dob.toLocalDate());

			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}
	
	public Patient getPatientById(Integer id) {

		Patient p = null;
		try {
			String sql = "SELECT * FROM Patient WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			// get the values
			String email = rs.getString("email");
			String name = rs.getString("name");
			Date dob = rs.getDate("dob");
			String address = rs.getString("address");
			Integer phone = rs.getInt("phone");
			String notes = rs.getString("notes");

			p = new Patient(id, name, email, phone, address, notes, dob.toLocalDate());

			rs.close();
			prep.close();
		} catch (SQLException ex) {
			return null;
		}
		return p;
	}
	
	public Integer getDesignCode(String functionalities, String type, Measurement m) {
		Integer prosCode = null;
		try {
			String sql = "SELECT code FROM Prosthetic WHERE functionalities = ? AND type = ? AND measurement_id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, functionalities);
			prep.setString(2, type);
			prep.setInt(3, m.getId());
			
			ResultSet rs = prep.executeQuery();
			prosCode = rs.getInt("code");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prosCode;
	}

}
