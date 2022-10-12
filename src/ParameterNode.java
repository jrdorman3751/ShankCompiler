/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 6-1st Draft 10/7/22
 */
public class ParameterNode extends Node{
    VariableReferenceNode variable;
    boolean constant;

    Node value;

    ParameterNode(VariableReferenceNode variable, boolean constant){
        this.variable = variable;
        this.constant=constant;
    }
    ParameterNode(Node value){
        this.value = value;
    }

    @Override
    public String toString() {
        if(value == null){
            if(constant == false) {
                return "var " + variable.toString();
            }
            else{
                return variable.toString();

            }
        }
        else{
            return value.toString();
        }
    }
}
