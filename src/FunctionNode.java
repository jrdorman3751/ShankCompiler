import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 6-1st Draft 10/7/22
 */
/*
    Assignment 6:
        1) use inherited parameters variable
 */
public class FunctionNode extends CallableNode{

    FunctionNode(String name, ArrayList<VariableNode> parameters){
        this.name = name;
        this.parameters = parameters;
    }


    @Override
    public String toString() {
        return null;
    }
}
