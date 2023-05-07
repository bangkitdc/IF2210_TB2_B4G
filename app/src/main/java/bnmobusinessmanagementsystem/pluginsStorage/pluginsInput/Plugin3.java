package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.SplitPane;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;

public class Plugin3 implements Plugin<String>{
    private final boolean needPage = true;
    private ArrayList<Pair<String, Double>> chartData;
    private Consumer<String> updatedStates;
    private String db = "itemDataStore";

    private BarChart<String, Number> barChart;

    private String name1 = "Total Bill per Hari";

    private LineChart<String, Number> lineChart;

    private String name2 = "Top 10 Products Sales";
    private String exchange = "";
    private Double rate = 0.0;

    public String getName() {
        return "Line&Bar Chart";
    }

    public void setRate(String exchange, Double rate) {
        this.exchange = exchange;
        this.rate = rate;
    }
    public void setUp(ArrayList<Pair<String, Double>> chartData, Consumer<String> updatedStates, String name) {
        this.chartData = chartData;
        this.updatedStates = updatedStates;
//        this.name = name;
    }

    public void run(String parameter) {
        // Create a line chart
        CategoryAxis xAxis1 = new CategoryAxis();
        xAxis1.setLabel("Tanggal");

        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Total Bill " + exchange);

        lineChart = new LineChart<>(xAxis1, yAxis1);
        lineChart.setTitle(name1);

        int middleIndex = -1;
        for (int i = 0; i < chartData.size(); i++) {
            Pair<String, Double> pair = chartData.get(i);
            if (pair.getKey().equals("mid") && pair.getValue() == -1.0) {
                middleIndex = i;
                break;
            }
        }

        // Sort the data based on the date in ascending order
        List<Pair<String, Double>> sortedData = chartData.subList(0, middleIndex);
        sortedData.sort(Comparator.comparing(p -> LocalDate.parse(p.getKey(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        // Create the series for the line chart
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        for (Pair<String, Double> data : sortedData) {
            series1.getData().add(new XYChart.Data<>(data.getKey(), data.getValue() * rate));
        }

        lineChart.getData().addAll(series1);
        lineChart.setLegendVisible(false);

        series1.nodeProperty().get().setStyle("""
            -fx-stroke: #509990;
        """);

        // Create a bar chart
        CategoryAxis xAxis2 = new CategoryAxis();
        xAxis2.setLabel("Product's Name");

        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Quantity");

        barChart = new BarChart<>(xAxis2, yAxis2);
        barChart.setTitle(name2);

        // Sort the data points in descending order by value
        Collections.sort(chartData.subList(middleIndex + 1, chartData.size()), new Comparator<Pair<String, Double>>() {
            @Override
            public int compare(Pair<String, Double> p1, Pair<String, Double> p2) {
                return p2.getValue().compareTo(p1.getValue());
            }
        });

        // Add the top 10 data points to the chart
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        for (int i = middleIndex + 1; i < chartData.size() && i < middleIndex + 11; i++) {
            Pair<String, Double> d = chartData.get(i);
            series2.getData().add(new XYChart.Data<>(d.getKey(), d.getValue()));
        }

        barChart.getData().addAll(series2);
        barChart.setLegendVisible(false);
        this.color();
    }

    public void color() {
        // Get the list of data objects
        ObservableList<XYChart.Data<String, Number>> data = barChart.getData().get(0).getData();

        for (XYChart.Data<String, Number> d : data) {
            d.getNode().setStyle("""
                -fx-bar-fill: #A6A6A6;
            """);
        }

        // Find the maximum value
        XYChart.Data<String, Number> maxData = data.get(0);
        for (XYChart.Data<String, Number> d : data) {
            if (d.getYValue().doubleValue() > maxData.getYValue().doubleValue()) {
                maxData = d;
            }
        }

        // Set the color of the maximum bar to red
        Node maxBar = maxData.getNode();
        maxBar.setStyle("-fx-bar-fill: #509990;");

        // Get the list of data objects
        ObservableList<XYChart.Data<String, Number>> data2 = lineChart.getData().get(0).getData();

        for (XYChart.Data<String, Number> d : data2) {
            d.getNode().setStyle("""
                -fx-background-color: #378077;
            """);
        }
    }

    public Scene getScene() {
        // Create a split pane and add charts to it
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(lineChart, barChart);

        // Set the divider position to 50% so that the charts are equally split
        splitPane.setDividerPosition(0, 0.5);
        splitPane.setPadding(new Insets(100, 10, 100, 10));

        // Set the size of the split pane
        splitPane.setPrefSize(1080, 634);

        // Create a scene with the split pane
        Scene scene = new Scene(splitPane);

        // Set the size of the scene
        scene.setRoot(splitPane);
        return scene;
    }

    public boolean isNeedPage() {
        return needPage;
    }
    public String getDBNeeded() {
        return db;
    }
}
