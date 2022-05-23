package prosthetidist.jdbc;

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
import prosthetidist.pojos.Prosthetic;

public class JDBCCompanyManager implements CompanyManager {

	private JDBCManager manager;

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

	

	public List<Prosthetic> listProstheticsOfCompany(Company c) {

		List<Prosthetic> prostheticsOfCompany = new ArrayList<Prosthetic>();

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

				Prosthetic p = new Prosthetic(code, price, functionalities, type, model);

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
			String sql = "UPDATE Prosthetic" + " SET price = ?" + " model = ?" + " company_id = ?";
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

	@Override
	public ArrayList<Prosthetic> getProstheticsOfCompany(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

}

