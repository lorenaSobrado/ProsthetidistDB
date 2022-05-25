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
	private JDBCProstheticManager pm;

	public JDBCInvoiceManager(JDBCManager m) {
		this.manager = m;
		this.pm = new JDBCProstheticManager(m);
	}

	public void addProstheticToCart(Patient pa, Integer prosCode) {
		try {
			String sql = "INSERT INTO Invoice SET(purchase, patient_id, prosthetic_code) VALUES (?,?,?)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setBoolean(1, false);
			p.setInt(2, pa.getId());
			p.setInt(3, prosCode);

			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteProstheticFromCart(Patient pa, Integer prosCode) {
		try {
			String sql = "DELETE * FROM Invoice WHERE patient_id = " + pa.getId() + " AND prosthetic_code = " + prosCode + " AND purchase = FALSE)";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Prosthetic> getPatientSelection(Patient pa) {

		ArrayList<Prosthetic> cart = new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT prosthetic_code FROM Invoice WHERE patient_id = ? AND purchase = FALSE"; 
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, pa.getId());
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer pcode = rs.getInt("prosthetic_code");
				Prosthetic pros = pm.getProstheticByCode(pcode);
				cart.add(pros);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return cart;

	}

	public void updateInvoice(Patient patient, Prosthetic pros, Integer creditCard, String deliveryType) {

		try {
			String sql = "UPDATE Invoice " + "SET purchase = ? " + "SET datePurchase = ? " + "creditCard = ?"
					+ "delivery_type = ? WHERE patient_id = ? AND prosthetic_code = ? AND purchase = ?";
			PreparedStatement p = manager.getConnection().prepareStatement(sql);
			p.setBoolean(1, true);
			LocalDate datePurchase = LocalDate.now();
			p.setDate(2, Date.valueOf(datePurchase));
			p.setInt(3, creditCard);
			p.setString(4, deliveryType);
			p.setInt(5, patient.getId());
			p.setInt(6, pros.getCode());
			p.setBoolean(7, false);

			p.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
