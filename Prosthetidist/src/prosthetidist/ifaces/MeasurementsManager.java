package prosthetidist.ifaces;

import prosthetidist.pojos.Measurement;

public interface MeasurementsManager {
	
	public void addMeasurement(Measurement m);
	public Measurement getMeasurementById (int measurement_id);
	
}
