import java.util.ArrayList;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 * Assignment 3-2nd Draft 9/16/22
 * Assignment 4-2nd Draft 9/22/22
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
    Assignment 3:
        1) Function definition method
            a) include toString
            b) populate name
            c) look for params
            d) look for vars
            e) look for constants
            f) look for variables
            g) look for body
     Assignment 4:
            h) look for statements in body
 */
public class Parser {
    private ArrayList<Token.symbols> tokens;

    public Parser(ArrayList<Token.symbols> input){
        tokens = input;
    }

    public Node parserMethod() throws Exception{
        Node function = functionDefinition();
        /*
        Node left = expression();
        while(!tokens.isEmpty()){//if more to read after first operation
            if(matchAndRemove(Token.symbols.MINUS) != null){
                left = new MathOpNode(Token.symbols.MINUS, left, expression());
            }
            else if(matchAndRemove(Token.symbols.PLUS) != null){
                left = new MathOpNode(Token.symbols.PLUS, left, expression());
            }
            else if(matchAndRemove(Token.symbols.TIMES) != null){
                if((((MathOpNode) left).getTokenOperator() == Token.symbols.MINUS || ((MathOpNode) left).getTokenOperator() == Token.symbols.PLUS)){//if new operation supersedes previous operation
                    left = new MathOpNode(((MathOpNode) left).getTokenOperator(), ((MathOpNode) left).getLeft(), new MathOpNode(Token.symbols.TIMES, ((MathOpNode) left).getRight(), expression()));
                }
                else {
                    left = new MathOpNode(Token.symbols.TIMES, left, expression());
                }
            }
            else if(matchAndRemove(Token.symbols.DIVIDE) != null){
                if((((MathOpNode) left).getTokenOperator() == Token.symbols.MINUS || ((MathOpNode) left).getTokenOperator() == Token.symbols.PLUS)){//if new operation supersedes previous operation
                    left = new MathOpNode(((MathOpNode) left).getTokenOperator(), ((MathOpNode) left).getLeft(), new MathOpNode(Token.symbols.DIVIDE, ((MathOpNode) left).getRight(), expression()));
                }
                else {
                    left = new MathOpNode(Token.symbols.DIVIDE, left, expression());
                }
            }
            else if(tokens.get(0) == Token.symbols.LPAREN){
                if(!((MathOpNode) left).isClosed() && (((MathOpNode) left).getTokenOperator() == Token.symbols.MINUS || ((MathOpNode) left).getTokenOperator() == Token.symbols.PLUS)){//if parentheses not closed and new operation supersedes previous operation
                    left = new MathOpNode(((MathOpNode) left).getTokenOperator(), ((MathOpNode) left).getLeft(), new MathOpNode(Token.symbols.TIMES, ((MathOpNode) left).getRight(), factor()));
                }
                else{
                    left = new MathOpNode(Token.symbols.TIMES, left, factor());
                }
            }
            else{
                tokens.remove(0);
            }
        }
        return left;

         */
        return function;
    }

    public Node functionDefinition() throws Exception {
        if(matchAndRemove(Token.symbols.DEFINE) != null && matchAndRemove(Token.symbols.IDENTIFIER) != null && matchAndRemove(Token.symbols.LPAREN) != null) {
            FunctionASTNode function = new FunctionASTNode();
            String name = Token.getWord();
            function.setName(name);
            ArrayList<VariableNode> params = new ArrayList<>();
            params = processVariables();
            function.setParams(params);
            if(matchAndRemove(Token.symbols.EOL) != null){
                while(matchAndRemove(Token.symbols.EOL) != null){}
                ArrayList<VariableNode> consts = new ArrayList<>();
                consts = Constants();
                function.setConstants(consts);
                while(matchAndRemove(Token.symbols.EOL) != null){}
                ArrayList<VariableNode> vars = new ArrayList<>();
                vars = variables();
                function.setVars(vars);
                while(matchAndRemove(Token.symbols.EOL) != null){}
                ArrayList<StatementNode> statements = new ArrayList<>();
                statements = body();
                function.setStatements(statements);
                while(matchAndRemove(Token.symbols.EOL) != null){}
                return function;
            }
            else{
                throw new Exception("Function Definition error");
            }
        }
        else{
            throw new Exception("Function Definition error");
        }
    }

