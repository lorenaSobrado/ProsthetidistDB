package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//no deberia
import prosthetidist.ifaces.ProstheticsManager;
import prosthetidist.pojos.*;

public class JDBCProstheticManager implements ProstheticsManager {

	private JDBCManager manager;
	private JDBCCompanyManager cm;
	private JDBCMeasurementManager mm;
	private JDBCMaterialManager matm;

	public JDBCProstheticManager(JDBCManager m) {
		this.manager = m;
		this.cm = new JDBCCompanyManager(m);
		this.mm = new JDBCMeasurementManager(m);
		this.matm = new JDBCMaterialManager(m);
	}

	public ArrayList<Prosthetic> getProstheticsWithCompanyId() {
		ArrayList<Prosthetic> allProsthetics = new ArrayList<Prosthetic>();

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetic WHERE company_id IS NOT NULL";
			ResultSet rs = stat.executeQuery(sql);
			// rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("code");
				Float price = rs.getFloat("price");
				String functionalities = rs.getString("functionalities");
				String type = rs.getString("type");
				String model = rs.getString("model");
				Integer company_id = rs.getInt("company_id");
				Integer measurement_id = rs.getInt("measurement_id");
				Company c = cm.getCompanyById(company_id);
				Measurement m = mm.getMeasurementById(measurement_id);
				ArrayList<Material> materials = matm.getMaterialsFromProstheticCode(code);
				Prosthetic p = new Prosthetic(code, price, functionalities, type, model, c, m, materials);
				allProsthetics.add(p);
			}
			rs.close();
			stat.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return allProsthetics;
	}

	public ArrayList<Prosthetic> getProstheticsWithoutCompanyId() {

		ArrayList<Prosthetic> prostheticsWithoutCompany = new ArrayList<Prosthetic>();

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetic WHERE company_id IS NULL";
			ResultSet rs = stat.executeQuery(sql); // a result set puts a cursor before the first row
			// rs.next() moves the cursor to the next row of the table, so the first time
			// puts it to the first row
			while (rs.next()) {
				Integer code = rs.getInt("code");
				String functionalities = rs.getString("functionalities");
				String type = rs.getString("type");
				Integer measurement_id = rs.getInt("measurement_id");
				Measurement meas = mm.getMeasurementById(measurement_id);
				ArrayList<Material> materials = matm.getMaterialsFromProstheticCode(code);
				Prosthetic p = new Prosthetic(code, functionalities, type, meas, materials);
				prostheticsWithoutCompany.add(p);
			}
			rs.close();
			stat.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prostheticsWithoutCompany;
	}

	public Prosthetic getProstheticByCode(Integer code) {
		Prosthetic pros = null;

		try {
			String sql = "SELECT * FROM Prosthetic WHERE code = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, code);
			ResultSet rs = prep.executeQuery();

			Float price = rs.getFloat("price");
			String functionalities = rs.getString("functionalities");
			String type = rs.getString("type");
			String model = rs.getString("model");
			Integer company_id = rs.getInt("company_id");
			Integer measurement_id = rs.getInt("measurement_id");
			Measurement meas = mm.getMeasurementById(measurement_id);
			ArrayList<Material> materials = matm.getMaterialsFromProstheticCode(code);
			if (company_id == 0) {
				pros = new Prosthetic(code, price, functionalities, type, model, meas, materials);
			} else {
				Company company = cm.getCompanyById(company_id);
				pros = new Prosthetic(code, price, functionalities, type, model, company, meas, materials);
			}
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pros;
	}

	public void uploadProsthetic(Prosthetic p) {

		int company_id = p.getCompany().getId();
		try {
			String sql = "INSERT INTO Prosthetic (price, functionalities, type, model, company_id, measurement_id) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, p.getPrice());
			prep.setString(2, p.getFunctionalities());
			prep.setString(3, p.getType());
			prep.setString(4, p.getModel());
			prep.setInt(5, company_id);
			prep.setInt(6, p.getMeasurement().getId());
			prep.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void addProstheticFromXML(Prosthetic p, Company c) {

		try {
			String sql = "INSERT INTO Prosthetic (price, functionalities, type, model, company_id, measurement_id) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, p.getPrice());
			prep.setString(2, p.getFunctionalities());
			prep.setString(3, p.getType());
			prep.setString(4, p.getModel());
			prep.setInt(5, c.getId());
			prep.setInt(6, p.getMeasurement().getId());
			prep.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@Override
	public void deleteProsthetic(Prosthetic pros) {
		try {
			String sql = "DELETE FROM Prosthetic WHERE code = ?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, pros.getCode());
			p.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Integer getProstheticCode(Prosthetic p) {
		Integer prosCode = null;
		try {
			String sql = "SELECT code FROM Prosthetic WHERE price = ? AND functionalities = ? AND type = ? AND model = ? AND "
					+ "company_id = ? AND measurement_id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, p.getPrice());
			prep.setString(2, p.getFunctionalities());
			prep.setString(3, p.getType());
			prep.setString(4, p.getModel());
			prep.setInt(5, p.getCompany().getId());
			prep.setInt(6, p.getMeasurement().getId());

			ResultSet rs = prep.executeQuery();
			prosCode = rs.getInt("code");

		} catch (Exception ex) {
			return null;
		}
		return prosCode;
	}
	
	public Integer getLastProstheticCode() {
		Integer prosCode = null;
		try {
			String sql = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			prosCode = rs.getInt("lastId");
			
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return prosCode;
	}

}
