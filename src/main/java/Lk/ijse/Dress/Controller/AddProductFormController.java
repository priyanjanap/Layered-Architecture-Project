package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.DTO.ProductDTO;
import Lk.ijse.Dress.Model.tm.ProductAddtm;
import Lk.ijse.Dress.Service.Custome.ImageService;
import Lk.ijse.Dress.Service.Custome.Impl.CustomerServiceImpl;
import Lk.ijse.Dress.Service.Custome.ProductService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class AddProductFormController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDes;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<ProductAddtm> tblNewProduct;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtType;
    CustomerServiceImpl customerService= (CustomerServiceImpl) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);
    ProductService productService= (ProductService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Products);

    ImageService imageService= (ImageService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Image);


    public void initialize() throws SQLException {
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("price"));
        colType.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("category"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }
    ObservableList<ProductAddtm> oblist= FXCollections.observableArrayList();
    @SneakyThrows
    public  void insert(){
        String id= txtId.getText();
        String name=txtName.getText();
        String des=txtDescription.getText();
        String category=txtCategory.getText();
        String type=txtType.getText();
        double price= Double.parseDouble(txtPrice.getText());
        int qty= Integer.parseInt(txtQuantity.getText());

        ProductDTO product=new ProductDTO(id,name,qty,price,des,type,category);
        ProductAddtm productAddtm= new ProductAddtm(id,name,qty,price,des,type,category,getJfxButton());

        try {
            boolean isSaved = productService.add(product);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product  saved!").show();
                    oblist.add(productAddtm);
                    tblNewProduct.setItems(oblist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private JFXButton getJfxButton() {
        JFXButton btnRemove = new JFXButton("action");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ProductAddtm selectedProduct =  tblNewProduct.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                try {
                    boolean deleted = customerService.delete(selectedProduct.getId());
                    if (deleted) {
                        oblist.remove(selectedProduct);
                        tblNewProduct.refresh();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete product.");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting product.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a product to remove.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        return btnRemove;
    }
@SneakyThrows
    @FXML
    void btmDesginPhotoAddOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Window stage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                FileInputStream fis = new FileInputStream(selectedFile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
                byte[] imageData = bos.toByteArray();

            imageService.saveImageToDatabase(imageData);

                fis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    void btmAddToCartOnAction(ActionEvent event) {
        insert();

    }
}
