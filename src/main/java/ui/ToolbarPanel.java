package ui;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import parser.CodeParser;
import service.AIService;

public class ToolbarPanel extends HBox {
private int currentStep = 0;

private final int[] lines = {
        1,
        4,
        7,
        10
};
private final String[] steps = {
        "START",
        "Variable Initialization",
        "FOR Loop Execution",
        "END"
};
public ToolbarPanel(ConsolePanel consolePanel,
                    MainView mainView,
                    VariablesPanel variablesPanel,
                    ExecutionPanel executionPanel,
                    CallStackPanel callStackPanel,
                    ASTPanel astPanel
                
                ){

        setSpacing(10);

        Button runButton = new Button("▶ Run");
        runButton.setStyle(
        "-fx-background-color:#00bfff;" +
        "-fx-text-fill:white;" +
        "-fx-background-radius:15;" +
        "-fx-padding:8 18;"
);
        
         Button stepButton = new Button("⏭ Step");
         stepButton.setStyle(
        "-fx-background-color:#4CAF50;" +
        "-fx-text-fill:white;" +
        "-fx-background-radius:15;" +
        "-fx-padding:8 18;"
);
       stepButton.setOnAction(e -> {

    if(currentStep < steps.length) {
        executionPanel.addStep(
                steps[currentStep]
        );
        mainView.highlightLine(
        lines[currentStep]
);
if(currentStep == 0){
    variablesPanel.setVariables(
        List.of("sum = 0")
    );
}

if(currentStep == 1){
    variablesPanel.setVariables(
        List.of(
            "sum = 0",
            "i = 0"
        )
    );
}

if(currentStep == 2){
    variablesPanel.setVariables(
        List.of(
            "sum = 1",
            "i = 1"
        )
    );
}

        currentStep++;
    }
});

  Button stopButton = new Button("■ Stop");
  stopButton.setOnAction(e -> {

    executionPanel.clearSteps();

    variablesPanel.setVariables(
            List.of()
    );

    callStackPanel.setMethods(
            List.of()
    );

    astPanel.setNodes(
            List.of()
    );

    mainView.clearAISuggestions();

    consolePanel.appendText(
            "Execution stopped.\n"
    );

    currentStep = 0;
});
  stopButton.setStyle(
        "-fx-background-color:#f44336;" +
        "-fx-text-fill:white;" +
        "-fx-background-radius:15;" +
        "-fx-padding:8 18;"
);

   Button aiButton = new Button("🤖 AI Optimize");
   aiButton.setStyle(
        "-fx-background-color:#9C27B0;" +
        "-fx-text-fill:white;" +
        "-fx-background-radius:15;" +
        "-fx-padding:8 18;"
);
  aiButton.setOnAction(e -> {

    String code = mainView.getCode();

    mainView.clearAISuggestions();

    String response =
            AIService.analyzeCode(code);

    mainView.updateAISuggestion(response);
});

   runButton.setOnAction(e -> { 
        
        currentStep = 0;
        consolePanel.clearConsole();

executionPanel.clearSteps();
variablesPanel.setVariables(List.of());
callStackPanel.setMethods(List.of());
astPanel.setNodes(List.of());
mainView.clearAISuggestions();
   
    String code = mainView.getCode();
    int loopCount = 0;
    List<String> methods =
        CodeParser.parseMethods(code);

        

callStackPanel.setMethods(methods);

List<String> astNodes =
        CodeParser.parseAST(code);
      

astPanel.setNodes(
        astNodes
);

if(code.contains("for(") || code.contains("for ("))
    loopCount++;

if(code.contains("while(") || code.contains("while ("))
    loopCount++;
   

    
  
  
String complexity;

int firstFor = code.indexOf("for");
int secondFor = code.indexOf(
        "for",
        firstFor + 1
);

if(secondFor != -1) {
    complexity =
            "Estimated Time Complexity: O(n²)";
}
else if(loopCount == 1) {
    complexity =
            "Estimated Time Complexity: O(n)";
}
else {
    complexity =
            "Estimated Time Complexity: O(1)";
}

mainView.updateAISuggestion(
        complexity
);

    if(code.contains("for")) {
    mainView.updateAISuggestion(
            "Consider using enhanced for loop if possible."
    );
}

if(code.contains("while")) {
    mainView.updateAISuggestion(
            "Ensure loop termination condition is correct."
    );
}

if(code.contains("System.out.println")) {
    mainView.updateAISuggestion(
            "Consider using logging framework for production code."
    );
}

    List<String> variables =
            CodeParser.parseVariables(code);

    variablesPanel.setVariables(variables);

    List<String> executionSteps =
            CodeParser.parseExecution(code);

        

    executionPanel.setSteps(executionSteps);
  

for(String method : methods){
    executionPanel.addStep(
            "Method Call -> " + method
    );
}

   consolePanel.appendText(
        "Compiling source code..."
);

consolePanel.appendText(
        "Parsing AST..."
);

consolePanel.appendText(
        "Generating execution graph..."
);

consolePanel.appendText(
        "Execution finished successfully."
);
});

        getChildren().addAll(
                runButton,
                stepButton,
                stopButton,
                aiButton
        );
    }
   
}