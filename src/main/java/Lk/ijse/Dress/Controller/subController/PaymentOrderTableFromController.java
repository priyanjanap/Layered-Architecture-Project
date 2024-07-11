package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.DTO.PaymentOrderDTO;
import Lk.ijse.Dress.Model.tm.PaymentOrderTm;
import Lk.ijse.Dress.Service.Custome.PaymentOrderService;
import Lk.ijse.Dress.Service.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.List;

public class PaymentOrderTableFromController {

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
    private TableView<PaymentOrderTm> tblPayment;
    PaymentOrderService paymentService = (PaymentOrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Payment_Order);


    public  void initialize() {
        setCellValueFactory();
        loadAllOrders();
    }
    private void setCellValueFactory() {
        colPID.setCellValueFactory(new PropertyValueFactory<>("payid"));
        colCID.setCellValueFactory(new PropertyValueFactory<>("cusid"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
    ObservableList<PaymentOrderTm> obList= FXCollections.observableArrayList();
    @SneakyThrows
    public void loadAllOrders() {
        try {
            List<PaymentOrderDTO> paymentOrders = paymentService.getAll();
            for (PaymentOrderDTO od : paymentOrders) {
                PaymentOrderTm tm = new PaymentOrderTm(
                        od.getPayid(),
                        od.getCusid(),
                        od.getName(),
                        od.getTotal(),
                        od.getNic(),
                        od.getPaymentType(),
                        od.getAmount()
                );
                obList.add(tm);
            }
            tblPayment.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }
