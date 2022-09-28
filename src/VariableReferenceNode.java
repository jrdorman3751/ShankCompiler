/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 4-2nd Draft 9/22/22
 */
/*
    Assignment 4:
        1) name of variable referenced
 */
public class VariableReferenceNode extends Node{
    String name;

    VariableReferenceNode(String in) {
        this.name = in;
    }

    public String getVariableName(){
        return this.name;
    }


    @Override
    public String toString() {
        return getVariableName();
    }
}
