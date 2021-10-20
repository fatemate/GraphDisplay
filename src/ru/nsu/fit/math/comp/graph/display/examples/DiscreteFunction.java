package ru.nsu.fit.math.comp.graph.display.examples;
import ru.nsu.fit.math.comp.graph.display.Function;

public class DiscreteFunction extends Function{
    private double[] x;
    private double[] y;
    private double xMax = Integer.MIN_VALUE;
    private double xMin = Integer.MAX_VALUE;

    public DiscreteFunction(double[] x, double[] y){
        if (x.length == y.length){
            this.x = x.clone();
            this.y = y.clone();
            for(int i = 0; i < x.length; i++){
                if(x[i] > xMax) xMax = x[i];
                if(x[i] < xMin) xMin = x[i];
            }
        }
    }

    @Override
    public double getValue(double x) {
        if ((x > xMax) || (x < xMin)) return Double.POSITIVE_INFINITY;
        return this.y[getClosestPointIndex(x)];
    }

    private int getClosestPointIndex(double x){
        int i = 0;
        double closest = Math.abs(this.x[i]);

        for (int j = 0; j < this.x.length; j++)
            if (Math.abs(this.x[j] - x) < closest){
                closest = Math.abs(this.x[j] - x);
                i = j;
            }

        return i;
    }
}
