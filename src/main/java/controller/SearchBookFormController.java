package controller;

import com.google.inject.Inject;
import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.custom.BookService;
import service.custom.impl.BookServiceImpl;

public class SearchBookFormController {

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtTitle;

    @Inject
    BookService service;
    @FXML
    void btnSearchBookAction(ActionEvent event) {
        txtIDOnAction(event);
    }



    public void txtIDOnAction(ActionEvent actionEvent) {
        Book book = service.searchBook(txtBookId.getText());
        txtISBN.setText(book.getISBN());
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtGenre.setText(book.getGenre());
    }
}
