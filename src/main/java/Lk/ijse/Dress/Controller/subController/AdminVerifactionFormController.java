package Lk.ijse.Dress.Controller.subController;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AdminVerifactionFormController {

    public ImageView colseImage;
    public AnchorPane sideAnchorPane5;
    @FXML
    private Label btmVerify;

    @FXML
    private TextField txtPassowrd;

    @FXML
    private TextField txtUserName;

    @FXML
    void btmVerifyOnAction(MouseEvent event) {

    }

    public void onCanselIconCLick(MouseEvent mouseEvent) {
        Parent parent = sideAnchorPane5.getParent();
        if (parent instanceof Pane) {
            Pane parentPane = (Pane) parent;
            parentPane.getChildren().remove(sideAnchorPane5);
        }
    }
}
