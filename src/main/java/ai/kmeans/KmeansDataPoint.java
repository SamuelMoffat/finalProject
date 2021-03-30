package ai.kmeans;

import com.opencsv.bean.CsvBindByPosition;
import datageneration.DataPoint;

public class KmeansDataPoint extends DataPoint {
    @CsvBindByPosition(position = 6)
    private String clusterAssigned;

    KmeansDataPoint(DataPoint dataPoint){
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
