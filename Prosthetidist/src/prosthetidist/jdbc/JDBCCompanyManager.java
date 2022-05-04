package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prosthetidist.ifaces.CompanyManager; //REVISAR
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurements;
import prosthetidist.pojos.Prosthetic;
import prosthetidist.pojos.Prosthetics;

public class JDBCCompanyManager implements CompanyManager {

	private JDBCManager manager;
	private JDBCMeasurementsManager mm;

	public JDBCCompanyManager(JDBCManager m) {
		this.manager = m;
	}

//@TODO addCompany mediante base de datos pero la relacion con el register????

//@Override

	public void deleteCompany(int companyId) {
		try {
			String sql = "DELETE FROM Company WHERE Id= ?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, companyId);
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Company getCompanyById(int company_id) {
		Company c = null;
		try {
			String sql = "SELECT * FROM Company WHERE Id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, company_id);
			ResultSet rs = prep.executeQuery();
			// get the values , @CHECK
			String name = rs.getString(2);
			String email = rs.getString(3);
			Integer phone = rs.getInt(4);

			c = new Company(company_id, name, email, phone);
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;

	}

	public void uploadProsthetics(Prosthetic p) {

		try {
			String sql = "INSERT INTO Prosthetic (Price, Functionalities, Type, Model, Company_id, Measurement_id) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, p.getPrice());
			prep.setString(2, p.getFunctionalities());
			prep.setString(3, p.getType());
			prep.setString(4, p.getModel());
			prep.setInt(5, p.getCompany().getId());
			prep.setInt(6, p.getMeasurements().getId());
			prep.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Prosthetic> listProstheticsWithoutCompanyID() {

		List<Prosthetic> prostheticsWithoutCompany = new ArrayList<Prosthetic>();
		Company c = null;
		Measurements m = null;

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetics WHERE Company_id= NULL";
			ResultSet rs = stat.executeQuery(sql);
			// rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("Code");
				String functionalities = rs.getString("Functionalities");
				String type = rs.getString("Type");
				Integer measurement_id = rs.getInt("Measurement_id");

				// FALTAN LOS MATERIALES

				m = mm.getMeasurementById(measurement_id);
				Prosthetic p = new Prosthetic(code, functionalities, type, m);
				prostheticsWithoutCompany.add(p);
			}
			rs.close();
			stat.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prostheticsWithoutCompany;
	}

//@TODO LA PROTESIS LA RECIBE A TRAVÉS DE SWING MEDIANTE SELECT

	public void offerDesign(Prosthetic prosthetic) {

		try {
			String sql = "UPDATE Prosthetics" + " SET Price=?" + " Model=?" + " Company_id=?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);

			// @TODO SOLVE PROBLEMS

			// p.setFloat(1, prosthetic.setPrice(36));
			// p.setString(2, prosthetic.setModel("x1"));
			// p.setInt(3, prosthetic.getCompany());

			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/*
 * public void addCompany(Company c) { try { String sql=
 * "INSERT INTO Company (Name, Email, Phone) VALUES (?,?,?)" } catch (Exception
 * ex) { ex.printStackTrace(); } }
 */
