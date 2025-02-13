package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookManagementFormController {

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

    @FXML
    void btnAddBookAction(ActionEvent event) {

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
