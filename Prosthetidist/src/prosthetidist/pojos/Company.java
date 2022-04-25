
package prosthetidist.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable{
	
	private static final long serialVersionUID = -2777088951087437973L;
	//ATTRIBUTES OF THE POJO
	
	
	private Integer id;
	private String name;
	private String email;
	private Integer phone;
	private List<Prosthetic> Prosthetics;
	
	//CONSTRUCTORS
	
	public Company() {
		super();
		this.Prosthetics = new ArrayList();
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
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", Prosthetics="
				+ Prosthetics + "]";
	}
	

}
