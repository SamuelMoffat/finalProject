package ai.knearestneighbour;

import datageneration.DataPoint;

import java.util.List;
import datageneration.DataGenerator;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class kKNearestNeighbourTest {
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