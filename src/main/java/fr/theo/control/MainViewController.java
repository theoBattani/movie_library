
package fr.theo.control;

import java.util.ResourceBundle;
import java.net.URL;

import javafx.beans.Observable;
import javafx.beans.binding.DoubleExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

import fr.theo.App;
import fr.theo.data.Movie;

public class MainViewController {
    
    ObservableList<Movie> movieList = FXCollections.observableArrayList();

    // ResourceBundle that was given to the FXMLLoader
    @FXML private ResourceBundle resources;

    // URL location of the FXML file that was given to the FXMLLoader
    @FXML private URL location;

    // Values injected by FXMLLoader
    @FXML Stage stage;
    @FXML Scene scene;
    @FXML private TableView<Movie> tableView; 
    @FXML private TableColumn<Movie, String> titleColumn;
    @FXML private TableColumn<Movie, Integer> yearColumn; 
    @FXML private TableColumn<Movie, String> directorColumn;

    @FXML void onStageClose(ActionEvent event) {App.getConnection().close();}

    @FXML void onAddAction(ActionEvent event) {App.openMovieView();}

    @FXML void onDeleteAction(ActionEvent event) {
        Movie movie = tableView.getSelectionModel().getSelectedItem();
        deleteMovie(movie);
        update();
    }

    @FXML void onUpdateAction(ActionEvent event) {
        update();
    }

    // This method is called by the FXMLLoader when initialization is complete
    @FXML void initialize() {

        stage.widthProperty().addListener(widthProperty -> onWidth(widthProperty));
        stage.heightProperty().addListener(heightProperty -> onHeight(heightProperty));

        titleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("directorName"));
        
        tableView.setItems(movieList);
        update();
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



    private void updateMovieList() {
        movieList.removeAll(movieList);
        for (Movie movie: App.getConnection().getMovies())
            movieList.add(movie);
    }

    private void update() {
        updateMovieList();
    }

    private void deleteMovie(Movie movie) {
        App.getConnection().deleteMovie(movie);
    }
}

