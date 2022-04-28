package prosthetidist.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Materials implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001802543288251520L;
	private String Name;
	private String Strength;
	private float Price;
	private String TemperatureResistence;
	private String Flexibility;
	
	public Materials () {
		super();
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStrength() {
		return Strength;
	}

	public void setStrength(String strength) {
		Strength = strength;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public String getTemperatureResistence() {
		return TemperatureResistence;
	}

	public void setTemperatureResistence(String temperatureResistence) {
		TemperatureResistence = temperatureResistence;
	}

	public String getFlexibility() {
		return Flexibility;
	}

	public void setFlexibility(String flexibility) {
		Flexibility = flexibility;
	}

	@Override
	public String toString() {
		return "Materials [Name=" + Name + ", Strength=" + Strength + ", Price=" + Price + ", TemperatureResistence="
				+ TemperatureResistence + ", Flexibility=" + Flexibility + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materials other = (Materials) obj;
		return Objects.equals(Name, other.Name);
	}
	
}
