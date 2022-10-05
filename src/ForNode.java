import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 *
 * Assignment 5
 *  1) variable reference node
 *  2) start ASTNode
 *  3) end ASTNode
 *  4) collection of statementNodes
 */
public class ForNode extends Node{
    VariableReferenceNode variable;
    Node start;
    Node end;

    ArrayList<StatementNode> statements;

    ForNode(VariableReferenceNode name,Node s, Node e, ArrayList<StatementNode> statements){
        this.variable = name;
        this.start = s;
        this.end = e;
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "\nFor Loop\nBegin\n" + variable.toString() + " From " + start.toString() + " to " + end.toString() + "\n" + statements.toString() + "\nEnd" ;
    }
}
