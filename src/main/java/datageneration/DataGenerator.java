package datageneration;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.bean.CsvToBeanBuilder;
import gis.UkLocation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataGenerator {

    List<DataPoint> listOfPoints;
    List<UkLocation> ukLocationsList;


    public DataGenerator(){
        //get list of uk coordinates
        String coordinates = "src/main/resources/Wards_(December_2017)_Boundaries_in_the_UK_(WGS84).csv";
        // if file not found then datapoints cannot be made
        try {
            ukLocationsList = new CsvToBeanBuilder(new FileReader(coordinates))
                    .withType(UkLocation.class).build().parse();

            generateData();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
        DataPoint newPoint = new DataPoint(ukLocationsList);

        return newPoint;
    }

    public List<DataPoint> getListofPoints() {
        return listOfPoints;
    }

    private void setListOfPoints(List<DataPoint> listOfPoints) {
        this.listOfPoints = listOfPoints;
    }
}
