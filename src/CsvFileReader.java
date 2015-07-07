import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CsvFileReader {
    List<Race> raceDB = new ArrayList<Race>();
    String raceFile = "E:\\Austin\\Downloads\\race_data (1)\\race_data.csv";
    File file = new File(raceFile);
    Scanner in = null;

    public Race getRaceObject(int objectID) {
        return raceDB.get(objectID);
    }

    public int getRaceID(int objectID){
        return raceDB.get(objectID).getRaceNumber();
    }

    public int getRaceWPM(int objectID){
        return raceDB.get(objectID).getWPM();
    }

    public void readCSV() throws IOException{

        //Removes the header in csv file
        in = new Scanner(file);
        System.out.println(in.nextLine());
        int x = 0;

        while (in.hasNextLine()){
            String[] raceA = new String[6];
            String line = in.nextLine();
            raceA = line.split(",");
            raceDB.add(new Race(
                    Integer.parseInt(raceA[0]),
                    Integer.parseInt(raceA[1]),
                    Double.parseDouble(raceA[2]),
                    Integer.parseInt(raceA[3]),
                    Integer.parseInt(raceA[4]),
                    raceA[5])
            );
        }
    }

}
