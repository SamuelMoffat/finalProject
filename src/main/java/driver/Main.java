package driver;

import java.io.File;

// README contains listed changes

public class Main {
        public static void main(String[] args) {
            File csvDirectory = new File("./src/main/resources");
            // add filter to only get specific files
            File filter = new File("./src/main/batch0plus1/batch0and1.txt");

            Driver driver = new Driver(csvDirectory,filter);

        }
    }
