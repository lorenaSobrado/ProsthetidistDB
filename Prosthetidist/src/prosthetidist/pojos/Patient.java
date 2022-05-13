package prosthetidist.pojos;

import java.io.Serializable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


public class Patient implements Serializable {
	
	
	private static final long serialVersionUID = -5369533665399855865L;
	
	
	private Integer id;
	private String name;
	private String email;
	private Integer phone;
	private String address;
	private String notes;
	private LocalDate dob;
	
	//CONSTRUCTORS
	
	public Patient() {
	  super();
	  
	}
	
	//test constructor

	public Patient(Integer id, String name, String email, Integer phone, String address, String notes, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.notes = notes;
		this.dob = dob;
	}

	//EQUALS AND HASH CODE
	
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}
	
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate d) {
		this.dob = d;
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", notes=" + notes + ", dob=" + dob + "]";
	}
	
	
	
	

}
