package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.Controller.subController.EmployeeNicFormController;
import Lk.ijse.Dress.DTO.EmployeeDTO;
import Lk.ijse.Dress.Service.Custome.EmployeeReportService;
import Lk.ijse.Dress.Service.Custome.EmployeeService;
import Lk.ijse.Dress.Service.Custome.ImageService;
import Lk.ijse.Dress.Service.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.*;

public class EmployeeReportFormController {

    @FXML
    private ImageView ImageEmployeeOR;

    @FXML
    private ImageView ImageEmployeephoto;

    @FXML
    private BarChart<String,Number> attendanceChart;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblDob;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblEmployeeNme;

    @FXML
    private Label lblEmpoyeeAddress;

    @FXML
    private Label lblJobRoll;

    @FXML
    private Label lblNic;

    @FXML
    private Label lblPhoneNumber;

    @FXML
    private Label lblSalary;
    ImageService imageService= (ImageService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Image);
    EmployeeService employeeService = (EmployeeService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Employee);
    EmployeeReportService employeeReportService= (EmployeeReportService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.EmployeeReport);

    @FXML
    void initialize() throws SQLException {
        loadEmployeeDetails();
        getQrCodes();
        loadEmployeeImage(empId,ImageEmployeephoto);
        loadQrImage(nic,ImageEmployeeOR);
        generateAttendanceChart();
    }
  static EmployeeNicFormController employeeNicFormController=new EmployeeNicFormController();


public  String id;
public  String empId;
private String nic;
@SneakyThrows
    public void loadEmployeeDetails() throws SQLException {
        String id = employeeNicFormController.getId();
        System.out.println(id);
        String nicNumber = id;
        String empId = employeeReportService.getNic(nicNumber);
        if (empId != null) {
            System.out.println("Employee ID: " + empId);
            try {
             EmployeeDTO employee = employeeService.getEmployeeById(empId);
                if (employee != null) {
                    lblEmployeeId.setText(employee.getEmployeeId());
                    lblEmployeeNme.setText(employee.getEmployeeName());
                    lblEmpoyeeAddress.setText(employee.getEmployeeAddress());
                    lblAge.setText(String.valueOf(employee.getEmployeeAge()));
                    lblDob.setText(String.valueOf(employee.getDateOfBirth()));
                    lblPhoneNumber.setText(String.valueOf(employee.getContactNumber()));
                    lblSalary.setText(String.valueOf(employee.getSalary()));
                } else {
                    System.out.println("Employee not found for NIC: " + nicNumber);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public  void getQrCodes(){
        String nic=employeeNicFormController.getId();
        lblNic.setText(nic);
        String job=employeeNicFormController.getJobRoll();
        lblJobRoll.setText(job);
    }





    public void loadEmployeeImage(String empId, ImageView  imageEmployeePhoto) {
        try {
            imageService.loadEmployeeImage(empId, imageEmployeePhoto);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void loadQrImage(String nic, ImageView imageEmployeeOR) {
        try {
            imageService.loadQrImage(nic, imageEmployeeOR);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
@SneakyThrows
    public void generateAttendanceChart() throws SQLException {
        String id2 = employeeNicFormController.getId();
        String empId1 = employeeReportService.getNic(nic);

        ObservableList<XYChart.Series<String, Number>> data = retrieveAllAttendanceDataFromDatabase(empId1);

        attendanceChart.getData().clear();
        attendanceChart.getData().addAll(data);
    }

    private ObservableList<XYChart.Series<String, Number>> retrieveAllAttendanceDataFromDatabase(String empId) throws SQLException {
        ObservableList<XYChart.Series<String, Number>> allData = FXCollections.observableArrayList();

        String url = "jdbc:mysql://localhost:3306/luxora";
        String username = "root";
        String password = "p1a2s3i4n5@P";
        String sql = "SELECT employee_id, DATE_FORMAT(attendance_date, '%Y-%m-%d') AS month, COUNT(*) AS present_count FROM attendance WHERE employee_id = ? AND attendance_status = 'Present'  GROUP BY employee_id, DATE_FORMAT(attendance_date, '%Y-%m-%d') ORDER BY month";


        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, empId);

            ResultSet resultSet = preparedStatement.executeQuery();

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Employee " + empId);
            System.out.println(empId);

            while (resultSet.next()) {
                String month = resultSet.getString("month");
                int presentCount = resultSet.getInt("present_count");
                System.out.println(month + ": " + presentCount);
                series.getData().add(new XYChart.Data<>(month, presentCount));
            }

            allData.add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allData;
    }
}