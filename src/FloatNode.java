/**
 * Julian Dorman
 * 311-Phipps
 * Assignment 2-2nd Draft 9/9/22
 */
/*
    Assignment 2:
        1) derives from Node
        2) private float
        3) read only accessor
 */
public class FloatNode extends Node {
    private float value;

    FloatNode(float value){
        this.value = value;
    }

    public float getValue(){
        return (float)value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
