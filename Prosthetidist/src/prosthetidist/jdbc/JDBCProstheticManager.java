package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	// @TODO comprobar si funciona

	public ArrayList<Prosthetic> listAllProstheticsWithCompanyId() {
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

	public Prosthetic getProstheticByCode(Integer code) {
		Prosthetic pros = null;

		try {
			String sql = "SELECT * FROM Prosthetic WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, code);
			ResultSet rs = prep.executeQuery();

			Float price = rs.getFloat("price");
			String functionalities = rs.getString("functionalities");
			String type = rs.getString("type");
			String model = rs.getString("model");
			int company_id = rs.getInt("company_id"); // que pasa si no tiene company
			int measurement_id = rs.getInt("measurement_id");
			Company company = cm.getCompanyById(company_id);
			Measurement meas = mm.getMeasurementById(measurement_id);
			ArrayList<Material> materials = matm.getMaterialsFromProstheticCode(code);

			pros = new Prosthetic(code, price, functionalities, type, model, company, meas, materials);
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pros;

	}
	
	public void uploadProsthetics(Company c, Prosthetic p) {

		int company_id = c.getId();
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
}