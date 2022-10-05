import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 *
 * Assignment 5
 *  1) boolean expression
 *  2) collection of statementNodes
 *  3)if node
 */
public class IfNode extends Node{
    BooleanExpressionNode expression;
    ArrayList<StatementNode> statements;
    IfNode elsif;

    IfNode(BooleanExpressionNode expression, ArrayList<StatementNode> statements, IfNode elsif){
        this.expression = expression;
        this.statements = statements;
        this.elsif= elsif;
    }
    IfNode(ArrayList<StatementNode> statements){
        this.statements = statements;
    }

    @Override
    public String toString() {
        if( expression != null) {
            return "\nif " + expression.toString() + "then\n" + statements.toString() + "\n" + elsif.toString();
        }
        else{
            return "\nelse\n" + statements.toString();
        }
    }
}
