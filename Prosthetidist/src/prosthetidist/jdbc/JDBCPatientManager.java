package prosthetidist.jdbc;

import java.sql.PreparedStatement;

import prosthetidist.ifaces.PatientManager;
import prosthetidist.pojos.Prosthetic;

public class JDBCPatientManager implements PatientManager {
	private JDBCManager manager;

	public JDBCPatientManager(JDBCManager m) {
		this.manager = m;
	}
	
	//@TODO materials
	
	public void designProsthetic () {
		Prosthetic p= null;
		try {
			String sql= "INSERT INTO Prosthetic (Functionalities, Type, Measurements) VALUES (?,?,?)";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, p.getFunctionalities());
			prep.setString(2, p.getType());
			prep.setInt(3, p.getMeasurements().getId());
			//@HELP funciona?
			p.getCompany().setId(null);

			prep.executeUpdate();
			//luego esta prosthetic se mete en la db, lo de null se ha metido???????
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
