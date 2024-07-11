package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.DTO.SupplierPaymentDTO;
import Lk.ijse.Dress.Model.tm.SupplierPaymentTm;
import Lk.ijse.Dress.Service.Custome.Impl.CustomerServiceImpl;
import Lk.ijse.Dress.Service.Custome.SupplierPaymentService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;

public class PaymentCostFormController {

    @FXML
    private AnchorPane anchorpane2;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCID;

    @FXML
    private TableColumn<?, ?> colCName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colPID;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<SupplierPaymentTm> tblPayment;
    SupplierPaymentService supplierPaymentService= (SupplierPaymentService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.SupplierPayment);

    public  void initialize() {
        setCellValueFactory();
loadAllSupplierPayment();    }
    private void setCellValueFactory() {
        colPID.setCellValueFactory(new PropertyValueFactory<>("id1"));
        colCID.setCellValueFactory(new PropertyValueFactory<>("id2"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("dressId"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }
    ObservableList<SupplierPaymentTm> obList= FXCollections.observableArrayList();
    @SneakyThrows
    public void loadAllSupplierPayment() {
        try {
            List<SupplierPaymentDTO> supplierPayments = supplierPaymentService.getAll();
            for (SupplierPaymentDTO payment : supplierPayments) {
                SupplierPaymentTm tm = new SupplierPaymentTm(
                        payment.getId1(),
                        payment.getId2(),
                        payment.getName(),
                        payment.getAmount(),
                        payment.getDate(),
                        getJfxButton()
                );
                obList.add(tm);
            }
            tblPayment.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    CustomerServiceImpl customerService= (CustomerServiceImpl) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);


    private JFXButton getJfxButton() {
        JFXButton btnRemove = new JFXButton("action");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            SupplierPaymentTm selectedCustomer = (SupplierPaymentTm) tblPayment.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                try {
                    boolean deleted = customerService.delete(selectedCustomer.getId1());
                    if (deleted) {
                        obList.remove(selectedCustomer);
                        tblPayment.refresh();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete id.");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting id.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a id to remove.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        return btnRemove;
    }


}
