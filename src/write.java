import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 6-1st Draft 10/7/22
 */
public class write extends BuiltInFunctionNode{
    write(String name, boolean variadic) {
        super(name, variadic);
    }

    @Override
    public void Execute(ArrayList<InterpreterDataType> dataTypes) {
        for(int x = 0; x < dataTypes.size(); x++){
            System.out.println(dataTypes.get(x).ToString());
        }
    }
}
