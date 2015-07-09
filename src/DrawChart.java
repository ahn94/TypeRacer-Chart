import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;

import static thorwin.math.Math.polyfit;
import static thorwin.math.Math.polynomial;


@SuppressWarnings("unchecked")
public class DrawChart extends Application {

//    @Override
//    @SuppressWarnings("unchecked")
    public void start(Stage stage) throws Exception {

        //Parses data from csv file
        CsvFileReader csv = new CsvFileReader();
        csv.readCSV();

        //line chart title
        stage.setTitle("TypeRacer - Chart");

        //arrays for xy values
        double[] xs = new double[csv.raceDB.size()];
        double[] ys = new double[csv.raceDB.size()];

        //create xy axis
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        //axis labels
        xAxis.setLabel("Race Number");
        yAxis.setLabel("WPM");

        //create line chart
        final LineChart lineChart = new LineChart(xAxis,yAxis);

        //series1 scatter style data points
        XYChart.Series<Number, Number> series1 = new XYChart.Series();

        //series2 polynominal curve data points
        XYChart.Series series2 = new XYChart.Series();

        //populates the xy arrays with data
        for (int x = 0; x < csv.raceDB.size(); x++) {
            xs[x] = csv.getRaceID(x);
            ys[x] = csv.getRaceWPM(x);
        }

        //fills series1 from the XYAxis element
        for (int x = 0; x < csv.raceDB.size(); x++) {
            series1.getData().add(new XYChart.Data(xs[x], ys[x]));
        }

        // calculate the polynomial coefficients and calculate trend points
        double[] coefficients = polyfit(xs, ys, 4);

        //uses thorwin library to calculate trend line curve
        //using polynomial formula
        for (double x = 0; x < csv.raceDB.size(); x += 5) {
            double y = polynomial(x, coefficients);
            series2.getData().add(new XYChart.Data(x,y));
        }

        //line chart title
        lineChart.setTitle("ahn94's progress");

        //adds both data series to chart
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);

        //turns off legend & removes the lock to zero on the range of the Y Axis
        lineChart.setLegendVisible(false);
        yAxis.setForceZeroInRange(false);

        //points the line chart to use CSS style chart
        lineChart.getStylesheets().add(DrawChart.class.getResource("style.css").toExternalForm());

        //stackpane container
        StackPane root = new StackPane();
        //adds linechart to root
        root.getChildren().add(lineChart);
        //creates scene & adds the container
        Scene scene = new Scene(root, 1700, 900);
        //fills stage(stage is complete program window)
        stage.setScene(scene);
        //makes window visible
        stage.show();
        //feed back on number of races on chart
        System.out.println(csv.raceDB.size());
    }

    public static void main(String[] args) {
        launch(args);
    }
}