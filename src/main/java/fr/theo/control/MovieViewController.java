
package fr.theo.control;

import fr.theo.data.table.Movie;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class MovieViewController {

  // Values injected by FXMLLoader
  @FXML private Stage stage; // fx:id="stage"
  @FXML private Scene scene; // fx:id="scene"
  @FXML private TextField titleField; // fx:id="titleField"
  @FXML private TextField yearField; // fx:id="yearField"
  @FXML private TextField directorNameField; // fx:id="directorNameField"

  @FXML void continueAction(ActionEvent event) {validate();}
  @FXML void cancelAction(ActionEvent event) {stage.close();}

  //  This method is called by the FXMLLoader 
  // when initialization is complete
  @FXML void initialize() {
    controlSelection();
    scene.setOnKeyPressed(keyHandler);
  }

  private EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>(){
    @Override
    public void handle(KeyEvent event) {
      switch (event.getCode()) {
        case ENTER: validate(); break;
        case ESCAPE: stage.close(); break;
        default: break;
      }
    }
  };

  private void controlSelection() {
    Movie selection = Controller.getSelection();
    if (selection != null) {
      titleField.setText(selection.getTitle());
      yearField.setText(String.format("%d", selection.getYear()));
      directorNameField.setText(selection.getDirectorName());
    }
  }

  private void validate() {
    if (Controller.getSelection() == null) {
      Controller.getConnection().addMovie(
        new Movie(
          -1,
          titleField.getText(), 
          Integer.parseInt(yearField.getText()), 
          directorNameField.getText()
        )
      );
    }
    else {
      Controller.getConnection().modifyMovie(
        Controller.getSelection(),
        new String[] {"title", "year", "director_name"},
        new String[] {
          titleField.getText(), 
          yearField.getText(), 
          directorNameField.getText()
        }
      );
    }
    Controller.requestPullMovieList();
    stage.close();
  }
}









