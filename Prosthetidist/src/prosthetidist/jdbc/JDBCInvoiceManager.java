package prosthetidist.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import prosthetidist.ifaces.InvoiceManager;
import prosthetidist.pojos.*;

public class JDBCInvoiceManager implements InvoiceManager {
	private JDBCManager manager;

	public JDBCInvoiceManager(JDBCManager m) {
		this.manager = m;
	}
	
	
	// add invoice
	
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

