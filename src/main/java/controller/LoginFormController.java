package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.custom.LibrarianService;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXTextField txtLEmail;

    @FXML
    private JFXPasswordField txtPassword;



    @FXML
    void btnLoginAction(ActionEvent event) {

    }

    @FXML
    void hyperRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerform.fxml"))));
        stage.show();
    }

}
