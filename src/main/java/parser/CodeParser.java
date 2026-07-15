package parser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.expr.MethodCallExpr;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;

public class CodeParser {

    public static List<String> parseVariables(String code) {

        List<String> variables = new ArrayList<>();

        CompilationUnit cu = StaticJavaParser.parse(code);

        cu.findAll(VariableDeclarator.class)
          .forEach(variable -> {

              String variableText =
                      variable.getNameAsString()
                      + " = "
                      + variable.getInitializer()
                                .map(init -> init.toString())
                                .orElse("null");

              variables.add(variableText);
          });

        return variables;
    }

    public static List<String> parseExecution(String code) {

        List<String> steps = new ArrayList<>();

        steps.add("START");

        if(code.contains("for")) {
            steps.add("FOR loop detected");
        }

        if(code.contains("while")) {
            steps.add("WHILE loop detected");
        }

        if(code.contains("if")) {
            steps.add("IF condition detected");
        }

        steps.add("END");

        return steps;
    }
    public static List<String> parseMethods(String code) {

    List<String> methods = new ArrayList<>();

    CompilationUnit cu = StaticJavaParser.parse(code);

    cu.findAll(MethodCallExpr.class)
      .forEach(method -> {
          methods.add(method.getNameAsString());
      });

    return methods;
}
public static List<String> parseAST(String code){

    List<String> nodes =
            new ArrayList<>();

    nodes.add("Compilation Unit");

    if(code.contains("class"))
        nodes.add("└── Class");

    if(code.contains("main"))
        nodes.add("    └── Method: main()");

    if(code.contains("int "))
        nodes.add("        ├── Variable Declaration");

    if(code.contains("for"))
        nodes.add("        └── For Loop");

    if(code.contains("while"))
        nodes.add("        └── While Loop");

    return nodes;
}
}