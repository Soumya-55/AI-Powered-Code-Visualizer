package ui;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class ExecutionPanel extends VBox {

    private Label title;
    private VBox contentBox;

    public ExecutionPanel() {

        title = new Label("⚡ Execution Graph");

        title.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:20px;"
        );

        contentBox = new VBox(15);

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
                "-fx-border-color:#00bfff;" +
                "-fx-border-width:2;"
        );
    }

    public void clearSteps() {
        contentBox.getChildren().clear();
    }

    public void addStep(String text) {

        Label step = new Label(text);

        step.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:16px;"
        );

        contentBox.getChildren().add(step);
    }

    public void setSteps(List<String> steps) {

        contentBox.getChildren().clear();

        for(String stepText : steps) {

            Label step = new Label(stepText);

            step.setStyle(
                    "-fx-text-fill:white;" +
                    "-fx-font-size:16px;"
            );

            contentBox.getChildren().add(step);
        }
    }
}