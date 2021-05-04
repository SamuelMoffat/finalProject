package ai.kmeans;

import datageneration.DataPoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMeans {
    //record iterations till convergence
    private static int iterationsTillConvergence = 0;
    //note there are 2 centroids in this implementation
    private static List<DataPoint> analysedListOfPoints = new ArrayList<>();

    public static List<DataPoint> findErrors(List<DataPoint> listOfPoints){
        //first select centroid data points
        double[] centroids = generateCentroids(listOfPoints);
        generateClusters(centroids,listOfPoints);
        return analysedListOfPoints;
    }

    private static void generateClusters(double[] centroids,List<DataPoint> listOfPoints ){
        //initialise cluster lists
        List<DataPoint> ClusterA = new ArrayList<>();
        List<DataPoint> ClusterB = new ArrayList<>();

        //compute distances
        for(DataPoint dataPoint: listOfPoints){
            double pressure = dataPoint.getPressure();
            // get distance to centroids
            BigDecimal distance1 = calculateDistance(centroids[0],pressure);
            BigDecimal distance2 = calculateDistance(centroids[1],pressure);

            //place point in correct cluster
            if( distance1.compareTo(distance2) < 0 ) {
                ClusterA.add(dataPoint);
            } else {
                ClusterB.add(dataPoint);
            }
        }
        //get averages
        BigDecimal newCentroidA = getClusterAverage(ClusterA);
        BigDecimal newCentroidB = getClusterAverage(ClusterB);

        double[] newCentroids = new double[2];
        newCentroids[0] = newCentroidA.doubleValue();
        newCentroids[1] = newCentroidB.doubleValue();

        //rerun with new centroids if average has not changed
        iterationsTillConvergence++;
        if(!Arrays.equals(centroids,newCentroids)) {
            generateClusters(newCentroids, listOfPoints);

        }
        else{
            for (DataPoint dataPoint : ClusterA) {
                KmeansDataPoint analysedPoint = new KmeansDataPoint(dataPoint);
                analysedPoint.setClusterAssigned("A");
                analysedListOfPoints.add(analysedPoint);
            }
            for (DataPoint dataPoint : ClusterB) {
                KmeansDataPoint analysedPoint = new KmeansDataPoint(dataPoint);
                analysedPoint.setClusterAssigned("B");
                analysedListOfPoints.add(analysedPoint);
            }
        }
    }

    private static BigDecimal getClusterAverage(List<DataPoint> clusterList) {
        double sum = 0;
        int totalsize = clusterList.size();
        for(DataPoint dataPoint: clusterList){
            sum+=dataPoint.getPressure();
        }
        BigDecimal average = BigDecimal.valueOf(sum);
        average = average.divide(BigDecimal.valueOf(totalsize),2,RoundingMode.HALF_EVEN);
        return average;
    }

    private static BigDecimal calculateDistance(double centroidPressure, double dataPointPressure){
        //convert to big decimal for precision
        BigDecimal cPressure = BigDecimal.valueOf(centroidPressure);
        BigDecimal dpPressure = BigDecimal.valueOf(dataPointPressure);
        BigDecimal difference = cPressure.subtract(dpPressure);

        return difference.abs();
    }

    private static double[] generateCentroids(List<DataPoint> listOfPoints){
        //returns value of first and second array position
        //TODO: Create iteration for x number of centroids
        double[] centroids = new double [2];
        centroids[0] = listOfPoints.get(0).getPressure();
        centroids[1] = listOfPoints.get(1).getPressure();
        return centroids;
    }


}