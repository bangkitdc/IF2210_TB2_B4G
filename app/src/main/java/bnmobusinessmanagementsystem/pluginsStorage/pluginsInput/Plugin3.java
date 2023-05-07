package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Plugin3 implements Plugin<String>{
    private final boolean needPage = true;

    private BarChart<String, Number> barChart;

    public String getName() {
        return "Plugin_Pie_Chart";
    }

    public void run(String parameter) {
        // Create a chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Category");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sample Chart");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Series 1");
        series1.getData().add(new XYChart.Data<>("A", 1));
        series1.getData().add(new XYChart.Data<>("B", 2));
        series1.getData().add(new XYChart.Data<>("C", 3));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Series 2");
        series2.getData().add(new XYChart.Data<>("A", 2));
        series2.getData().add(new XYChart.Data<>("B", 3));
        series2.getData().add(new XYChart.Data<>("C", 4));

        barChart.getData().addAll(series1, series2);
    }

    public Scene getScene() {
        StackPane root = new StackPane(barChart);
        root.setPrefSize(1080, 634);
        Scene scene = new Scene(root);
        return scene;
    }

    public boolean isNeedPage() {
        return needPage;
    }
}
