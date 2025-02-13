package controller;

import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.SuperService;
import service.custom.BookService;
import util.ServiceType;

public class BookManagementFormController {

    public TextField txtBookId;
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

    BookService service= ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);
    @FXML
    void btnAddBookAction(ActionEvent event) {
        String bookId = txtBookId.getText();
        String isbn = txtISBN.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();

        Book book = new Book(bookId, isbn, title, author, genre);
        service.addBook(book);

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

}
