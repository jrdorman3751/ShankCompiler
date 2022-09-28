/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 * Assignment 3-2nd Draft 9/16/22
 *
 */
/*
    Assignment 2:
        1) derives from Node
        2) private integer
        3) read only accessor
    Assignment 3:
        1) make constructor with no initial value
        2) make value setter
 */
public class IntegerNode extends Node {
    private int value;

    IntegerNode(int value){ this.value = value; }
    public IntegerNode(){}
    public float getValue(){
        return (float)value;
    }
    public void setValue(int in){
        this.value = in;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
