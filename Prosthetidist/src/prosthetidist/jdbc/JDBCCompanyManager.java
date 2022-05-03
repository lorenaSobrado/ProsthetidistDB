package prosthetidist.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import prosthetidist.ifaces.CompanyManager; //REVISAR

public class JDBCCompanyManager implements CompanyManager {

	private JDBCManager manager;

	public JDBCCompanyManager(JDBCManager m) {
		this.manager = m;
	}

//@TODO addCompany mediante base de datos pero la relacion con el register????

//@Override
//@help tendria sentido hacer un listbycompanyID?

	public void deleteCompany(int companyId) {
		try {
			String sql = "DELETE FROM Company WHERE Id= ?";
			PreparedStatement p = manager.getConnection().preparedStatement(sql);
			p.setInt(1, companyId);
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
