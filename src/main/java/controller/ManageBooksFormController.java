package controller;

import com.google.inject.Inject;
import db.DBConnection;
import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.custom.BookService;

import java.sql.SQLException;

public class ManageBooksFormController {

    public TextField txtAvailability;
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
        txtAvailability.setText(book.getAvailability());
    }

    public void btnUpdateBookOnAction(ActionEvent actionEvent) {
        boolean isUpdate = service.updateBook(
                new Book(
                        txtBookId.getText(),
                        txtISBN.getText(),
                        txtTitle.getText(),
                        txtAuthor.getText(),
                        txtGenre.getText(),
                        txtAvailability.getText()


                )
        );
        if(isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Book Updated success").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Cannot update book").show();
        }
    }

    public void btnDeleteBookONAction(ActionEvent actionEvent) {
    }

    public void btnGetBookReportOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            JasperDesign load = JRXmlLoader.load("src/main/resources/reports/Books.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
