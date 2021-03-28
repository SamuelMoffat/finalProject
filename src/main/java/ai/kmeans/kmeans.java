package main.java.ai.kmeans;

import main.java.datageneration.DataPoint;

import java.math.BigDecimal;
import java.util.List;

public class kmeans {
    //note there are 2 centroids in this implementation

    public static void findErrors(List<DataPoint> listOfPoints){
        //first select centroid data points
        double[] centroids = generateCentroids(listOfPoints);
        System.out.println("CENTROIDS");
        for(double centroid:centroids){
            System.out.println(centroid);
        }
        generateClusters(centroids,listOfPoints);
    }

    private static void generateClusters(double[] centroids,List<DataPoint> listOfPoints ){
        //compute distances
        for(DataPoint dataPoint: listOfPoints){
            double pressure = dataPoint.getPressure();
            // get distance to centroids
            BigDecimal distance1 = calculateDistance(centroids[0],pressure);
            BigDecimal distance2 = calculateDistance(centroids[1],pressure);

            System.out.println("Distance 1 = " + distance1);
            System.out.println("Distance 2 = " + distance2);
        }
    }

    private static BigDecimal calculateDistance(double centroidPressure, double dataPointPressure){
        System.out.println(centroidPressure + " - " + dataPointPressure);
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
