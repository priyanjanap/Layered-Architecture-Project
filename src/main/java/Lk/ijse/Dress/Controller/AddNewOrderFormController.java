package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.Controller.subController.PaymentOrderFormController;
import Lk.ijse.Dress.DTO.CustomerDTO;
import Lk.ijse.Dress.DTO.MaterialTypeDTO;
import Lk.ijse.Dress.Entity.OrderPayment;
import Lk.ijse.Dress.Model.tm.MaterialOrderTm;
import Lk.ijse.Dress.Service.Custome.Impl.CustomerServiceImpl;
import Lk.ijse.Dress.Service.Custome.MaterialService;
import Lk.ijse.Dress.Service.Custome.OrderService;
import Lk.ijse.Dress.Service.Custome.PaymentService;
import Lk.ijse.Dress.Service.Custome.TransactionService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddNewOrderFormController {

    public ComboBox cmbCustomerId;
    public Label lblCustomerName;
    public Label lblPaymentId;
    public JFXComboBox comboPaymentBox;
    CustomerServiceImpl customerService = (CustomerServiceImpl) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);
    MaterialService materialService= (MaterialService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Material);

    ObservableList<MaterialOrderTm> obList = FXCollections.observableArrayList();
    @FXML
    private JFXCheckBox choiceBoxPaid;
    @FXML
    private JFXComboBox<String> cmbMaterialId;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableColumn<?, ?> colAmount;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblLocalTime;
    @FXML
    private Label lblMaterialName;
    @FXML
    private Label lblMaterialPrice;
    @FXML
    private Label lblMatrialCost;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblTailorFees;
    @FXML
    private Label lblTotal;
    @FXML
    private TableView<MaterialOrderTm> tblMaterials;
    @FXML
    private TextField txtAmount;
    @FXML
    private TextField txtBuslt;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TextField txtHips;
    @FXML
    private TextField txtInseam;
    @FXML
    private TextField txtNeck;
    @FXML
    private TextField txtNicNumber;
    @FXML
    private TextField txtSholder;
    @FXML
    private TextField txtWalst;
    private double materialcost;
    TransactionService transactionService= (TransactionService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Trnas);

    OrderService orderService= (OrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.ORDER);
    PaymentService paymentService= (PaymentService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Payment);

    public void initialize() {
        setDate();
        getCustomerIds();
        getMaterialDs();
        setCellValueFactory();
        getCurrentPaymentID();
        getCurrentOrderId();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();
        lblLocalTime.setText(String.valueOf(time));
        lblDate.setText(String.valueOf(now));

    }
