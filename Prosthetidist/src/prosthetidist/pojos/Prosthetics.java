
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
	private String functionalities;
	private String type;
	private String model;
	private Company company;
	private Measurements measurements;
	private List<Materials> materials;
	private Patient patient;
	private List<Invoice> invoices;
	
	//CONSTRUCTORS
	
	public Prosthetics() {
		super();
		//?
		this.materials = new ArrayList();
		this.invoices = new ArrayList();
	}
	//@TODO problemas
	public Prosthetics (Integer code, Float price, String functionalities, String type, String model, Company company, Measurements measurement) {
		this.code=code;
		this.price=price;
		this.functionalities=functionalities;
		this.type=type;
		this.model=model;
		this.company=company;
		this.measurements=measurement;
		
	}
//test constructor
	public Prosthetics (Integer code, Float price, String functionalities, String type, String model, Company company) {
			this.code=code;
			this.price=price;
			this.functionalities=functionalities;
			this.type=type;
			this.model=model;
			this.company=company;
			
		}
	//test constructor
	public Prosthetics(Integer code, Float price, String abilities, String type, String model) {
		this.code=code;
		this.price=price;
		this.functionalities=abilities;
		this.type=type;
		this.model=model;
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


	public String getFunctionalities() {
		return functionalities;
	}


	public void setFunctionalities(String functionalities) {
		this.functionalities = functionalities;
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
	
	public Measurements getMeasurements() {
		return measurements;
	}
	public void setMeasurements(Measurements measurements) {
		this.measurements = measurements;
	}
	public List<Materials> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Materials> materials) {
		this.materials = materials;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	//TO STRING


	@Override
	public String toString() {
		return "Prosthetics [code=" + code + ", price=" + price + ", abilities=" + functionalities + ", type=" + type
				+ ", model=" + model + "]";
	}
	
	
	

	
	
	

}
