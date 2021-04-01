package ai.knearestneighbour;

import datageneration.DataPoint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNearestNeighbour {

    public static List<DataPoint> findErrors(List<DataPoint> listOfPoints,List<DataPoint> trainingListOfPoints){
        // first find out what value k is
        int k = determineK(trainingListOfPoints);
        for (DataPoint testDataPoint : listOfPoints) {
            List<KNNDataPoint> distanceList = calculateDistance(trainingListOfPoints,testDataPoint);
            //sort the list according to distance
            Collections.sort(distanceList);
            //then according to whatever number k is compare test data to k values
            // assign pressure label to mode of test data labels
            List<String> labelList = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                KNNDataPoint distancePoint = distanceList.get(i);
                labelList.add(distancePoint.getPressureLabel());
            }
            String testDataLabel = determineMode(labelList);
            testDataPoint.setPressureLabel(testDataLabel);
        }
        return listOfPoints;
    }

    private static String determineMode(List<String> listOfLabels){
        String maxValue = "undetermined";
        int maxCount = 0;

        for (int i = 0; i < listOfLabels.size(); ++i) {
            int count = 0;
            for (int j = 0; j < listOfLabels.size(); ++j) {
                if (listOfLabels.get(j).equals(listOfLabels.get(i))) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = listOfLabels.get(i);
            }
        }
        // if there is a tie in labels "undetermined" will return
        return maxValue;
    }

    private static int determineK (List<DataPoint> listOfPoints) {
        double sizeDouble = listOfPoints.size();
        double root = Math.sqrt(sizeDouble);
        double rawK = root / 2 ;
        int num = Math.round( ( float )rawK ) ;
        if ( num%2 != 0 ) {
            return num ;
        }
        else {
            return num - 1 ;
        }
    }

    private static List<KNNDataPoint> calculateDistance(List<DataPoint> trainingDataList, DataPoint dataPoint){
        // returns a list of how far each training point is to the dataPoint given
        List<KNNDataPoint> distanceList = new ArrayList<>();
        for (DataPoint trainingPoint:trainingDataList) {
            BigDecimal tPressure = BigDecimal.valueOf(trainingPoint.getPressure());
            BigDecimal dpPressure = BigDecimal.valueOf(dataPoint.getPressure());
            BigDecimal difference = tPressure.subtract(dpPressure);

            // convert training point to a KNN point and add difference value
            //convert difference to absolute
            KNNDataPoint distanceCalculatedPoint = new KNNDataPoint(trainingPoint);
            distanceCalculatedPoint.setDistance(difference.abs());
            distanceList.add(distanceCalculatedPoint);
        }
        return distanceList;
    }

}
