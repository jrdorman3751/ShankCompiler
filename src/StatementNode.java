/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 4-2nd Draft 9/22/22
 *
 */
/*
    Assignment 4:
        1) inherits from node
        2) returns result of assignment
 */
public class StatementNode extends Node{
    FunctionCallNode functionCall;
    AssignmentNode assignment;
    WhileNode whileLoop;
    ForNode forLoop;

    IfNode ifStatement;

    public StatementNode(FunctionCallNode in){this.functionCall = in;}
    public StatementNode(AssignmentNode in){
        this.assignment = in;
    }
    public StatementNode(WhileNode in){
        this.whileLoop = in;
    }
    public StatementNode(ForNode in){ this.forLoop = in; }
    public StatementNode(IfNode in){ this.ifStatement = in; }



    public StatementNode() {}


    @Override
    public String toString() {
        if(assignment != null) {
            return assignment.toString();
        }
        else if (whileLoop != null) {
            return whileLoop.toString();
        }
        else if(forLoop != null) {
            return forLoop.toString();
        }
        else{
            return ifStatement.toString();
        }
    }
}
