package ui;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class CallStackPanel extends VBox {

    private Label title;
    private VBox contentBox;

    public CallStackPanel() {

        title = new Label("📞 Call Stack");

        title.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:20px;"
        );

        contentBox = new VBox(10);

        ScrollPane scrollPane =
                new ScrollPane(contentBox);

        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
        "-fx-background:#16213e;" +
        "-fx-background-color:#16213e;"
);

contentBox.setStyle(
        "-fx-background-color:#16213e;"
);

        getChildren().addAll(
                title,
                scrollPane
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        setSpacing(10);

        setStyle(
                "-fx-background-color:#16213e;" +
                "-fx-border-color:#FF9800;" +
                "-fx-border-width:2;"
        );
    }

  public void setMethods(List<String> methods){

    contentBox.getChildren().clear();

    for(String method : methods){

        Label label = new Label("↳ " + method + "()");

        label.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;"
        );

        contentBox.getChildren().add(label);
    }
}
}