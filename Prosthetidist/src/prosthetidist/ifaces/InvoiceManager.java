package prosthetidist.ifaces;

import java.util.ArrayList;

import exceptions.CartException;
import prosthetidist.pojos.*;

public interface InvoiceManager {
	
	public void addProstheticToCart(Patient pa, Integer prosCode) throws CartException;
	public void deleteProstheticFromCart(Patient pa, Integer prosCode);
	public void updateInvoice (Patient patient, Integer prosCode, Long creditCard, String deliveryType);
	public ArrayList<Prosthetic> getPatientSelection(Patient pa);
	
}
