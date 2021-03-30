package datageneration;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    List<DataPoint> listOfPoints;


    public DataGenerator(){
        generateData();
    }


    private void generateData(){
        int numOfPoints = 50;
        int randomSeed = 123;
        List<DataPoint> listOfPoints = new ArrayList<>();
        randomGeneration rand = new randomGeneration(randomSeed);

        for (int i = 0; i < numOfPoints; i++) {
            listOfPoints.add(createPoint());
        }

        setListOfPoints(listOfPoints);
    }

    private DataPoint createPoint(){
        DataPoint newPoint = new DataPoint();

        return newPoint;
    }

    public List<DataPoint> getListofPoints() {
        return listOfPoints;
    }

    private void setListOfPoints(List<DataPoint> listOfPoints) {
        this.listOfPoints = listOfPoints;
    }
}
