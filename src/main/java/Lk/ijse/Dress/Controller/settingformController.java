package Lk.ijse.Dress.Controller;

import Lk.ijse.Dress.db.DBConnection;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;

public class settingformController {


    @FXML
    private JFXButton addadminButton;

    @FXML
    private AnchorPane ancpane;

    @FXML
    private JFXButton classButton;

    @FXML
    private JFXButton teacherButon;
    @FXML
    private JFXButton incomeReportButton;
    @FXML
    void OnINcomeClick(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("C:\\Games\\Dress2\\src\\main\\resources\\report\\employee_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

      //  Map<String,Object> data = new HashMap<>();
      //  data.put("CustomerID",txtID.getText());
     //   data.put("Total",getTotal());

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);


    }



    @FXML
    void onTeacherClick(ActionEvent event) {
    }

    @FXML
    void onAddAdinCLick(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/view/adminVerify-form.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void onclassClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/deleteUpdateClass-form.fxml"));
        ancpane.getChildren().clear();
        ancpane.getChildren().add(parent);

    }

    @FXML
    void onteacherClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/deleteUpdateteacher-form.fxml"));
        ancpane.getChildren().clear();
        ancpane.getChildren().add(parent);

    }

}
