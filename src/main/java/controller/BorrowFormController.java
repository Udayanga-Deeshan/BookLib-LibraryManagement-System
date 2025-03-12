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
import util.BookAvailabilityStatus;
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
    public TextField txtAvailability;


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




        if(txtAvailability.getText().toUpperCase().equals(BookAvailabilityStatus.AVAILABLE.name())){

            if(cartItems.size() <3){
                Cart cart = new Cart(bookId, title, borrowDate, dueDate);
                System.out.println(cart);
                cartItems.add(cart);
                addToCartTable();
            }else {
                new Alert(Alert.AlertType.WARNING,"borrow limit exceeded").show();
            }

        }else {
            new Alert(Alert.AlertType.WARNING,"This Book is Not available in this moment").show();
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
        boolean isBorrowed = borrowService.borrowBooks(
                new Borrow(
                        txtOrderId.getText(),
                        cmbMemberId.getValue().toString(),
                        date_BorrowDate.getValue(),
                        date_DueDate.getValue(),
                        BorrowStatus.BORROWED,
                        borrowDetails
                )
        );

        if(isBorrowed){
            new Alert(Alert.AlertType.CONFIRMATION,"The Book(s) have been sucessfully Borrowed").show();
            clearData();
        }else {
            new Alert(Alert.AlertType.ERROR,"Cannot Borrow these Books").show();
        }




    }

    private  void clearData(){
        txtOrderId.clear();
        txtName.clear();
        txtEmail.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();
        txtAvailability.clear();
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
        txtAvailability.setText(book.getAvailability());

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
