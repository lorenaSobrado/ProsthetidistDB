package prosthetidist.ifaces;

import java.util.List;


import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;
import prosthetidist.pojos.Prosthetic;

public interface CompanyManager {
	
	public void addCompany (Company c);
	public void deleteCompany (Company c); 	
	public Company getCompanyById (int company_id); 
	public void offerDesign (Prosthetic prosthetic);
	

}
