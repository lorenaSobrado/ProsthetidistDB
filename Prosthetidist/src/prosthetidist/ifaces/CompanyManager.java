package prosthetidist.ifaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetic;

public interface CompanyManager {
	
	public void addCompany (Company c) throws SQLException;
	public void deleteCompany (Company c); 	
	public Company getCompanyById (int company_id); 
	public void offerDesign (Prosthetic prosthetic);
	public List<Prosthetic> listProstheticsOfCompany(Company c);


}
