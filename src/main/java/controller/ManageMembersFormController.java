package controller;

import com.google.inject.Inject;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.MemberService;
import util.MembershipStatus;

import java.util.List;

public class ManageMembersFormController {

    public TableColumn colMembershipStatus;
    public TableColumn colMemberShipDate;
    public TableColumn colContactNumber;
    public TableColumn colEmail;
    public TableColumn colMemberName;
    public TableColumn colMemberId;
    public TableView tblMembers;
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

    }

    @FXML
    void txtSearchMemberOnAction(ActionEvent event) {

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
