import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 1-3rd Draft 9/7/22
 * Assignment 2-2nd Draft 9/9/22
 * Assignment 3-2nd Draft 9/16/22
 */
/*
    Assignment 1:
        1) have main that takes only one argument, filename.
            a) print error if not one arg
        2) use File.ReadAllLines to read from file
        3) instantiate one instance of Lexer class
        4) parse lines using lex method
            a) catch and print if exception
        5) temporary, print out tokens
     Assignment 2:
        6) create instance of parser for each line
        7) call parserMethod for each parser created
        8) temporary, print resolved parsed node
    Assignment 3:
        8) don't run resolve
 */
public class Shank {

    public static void main(String[] args) throws Exception{
        if(args.length != 1){ //1)
            System.out.println("Argument Error: usage is File Name");
            return;
        }
        Path filepath = Paths.get(args[0]);//create file path from arg
        List<String> lines = new ArrayList<>();//create list for file lines
        try {
            //use .size() & .get(x) to iterate
            lines = Files.readAllLines(filepath, StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        }
        catch (IOException ex) {//if no file
            System.out.format("I/O error: %s%n", ex);
        }
        Lexer lexicalAnalyzer = new Lexer();
        ArrayList<Token.symbols> allTokens = new ArrayList<>();
        for (String line : lines) {
            try {
                allTokens.addAll(lexicalAnalyzer.lexerMethod(line));
            } catch (StateException e) {
                System.out.println(e.toString());
                return;
            }
        }
        /*//temporary
        for (Token.symbols allToken : allTokens) {
            if (allToken == Token.symbols.EOL) {
                System.out.println(allToken);
            }
            else if (allToken == Token.symbols.NUMBER) {
                System.out.print(allToken + "(" + Token.getNum() + ") ");
            }
            else if (allToken == Token.symbols.IDENTIFIER) {
                System.out.print(allToken + "(" + Token.getWord() + ") ");
            }
            else{
                System.out.print(allToken);
            }
        }*/
        ///*
        Parser parser = new Parser(allTokens);
        Node parsedExpression = parser.parserMethod();
        //System.out.println(parsedExpression.toString());
        //temporary System.out.println(Interpreter.resolve(parsedExpression));
        //*/
    }
}

//custom exception for error in state machine
class StateException extends RuntimeException{
    public StateException(String message){
        super(message);
    }
}
