package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

public interface ProstheticsManager {
	public List<Prosthetic> listAllProstheticsWithCompanyId ();
	//returns a list of all prosthetics of all companies. All the prosthetics from the DB
	public void uploadProsthetics (Company c, Prosthetic p);//upload a prosthetic 
	public Prosthetic getProstheticByCode(Integer code);

}
