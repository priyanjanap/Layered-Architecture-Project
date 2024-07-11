package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.Controller.subController.PaymentRentalFormController;
import Lk.ijse.Dress.DTO.CustomerDTO;
import Lk.ijse.Dress.DTO.DressDTO;
import Lk.ijse.Dress.Entity.RentalDress;
import Lk.ijse.Dress.Model.tm.RentalTm;
import Lk.ijse.Dress.Service.Custome.*;
import Lk.ijse.Dress.Service.Custome.Impl.CustomerServiceImpl;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddNewRentalFormController implements Initializable {

    public JFXButton imageButton;
    public JFXButton btmSelectDress;
    public TableView<RentalTm> tblRental;
    CustomerServiceImpl customerService = (CustomerServiceImpl) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);
    ImageService imageService = (ImageService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Image);
    DressService dressService= (DressService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Dress);
    PaymentService paymentService= (PaymentService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Payment);

    ObservableList<RentalTm> obList = FXCollections.observableArrayList();
    @FXML
    private JFXCheckBox cCheckIn;
    @FXML
    private JFXCheckBox choicePaid;
    @FXML
    private JFXComboBox<String> cmbCustomerId;
    @FXML
    private JFXComboBox<String> cmbDressId;
    @FXML
    private ChoiceBox<?> cmbPayment;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private TableColumn<?, ?> colResDate;
    @FXML
    private TableColumn<?, ?> colUpdate;
    @FXML
    private DatePicker lastDate;
    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblDressName;
    @FXML
    private Label lblPyament;
    @FXML
    private Label lblRentPrice;
    @FXML
    private Label lblRentalId;
    @FXML
    private Label lblTotal;
    @FXML
    private DatePicker stDate;
    private Connection connection;
    @FXML
    private TextField txtNicNumber;
    private int contactNumber;
    RentalService rentalService= (RentalService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Rental);

    TransactionService transactionService= (TransactionService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Trnas);
    /*  private void displayImageFromDatabase() throws SQLException {
          String sql = "SELECT image_data FROM images";
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);

          if (resultSet.next()) {
              byte[] imageData = resultSet.getBytes("image_data");
              ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
              Image image = new Image(byteArrayInputStream);

              ImageView imageView = new ImageView(image);
              imageView.setFitWidth(100);
              imageView.setFitHeight(100);

              imageButton.setGraphic(imageView);
          } else {
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Warning");
              alert.setHeaderText(null);
              alert.setContentText("No image found in the database");
              alert.showAndWait();
          }
      }*/
    @FXML
    private Label lblGenaratePayment;

    public void connectio() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/luxora", "root", "p1a2s3i4n5@P");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void saveDate(LocalDate selectedDate) {
        //  System.out.println("Selected date: " + selectedDate);


    }
