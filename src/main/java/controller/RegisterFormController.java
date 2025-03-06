package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.jasypt.util.text.BasicTextEncryptor;
import service.custom.LibrarianService;

import java.util.Arrays;
import java.util.List;

public class RegisterFormController {

    @FXML
    private JFXPasswordField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @Inject
    LibrarianService service;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {


        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();


        if(!isFieldEmpty()){
            if(password.equals(confirmPassword)){
                boolean isRegister = service.register(new Librarian(name, email, password));
                if(isRegister){
                    new Alert(Alert.AlertType.INFORMATION,"user has been created successfully").show();
                    clearFromData();
                }else {
                    new Alert(Alert.AlertType.ERROR,"user cannot be created").show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR,"password mismatch").show();
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"All fields must be filled").show();
        }
        


    }

    private boolean isFieldEmpty(){
        return txtUserName.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty() || txtConfirmPassword.getText().isEmpty();
    }

    private void clearFromData(){
        txtUserName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtConfirmPassword.clear();
    }

}
