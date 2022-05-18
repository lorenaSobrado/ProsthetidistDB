package prosthetidist.ifaces;

import java.util.ArrayList;

import prosthetidist.pojos.*;

public interface InvoiceManager {
	public void addProstheticToCart(Patient pa, Integer prosCode);
	public void deleteProstheticFromCart(Patient pa, Integer prosCode);
	public void updateInvoice (Invoice i);
	public ArrayList<Prosthetic> patientSelection(Patient pa);
}
