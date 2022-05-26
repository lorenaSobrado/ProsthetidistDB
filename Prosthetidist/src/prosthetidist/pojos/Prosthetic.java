
package prosthetidist.pojos;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.swing.ImageIcon;

public class Prosthetic implements Serializable {

	private static final long serialVersionUID = -6066466581324943260L;

	// ATTRIBUTES OF THE POJO

	private Integer code;
	private Float price;
	private String functionalities;
	private String type;
	private String model;

	// @ManyToOne (fetch=FetchType.LAZY)
	private Company company;
	private Measurement measurement;
	private ArrayList<Material> materials;
	private Patient patient; // PARA QUE PONEMOS ESTO?? despues habra que hacer una lista en pacientes de
								// protesis
	private ArrayList<Invoice> invoices;

	// CONSTRUCTORS

	public Prosthetic() {
		super();
		// ?
		this.materials = new ArrayList<Material>();
		this.invoices = new ArrayList<Invoice>();
	}

	public Prosthetic(Integer code, Float price, String functionalities, String type, String model, Company company,
			Measurement measurement) {
		this.code = code;
		this.price = price;
		this.functionalities = functionalities;
		this.type = type;
		this.model = model;
		this.company = company;
		this.measurement = measurement;

	}

	public Prosthetic(Integer code, Float price, String functionalities, String type, String model,
			Measurement measurements, ArrayList<Material> materials) {
		super();
		this.code = code;
		this.price = price;
		this.functionalities = functionalities;
		this.type = type;
		this.model = model;
		this.measurement = measurements;
		this.materials = materials;
	}

	public Prosthetic(Float price, String functionalities, String type, String model, Company company,
			Measurement measurements, Patient patient) {
		super();
		this.price = price;
		this.functionalities = functionalities;
		this.type = type;
		this.model = model;
		this.company = company;
		this.measurement = measurements;
		this.patient = patient;
	}

	public Prosthetic(Integer code, Float price, String functionalities, String type, String model, Company company,
			Measurement measurements, ArrayList<Material> materials) {
		super();
		this.code = code;
		this.price = price;
		this.functionalities = functionalities;
		this.type = type;
		this.model = model;
		this.company = company;
		this.measurement = measurements;
		this.materials = materials;
	}

	public Prosthetic(Integer code, String functionalities, String type, Measurement measurements,
			ArrayList<Material> materials) {
		super();
		this.code = code;
		this.functionalities = functionalities;
		this.type = type;
		this.measurement = measurements;
		this.materials = materials;
	}

	// test constructor
	public Prosthetic(Integer code, Float price, String functionalities, String type, String model, Company company) {
		this.code = code;
		this.price = price;
		this.functionalities = functionalities;
		this.type = type;
		this.model = model;
		this.company = company;

	}

	// test constructor
	public Prosthetic(Integer code, Float price, String functionalities, String type, String model) {
		this.code = code;
		this.price = price;
		this.functionalities = functionalities;
		this.type = type;
		this.model = model;
	}
	// test constructor for design

	public Prosthetic(String functionalities, String type, Measurement m) {
		this.functionalities = functionalities;
		this.type = type;
		this.measurement = m;
	}

	// test constructor for listProstheticsWithoutCompanyID

	public Prosthetic(Integer code, String abilities, String type, Measurement m) {
		this.code = code;
		this.functionalities = abilities;
		this.type = type;
		this.measurement = m;
	}

	// EQUALS

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

	// GETTERS AND SETTERS

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

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public ArrayList<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Image hasPlastic() {
		for (Material m : this.materials) {
			if (m.getName().equalsIgnoreCase("plastic")) {
				Image img = new ImageIcon(this.getClass().getResource("/hasMaterial.png")).getImage();
				return img;
			}
		}
		Image img = new ImageIcon(this.getClass().getResource("/noMaterial.png")).getImage();
		return img;
	}

	public Image hasCarbonFiber() {
		for (Material m : this.materials) {
			if (m.getName().equalsIgnoreCase("carbon fiber")) {
				Image img = new ImageIcon(this.getClass().getResource("/hasMaterial.png")).getImage();
				return img;
			}
		}
		Image img = new ImageIcon(this.getClass().getResource("/noMaterial.png")).getImage();
		return img;
	}

	public Image hasAluminium() {
		for (Material m : this.materials) {
			if (m.getName().equalsIgnoreCase("aluminium")) {
				Image img = new ImageIcon(this.getClass().getResource("/hasMaterial.png")).getImage();
				return img;
			}
		}
		Image img = new ImageIcon(this.getClass().getResource("/noMaterial.png")).getImage();
		return img;
	}

	// TO STRING

	@Override
	public String toString() {
		return "Prosthetics [code=" + code + ", price=" + price + ", abilities=" + functionalities + ", type=" + type
				+ ", model=" + model + "]";
	}

}