@SneakyThrows
    private void getCurrentPaymentID() {
        try {
            String currentId = paymentService.getCurrentId();
            String nextPaymentId = generateNextPaymentId(currentId);
            lblGenaratePayment.setText(nextPaymentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getDiscountCodes() throws RuntimeException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = dressService.getAllDressIds();
            obList.addAll(codeList);
            cmbDressId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextPaymentId(String currentId) {
        if (currentId != null && currentId.startsWith("PAY")) {
            try {
                int idNum = Integer.parseInt(currentId.substring(3));
                return "PAY" + String.format("%03d", idNum + 1);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "PAY001";
    }

    @SneakyThrows
    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            var idList = customerService.getAllCustomerIds();
            obList.addAll(idList);
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentRentalId() {
        try {
            String currentId = rentalService.getAllRentalIds();
            String nextRentalId = generateNextRentalID(currentId);
            lblRentalId.setText(nextRentalId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextRentalID(String currentId) {
        if (currentId == null || currentId.isEmpty()) {
            return "RNT001";
        }

        String numericPart = currentId.replaceAll("\\D+", "");
        int idNumber = Integer.parseInt(numericPart);

        idNumber++;

        return String.format("RNT%03d", idNumber);
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {

        String id = cmbCustomerId.getValue();
        try {
            CustomerDTO customer = customerService.searchById(id);
            lblCustomerName.setText(customer.getCustomer_name());
            customer.getCustomer_contact_Number();
            contactNumber = customer.getCustomer_contact_Number();
            System.out.println(contactNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colResDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    @FXML
    void cmbRessIDOnAction(ActionEvent event) {
        String id = cmbDressId.getValue();
        try {
            List<DressDTO> dressList = dressService.getAllDress(id);
            if (!dressList.isEmpty()) {
                DressDTO dress = dressList.get(0);
                lblDressName.setText(dress.getName());
                lblRentPrice.setText(String.valueOf(dress.getPircePerDate()));
            } else {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AddToCartOnAction(ActionEvent event) {
        String code = cmbDressId.getValue();
        String name = lblDressName.getText();
        LocalDate stdate = stDate.getValue();
        LocalDate LastDate = lastDate.getValue();
        double rentPrice = Double.parseDouble(lblRentPrice.getText());
        long daysDifference = ChronoUnit.DAYS.between(stDate.getValue(), lastDate.getValue());
        double price = daysDifference * rentPrice;
        JFXButton update = getJfxButton2();
        JFXButton action = getJfxButton();
        boolean found = false;

        for (RentalTm tm : obList) {
            if (code.equals(tm.getId())) {


                tm.setPrice(price);

                tblRental.refresh();
                found = true;
                break;
            }
        }
        if (!found) {
            RentalTm tm = new RentalTm(code, name, stdate, LastDate, rentPrice, update, action);
            obList.add(tm);
            System.out.println(obList);
        }
        tblRental.setItems(obList);
        tblRental.refresh();
        lblTotal.setText(String.valueOf(price));

    }

    private JFXButton getJfxButton2() {

        JFXButton Edit = new JFXButton("update");
        Edit.setCursor(Cursor.HAND);

        Edit.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Do you want to edit this record?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(yesButton, noButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yesButton) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SubView/employeeUpdate_form.fxml"));
                try {
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return Edit;
    }

    private JFXButton getJfxButton() {
        JFXButton btnRemove = new JFXButton("action");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            RentalTm selectedEmployee = tblRental.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                try {
                    boolean deleted = customerService.delete(selectedEmployee.getId());
                    if (deleted) {
                        obList.remove(selectedEmployee);
                        tblRental.refresh();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete Rental");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting Rental.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a Rental to remove.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        return btnRemove;
    }

    @FXML
    void btmCanselOnAction(ActionEvent event) {

    }

    @FXML
    void btmSelectDress(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File selectedFile = fileChooser.showOpenDialog(btmSelectDress.getScene().getWindow());

        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                imageService.saveImageToDatabase(byteArrayOutputStream.toByteArray());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("image Save successful");

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @SneakyThrows
    @FXML
    void btmUpdateOnaction(ActionEvent event) {
        String rentalId = lblRentalId.getText();
        String cusId = cmbCustomerId.getValue();
        String paymentId = lblGenaratePayment.getText();
        String name = lblCustomerName.getText();

        LocalDate startDate = stDate.getValue();
        LocalDate lsDate = lastDate.getValue();
        long daysDifference = ChronoUnit.DAYS.between(startDate, lsDate);
        int days = (int) daysDifference;
        String payment = lblGenaratePayment.getText();
        Date date = Date.valueOf(stDate.getValue());
        double total = Double.parseDouble(lblTotal.getText());

        var Rental = new Lk.ijse.Dress.Entity.Rental(rentalId, cusId, paymentId, startDate, lsDate, days);
        System.out.println(Rental);
        var payemnt = new Lk.ijse.Dress.Entity.Payment(payment, date, total);
        Lk.ijse.Dress.Entity.OrderRental orderRental = new Lk.ijse.Dress.Entity.OrderRental(Rental, payemnt, name);

        List<Lk.ijse.Dress.Entity.RentalDress> odlist = new ArrayList<>();
        for (int i = 0; i < tblRental.getItems().size(); i++) {
            RentalTm tm = obList.get(i);

            Lk.ijse.Dress.Entity.RentalDress rd = new RentalDress(rentalId, tm.getId(), tm.getPrice(), tm.getDate(), tm.getReservationDate());
            odlist.add(rd);
        }
        Lk.ijse.Dress.Entity.PlaceRental po = new Lk.ijse.Dress.Entity.PlaceRental(Rental, odlist, payemnt);
        boolean isPlaced = transactionService.placeRental(po);
        System.out.println("True enowad trans eken: " + isPlaced);
        System.out.println("00");
        if (isPlaced) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Successfully Placed");
            alert.showAndWait();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SubView/PaymentRental_form.fxml"));
            Parent root = loader.load();
            PaymentRentalFormController paymentRentalFormController = loader.getController();

            paymentRentalFormController.setRentalDetails(orderRental.getRental(), orderRental.getPayment(), orderRental.getName());


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Rntal Placed Unsuccessfully!").show();
        }

    }

    @FXML
    void cCheckInOnAction(ActionEvent event) {

    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    @FXML
    void choicePaidOnAction(ActionEvent event) {

    }

    public void imageButtonOnAction(ActionEvent actionEvent) throws SQLException {
        // displayImageFromDatabase();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDate();
        getDiscountCodes();
        getCustomerIds();
        getCurrentRentalId();
        setCellValueFactory();
        getCurrentPaymentID();
        connectio();
        stDate.setOnAction(event -> {
            LocalDate selectedDate = stDate.getValue();

            saveDate(selectedDate);
        });

    }
}
