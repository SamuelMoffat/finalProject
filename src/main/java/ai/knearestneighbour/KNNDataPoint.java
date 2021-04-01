package ai.knearestneighbour;

import datageneration.DataPoint;

import java.math.BigDecimal;

public class KNNDataPoint extends DataPoint implements Comparable<KNNDataPoint> {

    private BigDecimal distance;

    KNNDataPoint(DataPoint dataPoint){
        //copy all fields from dummy datapoint to new object
        setPressure(dataPoint.getPressure());
        setLocationName(dataPoint.getLocationName());
        setLatitude(dataPoint.getLatitude());
        setLongitude(dataPoint.getLongitude());
        setDate(dataPoint.getDate());
        setError(dataPoint.getisError());
        setPressureLabel(dataPoint.getPressureLabel());
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(KNNDataPoint o) {
        return this.getDistance().compareTo(o.getDistance());
    }
}
