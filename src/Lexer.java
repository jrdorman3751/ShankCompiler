import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 1-3rd Draft 9/7/22
 * Assignment 2-2nd Draft 9/9/22
 * Assignment 3-2nd Draft 9/16/22
 * Assignment 4-2nd Draft 9/22/22
 * Assignment 5-1st Draft 10/1/22
 */
/*
    Assignment 1:
        1) lexer method, accepts string and returns collection of tokens
        2) method uses state machine(s) to iterate over string
        3) throw exception if not accepted char
    Assignment 2:
        4) add parenthesis case to all states
    Assignment 3:
        5) add new state for words
            a) hashmap for reserved words
        6) add case for : ; , = to state 1
    Assignment 4:
        7) add comment case to all states
        8) add comment state
        9) add assignment case to state 1
   Assignment 5:
         10) add if,then,else,elsif,for,from,to,while,repeat,until,mod to hashmap
         11) add boolean comparators to state 1 and 3
         12) space(2), sign(2), letter(7) to state 2
         13) letter(7) to state 5
 */
public class Lexer {
    private String input;
    private int inputLength;
    ArrayList<Token.symbols> result = new ArrayList<>();
    List<Character> previousNumChars = new ArrayList<>();
    List<Character> previousWordChars = new ArrayList<>();

    Lexer(){input = "";}

