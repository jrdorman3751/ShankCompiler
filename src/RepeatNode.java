import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 *
 * Assignment 5
 *  1) boolean expression
 *  2) collection of statement nodes
 */
public class RepeatNode extends Node{
    BooleanExpressionNode expression;

    ArrayList<StatementNode> statements;

    RepeatNode(BooleanExpressionNode expression, ArrayList<StatementNode> statements){
        this.expression = expression;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return null;
    }
}
