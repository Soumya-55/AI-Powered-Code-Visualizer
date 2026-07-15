package ui;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class VariablesPanel extends VBox {

    private Label title;
    private VBox contentBox;

    public VariablesPanel() {

        title = new Label("📊 Variables");

        title.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:18px;" +
                "-fx-font-weight:bold;"
        );

        contentBox = new VBox(8);

        ScrollPane scrollPane =
                new ScrollPane(contentBox);

        scrollPane.setFitToWidth(true);

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
                "-fx-border-color:#4CAF50;" +
                "-fx-border-width:2;" +
                "-fx-padding:10;"
        );
    }

  public void setVariables(List<String> variables) {

    getChildren().clear();

    Label title = new Label("📊 Variables");

    title.setStyle(
            "-fx-text-fill:white;" +
            "-fx-font-size:18px;" +
            "-fx-font-weight:bold;"
    );

    getChildren().add(title);

    for(String variable : variables) {

        Label label = new Label(variable);

        label.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;"
        );

        getChildren().add(label);
    }


        for(String variable : variables) {

            Label label =
                    new Label(variable);

            label.setStyle(
                    "-fx-text-fill:white;" +
                    "-fx-font-size:14px;"
            );

            contentBox.getChildren().add(label);
        }
    }
}