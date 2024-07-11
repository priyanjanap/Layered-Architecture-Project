package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.DTO.SupplierDTO;
import Lk.ijse.Dress.Model.tm.Suppliertm;
import Lk.ijse.Dress.Service.Custome.SupplierService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SuppliersFormController {

    public JFXButton btmClear;
    public JFXButton btmSearch;
    @FXML
    private JFXButton btmCansel;

    @FXML
    private JFXButton btmCharts;

    @FXML
    private JFXButton btmSave;

    @FXML
    private JFXButton btmUpdate;

    @FXML
    private TableColumn<Suppliertm, ?> colContactNumber;

    @FXML
    private TableColumn<Suppliertm, ?> colSupplierAddress;

    @FXML
    private TableColumn<Suppliertm, ?> colSupplierId;

    @FXML
    private TableColumn<Suppliertm, ?> colSupplierName;

    @FXML
    private Label lblSupplierCount;

    @FXML
    private TableView<Suppliertm> tblSupplier;

    @FXML
    private TextField txtSupplierSearch;
    @FXML
    private TextField txtSupplierAddress;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;


    @FXML
    private TextField txtTell;

    SupplierService supplierService= (SupplierService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Supplier);

    public void initialize() throws SQLException {

        setCellValueFactory();
        loadAllSupplier();
        try {
            supplierCount = getSupplierCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setSupplierCount(supplierCount);

    }
    @SneakyThrows
    public  void loadAllSupplier() {
        ObservableList<Suppliertm> obList= FXCollections.observableArrayList();
        try {
            List<SupplierDTO> materialModelList = supplierService.getAll();
            for (SupplierDTO model : materialModelList) {

                Suppliertm Suppliertm=new Suppliertm(
                        model.getSupplierId(),
                        model.getSupplierName(),
                        model.getSupplierAddress(),
                        model.getContactNumber()
                );
                obList.add(Suppliertm);
            }
            tblSupplier.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            tblSupplier.
                    refresh();
        }


    }

        private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("SupplierAddress"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));

    }

private  void setSupplierCount(int supplierCount){
    lblSupplierCount.setText(String.valueOf(this.supplierCount));

}

    private  int supplierCount = 0;

    public  int getSupplierCount() throws SQLException {
      supplierCount=0;
      try {
          supplierCount=supplierService.getSupplierCount();
      } catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
      }

        return supplierCount;
    }
    @FXML
    void btmCanselOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/SubView/MaterialSupllier_form.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btmChartsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/SubView/SupplierCharts_form.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();


    }

    private void clearFields() {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtSupplierAddress.setText("");
        txtTell.setText("");
    }
    @SneakyThrows
    @FXML
    void btmSaveOnAction(ActionEvent event) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String address = txtSupplierAddress.getText();
        int tel = Integer.parseInt(txtTell.getText());

        SupplierDTO supplier = new SupplierDTO(id, name, address, tel);

        try {
            boolean isSaved = supplierService.add(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier  saved!").show();

                clearFields();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML

    void btmUpdateOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/SubView/supplierUpdate_form.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btmClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }


}
