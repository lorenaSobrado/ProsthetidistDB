package prosthetidist.ifaces;

import prosthetidist.pojos.Measurement;
import prosthetidist.pojos.Patient;

public interface PatientManager {

	public void designProsthetic (Measurement m);
	public void addPatient (Patient p);

}
