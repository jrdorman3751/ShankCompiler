/**
 *  Julian Dorman
 *  311-Phipps
 *  Assignment 3-2nd Draft 9/16/22
 *
 */
/*
    Assignment 3:
        1) name of variable
        2) if a constant
        3) dataType(symbol)
        4) node i.e real or float
 */
public class VariableNode extends Node {
    String name;
    boolean isConstant;
    Token.symbols dataType;
    Node ASTNode;

    VariableNode(String name, boolean constant, Token.symbols type, Node n){
        this.name = name;
        this.isConstant = constant;
        this.dataType = type;
        ASTNode = n;
    }


    @Override
    public String toString() {
        return this.name+"("+ASTNode+")"+" "+this.dataType;
    }
}
