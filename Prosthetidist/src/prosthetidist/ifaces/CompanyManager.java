package prosthetidist.ifaces;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

public interface CompanyManager {
	
	public void deleteCompany (int companyId);
	//delete a company by its id
	public Company getCompanyById (int company_id);
	//get a company by its id
	public void uploadProsthetics (Prosthetic p);
	//upload a prosthetic 
}
