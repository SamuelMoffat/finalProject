package ai.knearestneighbour;

import com.opencsv.bean.CsvBindByPosition;
import datageneration.DataPoint;

public class KNearestNeighbourDataPoint extends DataPoint {
    @CsvBindByPosition(position = 6)
    private String clusterAssigned;

    KNearestNeighbourDataPoint(DataPoint dataPoint){
        //copy all fields from dummy datapoint to new object
        setPressure(dataPoint.getPressure());
        setLocationName(dataPoint.getLocationName());
        setLatitude(dataPoint.getLatitude());
        setLongitude(dataPoint.getLongitude());
        setDate(dataPoint.getDate());
        setError(dataPoint.getisError());
    }

    public String getClusterAssigned() {
        return clusterAssigned;
    }

    public void setClusterAssigned(String clusterAssigned) {
        this.clusterAssigned = clusterAssigned;
    }
}
