package ru.nsu.fit.math.comp.graph.display;

abstract public class Function {
    int color = 0x000000;
    int id;
    static int counter = 0;

    public Function(){
        this.id = Function.counter;
        Function.counter++;
    }

    public Function(int color){
        this.color = color;
        this.id = Function.counter;
        Function.counter++;
    }

    public int getId() {
        return id;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color =color;
    }

    abstract public double getValue(double x);
}
