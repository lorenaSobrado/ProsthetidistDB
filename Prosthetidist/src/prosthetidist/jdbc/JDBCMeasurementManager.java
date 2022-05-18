package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import prosthetidist.ifaces.MeasurementsManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurement;

public class JDBCMeasurementManager implements MeasurementsManager {
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

	public Measurement getMeasurementById(int measurement_id) {
		Measurement m = null;
		try {
			String sql = "SELECT * FROM Measurements WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, measurement_id);
			ResultSet rs = prep.executeQuery();
			// get the values , @CHECK
			float lengthiness = rs.getFloat(2);
			float width = rs.getFloat(3);
			float weight = rs.getFloat(4);

			m = new Measurement(measurement_id, lengthiness, width, weight);
			rs.close();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return m;

	}
	
}
