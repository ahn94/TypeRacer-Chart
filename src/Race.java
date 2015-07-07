public class Race {

    private int raceNumber;
    private int WPM;
    private double accuracy;
    private int rank;
    private int racers;
    private String date;

    public Race(int raceNumber, int WPM, double accuracy, int rank, int racers, String date) {

        this.raceNumber = raceNumber;
        this.WPM = WPM;
        this.accuracy = accuracy;
        this.rank = rank;
        this.racers = racers;
        this.date = date;
    }

    public int getRaceNumber(){
        return raceNumber;
    }

    public int getWPM(){
        return WPM;
    }

    public double getAccuracy(){
        return accuracy;
    }

    public int getRank(){
        return rank;
    }

    public int getRacers(){
        return racers;
    }

    public String getDate(){
        return date;
    }

}