package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.MemberService;
import util.MembershipStatus;

import java.time.LocalDate;
import java.util.List;

public class ManageMembersFormController {

    public TableColumn colMembershipStatus;
    public TableColumn colMemberShipDate;
    public TableColumn colContactNumber;
    public TableColumn colEmail;
    public TableColumn colMemberName;
    public TableColumn colMemberId;
    public TableView tblMembers;
    public JFXTextField txtSerachMember;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMemberName;

    @FXML
    private TextField txtMembershipDate;

    @FXML
    private TextField txtMembershipStatus;

    @FXML
    private TextField txtPhoneNumber;

    @Inject
    MemberService service;

    @FXML
    void btnDeleteMemberOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateMemberOnAction(ActionEvent event) {
        boolean isUpdate = service.updateMember(
               new Member(
                      txtSerachMember.getText(),
                       txtMemberName.getText(),
                       txtEmail.getText(),
                       txtPhoneNumber.getText(),
                      LocalDate.parse(txtMembershipDate.getText())
               )
        );

        if(isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Member details Updated successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Member cannot updated").show();
        }

    }

    @FXML
    void txtSearchMemberOnAction(ActionEvent event) {
        Member member = service.searchMember(txtSerachMember.getText());
        txtMemberName.setText(member.getName());
        txtEmail.setText(member.getEmail());
        txtMembershipDate.setText(member.getMembershipDate().toString());
        txtPhoneNumber.setText(member.getContactNumber());



    }


    public void btnViewAllMembersOnAction(ActionEvent actionEvent) {

        loadMemberTable();
        
    }

    private  void loadMemberTable(){
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colMemberShipDate.setCellValueFactory(new PropertyValueFactory<>("membershipDate"));


        List<Member> memberList = service.getAll();
        ObservableList<Member> memberObservableList = FXCollections.observableArrayList();
       for(Member member: memberList){
           memberObservableList.add(member);
       }
        tblMembers.setItems(memberObservableList);
    }
}
