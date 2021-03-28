package main.java.ai.kmeans;

import main.java.datageneration.DataPoint;

import java.util.List;

public class kmeans {
    //note there are 2 centroids in this implementation

    public static void findErrors(List<DataPoint> listOfPoints){
        //first select centroid data points
        double[] centroids = generateCentroids(listOfPoints);

    }

    private static void generateClusters(){

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
