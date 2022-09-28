import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 */
/*
    Assignment 2:
        1) constructor, accepts collection of Tokens
        2) parse method, returns Node
            a) calls expression then matchAndRemove
        3) expression = term {(plus or minus) term}
        4) term = factor {(times or divide) factor}
        5) factor = {-} number or lparen expresion rparen
        6) matchAndRemove to check for token
 */
public class Parser {
    private ArrayList<Token.symbols> tokens;

    public Parser(ArrayList<Token.symbols> input){
        tokens = input;
    }

    public Node parserMethod() throws Exception{
        Node left = expression();



        return left;
    }

     /*
        term { ( plus or minus) term }
        1) call term for left node
        2) if next operation is - or +, create math node and call term for right node
     */
     public Node expression() throws Exception {
         //System.out.println("Entered <expression>");
         Node left = term();

         if(left == null){
             throw new Exception("Nothing here");
         }

         if(matchAndRemove(Token.symbols.MINUS) != null){
             //System.out.println("Exiting <expression>");
             return new MathOpNode(Token.symbols.MINUS, left, term());
         }
         else if(matchAndRemove(Token.symbols.PLUS) != null){
             //System.out.println("Exiting <expression>");
             return new MathOpNode(Token.symbols.PLUS, left, term());
         }
         else{
             //System.out.println("Exiting <expression>");
             return left;
         }
     }
    /*
       factor { (times or divide) factor }
       1) call factor for left node
       2) if next operation is * or /, create math node and call factor for right node
    */
    public Node term() throws Exception {
        //System.out.println("Entered <term>");
        Node left = factor();

        if(left == null){
            //System.out.println("Exiting <term>");
            return null;
        }
        if(matchAndRemove(Token.symbols.TIMES) != null || ((!tokens.isEmpty()) && tokens.get(0) == Token.symbols.LPAREN )){//if times or left parentheses right after number
            //System.out.println("Exiting <term>");
            return new MathOpNode(Token.symbols.TIMES, left, factor());
        }
        else if(matchAndRemove(Token.symbols.DIVIDE) != null || ((!tokens.isEmpty()) && tokens.get(0) == Token.symbols.LPAREN )){//if divide or left parentheses right after number
            //System.out.println("Exiting <term>");
            return new MathOpNode(Token.symbols.DIVIDE, left, factor());
        }
        else{
            //System.out.println("Exiting <term>");
            return left;
        }
    }


    /*
        factor = {-} number or ( expression )
        1) if number create float or int node accordingly
        2) if ( call expression to get inside parentheses
            a) check for ) and close expression
        output: node
     */
    public Node factor() throws Exception {
        if(matchAndRemove(Token.symbols.NUMBER) != null){
            String numString = Token.getNum();
            try {
                int num = Integer.parseInt(numString);
                //System.out.println("Exiting <factor>");
                return new IntegerNode(num);
            }
            catch(NumberFormatException i){
                try{
                    float num = Float.parseFloat(numString);
                    //System.out.println("Exiting <factor>");
                    return new FloatNode(num);
                }
                catch(NumberFormatException f){
                    System.out.println("Not a number");
                }
            }
        }
        else{
            if(matchAndRemove(Token.symbols.LPAREN) != null) {
                Node expression = expression();
                if(matchAndRemove(Token.symbols.RPAREN) != null){
                    if(expression instanceof MathOpNode){
                        ((MathOpNode) expression).makeClosed();
                    }
                    //System.out.println("Exiting <factor>");
                    return expression;
                }
                else {
                    throw new Exception("No right parentheses");
                }
            }
            else {
                throw new Exception("Unidentified error");
            }
        }
        return null;
    }


    /*
        input: symbol
        checks if what should be next up in order of operations is what's next
        output: symbol
     */
    public Token.symbols matchAndRemove(Token.symbols symbol){
        if(tokens.isEmpty()){
            return null;
        }
        else{
            if(tokens.get(0) == symbol) {
                tokens.remove(0);
                return symbol;
            }
            else{
                return null;
            }
        }
    }
}
