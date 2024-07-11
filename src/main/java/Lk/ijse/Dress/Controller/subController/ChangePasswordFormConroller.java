package Lk.ijse.Dress.Controller.subController;


import Lk.ijse.Dress.Service.Custome.AdminService;
import Lk.ijse.Dress.Service.ServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class ChangePasswordFormConroller {

    public TextField txtOtp;
    public Label lblRepassswordEquals;
    public Label lblValidUserName1;
    javaMailUtil javaMailUtilRefer = new javaMailUtil();
    int randomNumber;
    @FXML
    private Button btmBackToLogin;
    @FXML
    private AnchorPane MainAnchoPane;
    @FXML
    private Button btmContinue;
    @FXML
    private Label btmEaualPassword;
    @FXML
    private Button btmVerifaction;
    @FXML
    private TextField lblOTP;
    @FXML
    private Label lblValidUserName;
    @FXML
    private Label lblotpEqualsVerifationCode;
    @FXML
    private TextField txtEmail1;
    @FXML
    private PasswordField txtPaasowd1;
    @FXML
    private PasswordField txtReTypePassword2;
    @FXML
    private TextField txtUserName1;
    private Node rootNode;
    AdminService adminService= (AdminService) ServiceFactory.getServiceFactory().getDAO(ServiceFactory.ServiceType.Admin);

    public static boolean isValidUsername(String Username) {

        String usernameRegex = "^[a-zA-Z0-9_.-]+$";
        return Username.matches(usernameRegex);
    }

    @SneakyThrows
    @FXML
    void btmContinueOnaction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String username = txtUserName1.getText();
        String password = txtPaasowd1.getText();

        try {
            boolean isPasswordChanged = adminService.changePassword(username, password);
            if (isPasswordChanged) {
                new Alert(Alert.AlertType.INFORMATION, "Change Password Success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Values!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class Not Found Error: " + e.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Unexpected Error: " + e.getMessage()).show();
        }
    }



    private void comparePasswords(String password, String retypePassword) {
        if (password.equals(retypePassword)) {
            lblRepassswordEquals.setText("Passwords match");
            lblRepassswordEquals.setStyle("-fx-text-fill: green;");
        } else {
            lblRepassswordEquals.setText("Passwords do not match");
            lblRepassswordEquals.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    void btmVerifactionOnAction(ActionEvent event) throws Exception {
        String email1Text = txtEmail1.getText();
        new Thread(() -> {
            try {
                javaMailUtilRefer.sendMail(email1Text);
            } catch (Exception e) {
                throw new RuntimeException(e);

            }
        }).start();
        new Alert(Alert.AlertType.INFORMATION, "You have to check your mailbox").show();
    }

    @FXML
    void onOTPTyping(KeyEvent event) {
        try {
            int enteredOtp = Integer.parseInt(txtOtp.getText());
            int savedRandomNumber = javaMailUtilRefer.getSaveRandomNumber();

            if (enteredOtp == savedRandomNumber) {
                lblotpEqualsVerifationCode.setText("Correct OTP");
                lblotpEqualsVerifationCode.setStyle("-fx-text-fill: green;");
                System.out.println("Correct OTP");


                txtPaasowd1.setDisable(false);
                txtReTypePassword2.setDisable(false);


                txtReTypePassword2.textProperty().addListener((observable, oldValue, newValue) -> {
                    comparePasswords(txtPaasowd1.getText(), newValue);
                });
            } else {
                lblotpEqualsVerifationCode.setText("Incorrect OTP");
                lblotpEqualsVerifationCode.setStyle("-fx-text-fill: red;");
                System.out.println("Incorrect OTP");


                txtPaasowd1.setDisable(true);
                txtReTypePassword2.setDisable(true);


                txtReTypePassword2.clear();
            }
        } catch (NumberFormatException e) {
            lblotpEqualsVerifationCode.setText("Invalid OTP");
            System.out.println("Invalid OTP");


            txtPaasowd1.setDisable(true);
            txtReTypePassword2.setDisable(true);
        }
    }

    @FXML
    void btmbacktologinOnaction(ActionEvent event) throws IOException {

        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/VIEW/Login_form.fxml")));
        Scene scene = btmBackToLogin.getScene();
        scene.setRoot(rootNode);
    }

    public void initialize() {

        txtUserName1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                validateUserName(newValue);
            }
        });
        txtReTypePassword2.textProperty().addListener((observable, oldValue, newValue) -> {
            comparePasswords(txtPaasowd1.getText(), newValue);
        });

    }

    private void validateUserName(String username) {
        if (isValidUsername(username)) {
            lblValidUserName1.setText("Valid username");
            lblValidUserName1.setStyle("-fx-text-fill: green;");
        } else {
            lblValidUserName1.setText("Invalid username!");
            lblValidUserName1.setStyle("-fx-text-fill: red;");
        }
    }


}

