
package prosthetidist.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prosthetics implements Serializable{

	private static final long serialVersionUID = -6066466581324943260L;
	
	//ATTRIBUTES OF THE POJO
	
	private Integer code;
	private Float price;
	private String abilities;
	private String type;
	private String model;
	private Company company;
	private List<Measurements> measuremetsList;
	private List<Materials> materials;
	private Patient patient;
	private List<Invoice> invoices;
	
	//CONSTRUCTORS
	
	public Prosthetics() {
		super();
		this.measuremetsList = new ArrayList();
		this.materials = new ArrayList();
		this.invoices = new ArrayList();
	}
	
	//EQUALS

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prosthetics other = (Prosthetics) obj;
		return Objects.equals(code, other.code);
	}

	
	//GETTERS AND SETTERS
	

	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public Float getPrice() {
		return price;
	}


	public void setPrice(Float price) {
		this.price = price;
	}


	public String getAbilities() {
		return abilities;
	}


	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}
	
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	//TO STRING

	@Override
	public String toString() {
		return "Prosthetics [code=" + code + ", price=" + price + ", abilities=" + abilities + ", type=" + type
				+ ", model=" + model + "]";
	}
	
	
	

	
	
	

}
