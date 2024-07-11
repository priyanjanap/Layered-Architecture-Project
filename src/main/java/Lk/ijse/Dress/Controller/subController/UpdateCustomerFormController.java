package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.DTO.CustomerDTO;
import Lk.ijse.Dress.Service.Custome.Impl.CustomerServiceImpl;
import Lk.ijse.Dress.Service.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.sql.SQLException;

public class UpdateCustomerFormController {

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtTell;
    CustomerServiceImpl customerService= (CustomerServiceImpl) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);

    public void initialize() {
    getCustomerIds();
    }
    @FXML
    void btmCanselOnACtion(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtCustomerName.setText("");
        txtAddress.setText("");
        txtMail.setText("");
        txtTell.setText("");
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
    @FXML
    void btmUpdateOnAction(ActionEvent event) {
        String id= (String) cmbCustomerId.getValue();
        String name=txtCustomerName.getText();
        String add=txtAddress.getText();
        int tell= Integer.parseInt(txtTell.getText());
        String  mail= txtMail.getText();

        CustomerDTO customer= new   CustomerDTO(id,name,add,tell,mail);

        try {
            boolean isUpdated = customerService.update(customer);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
