package prosthetidist.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Measurement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715526459276722419L;
	private Integer id;
	private float lengthiness;
	private float width;
	private float weight;
//CONSTRUCTOR
	public Measurement() {
		super();
	}

    public Measurement(int id, float lengthiness, float width, float weight) {
    	this.id=id;
    	this.lengthiness=lengthiness;
    	this.width=width;
    	this.weight=weight;
    	
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getLengthiness() {
		return lengthiness;
	}

	public void setLengthiness(float length) {
		this.lengthiness = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Measurements [Id=" + id + ", Length=" + lengthiness + ", Width=" + width + ", Weight=" + weight + "]";
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
		Measurement other = (Measurement) obj;
		return Objects.equals(id, other.id);
	}
	
}
