package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import service.custom.BookService;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {



    @FXML
    private AnchorPane loadContent;



    @FXML
    void btnSearchBookOnAction(ActionEvent event) throws IOException {
        Injector injector = Guice.createInjector(new AppModule());
        FXMLLoader loader  = new FXMLLoader(this.getClass().getResource("/view/searchbook.fxml"));
        loader.setControllerFactory(injector::getInstance);
        this.loadContent.getChildren().clear();
        this.loadContent.getChildren().add(loader.load());



    }

}
