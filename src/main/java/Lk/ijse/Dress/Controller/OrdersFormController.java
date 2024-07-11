package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.Entity.OrderTable;
import Lk.ijse.Dress.Model.tm.OrdersTm;
import Lk.ijse.Dress.Service.Custome.Impl.CustomerServiceImpl;
import Lk.ijse.Dress.Service.Custome.OrderService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class OrdersFormController {

    @FXML
    private JFXButton btmNewOrder;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colCusName;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colFinished;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colRetunDate;

    @FXML
    private TableColumn<?, ?> colViewDitals;

    @FXML
    private TableColumn<?, ?> colhandOver;

    @FXML
    private Label lblOrderCount;

    @FXML
    private TableView<OrdersTm> tblOrder;
    OrderService orderService= (OrderService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.ORDER);

    @FXML
    void btmNewOrderOnAction(ActionEvent event) {

    }

    public void initialize() {
        loadRentalAndOrdersData();
        setCellValueFactory();
        loadAllSupplier();
        loadCounts();
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id1"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("id2"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colRetunDate.setCellValueFactory(new PropertyValueFactory<>("number"));
        colViewDitals.setCellValueFactory(new PropertyValueFactory<>("handover"));
        colFinished.setCellValueFactory(new PropertyValueFactory<>("finish"));
        colhandOver.setCellValueFactory(new PropertyValueFactory<>("handover"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));
    }


    @SneakyThrows
    public  ObservableList<Lk.ijse.Dress.Entity.OrderTable> loadRentalAndOrdersData() {
        ObservableList<Lk.ijse.Dress.Entity.OrderTable>OrdersList = FXCollections.observableArrayList();
       OrdersList= orderService.getOrders();

        return OrdersList;
    }

    ObservableList<OrdersTm> obList = FXCollections.observableArrayList();

    @SneakyThrows
    public void loadAllSupplier() {

        try {
            List<Lk.ijse.Dress.Entity.OrderTable> materialModelList = loadRentalAndOrdersData();
            for (OrderTable model : materialModelList) {
                OrdersTm Suppliertm = new OrdersTm(
                        model.getOrderId(),
                        model.getCusid(),
                        model.getCusname(),
                        model.getDate(),
                        model.getNumber(),
                        seView(),
                        seTfinished(),
                        SETHANDOVER(),
                        getJfxButton()
                );

                obList.add(Suppliertm);
            }
            tblOrder.setItems(obList);
        } finally {
            tblOrder.refresh();
        }
    }

    private String SETHANDOVER() {
        return "handover";
    }

    private String seView() {
        return "Women";
    }

    private String seTfinished() {
        return "finished";
    }

    CustomerServiceImpl customerService= (CustomerServiceImpl) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.CUSTOMER);


    private JFXButton getJfxButton() {
        JFXButton btnRemove = new JFXButton("action");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            OrdersTm selectedCustomer = tblOrder.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                try {
                    boolean deleted = customerService.delete(selectedCustomer.getId1());
                    if (deleted) {
                        obList.remove(selectedCustomer);
                        tblOrder.refresh();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to delete customer.");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while deleting customer.");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer to remove.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });
        return btnRemove;
    }
    private void setOrderCount(int orderCount) {
        lblOrderCount.setText(String.valueOf(orderCount));
    }

    private int getOrderCount() throws SQLException {
       int ordercount=0;
       try {
           ordercount=orderService.getOrderCount();
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }return  ordercount;
    }
    private void loadCounts() {
        try {
            setOrderCount(getOrderCount());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load counts: " + e.getMessage()).show();
        }
    }
}