package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardFormController {



    @FXML
    private AnchorPane loadContent;



    @FXML
    void btnSearchBookOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/managebooks.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());



    }

    public void btnManageMembersOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/managememberinfo.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());
    }

    public void btnAddMemberOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/addmember.fxml "));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());
    }

    public void btnBorrowBooksOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/borrowform.fxml "));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());
    }

    public void btnAddBookOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/addbook.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());
    }

    public void btnReturnBooksOnAction(ActionEvent actionEvent) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/returnbookform.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());
    }
}
