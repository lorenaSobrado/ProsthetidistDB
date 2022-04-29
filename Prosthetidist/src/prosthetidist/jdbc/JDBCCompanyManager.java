package prosthetidist.jdbc;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import prosthetidist.ifaces.CompanyManager; //REVISAR
import prosthetidist.pojos.Company;
import prosthetidist.pojos.Prosthetics;

public class JDBCCompanyManager implements CompanyManager {
	
private JDBCManager manager;

public JDBCCompanyManager(JDBCManager m) {
	this.manager = m;
}

//@TODO addCompany mediante base de datos pero la relacion con el register????

//@Override

public void deleteCompany (int companyId) {
	try {
		String sql = "DELETE FROM Company WHERE Id= ?";
		PreparedStatement p = manager.getConnection().prepareStatement(sql);
		p.setInt(1, companyId);
		p.executeUpdate();
	}
	catch (SQLException ex) {
		ex.printStackTrace();
	}
}
/*
public Company getCompanyById (int company_id){
	try {
		String sql= "SELECT * FROM Company WHERE Id=?";
		PreparedStatement prep= manager.getConnection().prepareStatement(sql);
		prep.setInt(1, company_id);
		//prep.execute
		//result set, igual q jdbc prosthetics con select
	}
	catch (SQLException ex) {
		ex.printStackTrace();
	}
}

*/
public void uploadProsthetics (Prosthetics p) {
	
	try {
		String sql= "INSERT INTO Prosthetic (Price, Functionalities, Type, Model, Company_id, Measurement_id) VALUES (?,?,?,?,?,?)";
		PreparedStatement prep = manager.getConnection().prepareStatement(sql);
		prep.setFloat(1, p.getPrice());
		prep.setString(2, p.getFunctionalities());
		prep.setString(3, p.getType());
		prep.setString(4, p.getModel());
		prep.setInt(5, p.getCompany().getId());
		prep.setInt(6, p.getMeasurements().getId());
		prep.executeUpdate();
		
	}
	catch (Exception ex) {
		ex.printStackTrace();
	}
}


/*
public void addCompany(Company c) {
	try {
		String sql= "INSERT INTO Company (Name, Email, Phone) VALUES (?,?,?)"
	}
	catch (Exception ex) {
		ex.printStackTrace();
	}
}*/
}
