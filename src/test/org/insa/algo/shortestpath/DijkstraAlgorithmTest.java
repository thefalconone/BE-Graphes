package org.insa.algo.shortestpath;

import org.junit.Test;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.insa.algo.AbstractSolution;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class DijkstraAlgorithmTest{

    @Test
    public void testDoRun() throws Exception {

        // Visit these directory to see the list of available files on Commetud.
        String mapNameCarre = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";

        // Create a graph reader.
        GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapNameCarre))));

        // Read the graph.
        Graph graphCarre = reader.read();

        // 0 = LENGTH		2 = TIME
        //map carre et chemin valide longueur
    	ShortestPathData data = new ShortestPathData(graphCarre, new Node(23, null), new Node(0, null), ArcInspectorFactory.getAllFilters().get(0));
    	DijkstraAlgorithm Dj = new DijkstraAlgorithm(data);
    	ShortestPathSolution SPS = Dj.doRun();
    	BellmanFordAlgorithm Bf = new BellmanFordAlgorithm(data);
    	ShortestPathSolution SPSbf = Bf.doRun();

    	//chemin trouvé
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	//chamin valide
    	assertTrue(SPS.getPath().isValid());
    	//chemin bellman aussi long que chemin dijkstra
    	assert SPSbf.getPath().getLength() == SPS.getPath().getLength();
    	
        //map carre et chemin valide temps
    	data = new ShortestPathData(graphCarre, new Node(23, null), new Node(0, null), ArcInspectorFactory.getAllFilters().get(2));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();
    	Bf = new BellmanFordAlgorithm(data);
    	SPSbf = Bf.doRun();

    	//chemin trouvé
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	//chamin valide
    	assertTrue(SPS.getPath().isValid());
    	//chemin bellman aussi long que chemin dijkstra
    	assert SPSbf.getPath().getMinimumTravelTime() == SPS.getPath().getMinimumTravelTime();

        //map guadeloupe et chemin valide longueur
        String mapNameGuad = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/guadeloupe.mapgr";
        reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapNameGuad))));
        Graph graphGuad = reader.read();
        
    	data = new ShortestPathData(graphGuad, new Node(3205, null), new Node(21416, null), ArcInspectorFactory.getAllFilters().get(0));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();
    	Bf = new BellmanFordAlgorithm(data);
    	SPSbf = Bf.doRun();

    	//chemin trouvé
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	//chamin valide
    	assertTrue(SPS.getPath().isValid());
    	//chemin bellman aussi long que chemin dijkstra
    	assert SPSbf.getPath().getMinimumTravelTime() == SPS.getPath().getMinimumTravelTime();
    	
        //map guadeloupe et chemin valide temps
    	data = new ShortestPathData(graphGuad, new Node(3205, null), new Node(21416, null), ArcInspectorFactory.getAllFilters().get(2));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();
    	Bf = new BellmanFordAlgorithm(data);
    	SPSbf = Bf.doRun();

    	//chemin trouvé
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	//chamin valide
    	assertTrue(SPS.getPath().isValid());
    	//chemin bellman aussi long que chemin dijkstra
    	assert SPSbf.getPath().getMinimumTravelTime() == SPS.getPath().getMinimumTravelTime();
        
        //map guadeloupe et chemin pas valide
    	data = new ShortestPathData(graphGuad, new Node(14958, null), new Node(7749, null), ArcInspectorFactory.getAllFilters().get(0));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();

    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPS.getStatus());
    	
    	
        //map guadeloupe et chemin de longueur nulle
    	data = new ShortestPathData(graphGuad, new Node(14958, null), new Node(14958, null), ArcInspectorFactory.getAllFilters().get(0));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();

    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPS.getStatus());
    	assertTrue(SPS.getPath().isValid());
    }
}
