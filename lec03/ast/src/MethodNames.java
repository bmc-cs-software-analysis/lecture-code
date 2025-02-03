import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.io.FileInputStream;

public class MethodNames {

    public static void main(String[] args) throws Exception {

        String fname = args[0];

        JavaParser parser = new JavaParser();

        FileInputStream inputStream = new FileInputStream(fname);

        // parse the program into an AST
        CompilationUnit cu = parser.parse(inputStream).getResult().get();

        VoidVisitor<Void> methodNameVisitor = new MethodPrinter();
        methodNameVisitor.visit(cu, null);
    }

    /*
     
     * VoidVisitorAdapter - does not return anything or modify the underlying
     * tree.
     */
    private static class MethodPrinter extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration md, Void arg) {
            super.visit(md, arg);
            System.out.println("Method name: " + md.getName());
        }
    }

}
