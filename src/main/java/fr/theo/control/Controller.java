/**
 * Sample Skeleton for 'view.fxml' Controller Class
 */

package fr.theo.control;

import fr.theo.App;
import fr.theo.data.Movie;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class Controller {
    
    ObservableList<Movie> movieList = FXCollections.observableArrayList();

    private void updateMovieList() {
        movieList.removeAll(movieList);
        for (Movie movie: App.getConnection().getMovies()) {
            System.out.println(movie.toString());
            movieList.add(movie);
        } 
    }

    private void update() {
        updateMovieList();
    }

    private void delete(Movie movie) {

    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tableView"
    private TableView<Movie> tableView; // Value injected by FXMLLoader

    @FXML // fx:id="titleColumn"
    private TableColumn<Movie, String> titleColumn; // Value injected by FXMLLoader

    @FXML // fx:id="yearColumn"
    private TableColumn<Movie, Integer> yearColumn; // Value injected by FXMLLoader

    @FXML // fx:id="directorColumn"
    private TableColumn<Movie, String> directorColumn; // Value injected by FXMLLoader

    @FXML
    void onAddAction(ActionEvent event) {
        App.openAddViewStage();
    }

    @FXML
    void onDeleteAction(ActionEvent event) {
        Movie movie = tableView.getSelectionModel().getSelectedItem();

        update();
    }

    @FXML
    void onUpdateAction(ActionEvent event) {
        update();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("directorName"));
        
        tableView.setItems(movieList);
        update();
    }
}

