package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ReturnBookFormController {

    @FXML
    private JFXButton btnSearch;

    @FXML
    private CheckBox chkSelectAll;

    @FXML
    private Label lblStatus;

    @FXML
    private ListView<?> listViewBorrowedBooks;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    void btnReturnBooksOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchBorrowedBooksOnAction(ActionEvent event) {

    }

}
