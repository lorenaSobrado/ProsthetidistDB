package prosthetidist.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import prosthetidist.ifaces.CompanyManager; //REVISAR
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Material;
import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;
import prosthetidist.pojos.Prosthetic;

public class JDBCCompanyManager implements CompanyManager {

	private JDBCManager manager;
	private JDBCMeasurementManager mm;
	private JDBCMaterialManager matm;

	public JDBCCompanyManager(JDBCManager m) {
		this.manager = m;
		this.mm = new JDBCMeasurementManager(manager);
		this.matm = new JDBCMaterialManager(manager);
	}

//	public void addCompany(Company c) {
//
//		try {
//			String sql = "INSERT INTO Company (name, email, phone) VALUES (?,?,?)";
//			PreparedStatement p = manager.getConnection().prepareStatement(sql);
//			p.setString(1, c.getName());
//			p.setString(2, c.getEmail());
//			p.setInt(3, c.getPhone());
//			p.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//	}

	public void addCompany(Company c) throws SQLException {
		String sql = "INSERT INTO Company (name, email, phone) VALUES (?,?,?)";
		PreparedStatement p = manager.getConnection().prepareStatement(sql);
		p.setString(1, c.getName());
		p.setString(2, c.getEmail());
		p.setInt(3, c.getPhone());
		p.executeUpdate();
		
	}

	public void deleteCompany(Company c) {
		try {
			String sql = "DELETE FROM Company WHERE id = ?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setInt(1, c.getId());
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Company getCompanyById(int company_id) {
		Company c = null;
		try {
			String sql = "SELECT * FROM Company WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, company_id);
			ResultSet rs = prep.executeQuery();

			String name = rs.getString("name");
			String email = rs.getString("email");
			Integer phone = rs.getInt("phone");

			c = new Company(company_id, name, email, phone);

			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;

	}

	public ArrayList<Company> getAllCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Company";
			ResultSet rs = stat.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
				Company c = new Company(id, name, email, phone);
				companies.add(c);
			}
			rs.close();
			stat.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return companies;
	}

	public ArrayList<Prosthetic> listProstheticsOfCompany(Company c) {

		ArrayList<Prosthetic> prostheticsOfCompany = new ArrayList<Prosthetic>();
		ArrayList<Material> materials = new ArrayList<Material>();
		Measurement m = null;

		int company_id = c.getId();

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Prosthetic WHERE company_id = " + company_id;
			ResultSet rs = stat.executeQuery(sql);
			// rs.next() moves to the next row of the table
			while (rs.next()) {
				Integer code = rs.getInt("code");
				Float price = rs.getFloat("price");
				String functionalities = rs.getString("functionalities");
				String type = rs.getString("type");
				String model = rs.getString("model");
				Integer measurement_id = rs.getInt("measurement_id");
				m = mm.getMeasurementById(measurement_id);

				materials = matm.getMaterialsFromProstheticCode(code);
				Prosthetic p = new Prosthetic(code, price, functionalities, type, model, m, materials);

				prostheticsOfCompany.add(p);
			}
			rs.close();
			stat.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prostheticsOfCompany;
	}

	public void offerDesign(Prosthetic prosthetic, Integer companyId) {

		try {
			String sql = "UPDATE Prosthetic SET price = ?, model = ?, company_id = ? WHERE code = ?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);

			p.setFloat(1, prosthetic.getPrice());
			p.setString(2, prosthetic.getModel());
			p.setInt(3, companyId);
			p.setInt(4, prosthetic.getCode());

			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Company getCompanyByEmail(String email) {

		Company c = null;
		try {
			String sql = "SELECT * FROM Company WHERE email = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, email);
			ResultSet rs = prep.executeQuery();
			// get the values
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			Integer phone = rs.getInt("phone");

			c = new Company(id, name, email, phone);

			rs.close();
			prep.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;
	}

}
