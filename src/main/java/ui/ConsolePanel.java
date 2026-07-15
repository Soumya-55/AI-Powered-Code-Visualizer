package ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ConsolePanel extends VBox {

    private TextArea console;

    public ConsolePanel() {

        console = new TextArea();

        console.setText("""
Code Visualizer Started...

Ready to Execute.
""");

        // User cannot type into console
        console.setEditable(false);

        // Long lines wrap automatically
        console.setWrapText(true);

        // Dark terminal style
        console.setStyle(
                "-fx-control-inner-background:#0f172a;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:14px;" +
                "-fx-highlight-fill:#00BFFF;" +
                "-fx-highlight-text-fill:white;"
        );

        getChildren().add(console);

        // Allow console to grow with window size
        VBox.setVgrow(
                console,
                Priority.ALWAYS
        );

        // Panel styling
        setStyle(
                "-fx-background-color:#16213e;" +
                "-fx-border-color:#607D8B;" +
                "-fx-border-width:2;" +
                "-fx-padding:10;"
        );
    }

    public void appendText(String text) {
        console.appendText("\n" + text);
    }

    public void clearConsole() {
        console.clear();
    }

    public void setText(String text) {
        console.setText(text);
    }
}
