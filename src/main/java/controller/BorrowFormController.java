package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.custom.BookService;
import service.custom.MemberService;

import java.net.URL;
import java.util.ResourceBundle;

public class BorrowFormController implements Initializable {

    public JFXComboBox cmbBookCode;
    public TextField txtEmail;


    @FXML
    private JFXComboBox cmbMemberId;

    @FXML
    private TableColumn colBookCode;

    @FXML
    private TableColumn colBorrowDate;

    @FXML
    private TableColumn  colReturnDate;

    @FXML
    private TableColumn  colTitle;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private TableView<?> tbCart;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtName;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private TextField txtTitle;

    @Inject
    BookService bookService;

    @Inject
    MemberService memberService;

    @FXML
    void btnAddToCartAction(ActionEvent event) {

    }

    @FXML
    void btnCommitOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBookIDs();
        loadMemberIDs();

        cmbMemberId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue !=null){
                        System.out.println(newValue);
                        searchedMemberData(newValue.toString());


                    }
        });

        cmbBookCode.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue !=null){
                System.out.println("book cmb"+newValue);
                searchBookData(newValue.toString());
            }
        } );
    }

    private void searchBookData(String id) {
        Book book = bookService.searchBook(id);
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtGenre.setText(book.getGenre());
    }

    private void  loadBookIDs(){
            cmbBookCode.setItems(bookService.getBookIds());
    }

    private  void loadMemberIDs(){
        cmbMemberId.setItems(memberService.getMemberIds());
    }

    private void  searchedMemberData(String id){
        Member member = memberService.searchMemberData(id);
        txtName.setText(member.getName());
        txtEmail.setText(member.getEmail());
    }
}
