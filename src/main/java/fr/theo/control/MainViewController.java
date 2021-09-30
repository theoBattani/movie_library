
package fr.theo.control;

import fr.theo.data.table.Movie;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.beans.Observable;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController {

  // Values injected by FXMLLoader
  @FXML Stage stage;
  @FXML Scene scene;
  @FXML private TableView<Movie> tableView; 
  @FXML private TableColumn<Movie, String> titleColumn;
  @FXML private TableColumn<Movie, Integer> yearColumn; 
  @FXML private TableColumn<Movie, String> directorColumn;

  @FXML void onStageClose() {close();}
  @FXML void onNewAction(ActionEvent event) {newAction();}
  @FXML void onModifyAction(ActionEvent event) {modifyAction();}
  @FXML void onDeleteAction(ActionEvent event) {deleteAction();}
  @FXML void mouseClickedOnTable(MouseEvent event) {select();}

  //  This method is called by the FXMLLoader 
  // when initialization is complete
  @FXML void initialize() {

    // attach resize callbacks to the stage
    stage.widthProperty().addListener(
      widthProperty -> onWidth(widthProperty)
    );
    stage.heightProperty().addListener(
      heightProperty -> onHeight(heightProperty)
    );

    // define properties in table columns
    titleColumn.setCellValueFactory(
      new PropertyValueFactory<Movie, String>("title")
    );
    yearColumn.setCellValueFactory(
      new PropertyValueFactory<Movie, Integer>("year")
    ); 
    directorColumn.setCellValueFactory(
      new PropertyValueFactory<Movie, String>("directorName")
    );
    
    // attach list of observables to the table
    tableView.setItems(pullMovies());
  }

  // resize callbacks
  private void onWidth(Observable widthProperty) {
    double width = ((DoubleExpression) widthProperty).getValue();
    tableView.setPrefWidth(width - 196);
  }
  private void onHeight(Observable heightProperty) {
    double height = ((DoubleExpression) heightProperty).getValue();
    tableView.setPrefHeight(height - 64);
  }


  // Buttons actions
  private void modifyAction() {Controller.requestOpenMovieView();}
  private void deleteAction() {Controller.requestDeleteSelectedMovie();}
  private void newAction() {
    Controller.setSelection(null);
    Controller.requestOpenMovieView();
  }

  private void close() {
    // close the database connection
    Controller.getConnection().close();
  }

  private void select() {
    // give the selected movie in the table view to the controller
    Controller.setSelection(
      tableView.getSelectionModel().getSelectedItem()
    );
  } 

  private ObservableList<Movie> pullMovies() {
    // grab the movies in the controller 
    Controller.requestPullMovieList();
    return Controller.getMovies();
  }
}