    public ArrayList<VariableNode> Constants() throws Exception {
        if(matchAndRemove(Token.symbols.CONSTANTS) != null && matchAndRemove(Token.symbols.EOL) != null){
            while(matchAndRemove(Token.symbols.EOL) != null){}
            return processConstants();
        }
        else{
            throw new Exception("No constant statement");
        }
    }
    public ArrayList<VariableNode> processConstants() throws Exception{
        ArrayList<VariableNode> out = new ArrayList<>();
        while(matchAndRemove(Token.symbols.IDENTIFIER) != null){
            if(matchAndRemove(Token.symbols.EQUAL) != null && matchAndRemove(Token.symbols.NUMBER) != null && matchAndRemove(Token.symbols.EOL) != null){
                String numString = Token.getNum();
                try {
                    int num = Integer.parseInt(numString);
                    out.add(new VariableNode(Token.getWord(), true, Token.symbols.INTEGER, new IntegerNode(num)));
                }
                catch(NumberFormatException i){
                    try{
                        float num = Float.parseFloat(numString);
                        out.add(new VariableNode(Token.getWord(), true, Token.symbols.REAL, new FloatNode(num)));
                    }
                    catch(NumberFormatException f){
                        System.out.println("Not a number");
                    }
                }
                while(matchAndRemove(Token.symbols.EOL) != null){}
            }
            else{
                throw new Exception("constant processing error");
            }
        }
        return out;
    }
    public ArrayList<VariableNode> variables() throws Exception {
        if(matchAndRemove(Token.symbols.VARIABLES) != null && matchAndRemove(Token.symbols.EOL) != null){
            while(matchAndRemove(Token.symbols.EOL) != null){}
            return processVariables();
        }
        else{
            throw new Exception("no variable statement");
        }
    }
    public ArrayList<VariableNode> processVariables() throws Exception {
        int numVars = 0;
        ArrayList<VariableNode> out = new ArrayList<>();
        while(matchAndRemove(Token.symbols.EOL) != null || matchAndRemove(Token.symbols.RPAREN) == null){
            while(matchAndRemove(Token.symbols.EOL) != null){}
            if(matchAndRemove(Token.symbols.IDENTIFIER) != null){
                numVars++;
                if(matchAndRemove(Token.symbols.COMMA) != null){
                    continue;
                }
            }
            else if(matchAndRemove(Token.symbols.COLON) != null){
                if(matchAndRemove(Token.symbols.INTEGER) != null){
                    for(int x = 0; x < numVars; x++){
                        out.add(new VariableNode(Token.getWord(), false, Token.symbols.INTEGER, new IntegerNode()));
                    }
                }
                else if(matchAndRemove(Token.symbols.REAL) != null){
                    for(int x = 0; x < numVars; x++){
                        out.add(new VariableNode(Token.getWord(), false, Token.symbols.REAL, new FloatNode()));
                    }
                }
                numVars = 0;
            }
            else if(matchAndRemove(Token.symbols.SEMICOLON) != null){
                numVars = 0;
            }
            else{//end of variables
                break;
            }
        }
        return out;
    }

    public ArrayList<StatementNode> body() throws Exception {
        if(matchAndRemove(Token.symbols.BEGIN) != null && matchAndRemove(Token.symbols.EOL) != null){
            while(matchAndRemove(Token.symbols.EOL) != null){}
            ArrayList<StatementNode> out = new ArrayList<>();
            out = statement();
            while(matchAndRemove(Token.symbols.EOL) != null){}
            if(matchAndRemove(Token.symbols.END) != null && matchAndRemove(Token.symbols.EOL) != null){
                return out;
            }
            else{
                throw new Exception("Body error");
            }
        }
        else {
            throw new Exception("Body error");
        }
    }
    public ArrayList<StatementNode> statement() throws Exception {
        return assignment();
    }
    public ArrayList<StatementNode> assignment() throws Exception {
        ArrayList<StatementNode> out = new ArrayList<>();
        while(matchAndRemove(Token.symbols.IDENTIFIER) != null && matchAndRemove(Token.symbols.ASSIGNMENT) != null){
            out.add( new StatementNode(new AssignmentNode(new VariableReferenceNode(Token.getWord()), expression())));
            while(matchAndRemove(Token.symbols.EOL) != null){}
        }

        return out;
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
