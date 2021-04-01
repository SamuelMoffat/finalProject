package driver;

import ai.kmeans.Kmeans;
import ai.knearestneighbour.KNearestNeighbour;
import datageneration.DataGenerator;
import datageneration.DataPoint;
import printer.CsvPrinter;

import java.io.File;
import java.io.FileWriter;
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
        // initialise a file of errors
        File csvErrors = new File("./errors/errors.csv");
        try {
            FileWriter output = new FileWriter(csvErrors);
            // Headers
            String header = "File Found In, Element Found In, Error Description";
            output.write(header);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // initialise a file of data points
        File dummyDataPointsFile = new File("./dataPoints/dummyDataPoints.csv");
        try {
            FileWriter output = new FileWriter(dummyDataPointsFile);
            // Headers
            String header = "Name, Pressure, Lat, Long, Date, isIntentionalError";
            output.write(header);
            output.write("\n");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File dataPointsFile = new File("./dataPoints/kMeansDataPoints.csv");
        try {
            FileWriter output = new FileWriter(dataPointsFile);
            // Headers
            String header = "Name, Pressure, Lat, Long, Date, isIntentionalError, ClusterAssigned";
            output.write(header);
            output.write("\n");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File kNNPointsFile = new File("./dataPoints/kNNDataPoints.csv");
        try {
            FileWriter output = new FileWriter(kNNPointsFile);
            // Headers
            String header = "Name, Pressure, Lat, Long, Date, isIntentionalError, Pressure Description";
            output.write(header);
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