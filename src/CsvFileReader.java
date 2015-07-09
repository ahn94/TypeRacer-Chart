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
            String[] raceA;
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


    public int getPieData(int y){
        int[] percentage = new int[11];
        for (int x = 0; x < raceDB.size(); x++){
            switch ((int)(raceDB.get(x).getAccuracy()*100)) {
                case 100:
                    percentage[10]++;
                    break;
                case 99:
                    percentage[9]++;
                    break;
                case 98:
                    percentage[8]++;
                    break;
                case 97:
                    percentage[7]++;
                    break;
                case 96:
                    percentage[6]++;
                    break;
                case 95:
                    percentage[5]++;
                    break;
                case 94:
                    percentage[4]++;
                    break;
                case 93:
                    percentage[3]++;
                    break;
                case 92:
                    percentage[2]++;
                    break;
                case 91:
                    percentage[1]++;
                    break;
                case 90:
                    percentage[0]++;
                    break;
                default:
                    percentage[0]++;
                    break;

            }
        }
        return percentage[y];
    }


}
