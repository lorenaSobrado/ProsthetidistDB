package prosthetidist.pojos;

import java.io.Serializable;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Measurement")
@XmlType(propOrder = { "lengthiness", "width", "weight" })
public class Measurement implements Serializable{

	private static final long serialVersionUID = -4715526459276722419L;
	@XmlTransient
	private Integer id;
	@XmlElement
	private float lengthiness;
	@XmlElement
	private float width;
	@XmlElement
	private float weight;
	
	
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
		return "Measurements [Length=" + lengthiness + ", Width=" + width + ", Weight=" + weight + "]";
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
