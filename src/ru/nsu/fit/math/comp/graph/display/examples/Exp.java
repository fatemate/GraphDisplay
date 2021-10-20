package ru.nsu.fit.math.comp.graph.display.examples;
import ru.nsu.fit.math.comp.graph.display.Function;

public class Exp extends Function{
    @Override
    public double getValue(double x) {
        return Math.exp(x);
    }
}
