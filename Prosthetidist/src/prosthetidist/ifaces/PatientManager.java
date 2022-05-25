package prosthetidist.ifaces;

import java.sql.SQLException;

import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;

public interface PatientManager {

	public void designProsthetic(String functionalities, String type, Measurement measurement);
	public void addPatient (Patient p) throws SQLException;
	public Patient getPatientByEmail (String email);

}
