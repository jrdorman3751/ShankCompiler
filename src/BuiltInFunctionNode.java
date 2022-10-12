import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 6-1st Draft 10/7/22
 */
/*
    Assignment 6:
        1) have boolean to indicate if variadic
 */
public abstract class BuiltInFunctionNode extends CallableNode{
    boolean variadic;

    public abstract void Execute(ArrayList<InterpreterDataType> dataTypes);

    BuiltInFunctionNode(String name, boolean variadic){
        this.name = name;
        this.variadic = variadic;
    }

    @Override
    public String toString() {
        return null;
    }
}
