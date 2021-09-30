
package fr.theo;

// imports from project packages
import fr.theo.control.Controller;
import fr.theo.util.MovieDataBaseConnection;

// imports from JRE system libraries
import java.io.IOException;

// imports from external libraries
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class App extends Application {

  private static void newStage(String fxml, String title) throws IOException {
    Stage stage = (new FXMLLoader(App.class.getResource("/fxml/"+fxml+".fxml"))).load();
    stage.setTitle(title);
    stage.show();
  }

  public static void openMovieView() {
    try {newStage("movie-view", "Movie View");} 
    catch (IOException e) {e.printStackTrace();}
  }

  @Override 
  public void start(Stage s) throws IOException {
    newStage("main-view", "Movie Libray");
  }

  public static void main(String[] args) {
    Controller.setConnection(new MovieDataBaseConnection());
    launch(args);
  }
}









