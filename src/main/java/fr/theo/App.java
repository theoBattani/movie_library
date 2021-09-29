package fr.theo;

import fr.theo.control.AddViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import fr.theo.data.MovieDataBaseConnection;


public class App extends Application {

    private static Stage stage;
    private static Stage addStage;
    private static MovieDataBaseConnection dbConnection;

    @Override
    public void start(Stage s) throws IOException {
        dbConnection = new MovieDataBaseConnection();
        stage=s;
        addStage = new Stage();
        setRoot("view","");
    }

    public static Stage getStage() {return stage;}
    public static Stage getAddStage() {return addStage;}
    public static MovieDataBaseConnection getConnection() {return dbConnection;} 

    public static void openAddViewStage() {
        try {
            Scene scene = new Scene(loadFXML("add-view"));
            addStage.setTitle("Add movie window");
            addStage.setScene(scene);
            addStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
