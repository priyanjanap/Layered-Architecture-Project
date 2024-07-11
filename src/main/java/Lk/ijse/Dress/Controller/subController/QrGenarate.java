package Lk.ijse.Dress.Controller.subController;

import Lk.ijse.Dress.EmailSender;
import Lk.ijse.Dress.Model.QR;
import Lk.ijse.Dress.QRcode;
import Lk.ijse.Dress.Service.Custome.ImageService;
import Lk.ijse.Dress.Service.ServiceFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class QrGenarate {

    public AnchorPane QrAnchorPane;
    public JFXButton btmEmployeeDesgin;
    public TextField txtNicNumber;
    @FXML
    private JFXButton btmCansel;

    @FXML
    private JFXButton btmQr;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtJobRoll;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTell;

    ImageService imageService= (ImageService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Image);

    @FXML
    void btmCanselOnAction(ActionEvent event) {


    }

    @FXML
    void btmQrOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        int tell = Integer.parseInt(txtTell.getText());
        String jobRoll = txtJobRoll.getText();
        String nic = txtNicNumber.getText();



            QR qr = new QR(id, name, tell, jobRoll, nic);
            boolean isSaved = QRcode.QrGenarater(qr);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "QR code is generated and saved to the database successfully.").show();
                byte[] qrCodeByteArray = generateQRCode(String.valueOf(qr));
                imageService.saveQRCodeToDatabase(id,nic,qrCodeByteArray);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/SubView/emailSender_form.fxml"));
                Parent root = fxmlLoader.load();
                EmailSender emailSender = fxmlLoader.getController();
                QrAnchorPane.getChildren().clear();
                QrAnchorPane.getChildren().add(root);
            }

    }

    private static byte[] generateQRCode(String data) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            MatrixToImageWriter.writeToStream(new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500), "PNG", stream);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }


    public void btmEmployeeeDesginOnAction(ActionEvent actionEvent) {
        String empid = txtId.getText();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File selectedFile = fileChooser.showOpenDialog(btmEmployeeDesgin.getScene().getWindow());
        if (selectedFile != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                byte[] imageData = outputStream.toByteArray();

                boolean saved = imageService.saveEmployeeImage(empid, imageData);

                if (saved) {
                    new Alert(Alert.AlertType.INFORMATION, "Data saved successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save data").show();
                }

            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error reading image file").show();
            }
        }
    }
}