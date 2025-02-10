import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;

import java.io.FileInputStream;
import java.util.List;

public class CapitalizeVars {

    public static void main(String[] args) throws Exception {

        String fname = args[0];

        JavaParser parser = new JavaParser();

        FileInputStream inputStream = new FileInputStream(fname);

        // parse the program into an AST
        CompilationUnit cu = parser.parse(inputStream).getResult().get();

        ModifierVisitor<String> capitalizer = new Capitalizer();
        capitalizer.visit(cu, "decls");

        System.out.println(cu);
    }

    /*
     * ModifierVisitor - modifies the underlying tree.
     * Notice the return type of the visit method now returns a Node    
     */
    private static class Capitalizer extends ModifierVisitor<String> {
        @Override
        public MethodDeclaration visit(MethodDeclaration md, String targetMethod) {
            super.visit(md, targetMethod);

            if (md.getName().toString().equals(targetMethod)) {
                //Walks the AST rooted at md returning all nodes of type VariableDeclarator  
                List<SimpleName> identifiers = md.findAll(SimpleName.class);
                for (SimpleName ident : identifiers) {
                    ident.setIdentifier(ident.asString().toUpperCase());
                }
            }

            return md;
        }
    }

}
