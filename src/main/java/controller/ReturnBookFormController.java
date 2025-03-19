package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Borrow;
import dto.BorrowDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import service.custom.BorrowService;
import service.custom.ReturnBookService;

import java.util.List;

public class ReturnBookFormController {

    public JFXTextField txtMemberId;
    public ComboBox cmbBorrowedBooks;
    @FXML
    private JFXButton btnSearch;

    @FXML
    private DatePicker dateReturnDate;

    @FXML
    private Label lblStatus;

    @FXML
    private JFXTextField txtBorrowId;

    @FXML
    private JFXTextField txtBorrowedDate;

    @Inject
    ReturnBookService returnBookService;

    @Inject
    BorrowService borrowService;

    @FXML
    void btnReturnBooksOnAction(ActionEvent event) {


    }

    @FXML
    void btnSearchBorrowedBooksOnAction(ActionEvent event) {

        Borrow borrowedBooksById = borrowService.findBorrowedBooksById(txtBorrowId.getText());
        System.out.println(borrowedBooksById);
        txtMemberId.setText(borrowedBooksById.getMemberId());
        txtBorrowedDate.setText(borrowedBooksById.getBorrowDate().toString());

        List<String> bookList = borrowedBooksById.getBorrowedBooks().stream()
                .map(BorrowDetails::getBookId).toList();

        cmbBorrowedBooks.getItems().setAll(bookList);



    }

}
