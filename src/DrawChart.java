import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;

import static thorwin.math.Math.polyfit;
import static thorwin.math.Math.polynomial;


public class DrawChart extends Application {

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage) throws Exception {
        CsvFileReader csv = new CsvFileReader();
        csv.readCSV();
        stage.setTitle("Let's draw a square!");
        double[] xs = new double[csv.raceDB.size()];
        double[] ys = new double[csv.raceDB.size()];

        //Data points
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Race Number");
        yAxis.setLabel("WPM");




        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis,yAxis);
        XYChart.Series<Number, Number> series1 = new XYChart.Series();
        XYChart.Series<Number, Number> series2 = new XYChart.Series();

        //csv.raceDB.size()
        for (int x = 0; x < csv.raceDB.size(); x++) {
            xs[x] = csv.getRaceID(x);
            ys[x] = csv.getRaceWPM(x);
        }


        for (int x = 0; x < csv.raceDB.size(); x++) {
            series1.getData().add(new XYChart.Data(xs[x], ys[x]));
        }

        // calculate the polynomial coefficients and calculate trend points
        //coefficient - 7, poly - 15
        double[] coefficients = polyfit(xs, ys, 3);
        System.out.println(coefficients[0]);

        for (double x = 0; x < csv.raceDB.size(); x += 5) {
            double y = polynomial(x, coefficients);
            series2.getData().add(new XYChart.Data<Number, Number>(x,y));
        }


        lineChart.setTitle("Typeracer");
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        lineChart.getStylesheets().add(DrawChart.class.getResource("style.css").toExternalForm());

        StackPane root = new StackPane();
        root.getChildren().add(lineChart);
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.show();
        System.out.println(csv.raceDB.size());
    }

    public static void main(String[] args) {
        launch(args);
    }
}