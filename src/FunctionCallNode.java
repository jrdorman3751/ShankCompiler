import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 *
 */
/*
    Assignment 6:
        1) has name and list of parameters
        2) is a type of statement node
 */
public class FunctionCallNode extends StatementNode{
    String name;
    ArrayList<ParameterNode> parameters;

    FunctionCallNode(ArrayList<ParameterNode> parameters, String name){
        this.parameters= parameters;
        this.name = name;
    }

    public String toString(){
        return "Name: "+name+"\nParameters " + parameters.toString();
    }
}
