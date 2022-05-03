package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import prosthetidist.ifaces.MeasurementsManager;
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Measurements;

public class JDBCMeasurementsManager implements MeasurementsManager{
	private JDBCManager manager;

	public JDBCMeasurementsManager(JDBCManager m) {
		this.manager = m;
	}
	
	

public Measurements getMeasurementById (int measurement_id){
	Measurements m = null;
	try {
		String sql= "SELECT * FROM Measurements WHERE Id=?";
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		prep.setInt(1, measurement_id);
		ResultSet rs = prep.executeQuery();
		//get the values , @CHECK
		float lengthiness= rs.getFloat(2);
		float width =rs.getFloat(3);
		float weight =rs.getFloat(4);
		
		 m = new Measurements(measurement_id,lengthiness,width,weight);
		 rs.close();
		 prep.close();
	}
	catch (SQLException ex) {
		ex.printStackTrace();
	}
	 return m;

}
}
