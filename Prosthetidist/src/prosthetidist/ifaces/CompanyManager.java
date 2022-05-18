package prosthetidist.ifaces;

import java.util.List;

import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;
import prosthetidist.pojos.Prosthetic;

public interface CompanyManager {
	
	public void addCompany (Company c);
	public void deleteCompany (int companyId); 	//delete a company by its id
	public Company getCompanyById (int company_id); //get a company by its id
	public List<Prosthetic> listProstheticsWithoutCompanyID();
	public void offerDesign (Prosthetic prosthetic);
	

}
