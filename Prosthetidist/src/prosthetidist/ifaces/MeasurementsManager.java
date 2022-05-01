package prosthetidist.ifaces;

import prosthetidist.pojos.Measurements;

public interface MeasurementsManager {
	public Measurements getMeasurementById (int measurement_id);
	//returns a object from the class Measurement by its id
}
