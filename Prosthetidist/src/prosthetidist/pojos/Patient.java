package prosthetidist.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Patient implements Serializable {
	
	
	private static final long serialVersionUID = -5369533665399855865L;
	
	private Integer id;
	private String name;
	private String email;
	private Integer phone;
	private String adress;
	private String notes;
	private Date dob;
	
	
	public Patient() {
	  super();
	  
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}


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


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date s) {
		this.dob = s;
	}


	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", adress=" + adress
				+ ", notes=" + notes + ", dob=" + dob + "]";
	}
	
	
	
	

}
