package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        MainView root = new MainView();

        Scene scene =
                new Scene(root, 1400, 850);

        scene.getStylesheets()
                .add(getClass()
                .getResource("/dark-theme.css")
                .toExternalForm());

        stage.setTitle("Code Visualizer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}