package datageneration;

import com.opencsv.bean.CsvToBeanBuilder;
import gis.UkLocation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public List<DataPoint> listOfPoints;
    public List<DataPoint> trainingListOfPoints;
    List<UkLocation> ukLocationsList;


    public DataGenerator(){
        //initialise random generator
        int randomSeed = 123;
        randomGeneration rand = new randomGeneration(randomSeed);

        //get list of uk coordinates
        String coordinates = "src/main/resources/Wards_(December_2017)_Boundaries_in_the_UK_(WGS84).csv";
        // if file not found then datapoints cannot be made
        try {
            ukLocationsList = new CsvToBeanBuilder(new FileReader(coordinates))
                    .withType(UkLocation.class).build().parse();

            //generates random points
            int numOfPoints = 500;
            generateData(numOfPoints);
            generateTrainingData(numOfPoints);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void generateData(int numOfPoints){
        List<DataPoint> listOfPoints = new ArrayList<>();

        for (int i = 0; i < numOfPoints; i++) {
            listOfPoints.add(createPoint());
        }

        setListOfPoints(listOfPoints);
    }
    private void generateTrainingData(int numOfPoints){
        //Note - Training data will explicitly state the correct label
        List<DataPoint> listOfPoints = new ArrayList<>();

        for (int i = 0; i < numOfPoints; i++) {
            listOfPoints.add(createTrainingPoint());
        }

        setTrainingListOfPoints(listOfPoints);
    }

    private DataPoint createTrainingPoint() {
        DataPoint newPoint = new DataPoint(ukLocationsList,true);

        return newPoint;
    }


    private DataPoint createPoint(){
        DataPoint newPoint = new DataPoint(ukLocationsList);

        return newPoint;
    }

    public List<DataPoint> getListOfPoints() {
        return listOfPoints;
    }

    private void setListOfPoints(List<DataPoint> listOfPoints) {
        this.listOfPoints = listOfPoints;
    }

    public List<DataPoint> getTrainingListOfPoints() {
        return trainingListOfPoints;
    }

    public void setTrainingListOfPoints(List<DataPoint> trainingListOfPoints) {
        this.trainingListOfPoints = trainingListOfPoints;
    }
}
