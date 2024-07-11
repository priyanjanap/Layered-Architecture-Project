package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.Entity.RentalTable;
import Lk.ijse.Dress.Model.tm.Rental2Tm;
import Lk.ijse.Dress.Service.Custome.JoinQuearyService;
import Lk.ijse.Dress.Service.Custome.RentalService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class RentalsFormController {

    @FXML
    private TableView<Rental2Tm> btlRental;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colCusName;

    @FXML
    private TableColumn<?, ?> colDressId;

    @FXML
    private TableColumn<?, ?> colReId;

    @FXML
    private TableColumn<?, ?> colResComplete;

    @FXML
    private TableColumn<?, ?> colReservationDate;

    @FXML
    private TableColumn<?, ?> colReturnComplte;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private Label lblLate;

    @FXML
    private Label lblongoing;

    @FXML
    private Label lblrentalcount;

    @FXML
    private Label lblupcoming;

    RentalService rentalService= (RentalService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Rental);
    JoinQuearyService joinQuearyService= (JoinQuearyService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.JoinQueay);
    private void setRentalCount(int rentalCount) {
        lblrentalcount.setText(String.valueOf(rentalCount));
    }
    @FXML
    void btmNewRentalOnAction(ActionEvent event) {
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllRental();
        updateLateRentalCount();
        updateOngoingRentalCount();
        updateUpcomingRentalCount();
        loadCounts();
    }

    private void setCellValueFactory() {
        colReId.setCellValueFactory(new PropertyValueFactory<>("id1"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("id2"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("number"));
        colDressId.setCellValueFactory(new PropertyValueFactory<>("id3"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("date1"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("date2"));
        colResComplete.setCellValueFactory(new PropertyValueFactory<>("check"));
        colReturnComplte.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    public  ObservableList<Lk.ijse.Dress.Entity.RentalTable> loadRentalAndOrdersData() throws SQLException, ClassNotFoundException {
        ObservableList<Lk.ijse.Dress.Entity.RentalTable> rentalList = FXCollections.observableArrayList();


        rentalList=joinQuearyService.getRentals();

        return rentalList;
    }

    ObservableList<Rental2Tm> obList = FXCollections.observableArrayList();

    public void loadAllRental() throws SQLException, ClassNotFoundException {
        List<Lk.ijse.Dress.Entity.RentalTable> rentalTableList = loadRentalAndOrdersData();

        for (RentalTable rentalTable : rentalTableList) {
            Rental2Tm rentalTm = new Rental2Tm(
                    rentalTable.getId1(),
                    rentalTable.getId2(),
                    rentalTable.getName(),
                    rentalTable.getNumber(),
                    rentalTable.getId3(),
                    rentalTable.getDate1(),
                    rentalTable.getDate2(),
                    createCheckBox(),
                    new JFXButton("action")
            );

            obList.add(rentalTm);
        }

        btlRental.setItems(obList);
    }

    private CheckBox createCheckBox() {
        CheckBox checkBox = new CheckBox("compelte");
        checkBox.setCursor(Cursor.HAND);

        checkBox.setOnAction(e -> {
            String message = checkBox.isSelected() ? "CheckBox is selected." : "CheckBox is deselected.";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
            alert.setHeaderText(null);
            alert.showAndWait();
        });

        return checkBox;
    }

    private void updateLateRentalCount() {
        long lateRentalCount = obList.stream()
                .filter(rental -> {
                    Date rentalDate = (Date) rental.getDate2();
                    LocalDate localDate = rentalDate.toLocalDate();
                    return localDate.isBefore(LocalDate.now());
                })
                .count();

        lblLate.setText(String.valueOf(lateRentalCount));
    }

    private void updateOngoingRentalCount() {
        long ongoingRentalCount = obList.stream()
                .filter(rental -> {
                    Date startDate = (Date) rental.getDate1();
                    Date endDate = (Date) rental.getDate2();
                    LocalDate today = LocalDate.now();
                    LocalDate startLocalDate = startDate.toLocalDate();

                    if (endDate == null) {
                        return startLocalDate.isBefore(today) || startLocalDate.isEqual(today);
                    }

                    LocalDate endLocalDate = endDate.toLocalDate();
                    return startLocalDate.isBefore(today) || startLocalDate.isEqual(today) &&
                            endLocalDate.isAfter(today) || endLocalDate.isEqual(today);
                })
                .count();

        lblongoing.setText(String.valueOf(ongoingRentalCount));
    }
    private void updateUpcomingRentalCount() {
        long upcomingRentalCount = obList.stream()
                .filter(rental -> {
                    Date startDate = (Date) rental.getDate1();
                    LocalDate today = LocalDate.now();
                    LocalDate startLocalDate = startDate.toLocalDate();
                    return startLocalDate.isAfter(today);
                })
                .count();

        lblupcoming.setText(String.valueOf(upcomingRentalCount));
    }
    private int getRentalCount() throws SQLException, ClassNotFoundException {
        int re=0;
        try {
            re=rentalService.getRentalCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return  re;
    }
    private void loadCounts() {
        try {
            setRentalCount(getRentalCount());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load counts: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
