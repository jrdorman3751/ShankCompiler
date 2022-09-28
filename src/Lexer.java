import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isDigit;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 1-3rd Draft 9/7/22
 * Assignment 2-2nd Draft 9/9/22
 *
 */
/*
    Assignment 1:
        1) lexer method, accepts string and returns collection of tokens
        2) method uses state machine(s) to iterate over string
        3) throw exception if not accepted char
    Assignment 2:
        1) add parenthesis cases to all states
 */
public class Lexer {
    private String input;
    private int inputLength;
    private int s;
    ArrayList<Token.symbols> result = new ArrayList<>();
    List<Character> previousNumChars = new ArrayList<>();
    Lexer(){input = "";}

    public Token.symbols getChar(char c){
        if(c == '('){
            return Token.symbols.LPAREN;
        }
        else if(c == ')'){
            return Token.symbols.RPAREN;
        }
        return null;
    }
    public Token.symbols getOperatorToken(char operator){
        return switch (operator){
            case '+' -> Token.symbols.PLUS;
            case '-' -> Token.symbols.MINUS;
            case '*' -> Token.symbols.TIMES;
            case '/' -> Token.symbols.DIVIDE;
            default -> null;
        };
    }
    public Token.symbols ifOperatorToken(Token.symbols operator){
        if(operator == Token.symbols.MINUS || operator == Token.symbols.TIMES || operator == Token.symbols.PLUS || operator == Token.symbols.DIVIDE){
            return operator;
        }
        else{
            return null;
        }
    }
    public String ifDecimal(String input){
        String fixedNum = input;
        if(fixedNum.length() > 1){
            if(fixedNum.charAt(fixedNum.length() - 1) == '.') {
                fixedNum = fixedNum + "00";
            }
            else if(fixedNum.charAt(0) == '.'){
                fixedNum = "00" + fixedNum;
            }
            else{
                return fixedNum;
            }
        }
        else{
            if(fixedNum.equals(".")){
                fixedNum = "0.00";
            }
            else{
                return fixedNum;
            }
        }
        return fixedNum;
    }
    public void addNumber() throws StateException{
        if(previousNumChars.size() == 1){
            if(previousNumChars.get(0) == '-' || previousNumChars.get(0) == '+'){
                throw new StateException("sign with no number");
            }
        }
        if(previousNumChars.size() != 0) {
            String numString = ifDecimal(previousNumChars.toString().substring(1, 3 * previousNumChars.size() - 1).replaceAll(", ", ""));
            Token.addNum(numString);
            result.add(Token.symbols.NUMBER);
            previousNumChars.clear();
        }
    }
    public ArrayList<Token.symbols> lexerMethod(String expression){
        result = new ArrayList<>();
        previousNumChars = new ArrayList<>();
        input = expression;
        inputLength = expression.length();

        s = 1;

        for(int x = 0; x < inputLength; x++){
            char current = input.charAt(x);
            switch(s){
                case 1:
                    if(isDigit(current)){
                        previousNumChars.add(current);
                        s = 3;
                    }
                    else if(current == '-' || current == '+'){
                        previousNumChars.add(current);
                        s = 2;
                    }
                    else if(current == '.'){
                        previousNumChars.add(current);
                        s = 6;
                    }
                    else if(current == ' '){
                        s = 1;
                    }
                    else if(current == '(' || current == ')'){
                        addNumber();
                        result.add(getChar(current));
                    }
                    else{
                        throw new StateException("Incorrect Input:State 1");
                    }
                    break;
                case 2:
                    if(isDigit(current)){
                        previousNumChars.add(current);
                        s = 3;
                    }
                    else if(current == '.'){
                        previousNumChars.add(current);
                        s = 6;
                    }
                    else if(current == '(' || current == ')'){
                        addNumber();
                        result.add(getChar(current));
                    }
                    else{
                        throw new StateException("Incorrect Input:State 2");
                    }
                    break;
                case 3:
                    if(current == ' ') {
                        s = 5;
                    }
                    else if(current == '.'){
                        previousNumChars.add(current);
                        s = 4;
                    }
                    else if(current == '-' || current == '+' || current == '*' || current == '/'){
                        addNumber();//check if number before and add to result
                        result.add(getOperatorToken(current));
                        s = 1;
                    }
                    else if(isDigit(current)){
                        previousNumChars.add(current);
                        s = 3;
                    }
                    else if(current == '(' || current == ')'){
                        addNumber();
                        result.add(getChar(current));
                    }
                    else{
                        throw new StateException("Incorrect Input:State 3");
                    }
                    break;
                case 4:
                    if(current == ' ') {
                        s = 5;
                    }
                    else if(current == '-' || current == '+' || current == '*' || current == '/'){
                        addNumber();
                        result.add(getOperatorToken(current));
                        s = 1;
                    }
                    else if(isDigit(current)){
                        previousNumChars.add(current);
                        s = 4;
                    }
                    else if(current == '(' || current == ')'){
                        addNumber();
                        result.add(getChar(current));
                    }
                    else{
                        throw new StateException("Incorrect Input:State 4");
                    }
                    break;
                case 5:
                    if(current == '-' || current == '+' || current == '*' || current == '/'){
                        addNumber();
                        result.add(getOperatorToken(current));
                        s = 1;
                    }
                    else if(current == ' ') {
                        s = 5;
                    }
                    else if(current == '(' || current == ')'){
                        addNumber();
                        result.add(getChar(current));
                    }
                    else{
                        throw new StateException("Incorrect Input:State 5");
                    }
                    break;
                case 6:
                    if(isDigit(current)){
                        previousNumChars.add(current);
                        s = 4;
                    }
                    else if(current == '-' || current == '+' || current == '*' || current == '/'){
                        addNumber();
                        result.add(getOperatorToken(current));
                        s = 1;
                    }
                    else if(current == '(' || current == ')'){
                        addNumber();
                        result.add(getChar(current));
                    }
                    else{
                        throw new StateException("Incorrect Input:State 5");
                    }
                    break;
            }
        }
        addNumber();
        if(ifOperatorToken(result.get(result.size()-1)) != null){
            throw new StateException("Incorrect Input:Ends with Operator");
        }
        result.add(Token.symbols.EOL);//add end of line symbol




        return result;
    }
}
