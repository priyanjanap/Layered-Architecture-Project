package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.DTO.PaymentRentalDTO;
import Lk.ijse.Dress.Model.Enum.PaymentType;
import Lk.ijse.Dress.Service.Custome.PaymentOrderService;
import Lk.ijse.Dress.Service.Custome.PaymentRentalService;
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

import java.awt.*;
import java.sql.SQLException;

public class PaymentRentalFormController {
    @FXML
    private TextField amount;

    @FXML
    private TextField nicnumber;

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

    PaymentOrderService paymentOrderService= (PaymentOrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Payment_Order);

PaymentRentalService paymentRentalService= (PaymentRentalService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.PRntal);

    public  void initialize(){
        ObservableList<PaymentType> oblist = FXCollections.observableArrayList(PaymentType.values());
        comboType.setItems(oblist);
        comboType.setValue(PaymentType.FullPayment);
        comboType.setValue(PaymentType.HalfPayment);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String pay=lblPaymentId.getText();
        String cus=lblCusId.getText();
        String name= lblName.getText();
        double total= Double.parseDouble(lblamount.getText());
        String nic=nicnumber.getText();
        PaymentType type=comboType.getValue();
        double amount1= Double.parseDouble(amount.getText());
        double amount2=total-amount1;

        PaymentRentalDTO paymentRental=new PaymentRentalDTO(pay,cus,name,total,nic,type,amount2);

        try {
          //  boolean isSaved = transactionRepo.save1(paymentRental);
            boolean isSaved= paymentRentalService.add(paymentRental);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "payemnt Succefull  saved!").show();


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void setRentalDetails(Lk.ijse.Dress.Entity.Rental rental, Lk.ijse.Dress.Entity.Payment payment, String name) {
    }
}
