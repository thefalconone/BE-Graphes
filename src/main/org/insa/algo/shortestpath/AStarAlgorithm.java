package org.insa.algo.shortestpath;

import org.insa.graph.LabelStar;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    @Override
    public LabelStar creerLabel(Node n, ShortestPathData data, double cout) {
    	return new LabelStar(n, data, cout);
    }
}








