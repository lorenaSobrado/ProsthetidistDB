package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prosthetidist.ifaces.CompanyManager; //REVISAR
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Prosthetic;


public class JDBCCompanyManager implements CompanyManager {

	private JDBCManager manager;
	private JDBCMeasurementsManager mm;

	public JDBCCompanyManager(JDBCManager m) {
		this.manager = m;
	}


	public void addCompany(Company c) {
		
		try {
			String sql = "INSERT INTO Company (name, email, phone) VALUES (?,?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setString(1, c.getName());
			p.setString(2, c.getEmail());
			p.setInt(3, c.getPhone());
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteCompany(int companyId) {
		try {
			String sql = "DELETE FROM Company WHERE id= ?";
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
			String sql = "SELECT * FROM Company WHERE id=?";
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
	//falta materials
	public void uploadProsthetics(Company c, Prosthetic p) {
		
		int company_id= c.getId();

		try {
			String sql = "INSERT INTO Prosthetic (price, functionalities, type, model, company_id, measurement_id) VALUES (?,?,?,?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, p.getPrice());
			prep.setString(2, p.getFunctionalities());
			prep.setString(3, p.getType());
			prep.setString(4, p.getModel());
			prep.setInt(5, company_id);
			prep.setInt(6, p.getMeasurements().getId());
			prep.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Prosthetic> listProstheticsWithoutCompanyID() {

		ArrayList<Prosthetic> prostheticsWithoutCompany = new ArrayList<Prosthetic>();
		Company c = null;
		Measurement m = null;

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetic WHERE company_id= NULL";
			ResultSet rs = stat.executeQuery(sql);
			// rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("code");
				String functionalities = rs.getString("functionalities");
				String type = rs.getString("type");
				Integer measurement_id = rs.getInt("measurement_id");

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
	public List<Prosthetic> listProstheticsOfCompany(Company c) {

		List<Prosthetic> prostheticsOfCompany = new ArrayList<Prosthetic>();
		
		int company_id= c.getId();

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetic WHERE company_id = " + company_id;
			ResultSet rs = stat.executeQuery(sql);
			// rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("code");
				Float price =rs.getFloat("price");
				String functionalities =rs.getString("functionalities");
				String type =rs.getString("type");
				String model = rs.getString("model");

				Prosthetic p = new Prosthetic(code, price,functionalities, type,model);
				
				prostheticsOfCompany.add(p);
			}
			rs.close();
			stat.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prostheticsOfCompany;
	}

//@TODO LA PROTESIS LA RECIBE A TRAVES DE SWING MEDIANTE SELECT

	public void offerDesign(Prosthetic prosthetic) {

		try {
			String sql = "UPDATE Prosthetic" + " SET price=?" + " model=?" + " company_id=?";
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
