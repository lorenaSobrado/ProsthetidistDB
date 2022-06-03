package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import prosthetidist.ifaces.MeasurementManager;
import prosthetidist.pojos.Measurement;

public class JDBCMeasurementManager implements MeasurementManager {
	private JDBCManager manager;

	public JDBCMeasurementManager(JDBCManager m) {
		this.manager = m;
	}

	public void addMeasurement(Measurement m) {

		try {
			String sql = "INSERT INTO Measurement (lengthiness, width, weight) VALUES (?,?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setFloat(1, m.getLengthiness());
			p.setFloat(2, m.getWidth());
			p.setFloat(3, m.getWeight());
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Measurement getMeasurementById(Integer measurement_id) {
		Measurement m = null;
		try {
			String sql = "SELECT * FROM Measurement WHERE id = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, measurement_id);
			ResultSet rs = prep.executeQuery();
			Float lengthiness = rs.getFloat("lengthiness");
			Float width = rs.getFloat("width");
			Float weight = rs.getFloat("weight");

			m = new Measurement(measurement_id, lengthiness, width, weight);
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return m;
	}

	public Measurement getMeasurement(Float length, Float width, Float weight) {
		Measurement m = null;
		try {
			String sql = "SELECT id FROM Measurement WHERE lengthiness = ? AND width = ? AND weight = ?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setFloat(1, length);
			prep.setFloat(2, width);
			prep.setFloat(3, weight);
			ResultSet rs = prep.executeQuery();// throws exception if query does not return a ResultSet object
			Integer id = rs.getInt("id"); // returns 0 if SQL value is null
			m = this.getMeasurementById(id);

			rs.close();
			prep.close();
		} catch (SQLException ex) {
//			System.out.println("No measurement, well create it");
			return m; // returns null if the no rs exception is thrown
		}
		return m;
	}
}
