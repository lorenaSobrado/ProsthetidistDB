package prosthetidist.ifaces;

import prosthetidist.pojos.Measurement;

public interface MeasurementsManager {
	
	public void addMeasurement(Measurement m);
	public Measurement getMeasurementById (Integer measurement_id);
	public Measurement getMeasurement(Float length, Float width, Float weight);
	
}
