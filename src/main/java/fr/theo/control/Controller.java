package fr.theo.control;

import fr.theo.App;
import fr.theo.data.base.MovieDataBaseConnection;
import fr.theo.data.table.Movie;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Controller {

  // class fields
  private static MovieDataBaseConnection connection;
  private static Movie selection;
  private static ObservableList<Movie> movies = FXCollections.observableArrayList();
  
  // static getters 
  public static Movie getSelection() {return selection;}
  public static MovieDataBaseConnection getConnection() {return connection;}
  public static ObservableList<Movie> getMovies() {return movies;}

  // static setters
  public static void setSelection(Movie pSelection) {selection = pSelection;}
  public static void setConnection(MovieDataBaseConnection pConnection) {connection = pConnection;}

  // requests from view controllers
  public static void requestOpenMovieView() {App.openMovieView();}
  public static void requestPullMovieList() {pullMovieList();}
  public static void requestDeleteSelectedMovie() {deleteSelectedMovie();}

  private static void pullMovieList() {
    movies.removeAll(movies);
    for (Movie movie: connection.getMovies())
      movies.add(movie);
  } 

  private static void deleteSelectedMovie() {
    if (selection == null) return;
    else connection.deleteMovie(selection);
  }
}









