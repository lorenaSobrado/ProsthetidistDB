 package prosthetidist.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Invoice implements Serializable{
	
	
	private static final long serialVersionUID = -3481707326557703120L;
	
	private Integer id;
	private LocalDate datePurchase;
	private boolean purchase;
	private Long creditCard;
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


	public LocalDate getdatePurchase() {
		return datePurchase;
	}


	public void setdatePurchase(LocalDate datePurchase) {
		this.datePurchase = datePurchase;
	}


	public boolean isPurchase() {
		return purchase;
	}


	public void setPurchase(boolean purchase) {
		this.purchase = purchase;
	}


	public Long getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(Long creditCard) {
		this.creditCard = creditCard;
	}


	public String getDelivery_type() {
		return delivery_type;
	}


	public void setDelivery_type(String delivery_type) {
		this.delivery_type = delivery_type;
	}


	public Integer getPatient_id() {
		return patient_id;
	}


	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}


	public Integer getProsthetic_code() {
		return prosthetic_code;
	}


	public void setProsthetic_code(Integer prosthetic_code) {
		this.prosthetic_code = prosthetic_code;
	}


	@Override
	public String toString() {
		return "Invoice [Id=" + id + ", DatePurchase=" + datePurchase + ", Purchase=" + purchase + ", PaymentMethod="
				+ creditCard + "]";
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
