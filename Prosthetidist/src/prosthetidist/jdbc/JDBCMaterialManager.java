package prosthetidist.jdbc;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import prosthetidist.ifaces.MaterialManager;
import prosthetidist.pojos.Material;
import prosthetidist.pojos.Prosthetic;

public class JDBCMaterialManager implements MaterialManager {

	private JDBCManager manager;

	public JDBCMaterialManager(JDBCManager m) {
		this.manager = m;
	}

	public Material getMaterialByName(String name) {
		Material mat = null;
		try {
			String sql = "SELECT * FROM Material WHERE name = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, name);
			ResultSet rs = prep.executeQuery();

			Float price = rs.getFloat("price");
			String strength = rs.getString("strength");
			String flexibility = rs.getString("flexibility");
			String temperatureResistance = rs.getString("temperatureResistance");

			mat = new Material(name, price, strength, flexibility, temperatureResistance);

			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return mat;
	}

	public ArrayList<Material> getMaterialsFromProstheticCode(Integer code) {
		ArrayList<Material> materials = new ArrayList<Material>();

		try {
			Statement stat = manager.getConnection().createStatement();
			String sql2 = "SELECT material_name FROM ProstheticHasMaterials WHERE prosthetic_code = " + code;
			ResultSet rs = stat.executeQuery(sql2);
			while (rs.next()) {
				String name = rs.getString("material_name");
				Material mat = this.getMaterialByName(name);
				materials.add(mat);
			}
			rs.close();
			stat.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return materials;
	}

	public void uploadMaterialsOfProsthetic(Material material, Prosthetic prosthetic) {
		try {
			String sql = "INSERT INTO ProstheticHasMaterials (prosthetic_code, material_name) VALUES (?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, prosthetic.getCode());
			prep.setString(2, material.getName());

			prep.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
