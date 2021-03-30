package driver;

import ai.kmeans.Kmeans;
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

        CsvPrinter.updateCSV(listOfPoints);

        for (DataPoint dataPoint: listOfPoints) {
            System.out.println(dataPoint.getPressure());
        }

        Kmeans.findErrors(listOfPoints);


    }


    private void initialiseFiles(){
        // initialise a file of errors
        File csvErrors = new File("./errors/errors.csv");
        try {
            FileWriter output = new FileWriter(csvErrors);
            // Headers
            String header = "File Found In, Element Found In, Error Description";
            output.write(header);
            output.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // initialise a file of data points
        File dataPointsFile = new File("./dataPoints/dataPoints.csv");
        try {
            FileWriter output = new FileWriter(dataPointsFile);
            // Headers
            String header = "Name, Pressure, Lat, Long, Date";
            output.write(header);
            output.write("\n");
            output.close();
        }
        catch (Exception e) {
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