/**
 * Sample Skeleton for 'add-view.fxml' Controller Class
 */

 package fr.theo.control;

import java.net.URL;
import java.util.ResourceBundle;

import fr.theo.App;
import fr.theo.data.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="titleField"
    private TextField titleField; // Value injected by FXMLLoader

    @FXML // fx:id="yearField"
    private TextField yearField; // Value injected by FXMLLoader

    @FXML // fx:id="directorNameField"
    private TextField directorNameField; // Value injected by FXMLLoader


    @FXML
    void addMovieAction(ActionEvent event) {
        App.getConnection().addMovie(
            new Movie(
                "null",
                titleField.getText(), 
                Integer.parseInt(yearField.getText()), 
                directorNameField.getText()
            )
        );
        App.getAddStage().close();
    }

    @FXML
    void cancelAction(ActionEvent event) {
        App.getAddStage().close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
