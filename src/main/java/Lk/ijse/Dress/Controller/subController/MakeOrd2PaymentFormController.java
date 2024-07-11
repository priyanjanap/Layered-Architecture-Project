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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;

public class MakeOrd2PaymentFormController {

    MakeOrdPaymentFormController makeOrdPaymentFormController = new MakeOrdPaymentFormController();
    PaymentOrderService paymentService = (PaymentOrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Payment_Order);

    @FXML
    private JFXButton cansel;
    @FXML
    private JFXComboBox<PaymentType> comboType;
    @FXML
    private Label lblAmountDue;
    @FXML
    private Label lblPayId;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblcusid;
    @FXML
    private Label lblname;
    @FXML
    private TextField txtpayemnt;
    @FXML
    private JFXButton update;

    @FXML
    void initialize() throws SQLException {
        loadOrderDetails();
        ObservableList<PaymentType> oblist = FXCollections.observableArrayList(PaymentType.values());
        comboType.setItems(oblist);
        comboType.setValue(PaymentType.FullPayment);
        comboType.setValue(PaymentType.HalfPayment);
    }

    @SneakyThrows
    public void loadOrderDetails() throws SQLException {
        String nic = makeOrdPaymentFormController.getId();
        if (nic != null) {
            System.out.println("NIC: " + nic);
            try {
                List<PaymentOrderDTO> paymentOrders = paymentService.getPaymentOrdersByNic(nic);
                if (!paymentOrders.isEmpty()) {
                    PaymentOrderDTO paymentOrder = paymentOrders.get(0);
                    lblPayId.setText(paymentOrder.getPayid());
                    lblcusid.setText(paymentOrder.getCusid());
                    lblname.setText(paymentOrder.getName());
                    lblTotal.setText(String.valueOf(paymentOrder.getTotal()));
                    lblAmountDue.setText(String.valueOf(paymentOrder.getAmount()));
                } else {
                    System.out.println("Can't find NIC number.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void canselonAction(ActionEvent event) {

    }

    @FXML
    void comboTypeOnAction(ActionEvent event) {

    }
@SneakyThrows
    @FXML
    void updateonAction(ActionEvent event) {
        String nic = makeOrdPaymentFormController.getId();
        double amount = Double.parseDouble(txtpayemnt.getText());
        PaymentType paymentType = PaymentType.valueOf(String.valueOf(comboType.getValue()));


        try {
            boolean isUpdated =paymentService.update2(nic,amount,paymentType);
            // PaymentRepo.updatePaymentOrder(nic, amount, paymentType);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment order updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Failed to update payment order.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
