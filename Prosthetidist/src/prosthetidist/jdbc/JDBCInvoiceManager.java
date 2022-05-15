package prosthetidist.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import prosthetidist.ifaces.InvoiceManager;
import prosthetidist.pojos.*;

public class JDBCInvoiceManager implements InvoiceManager {
	private JDBCManager manager;
	private JDBCProstheticsManager pm;


	public JDBCInvoiceManager(JDBCManager m) {
		this.manager = m;
	}
	
	
 	
public void addProstheticToCart(Invoice i, Patient pa, Prosthetic pros) {
		try {
			String sql = "INSERT Invoice SET(purchase, patient_id, prosthetic_code) VALUES (?,?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setBoolean(1, false);
			p.setInt(2, pa.getId());
			p.setInt(3, pros.getCode());
		
			
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
}
//TODO la prosthetic que te devuelve debe tener el id de una invoice??
public ArrayList <Prosthetic> patientSelection (Patient pa){
	
	ArrayList<Prosthetic> prostheticInvoice = new ArrayList <Prosthetic>();
	int patientId = pa.getId();
	Prosthetic pros=null;
	try {
		Statement stat = manager.getConnection().createStatement();
		String sql = "SELECT prosthetic_code FROM Invoice WHERE patient_id= ? AND purchase= FALSE";
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()){
			int pcode=rs.getInt("prosthetic_code");
			pros=pm.getProstheticByCode(pcode);
			pros.setPatient(pa);
			prostheticInvoice.add(pros);
		}
	}catch (SQLException ex) {
		ex.printStackTrace();
	}
	return prostheticInvoice;
	
}
public void updateInvoice (Invoice i){

try {
	String sql = "UPDATE Invoice " + "SET purchase = ?"+ "SET datePurchase=?" + "paymentMethod=?" +"delivery_type=?";
	PreparedStatement p = manager.getConnection().prepareStatement(sql);
	p.setBoolean(1, true);
	LocalDate datePurchase= LocalDate.now();
	datePurchase= i.getdatePurchase();
	p.setDate(2, Date.valueOf(datePurchase));	
	p.setString(3, i.getPaymentMethod());
	p.setString(4, i.getDelivery_type());
	
	p.executeUpdate();
} catch (SQLException ex) {
	ex.printStackTrace();
}
}

}

