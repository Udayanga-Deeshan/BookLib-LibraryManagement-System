package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.SuperService;
import service.custom.BookService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class BookManagementFormController implements Initializable {

    public TextField txtBookId;
    public JFXComboBox cmbAvailability;
    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colISBN;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableView<?> tbBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtTitle;

    @Inject
    BookService service;
    @FXML
    void btnAddBookAction(ActionEvent event) {
        String bookId = txtBookId.getText();
        String isbn = txtISBN.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String availability = cmbAvailability.getValue().toString();


        Book book = new Book(bookId, isbn, title, author, genre,availability);

        boolean isAdd = service.addBook(book);
        if(isAdd){
            new Alert(Alert.AlertType.INFORMATION,"Book has been Added Successfully").show();
            clearData();
        }else {
            new Alert(Alert.AlertType.ERROR,"Book Not Added").show();
        }

    }

    public  void clearData(){
        txtBookId.clear();
        txtISBN.clear();
        txtAuthor.clear();
        txtGenre.clear();

    }

    @FXML
    void btnDeleteCustomerAction(ActionEvent event) {

    }

    @FXML
    void btnReloadBooksAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBookAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCustomerAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAvailabilityStatus();
    }

    private void loadAvailabilityStatus(){
        ObservableList<String> availabilityStatus = FXCollections.observableArrayList();
        availabilityStatus.add("Available");
        availabilityStatus.add("Not Available");
        cmbAvailability.setItems(availabilityStatus);
    }
}
