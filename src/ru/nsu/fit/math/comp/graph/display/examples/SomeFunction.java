package ru.nsu.fit.math.comp.graph.display.examples;
import ru.nsu.fit.math.comp.graph.display.Function;

public class SomeFunction extends Function{

    @Override
    public double getValue(double x) {
        return Math.tan(0.5 * x + 0.2) - (x * x);
    }
}
