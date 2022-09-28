/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 4-2nd Draft 9/22/22
 *
 */
/*
    Assignment 4:
        1)inherits from statement node
        2) variable reference node and node for expression
 */
public class AssignmentNode extends StatementNode {
    VariableReferenceNode target;
    Node expression;

    public AssignmentNode(VariableReferenceNode varIn, Node exprIn){
        this.target = varIn;
        this.expression = exprIn;
    }

    public AssignmentNode(AssignmentNode in) {
        super(in);
    }


    @Override
    public String toString() {
        return target.toString() + " = " + expression.toString();
    }

}
