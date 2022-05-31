package prosthetidist.pojos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Company")
@XmlType(propOrder = { "name", "email", "phone", "prosthetics" })
public class Company implements Serializable{


	
	private static final long serialVersionUID = -2777088951087437973L;
	
	//ATTRIBUTES OF THE POJO
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String email;
	@XmlElement
	private Integer phone;
	@XmlElement
	@XmlElementWrapper(name = "Prosthetics")
	private List<Prosthetic> prosthetics;
	
	//CONSTRUCTORS
	
	public Company() {
		super();
		this.prosthetics = new ArrayList<>();
	}
	
	//@CHECK
	public Company(Integer id, String name, String email, Integer phone) {
		//necesita super?
		this.id=id;
		this.name=name;
		this.email=email;
		this.phone=phone;
	}
	
	//EQUALS
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {//Equals: true if there are not in the same memory but have the same characteristcs
		if (this == obj) //Same memory reference 
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) //2 objects not in the same class
			return false;
		
		Company other = (Company) obj; //cast obj to Company
		return Objects.equals(id, other.id);
	}
	//returns true if we have two companies with the same id
	
	//GETTERS AND SETTERS

	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	
	//TO STRING

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + this.prosthetics.toString() + "]";
	}

	public void addProsthetic (Prosthetic p) {

		if (!prosthetics.contains(p)) {
			prosthetics.add(p);
		}
		
	}
	

	public void deleteProsthetic (Prosthetic p) {
		prosthetics.remove(p);
	}
	
}
	


