package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

public interface ProstheticsManager {
	
	public List<Prosthetic> listProstheticsWithCompanyId ();
	public List<Prosthetic> listProstheticsWithoutCompanyId();
	public void uploadProsthetics (Company c, Prosthetic p);
	public Prosthetic getProstheticByCode(Integer code);
	public void deleteProsthetic(Prosthetic p);

}
