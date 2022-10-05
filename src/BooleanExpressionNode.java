/**
 * Julian Dorman
 * 311-Phipps
 *
 * Assignment 5
 *  1) left and right expression
 *  2) condition
 */
public class BooleanExpressionNode extends Node{
    Node left;
    Node right;
    Token.symbols condition;

    BooleanExpressionNode(Node l, Token.symbols condition, Node r){
        this.left = l;
        this.right = r;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return left.toString() + " " + condition + " " + right.toString();

    }
}
