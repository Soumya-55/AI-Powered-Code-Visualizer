package ui;

import ui.VariablesPanel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.flowless.VirtualizedScrollPane;


public class MainView extends BorderPane {
        private CodeArea codeArea;
        private int highlightedLine = -1;
        private int previousStart = -1;
        private int previousEnd = -1;
        private Label aiLabel;

    public MainView() {


        ConsolePanel consolePanel = new ConsolePanel();
ASTPanel astPanel =
        new ASTPanel();



        // Code Editor
         codeArea = new CodeArea();
         setStyle(
        "-fx-background-color:#0f172a;"
);


        codeArea.replaceText("""
              public class Demo {
    public static void main(String[] args) {
        calculate();
        printResult();
    }

    static void calculate() {
        add();
    }

    static void add() {
    }

    static void printResult() {
    }
}
                """);

        codeArea.setParagraphGraphicFactory(
                LineNumberFactory.get(codeArea)
        );

        VirtualizedScrollPane<CodeArea> editor =
                new VirtualizedScrollPane<>(codeArea);

        VBox editorPanel = createPanel(
                "💻 Code Editor",
                editor
        );
        VariablesPanel variablesPanel =
        new VariablesPanel();

        CallStackPanel callStackPanel =
        new CallStackPanel();
        // Execution Flow
            ExecutionPanel executionPanel =
        new ExecutionPanel();
                setTop(new ToolbarPanel(
        consolePanel,
        this,
        variablesPanel,
        executionPanel,
         callStackPanel,
          astPanel

));

        variablesPanel.setPrefHeight(170);


        // AI Panel
 aiLabel = new Label("AI Output Here");

aiLabel.setStyle(
        "-fx-text-fill:white;" +
        "-fx-font-size:16px;" 
); 
aiLabel.setWrapText(true);

ScrollPane aiScroll = new ScrollPane(aiLabel);

aiScroll.setFitToWidth(true);
aiScroll.setFitToHeight(true);
aiScroll.setStyle(
        "-fx-background:#16213e;" +
        "-fx-background-color:#16213e;"
);

aiScroll.setStyle(
        "-fx-control-inner-background:#16213e;" +
        "-fx-background:#16213e;" +
        "-fx-background-color:#16213e;"
);


VBox aiPanel = createPanel(
        "🤖 AI Suggestions",
        aiScroll
);

aiPanel.setStyle(
        "-fx-background-color:#16213e;" +
        "-fx-border-color:#9C27B0;" +
        "-fx-border-width:2;"
);
        VBox leftColumn = new VBox(
        editorPanel,
        variablesPanel
);

leftColumn.setPrefWidth(450);

VBox.setVgrow(editorPanel, Priority.ALWAYS);
HBox.setHgrow(leftColumn, Priority.ALWAYS);
HBox.setHgrow(executionPanel, Priority.ALWAYS);
HBox.setHgrow(callStackPanel, Priority.ALWAYS);
HBox.setHgrow(aiPanel, Priority.ALWAYS);
HBox.setHgrow(astPanel, Priority.ALWAYS);

leftColumn.setMaxWidth(Double.MAX_VALUE);
executionPanel.setMaxWidth(Double.MAX_VALUE);
callStackPanel.setMaxWidth(Double.MAX_VALUE);
aiPanel.setMaxWidth(Double.MAX_VALUE);
astPanel.setMaxWidth(Double.MAX_VALUE);

HBox centerContent = new HBox(
        leftColumn,
        executionPanel,
        callStackPanel,
        aiPanel,
        astPanel
);
centerContent.setSpacing(5);
centerContent.setStyle(
        "-fx-background-color:#0f172a;"
);
centerContent.setPadding(
        new Insets(10)
);
        HBox.setHgrow(editorPanel, Priority.ALWAYS);

        setCenter(centerContent);

        // Console
     consolePanel.setPrefHeight(180);
setBottom(consolePanel);

setStyle(
        "-fx-background-color:#0f172a;"
);
    }
 
  public String getCode() {
         return codeArea.getText();
    }

public void clearAISuggestions() {
    aiLabel.setText("AI Suggestions:");
}

public void updateAISuggestion(String suggestion) {
    aiLabel.setText(
            aiLabel.getText()
            + "\n• "
            + suggestion
    );
}

      public void highlightLine(int lineNumber) {

    try {

        if(previousStart != -1) {
            codeArea.clearStyle(
                    previousStart,
                    previousEnd
            );
        }

        int start =
                codeArea.position(
                        lineNumber,
                        0
                ).toOffset();

        int end =
                start +
                codeArea.getParagraph(
                        lineNumber
                ).length();

        codeArea.setStyle(
                start,
                end,
                java.util.Collections.singleton(
                        "current-line"
                )
        );

        previousStart = start;
        previousEnd = end;

    } catch(Exception e) {
        e.printStackTrace();
    }
}

    private VBox createPanel(String title, Node content) {

        Label heading = new Label(title);

        heading.setStyle(
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:white;"
        );

        VBox panel = new VBox(10);

        panel.getChildren().addAll(
                heading,
                content
        );

        panel.setPrefWidth(350);

        panel.setStyle(
                "-fx-background-color:#16213e;" 
               
        );

        return panel;
    }

}