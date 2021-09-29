/**
 * Sample Skeleton for 'add-view.fxml' Controller Class
 */

 package fr.theo.control;

import java.net.URL;
import java.util.ResourceBundle;

import fr.theo.App;
import fr.theo.data.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AddViewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="stage"
    private Stage stage; // Value injected by FXMLLoader

    @FXML // fx:id="scene"
    private Scene scene; // Value injected by FXMLLoader

    @FXML // fx:id="titleField"
    private TextField titleField; // Value injected by FXMLLoader

    @FXML // fx:id="yearField"
    private TextField yearField; // Value injected by FXMLLoader

    @FXML // fx:id="directorNameField"
    private TextField directorNameField; // Value injected by FXMLLoader

    private void addMovie() {
        App.getConnection().addMovie(
            new Movie(
                -1,
                titleField.getText(), 
                Integer.parseInt(yearField.getText()), 
                directorNameField.getText()
            )
        );
        stage.close();
    }

    private void cancel() {stage.close();}

    @FXML void addMovieAction(ActionEvent event) {addMovie();}
    @FXML void cancelAction(ActionEvent event) {cancel();}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER:
                        addMovie();
                        break;
                    case ESCAPE:
                        cancel();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
