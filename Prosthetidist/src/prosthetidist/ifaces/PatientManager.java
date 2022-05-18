package prosthetidist.ifaces;

import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;

public interface PatientManager {

	public void designProsthetic(String functionalities, String type, Measurement measurement);
	public void addPatient (Patient p);
	public Patient getPatientByEmail (String email);


}
