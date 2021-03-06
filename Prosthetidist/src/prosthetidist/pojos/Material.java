package prosthetidist.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


public class Material implements Serializable{

	private static final long serialVersionUID = -5001802543288251520L;
	private String name;
	private String strength;
	private String flexibility;
	private String temperatureResistance;
	private List <Prosthetic> prosthetics;
	
	
	public Material () {
		super();
	}

	public Material(String name, String strength, String flexibility, String temperatureResistence) {
		super();
		this.name = name;
		this.strength = strength;
		this.flexibility = flexibility;
		this.temperatureResistance = temperatureResistence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}



	public String getTemperatureResistence() {
		return temperatureResistance;
	}

	public void setTemperatureResistence(String temperatureResistence) {
		this.temperatureResistance = temperatureResistence;
	}

	public String getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(String flexibility) {
		this.flexibility = flexibility;
	}

	
	public List<Prosthetic> getProsthetics() {
		return prosthetics;
	}

	public void setProsthetics(List<Prosthetic> prosthetics) {
		this.prosthetics = prosthetics;
	}

	@Override
	public String toString() {
		return "Materials [Name=" + name + ", Strength=" + strength + ", TemperatureResistence="
				+ temperatureResistance + ", Flexibility=" + flexibility + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
