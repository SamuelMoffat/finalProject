package main.java.printer;

import main.java.datageneration.DataPoint;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CsvPrinter {


    public static void updateCSV(List<DataPoint> listOfPoints) {
        File dataPoints = new File("./dataPoints/dataPoints.csv");
        try {
            FileWriter output = new FileWriter(dataPoints, true);
            // new line first, then write data
            output.append("\n");

            for (DataPoint dataPoint : listOfPoints) {
                output.append(dataPoint.getPressure() + "," + dataPoint.isError());
                output.append("\n");
            }

            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