    public Token.symbols getChar(char c){
        return switch(c){
            case '(' -> Token.symbols.LPAREN;
            case ')' -> Token.symbols.RPAREN;
            case ',' -> Token.symbols.COMMA;
            case ':' -> Token.symbols.COLON;
            case '=' -> Token.symbols.EQUAL;
            case ';' -> Token.symbols.SEMICOLON;
            //A5
            case '>' -> Token.symbols.GREATERTHAN;
            case '<' -> Token.symbols.LESSTHAN;

            default -> null;
        };
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
    public void addWord(){
        HashMap<String, Token.symbols> reserved = new HashMap<>();//hash map for reserved words
        reserved.put("integer", Token.symbols.INTEGER);reserved.put("real", Token.symbols.REAL);reserved.put("begin", Token.symbols.BEGIN);
        reserved.put("end", Token.symbols.END);reserved.put("variables", Token.symbols.VARIABLES);reserved.put("constants", Token.symbols.CONSTANTS);
        reserved.put("define", Token.symbols.DEFINE);reserved.put("if", Token.symbols.IF);reserved.put("then", Token.symbols.THEN);
        reserved.put("else", Token.symbols.ELSE);reserved.put("elsif", Token.symbols.ELSIF);reserved.put("for", Token.symbols.FOR);
        reserved.put("from", Token.symbols.FROM);reserved.put("to", Token.symbols.TO);reserved.put("while", Token.symbols.WHILE);
        reserved.put("repeat", Token.symbols.REPEAT);reserved.put("until", Token.symbols.UNTIL);reserved.put("mod", Token.symbols.MOD);

        if(previousWordChars.size() != 0) {
            String wordString = previousWordChars.toString().substring(1, 3 * previousWordChars.size() - 1).replaceAll(", ", "");
            if (reserved.containsKey(wordString)) {
                result.add(reserved.get(wordString));
                previousWordChars.clear();
            } else {
                Token.addWord(wordString);
                result.add(Token.symbols.IDENTIFIER);
                previousWordChars.clear();
            }
        }
    }
    public ArrayList<Token.symbols> lexerMethod(String expression){
        result = new ArrayList<>();
        previousNumChars = new ArrayList<>();
        previousWordChars = new ArrayList<>();
        input = expression;
        inputLength = expression.length();

        int s = 1;

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
                        if(current == '(' && input.charAt(x+1) == '*'){
                            x++;
                            s = 8;
                        }
                        else {
                            result.add(getChar(current));
                        }
                    }
                    else if(isLetter(current)){
                        previousWordChars.add(current);
                        s = 7;
                    }
                    else if(current == ',' || current == ':' || current == '=' || current == ';' || current == '>' || current == '<'){
                        if(current == ':' && input.charAt(x+1) == '='){
                            x++;
                            result.add(Token.symbols.ASSIGNMENT);
                        }
                        else if(current == '>' && input.charAt(x+1) == '='){
                            x++;
                            result.add(Token.symbols.GREATEROREQUAL);
                        }
                        else if(current == '<' && input.charAt(x+1) == '='){
                            x++;
                            result.add(Token.symbols.LESSOREQUAL);
                        }
                        else if(current == '<' && input.charAt(x+1) == '>'){
                            x++;
                            result.add(Token.symbols.NOTEQUAL);
                        }
                        else {
                            result.add(getChar(current));
                        }
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
                        if(current == '(' && input.charAt(x+1) == '*'){
                            x++;
                            s = 8;
                        }
                        else {
                            result.add(getChar(current));
                        }
                    }
                    else if(current == ' '){
                        s = 2;
                    }
                    else if(isLetter(current)){
                        result.add(getOperatorToken(previousNumChars.get(0)));
                        previousNumChars.clear();
                        previousWordChars.add(current);
                        s = 7;
                    }
                    else if(current == '-' || current == '+'){
                        result.add(getOperatorToken(previousNumChars.get(0)));
                        previousNumChars.clear();
                        previousNumChars.add(current);
                        s = 2;
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
                        if(current == '(' && input.charAt(x+1) == '*'){
                            x++;
                            s = 8;
                        }
                        else {
                            result.add(getChar(current));
                        }
                    }
                    else if(current == ',' || current == ':' || current == '=' || current == ';' || current == '>' || current == '<'){
                        addNumber();
                        if(current == ':' && input.charAt(x+1) == '='){
                            x++;
                            result.add(Token.symbols.ASSIGNMENT);
                        }
                        else if(current == '>' && input.charAt(x+1) == '='){
                            x++;
                            result.add(Token.symbols.GREATEROREQUAL);
                        }
                        else if(current == '<' && input.charAt(x+1) == '='){
                            x++;
                            result.add(Token.symbols.LESSOREQUAL);
                        }
                        else if(current == '<' && input.charAt(x+1) == '>'){
                            x++;
                            result.add(Token.symbols.NOTEQUAL);
                        }
                        else {
                            result.add(getChar(current));
                        }
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
                        if(current == '(' && input.charAt(x+1) == '*'){
                            x++;
                            s = 8;
                        }
                        else {
                            result.add(getChar(current));
                        }
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
                        if(current == '(' && input.charAt(x+1) == '*'){
                            x++;
                            s = 8;
                        }
                        else {
                            result.add(getChar(current));
                        }
                    }
                    else if(isLetter(current)){
                        addNumber();
                        previousWordChars.add(current);
                        s = 7;
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
                        if(current == '(' && input.charAt(x+1) == '*'){
                            x++;
                            s = 8;
                        }
                        else {
                            result.add(getChar(current));
                        }
                    }
                    else{
                        throw new StateException("Incorrect Input:State 5");
                    }
                    break;
                case 7:
                    if(isLetter(current) || isDigit(current)){
                        previousWordChars.add(current);
                        s = 7;
                        break;
                    }
                    else {
                        addWord();
                        if (current == ' ') {
                            s = 1;
                        }
                        else if (getChar(current) != null) {//'(',')',':',';','=',','
                            if(current == '(' && current == '*') {
                                x++;
                                s = 8;
                            }
                            else {
                                result.add(getChar(current));
                                s = 1;
                            }
                        }
                        else {
                            throw new StateException("Incorrect Input:State 7");
                        }
                    }
                    break;
                case 8:
                    if(current == '*' && input.charAt(x+1) == ')'){
                        x++;
                        s = 1;
                    }
                    else{
                        s = 8;
                    }
            }
        }
        addNumber();
        addWord();
        if(result.size() != 0) {
            if (ifOperatorToken(result.get(result.size() - 1)) != null) {
                throw new StateException("Incorrect Input:Ends with Operator");
            }
        }
        result.add(Token.symbols.EOL);//add end of line symbol




        return result;
    }
}
