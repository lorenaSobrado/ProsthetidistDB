package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

public interface ProstheticsManager {
	
	public List<Prosthetic> getProstheticsWithCompanyId ();
	public List<Prosthetic> getProstheticsWithoutCompanyId();
	public void uploadProsthetic(Prosthetic p);
	public Prosthetic getProstheticByCode(Integer code);
	public void deleteProsthetic(Prosthetic p);
	public Integer getProstheticCode(Prosthetic p);

}
