package prosthetidist.pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Material implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001802543288251520L;
	
	private String name;
	private String strength;
	private float price;
	private String temperatureResistence;
	private String flexibility;
	private List <Prosthetic> prosthetics;
	
	
	public Material () {
		super();
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTemperatureResistence() {
		return temperatureResistence;
	}

	public void setTemperatureResistence(String temperatureResistence) {
		this.temperatureResistence = temperatureResistence;
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
		return "Materials [Name=" + name + ", Strength=" + strength + ", Price=" + price + ", TemperatureResistence="
				+ temperatureResistence + ", Flexibility=" + flexibility + "]";
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
