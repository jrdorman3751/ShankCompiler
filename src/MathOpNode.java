/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 */
/*
    Assignment 2:
        1) derives from Node
        2) enum of math operation, read only
        3) two references, left & right, read only

 */
public class MathOpNode extends Node {
    private Token.symbols operator;
    private Node left;
    private Node right;

    private boolean closed = false;

    MathOpNode(Token.symbols operator, Node left, Node right){
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    public Token.symbols getTokenOperator(){
        return operator;
    }
    public void makeClosed(){
        closed = true;
    }
    public boolean isClosed(){
        return closed;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }

}
