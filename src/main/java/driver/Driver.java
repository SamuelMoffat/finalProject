package driver;

import ai.kmeans.Kmeans;
import ai.knearestneighbour.KNearestNeighbour;
import datageneration.DataGenerator;
import datageneration.DataPoint;
import printer.CsvPrinter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {

    private File sourcePath;
    public static File filter;

    public Driver(File path) {
        setSourcePath(path);
        start(getSourcePath());
    }

    public Driver(File path, File filter) {
        setSourcePath(path);
        Driver.filter = filter;
        start(getSourcePath());

    }

    public void start(File path) {

        initialiseFiles();

        DataGenerator dataGenerator = new DataGenerator();

        List<DataPoint> listOfPoints = dataGenerator.getListOfPoints();
        String dummyOutputFileName = "./dataPoints/dummyDataPoints.csv";
        CsvPrinter.updateCSV(listOfPoints, dummyOutputFileName);

        for (DataPoint dataPoint : listOfPoints) {
            System.out.println(dataPoint.getPressure());
        }

        List<DataPoint> analysedListOfPoints = Kmeans.findErrors(listOfPoints);
        String kMeansOutputFileName = "./dataPoints/kMeansDataPoints.csv";
        CsvPrinter.updateCSV(analysedListOfPoints, kMeansOutputFileName);

        // note - training data is needed for this analysis
        List<DataPoint> trainingListOfPoints = dataGenerator.getTrainingListOfPoints();
        List<DataPoint> kNNAnalysedListOfPoints = KNearestNeighbour.findErrors(listOfPoints, trainingListOfPoints);
        String kNNOutputFileName = "./dataPoints/kNNDataPoints.csv";
        CsvPrinter.updateCSV(kNNAnalysedListOfPoints, kNNOutputFileName);

    }


    private void initialiseFiles() {
        // initialise a file of data points
        List<String> fileHeader = new ArrayList<>(Arrays.asList("Name","Pressure","Lat","Long","Date","isIntentionalError","Pressure Description"));
        String dummyDataFile = "./dataPoints/dummyDataPoints.csv";
        initialiseFile(dummyDataFile,fileHeader);

        // initialise a file of kmeans points
        List<String> kMeansfileHeader = new ArrayList<>(fileHeader);
        kMeansfileHeader.add("Cluster Assigned");
        String kmeansDataFile = "./dataPoints/kMeansDataPoints.csv";
        initialiseFile(kmeansDataFile,kMeansfileHeader);

        //initialise a file of knn points
        String knnDataFile = "./dataPoints/kNNDataPoints.csv";
        initialiseFile(knnDataFile,fileHeader);

    }

    private void initialiseFile(String fileName, List<String> columnNames) {
        try {
            FileWriter output = new FileWriter(fileName);
            // Headers
            String objectsCommaSeparated = String.join(", ", columnNames);
            output.write(objectsCommaSeparated);
            output.write("\n");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(File sourcePath) {
        this.sourcePath = sourcePath;
    }
}