/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 1-3rd Draft 9/9/22
 *
 */
/*
    Assignment 1:
        1) instance of enum and value String
            a) variables are private
            b) pulic accessor
        2) definition of enum is public
            a)instances inside are private
            b)create ToString overload
 */
public class Token {
    private static String value; //1)

    public static void addNum(String add){
        String num = add.concat(" ");
        if(value == null){
            value = num;
        }
        else {
            value = value.concat(num);
        }
    }
    public static String getNum(){
        String out;
        int index = value.indexOf(' ');
        out = value.substring(0, index);
        value = value.substring(index+1);
        return out;
    }

    public enum symbols{ //2)
        NUMBER,
        MINUS, PLUS, TIMES, DIVIDE,
        EOL;

        @Override
        public String toString() {
            return switch (this) {
                case MINUS -> "MINUS ";
                case PLUS -> "PLUS ";
                case DIVIDE -> "DIVIDE ";
                case TIMES -> "TIMES ";
                case NUMBER -> "NUMBER";
                default -> "EndOfLine ";
            };
        }
    }


}
