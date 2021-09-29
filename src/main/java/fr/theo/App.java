
package fr.theo;

// imports from JRE system libraries
import java.io.IOException;

// imports from external libraries
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

  private static MovieDataBaseConnection dbConnection;

  private static void newStage(String fxml, String title) throws IOException {
    Stage stage = (new FXMLLoader(App.class.getResource("/fxml/"+fxml+".fxml"))).load();
    stage.setTitle(title);
    stage.show();
  }

  public static MovieDataBaseConnection getConnection() {return dbConnection;} 

  public static void openMovieView() {
    try {newStage("movie-view", "Movie View");} 
    catch (IOException e) {e.printStackTrace();}
  }

  @Override 
  public void start(Stage s) throws IOException {
    newStage("main-view", "Movie Libray");
  }

  public static void main(String[] args) {
    dbConnection = new MovieDataBaseConnection();
    launch(args);
  }
}









