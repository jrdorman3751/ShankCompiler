/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 */
/*
    Assignment 2:
        1) resolve method
            a) take in Node and return float
            b) recursively call itself
 */
public class Interpreter {
    public static float resolve(Node n){

        if(n instanceof FloatNode){
            return ((FloatNode) n).getValue();
        }
        else if(n instanceof IntegerNode){
            return ((IntegerNode) n).getValue();
        }
        else if(n instanceof MathOpNode){
            float numOne = resolve(((MathOpNode) n).getLeft());
            float numTwo = resolve(((MathOpNode) n).getRight());
            if(((MathOpNode) n).getTokenOperator() == Token.symbols.MINUS){
                return (numOne - numTwo);
            }
            else if(((MathOpNode) n).getTokenOperator() == Token.symbols.PLUS){
                return (numOne + numTwo);
            }
            else if(((MathOpNode) n).getTokenOperator() == Token.symbols.DIVIDE){
                return (numOne / numTwo);
            }
            else if(((MathOpNode) n).getTokenOperator() == Token.symbols.TIMES){
                return (numOne * numTwo);
            }
        }
        return 0.00F;//fail case
    }
}
