/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 6-1st Draft 10/7/22
 */

public class IntDataType extends InterpreterDataType {
    public int value;
    @Override
    public String ToString() {
        return Integer.toString(value);
    }

    @Override
    public void FromString(String input) { value = Integer.parseInt(input); }
}
