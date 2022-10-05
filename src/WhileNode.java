import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 *
 * Assignment 5
 *  1) boolean expression
 *  2) collection of statement nodes
 */
public class WhileNode extends Node{
    BooleanExpressionNode expression; //ex input != rand

    ArrayList<StatementNode> statements;

    WhileNode(BooleanExpressionNode expression, ArrayList<StatementNode> statements){
        this.expression = expression;
        this.statements = statements;
    }

    @Override
    public String toString() {

        return "\nWhile loop\nBegin\n"+ expression.toString() + "\nStatements: " + statements + "\nEnd" ;
    }
}
