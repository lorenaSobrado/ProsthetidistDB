package prosthetidist.ifaces;

import prosthetidist.pojos.Measurement;

public interface MeasurementsManager {
	public Measurement getMeasurementById (int measurement_id);
	//returns a object from the class Measurement by its id
}
