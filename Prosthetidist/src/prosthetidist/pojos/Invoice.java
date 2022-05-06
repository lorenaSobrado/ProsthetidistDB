 package prosthetidist.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice implements Serializable{
	
	
	private static final long serialVersionUID = -3481707326557703120L;
	
	private Integer id;
	private LocalDate datePurchase;
	private boolean purchase;
	private String paymentMethod;
	private String delivery_type;
	private Integer patient_id;
	private Integer prosthetic_code;
	
	
	public Invoice() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDate getDatePurchase() {
		return datePurchase;
	}


	public void setDatePurchase(LocalDate datePurchase) {
		this.datePurchase = datePurchase;
	}


	public boolean isPurchase() {
		return purchase;
	}


	public void setPurchase(boolean purchase) {
		this.purchase = purchase;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	@Override
	public String toString() {
		return "Invoice [Id=" + id + ", DatePurchase=" + datePurchase + ", Purchase=" + purchase + ", PaymentMethod="
				+ paymentMethod + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
