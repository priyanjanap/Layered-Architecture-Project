package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.DTO.MaterialTypeDTO;
import Lk.ijse.Dress.Model.tm.Materialtm;
import Lk.ijse.Dress.Service.Custome.MaterialService;
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

public class MaterialsFormController {

    public JFXButton btmupdate;
    public JFXButton btmCansel;
    public JFXButton btmSave;
    @FXML
    private JFXButton btmCharts;

    @FXML
    private JFXButton btmMaterialDress;

    @FXML
    private TableColumn<Materialtm, String> colMaterialId;

    @FXML
    private TableColumn<Materialtm, String> colMaterialName;

    @FXML
    private TableColumn<Materialtm, Double> colPrice;

    @FXML
    private TableColumn<Materialtm, Integer> colQty;

    @FXML
    private Label lblMaterial_count;

    @FXML
    private TableView<Materialtm> tblMaterialCarts;

    @FXML
    private TextField txtMaterialId;

    @FXML
    private TextField txtMaterialName;

    @FXML
    private TextField txtMaterialPrice;

    @FXML
    private TextField txtMaterialQty;

    @FXML
    private TextField txtmaterialSearch;
    MaterialService materialService= (MaterialService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Material);

    public void initialize() {

        setCellValueFactory();
        loadAllMaterial();

    }

    private  void setCellValueFactory(){
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("MaterialId"));
        colMaterialName.setCellValueFactory(new PropertyValueFactory<>("MaterialName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public  void loadAllMaterial(){
        ObservableList<Materialtm> oblist= FXCollections.observableArrayList();

        try {
            String id = null;
            List<MaterialTypeDTO> materialModelList = materialService.loadAllMaterial1();
            for (MaterialTypeDTO model : materialModelList) {
                Materialtm materialtm=new Materialtm(
                        model.getMaterialId(),
                        model.getMaterialName(),
                        model.getQty(),
                        model.getPrice()
                );
                oblist.add(materialtm);
            }
         tblMaterialCarts.setItems(oblist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btmChartsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/SubView/Materialcharts_form.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btmMaterialDressOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/View/SubView/materialDress_form.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();

    }
@SneakyThrows
    @FXML
    void btmMaterialUpdateOnAction(ActionEvent event) {
        String materialId = txtMaterialId.getText().trim();

        if (materialId.isEmpty()) {
            showAlert("Validation Error", "Material ID is required.");
            return;
        }

        try {
            MaterialTypeDTO material = materialService.search(materialId);
            if (material == null) {
                showAlert("Error", "Not a valid Material ID. No such material exists.");
                return;
            }

            MaterialTypeDTO materialToUpdate = createMaterialFromForm();

            boolean success = materialService.update1(materialToUpdate);
            if (success) {
                showAlert("Update Successful", "Material updated successfully.");
                refreshMaterialForm();
            } else {
                showAlert("Update Failed", "Failed to update the Material.");
                refreshMaterialForm();
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to update Material: " + e.getMessage());
            refreshMaterialForm();
        }
    }

    private MaterialTypeDTO createMaterialFromForm() {
        String id = txtMaterialId.getText().trim();
        String name = txtMaterialName.getText().trim();
        String qtyStr = txtMaterialQty.getText().trim();
        String priceStr = txtMaterialPrice.getText().trim();

        MaterialTypeDTO materialModel = new MaterialTypeDTO();
        materialModel.setMaterialId(id);

        if (!name.isEmpty()) {
            materialModel.setMaterialName(name);
        }

        if (!qtyStr.isEmpty()) {
            int qty = Integer.parseInt(qtyStr);
            materialModel.setQty(qty);
        }

        if (!priceStr.isEmpty()) {
            double price = Double.parseDouble(priceStr);
            materialModel.setPrice(price);
        }

        return materialModel;
    }


    private void refreshMaterialForm() {
        txtMaterialId.clear();
        txtMaterialName.clear();
        txtMaterialQty.clear();
        txtMaterialPrice.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void btmCanselOnAction(ActionEvent actionEvent) {
        clearFields();
    }
@SneakyThrows
    public void btmSaveOnAction(ActionEvent actionEvent) {
        String id= txtMaterialId.getText();
        String name= txtMaterialName.getText();
        int qty= Integer.parseInt(txtMaterialQty.getText());
        double price= Double.parseDouble(txtMaterialPrice.getText());
        MaterialTypeDTO materialModel=new MaterialTypeDTO(id,name,qty,price);
        try {
            boolean isSaved = materialService.save(materialModel);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Material  saved!").show();

                clearFields();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtMaterialId.setText("");
        txtMaterialName.setText("");
        txtMaterialPrice.setText("");
        txtMaterialQty.setText("");
    }
}
