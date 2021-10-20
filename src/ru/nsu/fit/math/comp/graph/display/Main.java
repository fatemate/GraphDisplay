package ru.nsu.fit.math.comp.graph.display;
import ru.nsu.fit.math.comp.graph.display.examples.*;

public class Main {

    public static void main(String[] args) {
	    Graph graph = new Graph(640,640);
	    graph.setBackgroundColor(Graph.WHITE);
	    graph.setGridColor(Graph.GRAY);

	    Sin sin = new Sin();
	    sin.setColor(Graph.DARK_GREEN);

	    Exp exp = new Exp();
	    exp.setColor(Graph.DARK_BLUE);

	    SomeFunction some = new SomeFunction();
	    some.setColor(Graph.BLACK);

		double[] x = {-3, -2, -1, 0, 1, 2, 3};
		double[] y = {5, 3, 1, 0, -1, -0.5, -1};
		DiscreteFunction discrete = new DiscreteFunction(x, y);
		discrete.setColor(Graph.ORANGE_RED);

		graph.addFunction(sin);
		graph.addFunction(exp);
		graph.addFunction(some);
		graph.addFunction(discrete);

		graph.start();
    }
}