@SneakyThrows
    private void getCurrentPaymentID() {
        try {
            String currentId = paymentService.getCurrentId();
            String nextPaymentId = generateNextPaymentId(currentId);
            lblPaymentId.setText(nextPaymentId);
        } catch (SQLException e) {
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
    private void getCurrentOrderId() {
        try {
            String currentId = orderService.getCurrentId();
            String nextOrderId = generateNextOrderId(currentId);
            lblOrderId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null && currentId.startsWith("ORD")) {
            try {
                int idNum = Integer.parseInt(currentId.substring(3));
                return "ORD" + String.format("%03d", idNum + 1);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "ORD001";
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
@SneakyThrows
    private void getMaterialDs() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = materialService.loadAllMaterialIds();
            obList.addAll(codeList);
            cmbMaterialId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    @FXML
    void btmAddOnAction(ActionEvent event) {
        try {
            String orderId = lblOrderId.getText();
            String customerId = cmbCustomerId.getValue().toString();
            String cusname = lblCustomerName.getText();
            LocalDate currentDate = LocalDate.now();
            java.sql.Date orderDate = java.sql.Date.valueOf(currentDate);
            java.sql.Date paymentDate = java.sql.Date.valueOf(currentDate);

            String paymentId = lblPaymentId.getText();
            double amount = Double.parseDouble(lblTotal.getText());

            var order = new Lk.ijse.Dress.Entity.Order(orderId, orderDate, customerId, paymentId);
            var payment = new Lk.ijse.Dress.Entity.Payment(paymentId, paymentDate, amount);

            Lk.ijse.Dress.Entity.OrderPayment orderProcessor = new OrderPayment(order, payment, cusname);

            List<Lk.ijse.Dress.Entity.OrderMaterial> orderMaterialsList = new ArrayList<>();

            for (MaterialOrderTm tm : tblMaterials.getItems()) {
                Lk.ijse.Dress.Entity.OrderMaterial orderMaterial = new Lk.ijse.Dress.Entity.OrderMaterial(orderId, tm.getId(), tm.getAmount(), tm.getPrice());
                orderMaterialsList.add(orderMaterial);
            }

            Lk.ijse.Dress.Entity.PlaceOrder placeOrder = new Lk.ijse.Dress.Entity.PlaceOrder(order, orderMaterialsList, payment);

          //  boolean isPlaced = transactionRepo.placeOrder(placeOrder);

           boolean is=transactionService.placeOrder(placeOrder);

            if (is) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Successfully Placed");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SubView/PayementOrder_form.fxml"));
                Parent root = loader.load();
                PaymentOrderFormController paymentOrderFormController = loader.getController();

                paymentOrderFormController.setOrderDetails(orderProcessor.getOrder(), orderProcessor.getPayment(), orderProcessor.getName());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Place Order").show();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Amount").show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred").show();
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    @FXML
    void btmCanselOnAction(ActionEvent event) {

    }

    @FXML
    void btmGenarateInvoice(ActionEvent event) {

    }

    @FXML
    void buttonAddAnotherOnAction(ActionEvent event) {
        String id = cmbMaterialId.getValue();
        String name = lblMaterialName.getText();
        double price = Double.parseDouble(lblMaterialPrice.getText());
        int amount = Integer.parseInt(txtAmount.getText());
        JFXButton btnRemove = getJfxButton();
        materialcost = price * amount;
        lblMaterialPrice.setText(String.valueOf(materialcost));
        boolean found = false;

        for (MaterialOrderTm tm : obList) {
            if (id.equals(tm.getId())) {


                tblMaterials.refresh();
                found = true;
                break;
            }
        }
        if (!found) {
            MaterialOrderTm tm = new MaterialOrderTm(id, name, price, amount, btnRemove);
            obList.add(tm);
        }
        tblMaterials.setItems(obList);
        tblMaterials.refresh();
        lblMatrialCost.setText(String.valueOf(materialcost));


    }

    public void Mashiment() {
        double wast = 250;
        double busl = 100;
        double insea = 100;
        double hip = 100;
        double nec = 100;
        double shol = 200;

        double Waslt = Double.parseDouble(txtWalst.getText());
        double buslt = Double.parseDouble(txtBuslt.getText());
        double inseam = Double.parseDouble(txtInseam.getText());
        double Hips = Double.parseDouble(txtHips.getText());
        double Neck = Double.parseDouble(txtNeck.getText());
        double Sholder = Double.parseDouble(txtSholder.getText());

        double tailerFees = (wast * Waslt) + (buslt * busl) + (inseam * insea) + (Hips * hip) + (Neck * nec) + (Sholder * shol);

        lblTailorFees.setText(String.valueOf(tailerFees));


        lblTotal.setText(String.valueOf(tailerFees + materialcost));


    }
    private JFXButton getJfxButton() {
        JFXButton btnRemove = new JFXButton("action");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            MaterialOrderTm selectedEmployee = tblMaterials.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                try {
                    boolean deleted = customerService.delete(selectedEmployee.getId());
                    if (deleted) {
                        obList.remove(selectedEmployee);
                        tblMaterials.refresh();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete Material");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting Material.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a Material to remove.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        return btnRemove;
    }

    @FXML
    void cmbChooseDesginOnAction(ActionEvent event) {


    }
@SneakyThrows
    @FXML
    public void cmbCustomerIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomerId.getValue();
        try {

            CustomerDTO customer =   customerService.searchById(id);
             lblCustomerName.setText(customer.getCustomer_name());
            customer.getCustomer_contact_Number();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
@SneakyThrows
    @FXML
    public void cmbMaterialIdOnACtion(ActionEvent actionEvent) {
        String id = cmbMaterialId.getValue();
        try {
            List<MaterialTypeDTO> dressList = materialService.loadAllMaterial2(id);
            if (!dressList.isEmpty()) {
                MaterialTypeDTO dress;
                dress = dressList.get(0);
                lblMaterialName.setText(dress.getMaterialName());
                lblMaterialPrice.setText(String.valueOf(dress.getPrice()));

            } else {
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btmGenaraetOnAction(ActionEvent actionEvent) {
        Mashiment();
    }

    public void comboPayemntBoxOnAction(ActionEvent actionEvent) {
    }
}

