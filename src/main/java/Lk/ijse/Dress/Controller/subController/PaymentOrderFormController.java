package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.DTO.PaymentOrderDTO;
import Lk.ijse.Dress.Model.Enum.PaymentType;
import Lk.ijse.Dress.Service.Custome.PaymentOrderService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.sql.SQLException;

public class PaymentOrderFormController {

    PaymentOrderService paymentService = (PaymentOrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Payment_Order);
    @FXML
    private JFXButton btmUpdate;
    @FXML
    private JFXButton btnCansel;
    @FXML
    private JFXComboBox<PaymentType> comboType;
    @FXML
    private DatePicker date;
    @FXML
    private Label lblCusId;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPaymentId;
    @FXML
    private Label lblamount;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtPayemnt;

    public void initialize() {
        ObservableList<PaymentType> oblist = FXCollections.observableArrayList(PaymentType.values());
        comboType.setItems(oblist);
        comboType.setValue(PaymentType.FullPayment);
        comboType.setValue(PaymentType.HalfPayment);

    }



    @FXML
    @SneakyThrows
    void btmUpdateOnAction(ActionEvent event) {
        String pay = lblPaymentId.getText();
        String cus = lblCusId.getText();
        String name = lblName.getText();
        double total = Double.parseDouble(lblamount.getText());
        String nic = txtNic.getText();
        PaymentType type = comboType.getValue();
        double amount1 = Double.parseDouble(txtPayemnt.getText());
        double amount2 = total - amount1;

        PaymentOrderDTO paymentOrder = new PaymentOrderDTO(pay, cus, name, total, nic, type, amount2);
        try {
            boolean isSaved = paymentService.add(paymentOrder);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "payemnt Succefull  saved!").show();

                clearFields();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void clearFields() {

    }

    @FXML
    void btnCanselOnAction(ActionEvent event) {

    }

    public void setOrderDetails(Lk.ijse.Dress.Entity.Order order, Lk.ijse.Dress.Entity.Payment payment, String name) {
    }
}
