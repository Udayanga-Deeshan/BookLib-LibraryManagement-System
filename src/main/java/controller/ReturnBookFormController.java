package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Borrow;
import dto.BorrowDetails;
import dto.ReturnBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import service.custom.BorrowService;
import service.custom.ReturnBookService;
import util.BorrowStatus;

import java.time.LocalDate;
import java.util.List;

public class ReturnBookFormController {

    public JFXTextField txtMemberId;
    public ComboBox cmbBorrowedBooks;
    public JFXButton btnReturnBooks;
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
        String borrowID = txtBorrowId.getText();
        String memberId = txtMemberId.getText();
        String bookID = cmbBorrowedBooks.getValue().toString();
        LocalDate borrowedDate = LocalDate.parse(txtBorrowedDate.getText());
        LocalDate returnDate = dateReturnDate.getValue();

        ReturnBook returnBook = new ReturnBook(borrowID, memberId, bookID,borrowedDate, returnDate, BorrowStatus.RETURNED);
        boolean isReturned = returnBookService.returnBook(returnBook);



        if(isReturned){
            new Alert(Alert.AlertType.INFORMATION,"Book Returned Successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Failed to return book").show();
        }

    }

    private boolean isSelectedBook(){
        return  false;
    }

    @FXML
    void btnSearchBorrowedBooksOnAction(ActionEvent event) {

        Borrow borrowedBooksById = borrowService.findBorrowedBooksById(txtBorrowId.getText());
        System.out.println(borrowedBooksById);
        if(borrowedBooksById.getBorrowStatus().equals(BorrowStatus.RETURNED)){
            new Alert(Alert.AlertType.WARNING,"books are already returned").show();
            btnReturnBooks.setDisable(true);

        }else {
            txtMemberId.setText(borrowedBooksById.getMemberId());
            txtBorrowedDate.setText(borrowedBooksById.getBorrowDate().toString());

            List<String> bookList = borrowedBooksById.getBorrowedBooks().stream()
                    .map(BorrowDetails::getBookId).toList();

            cmbBorrowedBooks.getItems().setAll(bookList);
        }




    }

}
