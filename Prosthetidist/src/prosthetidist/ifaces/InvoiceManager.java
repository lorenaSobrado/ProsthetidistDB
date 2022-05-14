package prosthetidist.ifaces;

import prosthetidist.pojos.*;

public interface InvoiceManager {
	public void addProstheticToCart(Invoice i, Patient pa, Prosthetic pros);
	public void updateInvoice (Invoice i);
}
