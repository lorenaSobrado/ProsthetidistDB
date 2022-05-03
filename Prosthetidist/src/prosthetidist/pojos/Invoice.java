 package prosthetidist.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice implements Serializable{
	
	
	private static final long serialVersionUID = -3481707326557703120L;
	
	private Integer Id;
	private LocalDate DatePurchase;
	private boolean Purchase;
	private String PaymentMethod;
	private String DeliveryType;
	private Integer PatientId;
	private Integer ProstheticId;
	
	
	public Invoice() {
		super();
	}


	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public LocalDate getDatePurchase() {
		return DatePurchase;
	}


	public void setDatePurchase(LocalDate datePurchase) {
		DatePurchase = datePurchase;
	}


	public boolean isPurchase() {
		return Purchase;
	}


	public void setPurchase(boolean purchase) {
		Purchase = purchase;
	}


	public String getPaymentMethod() {
		return PaymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}


	@Override
	public String toString() {
		return "Invoice [Id=" + Id + ", DatePurchase=" + DatePurchase + ", Purchase=" + Purchase + ", PaymentMethod="
				+ PaymentMethod + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(Id);
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
		return Objects.equals(Id, other.Id);
	}
	
	
}
