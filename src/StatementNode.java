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
public class StatementNode extends Node {
    AssignmentNode assignment;

    public StatementNode(AssignmentNode in){
        this.assignment = in;
    }

    public StatementNode() {}


    @Override
    public String toString() {
        return assignment.toString();
    }
}
