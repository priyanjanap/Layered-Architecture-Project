package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.DTO.SupplierDTO;
import Lk.ijse.Dress.Service.Custome.SupplierService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.SneakyThrows;

import java.sql.SQLException;

public class SupplierUpdateFormController {

    public JFXButton btmClear;
    @FXML
    private JFXButton btmCansel;

    @FXML
    private JFXButton btmUpdate;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;
    SupplierService supplierService= (SupplierService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Supplier);

    @FXML
    public void initialize() {
        txtSupplierId.setOnKeyPressed(this::handleEnterKeyPress);
    }

    private void handleEnterKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            fetchSupplierDetails();
        }
    }
@SneakyThrows
    @FXML
    void btmCanselOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();

        try {
            boolean isDeleted = supplierService.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btmUpdateOnAction(ActionEvent event) {
        updateSupplierDetails();
    }
@SneakyThrows
    private void fetchSupplierDetails() {
        String supplierId = txtSupplierId.getText().trim();

        if (supplierId.isEmpty()) {
            showAlert("Validation Error", "Supplier ID is required.");
            return;
        }

        try {
            SupplierDTO supplier = supplierService.search(supplierId);
            if (supplier == null) {
                showAlert("Error", "Not a valid Supplier ID. No such supplier exists.");
            } else {
                populateForm(supplier);
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to fetch supplier: " + e.getMessage());
        }
    }
@SneakyThrows
    private void updateSupplierDetails() {
        String supplierId = txtSupplierId.getText().trim();

        if (supplierId.isEmpty()) {
            showAlert("Validation Error", "Supplier ID is required.");
            return;
        }

        try {
            SupplierDTO supplierToUpdate = createSupplierFromForm();
            boolean success = supplierService.update(supplierToUpdate);
            if (success) {
                showAlert("Update Successful", "Supplier updated successfully.");
                refreshForm();
            } else {
                showAlert("Update Failed", "Failed to update the supplier.");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to update supplier: " + e.getMessage());
        }
    }

    private SupplierDTO createSupplierFromForm() {
        String supplierId = txtSupplierId.getText().trim();
        String supplierName = txtSupplierName.getText().trim();
        String supplierAddress = txtAddress.getText().trim();
        String contactNumberStr = txtNumber.getText().trim();

        SupplierDTO supplier = new SupplierDTO();
        supplier.setSupplierId(supplierId);

        if (!supplierName.isEmpty()) {
            supplier.setSupplierName(supplierName);
        }
        if (!supplierAddress.isEmpty()) {
            supplier.setSupplierAddress(supplierAddress);
        }
        if (!contactNumberStr.isEmpty()) {
            supplier.setContactNumber(Integer.parseInt(contactNumberStr));
        }

        return supplier;
    }

    private void populateForm(SupplierDTO supplier) {
        txtSupplierName.setText(supplier.getSupplierName());
        txtAddress.setText(supplier.getSupplierAddress());
        txtNumber.setText(String.valueOf(supplier.getContactNumber()));
    }

    private void refreshForm() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtNumber.clear();
        txtAddress.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btmClearOnAction(ActionEvent event) {
        refreshForm();
    }
}
