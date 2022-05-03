package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Prosthetics;

public interface ProstheticsManager {
	public List<Prosthetics> listAllProsthetics ();
	//returns a list of all prosthetics of all companies. All the prosthetics from the DB

}
