/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 1-3rd Draft 9/7/22
 * Assignment 2-2nd Draft 9/9/22
 * Assignment 3-2nd Draft 9/16/22
 * Assignment 4-2nd Draft 9/24/22
 * Assignment 5-1st Draft 10/1/22
 */
/*
    Assignment 1:
        1) instance of enum and value String
            a) variables are private
            b) public accessor
        2) definition of enum is public
            a)instances inside are private
            b)create ToString overload
    Assignment 2:
        3) add right and left parenthesis token
    Assignment 3:
        4) add Identifier, define, integer, real, begin, end, semicolon, colon, equal, comma, variables, constants
        5) instance of words String
            a) variable is private, public accessor
    Assignment 4:
        6) add assignments
    Assignment 5:
        7) add if, then, else, elsif, for, from, to, while, repeat, until, mod, >, <, >=, <=, <>(not equal)


 */
public class Token {
    private static String value;
    private static String words;

    public static void addNum(String add){
        String num = add.concat(" ");
        if(value == null){
            value = num;
        }
        else {
            value = value.concat(num);
        }
    }
    public static void addWord(String add){
        String word = add.concat(" ");
        if(words == null){
            words = word;
        }
        else {
            words = words.concat(word);
        }
    }
    public static String getNum(){
        String out;
        int index = value.indexOf(' ');
        out = value.substring(0, index);
        value = value.substring(index+1);
        return out;
    }
    public static String getWord(){
        String out;
        int index = words.indexOf(' ');
        out = words.substring(0, index);
        words = words.substring(index+1);
        return out;
    }
    public enum symbols{ //2)
        IDENTIFIER, DEFINE, VARIABLES, CONSTANTS, ASSIGNMENT,//5
        SEMICOLON, COLON, COMMA, EQUAL, GREATERTHAN, LESSTHAN, GREATEROREQUAL, LESSOREQUAL, NOTEQUAL,//9
        IF, THEN, ELSE, ELSIF, FOR, FROM, TO, WHILE, REPEAT, UNTIL, MOD,//11
        NUMBER, INTEGER, REAL,//3
        MINUS, PLUS, TIMES, DIVIDE,//4
        LPAREN, RPAREN,//2
        EOL, BEGIN, END;//3

        @Override
        public String toString() {
            return switch (this) {
                case IDENTIFIER -> "Identifier";
                case DEFINE -> "Define ";
                case VARIABLES -> "Variables ";
                case CONSTANTS -> "Constants ";
                case ASSIGNMENT ->  "Assignment ";//5
                case SEMICOLON -> "Semicolon ";
                case COLON -> "Colon ";
                case COMMA -> "Comma ";
                case EQUAL -> "Equals ";
                case GREATERTHAN -> "Greater than ";
                case LESSTHAN -> "Less than ";
                case GREATEROREQUAL -> "Greater than or equal to ";
                case LESSOREQUAL -> "Less than or equal to ";
                case NOTEQUAL -> "Not equal to ";//9
                case IF -> "If ";
                case THEN -> "Then ";
                case ELSE -> "Else ";
                case ELSIF -> "Else-if ";
                case FOR -> "For ";
                case FROM -> "From ";
                case TO -> "To ";
                case WHILE -> "While ";
                case REPEAT -> "Repeat ";
                case UNTIL -> "Until ";
                case MOD -> "Mod ";//11
                case NUMBER -> "NUMBER";
                case INTEGER -> "Integer ";
                case REAL -> "Real ";//3
                case MINUS -> "MINUS ";
                case PLUS -> "PLUS ";
                case DIVIDE -> "DIVIDE ";
                case TIMES -> "TIMES ";//4
                case LPAREN -> "LPAREN ";
                case RPAREN -> "RPAREN ";//2
                case BEGIN -> "BEGIN ";
                case END -> "END ";
                default -> "EndOfLine";//3
            };
        }
    }
}
