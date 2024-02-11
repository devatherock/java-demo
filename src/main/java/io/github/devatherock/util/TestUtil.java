package io.github.devatherock.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.UnaryExpr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestUtil {

    public static String sayHello() {
        return "Hello";
    }

    public static void listFieldAccess() throws FileNotFoundException {
        File sourceFile = new File("/home/devaprasadh/Downloads/Example.java");
        CompilationUnit cu = StaticJavaParser.parse(sourceFile);

        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(classDeclaration -> {
            System.out.println("Class: " + classDeclaration.getNameAsString());
            List<String> fields = new ArrayList<>();

            // Find all field names
			classDeclaration.findAll(FieldDeclaration.class).forEach(fieldDeclaration -> {
                fieldDeclaration.getVariables().forEach(variable -> {
                    fields.add(variable.getNameAsString());
                });
            });

            classDeclaration.findAll(MethodDeclaration.class).forEach(methodDeclaration -> {
                System.out.println("  Method: " + methodDeclaration.getNameAsString());

                methodDeclaration.findAll(Expression.class).forEach(expression -> {
                    // Process only specific types of expressions
					if (expression instanceof MethodCallExpr || expression instanceof AssignExpr ||
                            expression instanceof UnaryExpr) {
						// Check if any of the expression fields match the class level fields
                        List<String> matchedFields = fields.stream().filter(field -> {
                            return expression.getChildNodes().stream().anyMatch((node) -> node.toString().contains(field));
                        }).collect(Collectors.toList());
                        System.out.println("Field access: " + matchedFields);
                    }
                });
            });
        });
    }
}
