package prosthetidist.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Delivery implements Serializable {
	
	private static final long serialVersionUID = -2146109168583634096L;
	
	//ATTRIBUTES OF THE POJO
	
	private String type;
	private String description;
	private float price;
	private List<Invoice> invoices;
	
	//CONSTRUCTORS
	
	public Delivery() {
		super();
		this.invoices = new ArrayList();
	}
	
	//EQUALS

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(type, other.type);
	}
	
	//GETTERS AND SETTERS

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	
	//TO STRING

	@Override
	public String toString() {
		return "Delivery [Type=" + type + ", Description=" + description + ", Price=" + price + "]";
	}
	
	
     
}
