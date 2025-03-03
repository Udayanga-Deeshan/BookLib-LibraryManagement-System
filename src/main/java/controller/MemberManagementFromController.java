package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import service.custom.MemberService;

import java.time.LocalDate;

public class MemberManagementFromController {

    @FXML
    private DatePicker DataMembership;

    @FXML
    private JFXTextField txtContactNO;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtMemberId;

    @FXML
    private JFXTextField txtName;

    @Inject
    MemberService service;

    @FXML
    void btnMemberAddOnAction(ActionEvent event) {
        String MemberId = txtMemberId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contactNumber = txtContactNO.getText();
        LocalDate date = DataMembership.getValue();

        Member member = new Member(MemberId,name,email,contactNumber,date);
        System.out.println(member);
        boolean isAdd = service.createMember(member);
        if(isAdd){
            new Alert(Alert.AlertType.INFORMATION,"Member has been created successfully").show();
            clearFormData();
        }else {
            new Alert(Alert.AlertType.ERROR,"Member cannot be created").show();
        }
    }

    private  void clearFormData(){
        txtMemberId.clear();
        txtName.clear();
        txtEmail.clear();
        txtContactNO.clear();
    }

}
