package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import config.AppModule;
import dto.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.custom.LibrarianService;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXTextField txtLEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @Inject
    LibrarianService librarianService;

    @FXML
    void btnLoginAction(ActionEvent event) throws IOException {
        Librarian librarian = librarianService.searchByEmail(txtLEmail.getText());
        if(txtLEmail.getText().isEmpty() || txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"ALl fields must be filled out").show();
        } else if (txtLEmail.getText().equals(librarian.getEmail()) ||txtPassword.getText().equals(librarian.getPassword())) {
            Stage stage = new Stage();
            Injector injector = Guice.createInjector(new AppModule());

            FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/dashboard.fxml")));
            loader.setControllerFactory(injector::getInstance);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            txtLEmail.clear();
            txtPassword.clear();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
        }
    }

    @FXML
    void hyperRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/registerform.fxml"))));
        stage.show();
    }

}
