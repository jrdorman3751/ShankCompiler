import java.util.ArrayList;
import java.util.function.Function;
/**
 *  Julian Dorman
 *  311-Phipps
 *  Assignment 3-2nd Draft 9/16/22
 *  Assignment 4-2nd Draft 9/22/22
 */
/*
    Assignment 3:
        1) name of function
        2) list of parameters
        2) list of variables
        3) list of constants
    Assignment 4:
        1) list of statements
 */
public class FunctionASTNode extends Node{
    String name;
    ArrayList<VariableNode> params = new ArrayList<>();
    ArrayList<VariableNode> variables = new ArrayList<>();
    ArrayList<VariableNode> constants = new ArrayList<>();

    ArrayList<StatementNode> statements = new ArrayList<>();



    FunctionASTNode(){}

    public void setName(String in){
        this.name = in;
    }

    public void setParams(ArrayList<VariableNode> in){
        this.params = in;
    }
    public void setVars(ArrayList<VariableNode> in){
        this.variables = in;
    }
    public void setConstants(ArrayList<VariableNode> in){
        this.constants = in;
    }
    public void setStatements(ArrayList<StatementNode> in){ this.statements = in;}






    @Override
    public String toString() {
        return "function: "+this.name+ "\nParams: "+params+"\nConstants: "+constants+"\nVariables: "+variables+"\nStatements: "+statements;
    }
}
