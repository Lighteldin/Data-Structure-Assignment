package me.jehn;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Sort sort;
    //GRAPH TAB ATTRIBUTES
    XYChart.Series bar;
    @FXML
    private Button graphVisualizeButton;
    @FXML
    private VBox graphVBoxBarCharts;
    @FXML
    private ChoiceBox<String> graphSortChoiceBox;
    @FXML
    private ChoiceBox<String> graphTypeChoiceBox;
    //TABLE ATTRIBUTES
    @FXML
    TableColumn<Details, String> tableArrayTypeColumn = new TableColumn<>("Array Type");
    @FXML
    TableColumn<Details, Double> tableTimeColumn = new TableColumn<>("Time");
    @FXML
    TableColumn<Details, Long> tableComparisonsColumn = new TableColumn<>("Comparisons");
    @FXML
    TableColumn<Details, Long> tableInterchangesCol = new TableColumn<>("Interchanges");
    @FXML
    TableView<Details> tableView = new TableView<>();
    //SIMULATION TAB ATTRIBUTES
    @FXML
    private ChoiceBox<String> simulationSortChoiceBox;
    @FXML
    private Button simulationSimulateButton;
    @FXML
    private VBox simulationVBoxLineChart;
    private int[] simulationArr;
    private LineChart<Number,Number> simulationLineChart;
    private XYChart.Series<Number, Number> series;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar = new XYChart.Series();
        //GRAPH TAB
        graphSortChoiceBox.getItems().addAll("Quick Sort", "Counting Sort", "Bubble Sort");
        graphSortChoiceBox.setValue("CHOOSE");
        graphTypeChoiceBox.getItems().addAll("Time", "Comparison", "Interchanges");
        graphTypeChoiceBox.setValue("CHOOSE");
        //TABLE
        tableArrayTypeColumn.setCellValueFactory(new PropertyValueFactory<>("arrayType"));
        tableTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        tableComparisonsColumn.setCellValueFactory(new PropertyValueFactory<>("comparisons"));
        tableInterchangesCol.setCellValueFactory(new PropertyValueFactory<>("interchanges"));
        //SIMULATION TAB
        simulationSortChoiceBox.getItems().addAll("Quick Sort", "Counting Sort", "Bubble Sort");
        simulationSortChoiceBox.setValue("CHOOSE");
    }
    //GRAPH TAB
    @FXML
    void onClickGraphGenerate(ActionEvent event) {
        sort = new Sort();
        graphVisualizeButton.setDisable(false);
    }
    @FXML
    void onClickVisualize(ActionEvent event) throws InterruptedException {
        String sortChoice = graphSortChoiceBox.getValue();
        String typeChoice = graphTypeChoiceBox.getValue();
        if(!(sortChoice.equalsIgnoreCase("choose") || typeChoice.equalsIgnoreCase("choose"))) {
            if (sortChoice.equalsIgnoreCase("Quick Sort")) {
                ObservableList<Details> details = FXCollections.observableArrayList(
                        new Details("SORTED", Sort.get2DArrayElemnt(sort.q2DArr, 1), (long) Sort.get2DArrayElemnt(sort.q2DArr, 2), (long) Sort.get2DArrayElemnt(sort.q2DArr, 3)),
                        new Details("INVERTED", Sort.get2DArrayElemnt(sort.q2DArr, 4), (long) Sort.get2DArrayElemnt(sort.q2DArr, 5), (long) Sort.get2DArrayElemnt(sort.q2DArr, 6)),
                        new Details("RANDOM", Sort.get2DArrayElemnt(sort.q2DArr, 7), (long) Sort.get2DArrayElemnt(sort.q2DArr, 8), (long) Sort.get2DArrayElemnt(sort.q2DArr, 9))
                );
                tableView.setItems(details);
                switch(typeChoice){
                    case "Time":
                        setBarGraph("Time (Milliseconds)", sort.get2DArrayElemnt(sort.q2DArr, 1), sort.get2DArrayElemnt(sort.q2DArr, 4), sort.get2DArrayElemnt(sort.q2DArr, 7));
                        break;
                    case "Comparison":
                        setBarGraph("Comparison", sort.get2DArrayElemnt(sort.q2DArr, 2), sort.get2DArrayElemnt(sort.q2DArr, 5), sort.get2DArrayElemnt(sort.q2DArr, 8));
                        break;
                    case "Interchanges":
                        setBarGraph("Interchanges", sort.get2DArrayElemnt(sort.q2DArr, 3), sort.get2DArrayElemnt(sort.q2DArr, 6), sort.get2DArrayElemnt(sort.q2DArr, 9));
                        break;
                }
            }
            if (sortChoice.equalsIgnoreCase("Counting Sort")) {
                ObservableList<Details> details = FXCollections.observableArrayList(
                        new Details("SORTED", Sort.get2DArrayElemnt(sort.c2DArr, 1), (long) Sort.get2DArrayElemnt(sort.c2DArr, 2), (long) Sort.get2DArrayElemnt(sort.c2DArr, 3)),
                        new Details("INVERTED", Sort.get2DArrayElemnt(sort.c2DArr, 4), (long) Sort.get2DArrayElemnt(sort.c2DArr, 5), (long) Sort.get2DArrayElemnt(sort.c2DArr, 6)),
                        new Details("RANDOM", Sort.get2DArrayElemnt(sort.c2DArr, 7), (long) Sort.get2DArrayElemnt(sort.c2DArr, 8), (long) Sort.get2DArrayElemnt(sort.c2DArr, 9))
                );
                tableView.setItems(details);
                switch(typeChoice){
                    case "Time":
                        setBarGraph("Time", sort.get2DArrayElemnt(sort.c2DArr, 1), sort.get2DArrayElemnt(sort.c2DArr, 4), sort.get2DArrayElemnt(sort.c2DArr, 7));
                        break;
                    case "Comparison":
                        setBarGraph("Comparison", sort.get2DArrayElemnt(sort.c2DArr, 2), sort.get2DArrayElemnt(sort.c2DArr, 5), sort.get2DArrayElemnt(sort.c2DArr, 8));
                        break;
                    case "Interchanges":
                        setBarGraph("Interchanges", sort.get2DArrayElemnt(sort.c2DArr, 3), sort.get2DArrayElemnt(sort.c2DArr, 6), sort.get2DArrayElemnt(sort.c2DArr, 9));
                        break;
                }
            }
            if (sortChoice.equalsIgnoreCase("Bubble Sort")) {
                ObservableList<Details> details = FXCollections.observableArrayList(
                        new Details("SORTED", Sort.get2DArrayElemnt(sort.b2DArr, 1), (long) Sort.get2DArrayElemnt(sort.b2DArr, 2), (long) Sort.get2DArrayElemnt(sort.b2DArr, 3)),
                        new Details("INVERTED", Sort.get2DArrayElemnt(sort.b2DArr, 4), (long) Sort.get2DArrayElemnt(sort.b2DArr, 5), (long) Sort.get2DArrayElemnt(sort.b2DArr, 6)),
                        new Details("RANDOM", Sort.get2DArrayElemnt(sort.b2DArr, 7), (long) Sort.get2DArrayElemnt(sort.b2DArr, 8), (long) Sort.get2DArrayElemnt(sort.b2DArr, 9))
                );
                tableView.setItems(details);
                switch(typeChoice){
                    case "Time":
                        setBarGraph("Time", sort.get2DArrayElemnt(sort.b2DArr, 1), sort.get2DArrayElemnt(sort.b2DArr, 4), sort.get2DArrayElemnt(sort.b2DArr, 7));
                        break;
                    case "Comparison":
                        setBarGraph("Comparison", sort.get2DArrayElemnt(sort.b2DArr, 2), sort.get2DArrayElemnt(sort.b2DArr, 5), sort.get2DArrayElemnt(sort.b2DArr, 8));
                        break;
                    case "Interchanges":
                        setBarGraph("Interchanges", sort.get2DArrayElemnt(sort.b2DArr, 3), sort.get2DArrayElemnt(sort.b2DArr, 6), sort.get2DArrayElemnt(sort.b2DArr, 9));
                        break;
                }
            }
        }
    }
    void setBarGraph(String name, double sorted, double inverted, double random){
        //CLEAR EXISTING GRAPH
        int removeIndex = graphVBoxBarCharts.getChildren().size()-1;
        if(graphVBoxBarCharts.getChildren().get(removeIndex) instanceof BarChart)
            graphVBoxBarCharts.getChildren().remove(removeIndex);
        //CREATE GRAPH
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> graphBarChart = new BarChart<String,Number>(xAxis,yAxis);
        graphVBoxBarCharts.getChildren().add(graphBarChart);
        //ADD DATA TO GRAPH
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName(name);
        dataSeries.getData().add(new XYChart.Data("SORTED", sorted));
        dataSeries.getData().add(new XYChart.Data("INVERTED"  , inverted));
        dataSeries.getData().add(new XYChart.Data("RANDOM"  , random));
        graphBarChart.getData().addAll(dataSeries);

    }
    //SIMULATION TAB
    @FXML
    void onClickSimulationGenerate(ActionEvent event) {
        simulationArr = Sort.randomArray(200);
        setLineGraph(null, simulationArr);
        simulationSimulateButton.setDisable(false);
    }
    @FXML
    void onClickSimulate(ActionEvent event) {
        simulationSimulateButton.setDisable(true);
        String sortChoice = simulationSortChoiceBox.getValue();
        if(!(sortChoice.equalsIgnoreCase("choose"))) {
            switch(sortChoice){
                case "Quick Sort":
                    Thread t1 = new Thread(()->{
                        quickSort(simulationArr, 0, simulationArr.length-1);
                    });
                    t1.start();
                    break;
                case "Counting Sort":
                    Thread t2 = new Thread(()->{
                        countingSort(simulationArr);

                    });
                    t2.start();
                    break;
                case "Bubble Sort":
                    Thread t3 = new Thread(()->{
                        bubbleSort(simulationArr);
                    });
                    t3.start();
                    break;
            }
        }
    }
    void setLineGraph(String name, int[]arr){
        //CLEAR EXISTING GRAPH
        int removeIndex = simulationVBoxLineChart.getChildren().size()-1;
        if(simulationVBoxLineChart.getChildren().get(removeIndex) instanceof LineChart)
            simulationVBoxLineChart.getChildren().remove(removeIndex);
        //CREATE GRAPH
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        simulationLineChart = new LineChart<Number,Number>(xAxis,yAxis);
        simulationLineChart.setCreateSymbols(false);
        simulationLineChart.setAnimated(false);
        if (!(name==null))
            simulationLineChart.setTitle(name);
        simulationLineChart.setLegendVisible(false);
        series = new XYChart.Series<>();
        for (int i = 0; i < arr.length; i++)
            series.getData().add(new XYChart.Data<>(i, arr[i]));
        simulationLineChart.getData().add(series);
        simulationVBoxLineChart.getChildren().add(simulationLineChart);
    }
    void updateLineGraph(int[] arr){
        series.getData().clear();
        for (int i = 0; i < arr.length; i++) {
            series.getData().add(new XYChart.Data<>(i, arr[i]));
        }
    }
    //SORTING ALGORITHMS FOR SIMULATION
    //QUICK SORT
    private void quickSort(int[] arr, int low, int high){
        if (low < high){
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    private int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j]<=pivot){
                i++;
                swap(arr, i, j);

                long startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 10000000
                ) {
                    Thread.onSpinWait();
                }

                Platform.runLater(() -> updateLineGraph(simulationArr));
            }
        }
        swap(arr, i+1, high);

        return i+1;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //COUNTING SORT
    private void countingSort(int[] arr){
        int max = getMax(arr);
        int[] count = new int[max+1];
        for (int num : arr)
            count[num]++;
        for (int i=1 ; i<=max ; i++)
            count[i] += count[i-1];
        int[] output = new int[arr.length];
        for (int i=arr.length-1; i>=0 ;i--  ){
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        for (int i=0; i<arr.length;i++) {
            arr[i] = output[i];
        }
    }
    private int getMax(int[] arr){
        int max = -1 ;
        for (int i : arr)
            if (i > max) max=i;
        return max;
    }
    //BUBBLE SORT
    private void bubbleSort(int a[]) {
        int interchanges;
        for (int pass = 0; pass < a.length; pass++ ) {
            interchanges=0;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    interchanges++;
                }

                long startTime = System.nanoTime();
                while (System.nanoTime() - startTime < 1000000
                ) {
                    Thread.onSpinWait();
                }

                Platform.runLater(() -> updateLineGraph(simulationArr));
            }
            if (interchanges==0) return;
        }
    }
}