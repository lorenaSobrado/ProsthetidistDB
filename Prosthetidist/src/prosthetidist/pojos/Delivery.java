package prosthetidist.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Delivery implements Serializable {
	
	private static final long serialVersionUID = -2146109168583634096L;
	
	//ATTRIBUTES OF THE POJO
	
	private String Type;
	private String Description;
	private float Price;
	private List<Invoice> Invoices;
	
	//CONSTRUCTORS
	
	public Delivery() {
		super();
		this.Invoices = new ArrayList();
	}
	
	//EQUALS

	@Override
	public int hashCode() {
		return Objects.hash(Type);
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
		return Objects.equals(Type, other.Type);
	}
	
	//GETTERS AND SETTERS

	public String getType() {
		return Type;
	}


	public void setType(String type) {
		Type = type;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		Price = price;
	}
	
	//TO STRING

	@Override
	public String toString() {
		return "Delivery [Type=" + Type + ", Description=" + Description + ", Price=" + Price + "]";
	}
	
	
     
}
