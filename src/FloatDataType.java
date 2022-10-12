/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 6-1st Draft 10/7/22
 */

public class FloatDataType extends InterpreterDataType{

    public float value;

    @Override
    public String ToString() {
        return Float.toString(value);
    }

    @Override
    public void FromString(String input) {
        value = Float.parseFloat(input);
    }
}
