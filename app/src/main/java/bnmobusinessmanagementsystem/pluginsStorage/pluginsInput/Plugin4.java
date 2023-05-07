package bnmobusinessmanagementsystem.pluginsStorage.pluginsInput;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Plugin4 implements Plugin<String> {
    private final boolean needPage = true;
    private PieChart pieChart;
    private ArrayList<Pair<String, Double>> chartData;
    private Consumer<String> updatedStates;
    private String name = "Persebaran Pengguna";
    private String db = "customer";

    public String getName() {
        return "Pie Chart";
    }

    public void setUp(ArrayList<Pair<String, Double>> chartData, Consumer<String> updatedStates, String name) {
        this.chartData = chartData;
        this.updatedStates = updatedStates;
//        this.name = name;
    }

    public void run(String parameter) {
        // Create a chart
        pieChart = new PieChart();
        pieChart.setTitle(name);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Pair<String, Double> pair : chartData) {
            PieChart.Data data = new PieChart.Data(pair.getKey(), pair.getValue());
            Integer val = pair.getValue().intValue();
            data.nameProperty().set(String.format("%s %d", pair.getKey(), val));
            pieChartData.add(data);
        }
        pieChart.setData(pieChartData);
    }

    public Scene getScene() {
        SplitPane root = new SplitPane(pieChart);
        root.setPadding(new Insets(100));
        root.setPrefSize(1080, 634);
        Scene scene = new Scene(root);
        return scene;
    }

    public boolean isNeedPage() {
        return needPage;
    }

    public String getDBNeeded() {
        return db;
    }
}
