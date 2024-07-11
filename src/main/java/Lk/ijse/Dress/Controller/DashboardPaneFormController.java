package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.Controller.subController.EmployeeNicFormController;
import Lk.ijse.Dress.Controller.subController.MakePaymentFormController;
import Lk.ijse.Dress.Service.Custome.CustomerService;
import Lk.ijse.Dress.Service.Custome.OrderService;
import Lk.ijse.Dress.Service.Custome.RentalService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardPaneFormController implements Initializable {
    @FXML
    public JFXButton btmCheckNow;
    @FXML
    public LineChart<String, Number> chart;
    @FXML
    public PieChart pieChart;
    @FXML
    public JFXButton btmAddCustomer;
    @FXML
    public JFXButton btmAddOrders;
    @FXML
    public JFXButton btmAddRental;
    @FXML
    public JFXButton btmEmployeeReport;
    @FXML
    public JFXButton btmAddProduct1;
    @FXML
    public AnchorPane subAnchorPane2;
    @FXML
    public VBox vb;
    @FXML
    public ProgressBar pg;
    @FXML
    public Pane pane2;
    RentalService rentalService = (RentalService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Rental);
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);
    OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.ORDER);
    @FXML
    private Label lblCustomerCount;
    @FXML
    private Label lblMonthlyRevenue;
    @FXML
    private Label lblOrdersCount;
    @FXML
    private Label lblProductSold;
    @FXML
    private Label lblRentalCount;
    @FXML
    private Label lblTodayIncome;
    @FXML
    private Label lblTodayOrders;

    private int customerCount;
    private int orderCount;
    private int rentalCount;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart.setAnimated(false);
        lineChart();
        pg.getStyleClass().add("progress-bar");
        vb.getStyleClass().add("bar");
        loadCounts();
        setDashboardValues(1000.0, 50, 500.0, 10);
    }

    @FXML
    public void btmCheckNowOnAction(ActionEvent actionEvent) throws IOException {
        openWindow("/View/OtherViews/CheckNow_form.fxml");
    }

    @FXML
    public void btmAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/AnchorPaneMoves/makePayment_form.fxml"));
        Parent parent = fxmlLoader.load();
        MakePaymentFormController makePaymentFormController = fxmlLoader.getController();
        subAnchorPane2.getChildren().clear();
        subAnchorPane2.getChildren().add(parent);
    }

    @FXML
    public void btmAddOrdersOnAction(ActionEvent actionEvent) throws IOException {
        openWindow("/View/addNewOrder_form.fxml");
    }

    @FXML
    public void btmAddRentalOnAction(ActionEvent actionEvent) throws IOException {
        openWindow("/View/addNewRental_form.fxml");
    }

    @FXML
    public void btmAddProductOnAction(ActionEvent actionEvent) throws IOException {
        openWindow("/View/AddProduct_form.fxml");
    }

    @FXML
    public void btmEmployeeReportOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/AnchorPaneMoves/employeeNic_form.fxml"));
        Parent parent = fxmlLoader.load();
        EmployeeNicFormController employeeNicFormController = fxmlLoader.getController();
        subAnchorPane2.getChildren().clear();
        subAnchorPane2.getChildren().add(parent);
    }

    private void openWindow(String resource) throws IOException {
        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(resource)));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void lineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        chart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        chart.setTitle("Product Sales Chart");
        xAxis.setLabel("Month");
        yAxis.setLabel("Quantity Sold");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        XYChart.Series<String, Number> series6 = new XYChart.Series<>();
        XYChart.Series<String, Number> series7 = new XYChart.Series<>();

        series1.setName("Product 1");
        series2.setName("Product 2");
        series3.setName("Product 3");
        series4.setName("Product 4");
        series5.setName("Product 5");
        series6.setName("Product 6");
        series7.setName("Product 7");

        addData(series1, "January", 100);
        addData(series2, "January", 150);
        addData(series3, "January", 200);
        addData(series4, "January", 80);
        addData(series5, "January", 120);
        addData(series6, "January", 130);
        addData(series7, "January", 110);
        addData(series1, "February", 110);
        addData(series1, "March", 120);
        addData(series1, "April", 130);
        addData(series1, "May", 140);
        addData(series1, "June", 150);
        addData(series1, "July", 160);

        addData(series2, "February", 160);
        addData(series2, "March", 170);
        addData(series2, "April", 180);
        addData(series2, "May", 190);
        addData(series2, "June", 200);
        addData(series2, "July", 210);

        addData(series3, "February", 210);
        addData(series3, "March", 220);
        addData(series3, "April", 230);
        addData(series3, "May", 240);
        addData(series3, "June", 250);
        addData(series3, "July", 260);

        addData(series4, "February", 100);
        addData(series4, "March", 110);
        addData(series4, "April", 120);
        addData(series4, "May", 130);
        addData(series4, "June", 140);
        addData(series4, "July", 150);

        addData(series5, "February", 150);
        addData(series5, "March", 160);
        addData(series5, "April", 170);
        addData(series5, "May", 180);
        addData(series5, "June", 190);
        addData(series5, "July", 200);

        addData(series6, "February", 200);
        addData(series6, "March", 210);
        addData(series6, "April", 220);
        addData(series6, "May", 230);
        addData(series6, "June", 240);
        addData(series6, "July", 250);

        addData(series7, "February", 250);
        addData(series7, "March", 260);
        addData(series7, "April", 270);
        addData(series7, "May", 280);
        addData(series7, "June", 290);
        addData(series7, "July", 300);

        chart.getData().addAll(series1, series2, series3, series4, series5, series6, series7);
    }

    private void addData(XYChart.Series<String, Number> series, String month, int quantity) {
        series.getData().add(new XYChart.Data<>(month, quantity));
    }

    private void loadCounts() {
        try {
            setCustomerCount(getCustomerCount());
            setOrderCount(getOrderCount());
            setRentalCount(getRentalCount());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load counts: " + e.getMessage()).show();
        }
    }

    private int getCustomerCount() throws SQLException {
         customerCount = 0;

        try {
            customerCount = customerService.getCustomerCount();
            System.out.println("Total customer Count: " + customerCount);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customerCount;

    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getOrderCount() throws SQLException {
        orderCount=0;
        try {
            orderCount=orderService.getOrderCount();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }return orderCount;
    }

    private void setOrderCount(int orderCount) {
        lblOrdersCount.setText(String.valueOf(orderCount));
    }

    public int getRentalCount() {
         rentalCount = 0;
        try {
            rentalCount = rentalService.getRentalCount();
            System.out.println("Total Rental Count: " + rentalCount);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rentalCount;
    }

    private void setRentalCount(int rentalCount) {
        lblRentalCount.setText(String.valueOf(rentalCount));
    }

    private void setDashboardValues(double monthlyRevenue, int productSold, double todayIncome, int todayOrders) {
        lblMonthlyRevenue.setText(String.format("$%.2f", monthlyRevenue));
        lblMonthlyRevenue.setStyle("-fx-text-fill: black;");

        lblProductSold.setText(String.valueOf(productSold));
        lblProductSold.setStyle("-fx-text-fill: black;");

        lblTodayIncome.setText(String.format("$%.2f", todayIncome));
        lblTodayIncome.setStyle("-fx-text-fill: black;");

        lblTodayOrders.setText(String.valueOf(todayOrders));
        lblTodayOrders.setStyle("-fx-text-fill: black;");
    }


}
