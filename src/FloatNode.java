/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 * Assignment 3-2nd Draft 9/16/22
 */
/*
    Assignment 2:
        1) derives from Node
        2) private float
        3) read only accessor
    Assignment 3:
        1) make constructor with no initial value
        2) make value setter
 */
public class FloatNode extends Node {
    private float value;

    FloatNode(float value){
        this.value = value;
    }
    public FloatNode(){}

    public void setValue(float in){
        this.value = in;
    }
    public float getValue(){
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
