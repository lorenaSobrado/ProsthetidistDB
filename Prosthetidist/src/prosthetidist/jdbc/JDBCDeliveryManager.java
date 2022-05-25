package prosthetidist.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import prosthetidist.pojos.Delivery;

public class JDBCDeliveryManager {
	
	private JDBCManager manager;
	
	public JDBCDeliveryManager(JDBCManager m) {
		this.manager = m;
	}
	
	public Delivery getPremiumDelivery() {
		Delivery d = new Delivery();
		try {
			Statement stat = manager.getConnection().createStatement();
			String sql = "SELECT * FROM Delivery WHERE type = Premium";
			ResultSet rs = stat.executeQuery(sql);

			d.setType(rs.getString("type"));
			d.setDescription(rs.getString("description"));
			d.setPrice(rs.getFloat("price"));

			rs.close();
			stat.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return d;
	}

}
