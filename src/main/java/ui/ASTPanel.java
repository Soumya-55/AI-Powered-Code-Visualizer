package ui;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ASTPanel extends VBox {

private Label title;
private VBox contentBox;

public ASTPanel(){

    title = new Label("🌳 AST Tree");

    title.setStyle(
            "-fx-text-fill:white;" +
            "-fx-font-size:20px;"
    );

    contentBox = new VBox(10);

    ScrollPane scrollPane =
            new ScrollPane(contentBox);
            setStyle(
        "-fx-background-color:#16213e;" +
        "-fx-border-color:#E91E63;" +
        "-fx-border-width:2;" +
        "-fx-padding:10;"
);
scrollPane.setStyle(
        "-fx-background:#16213e;" +
        "-fx-background-color:#16213e;"
);

contentBox.setStyle(
        "-fx-background-color:#16213e;"
);

    scrollPane.setFitToWidth(true);

    getChildren().addAll(
            title,
            scrollPane
    );

    VBox.setVgrow(
            scrollPane,
            javafx.scene.layout.Priority.ALWAYS
    );

    setSpacing(10);

    setStyle(
            "-fx-background-color:#16213e;" +
            "-fx-border-color:#E91E63;" +
            "-fx-border-width:2;"
    );
}

    public void setNodes(List<String> nodes){

       contentBox.getChildren().clear();

for(String node : nodes){

    Label label =
            new Label(node);

    label.setStyle(
        "-fx-text-fill:white;" +
        "-fx-background-color:transparent;"
);
setStyle(
        "-fx-background-color:#16213e;" +
        "-fx-border-color:#E91E63;" +
        "-fx-border-width:2;" +
        "-fx-padding:10;"
);


    contentBox.getChildren().add(label);
}
    }
}