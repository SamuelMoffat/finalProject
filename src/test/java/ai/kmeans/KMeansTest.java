package ai.kmeans;

import ai.knearestneighbour.KNearestNeighbour;
import datageneration.DataGenerator;
import datageneration.DataPoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {
    List<DataPoint> listOfPoints;
    List<DataPoint> trainingListOfPoints;

    void generateData(){
        DataGenerator dataGenerator = new DataGenerator();
        listOfPoints = dataGenerator.getListOfPoints();
        trainingListOfPoints = dataGenerator.getTrainingListOfPoints();}

    @Test
    void findErrors() throws Exception{
        //ensures a valid output is made, not null
        generateData();
        List<DataPoint> output = KNearestNeighbour.findErrors(listOfPoints,trainingListOfPoints);
        assertNotNull(output);
    }
}