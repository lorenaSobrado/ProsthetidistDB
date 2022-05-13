package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Prosthetic;

public interface ProstheticsManager {
	public List<Prosthetic> listAllProstheticsWithCompanyID ();
	//returns a list of all prosthetics of all companies. All the prosthetics from the DB

}
