
package prosthetidist.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prosthetic implements Serializable{

	private static final long serialVersionUID = -6066466581324943260L;
	
	//ATTRIBUTES OF THE POJO
	
	private Integer code;
	private Float price;
	private String functionalities;
	private String type;
	private String model;
	private Company company;
	private Measurement measurements;
	private List<Material> materials;
	private Patient patient;
	private List<Invoice> invoices;
	
	//CONSTRUCTORS
	
	public Prosthetic() {
		super();
		//?
		this.materials = new ArrayList();
		this.invoices = new ArrayList();
	}
	//@TODO problemas
	public Prosthetic (Integer code, Float price, String functionalities, String type, String model, Company company, Measurement measurement) {
		this.code=code;
		this.price=price;
		this.functionalities=functionalities;
		this.type=type;
		this.model=model;
		this.company=company;
		this.measurements=measurement;
		
	}
//test constructor
	public Prosthetic (Integer code, Float price, String functionalities, String type, String model, Company company) {
			this.code=code;
			this.price=price;
			this.functionalities=functionalities;
			this.type=type;
			this.model=model;
			this.company=company;
			
		}
	//test constructor
	public Prosthetic(Integer code, Float price, String abilities, String type, String model) {
		this.code=code;
		this.price=price;
		this.functionalities=abilities;
		this.type=type;
		this.model=model;
	}
	//test constructor for design
	
			public Prosthetic(String functionalities, String type, Measurement m) {
				this.functionalities=functionalities;
				this.type=type;
				this.measurements=m;
			}
		
	//test constructor for listProstheticsWithoutCompanyID
	
		public Prosthetic(Integer code, String abilities, String type, Measurement m) {
			this.code=code;
			this.functionalities=abilities;
			this.type=type;
			this.measurements=m;
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
		Prosthetic other = (Prosthetic) obj;
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
	
	public Measurement getMeasurements() {
		return measurements;
	}
	public void setMeasurements(Measurement measurements) {
		this.measurements = measurements;
	}
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
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
