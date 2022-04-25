package prosthetidist.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Measurements implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4715526459276722419L;
	private Integer Id;
	private float Length;
	private float Width;
	private float Weight;

	public Measurements() {
		super();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public float getLength() {
		return Length;
	}

	public void setLength(float length) {
		Length = length;
	}

	public float getWidth() {
		return Width;
	}

	public void setWidth(float width) {
		Width = width;
	}

	public float getWeight() {
		return Weight;
	}

	public void setWeight(float weight) {
		Weight = weight;
	}

	@Override
	public String toString() {
		return "Measurements [Id=" + Id + ", Length=" + Length + ", Width=" + Width + ", Weight=" + Weight + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Measurements other = (Measurements) obj;
		return Objects.equals(Id, other.Id);
	}
	
}
