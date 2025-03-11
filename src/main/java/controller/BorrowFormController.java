package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.BookService;
import service.custom.BorrowDetailService;
import service.custom.BorrowService;
import service.custom.MemberService;
import util.BorrowStatus;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowFormController implements Initializable {

    public JFXComboBox cmbBookCode;
    public TextField txtEmail;
    public DatePicker date_BorrowDate;

    public DatePicker date_DueDate;
    public TableColumn colDueDate;


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
    private TableView tbCart;

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

    @Inject
    BorrowService borrowService;

    @Inject
    BorrowDetailService borrowDetailService;

    ObservableList<Cart> cartItems = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        String bookId = cmbBookCode.getValue().toString();
        String title = txtTitle.getText();
        LocalDate borrowDate = date_BorrowDate.getValue();
        LocalDate dueDate = date_DueDate.getValue();

        Cart cart = new Cart(bookId, title, borrowDate, dueDate);
        System.out.println(cart);


        if(cartItems.size() <3){
            cartItems.add(cart);
            addToCartTable();
        }else {
            new Alert(Alert.AlertType.WARNING,"borrow limit exceeded").show();
        }








    }

    private void addToCartTable() {
        colBookCode.setCellValueFactory(new PropertyValueFactory<>("bookCode"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        tbCart.setItems(cartItems);
    }

    @FXML
    void btnCommitOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        List<BorrowDetails> borrowDetails = new ArrayList<>();
        cartItems.forEach(item->{
            borrowDetails.add(
                    new BorrowDetails(
                          txtOrderId.getText(),
                          item.getBookCode(),
                          item.getBorrowDate(),
                          null
                    )
            );
        });
        boolean b = borrowService.borrowBooks(
                new Borrow(
                        txtOrderId.getText(),
                        cmbMemberId.getValue().toString(),
                        cmbBookCode.getValue().toString(),
                        date_BorrowDate.getValue(),
                        date_DueDate.getValue(),
                        BorrowStatus.BORROWED,
                        borrowDetails
                )
        );

        System.out.println(b);




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
