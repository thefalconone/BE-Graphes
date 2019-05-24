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
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;

public class DijkstraAlgorithmTest{

	private DijkstraAlgorithm Dj;
	private BellmanFordAlgorithm Bf;
	private AStarAlgorithm AStar;
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
        
        System.out.println("Test map carre et chemin valide en longueur");
    	ShortestPathData data = new ShortestPathData(graphCarre, graphCarre.get(23), graphCarre.get(0), ArcInspectorFactory.getAllFilters().get(0));

    	ShortestPathSolution SPSbf = runBellmanFord(data);
    	ShortestPathSolution SPS = runDijkstra(data);
    	ShortestPathSolution SPSa = runAStar(data);

    	System.out.println("	chemin trouvé");
    	System.out.println("		-Dijkstra");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	System.out.println("		-AStar");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPSa.getStatus());
    	System.out.println("	chamin valide");
    	System.out.println("		-Dijkstra");
    	assertTrue(SPS.getPath().isValid());
    	System.out.println("		-AStar");
    	assertTrue(SPSa.getPath().isValid());
    	System.out.println("	chemin bellman de même longueur que chemin dijkstra");
    	System.out.println("		-Dijkstra");
    	assert SPSbf.getPath().getLength() == SPS.getPath().getLength();
    	System.out.println("		-AStar");
    	assert SPSbf.getPath().getLength() == SPSa.getPath().getLength();
    	
    	System.out.println("Test map carre et chemin valide en temps");
    	data = new ShortestPathData(graphCarre, graphCarre.get(23), graphCarre.get(0), ArcInspectorFactory.getAllFilters().get(2));

    	SPSbf = runBellmanFord(data);
    	SPS = runDijkstra(data);
    	SPSa = runAStar(data);

    	System.out.println("	chemin trouvé");
    	System.out.println("		-Dijkstra");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	System.out.println("		-AStar");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPSa.getStatus());
    	System.out.println("	chamin valide");
    	System.out.println("		-Dijkstra");
    	assertTrue(SPS.getPath().isValid());
    	System.out.println("		-AStar");
    	assertTrue(SPSa.getPath().isValid());
    	System.out.println("	chemin bellman de même temps que chemin dijkstra");
    	System.out.println("		-Dijkstra");
    	assert SPSbf.getPath().getMinimumTravelTime() == SPS.getPath().getMinimumTravelTime();
    	System.out.println("		-AStar");
    	assert SPSbf.getPath().getMinimumTravelTime() == SPSa.getPath().getMinimumTravelTime();

    	System.out.println("Test map guadeloupe et chemin valide en longueur");
        String mapNameGuad = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/guadeloupe.mapgr";
        reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapNameGuad))));
        Graph graphGuad = reader.read();
        
    	data = new ShortestPathData(graphGuad, graphGuad.get(3205), graphGuad.get(21416), ArcInspectorFactory.getAllFilters().get(0));

    	SPSbf = runBellmanFord(data);
    	SPS = runDijkstra(data);
    	SPSa = runAStar(data);

    	System.out.println("	chemin trouvé");
    	System.out.println("		-Dijkstra");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	System.out.println("		-AStar");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPSa.getStatus());
    	System.out.println("	chemin valide");
    	System.out.println("		-Dijkstra");
    	assertTrue(SPS.getPath().isValid());
    	System.out.println("		-AStar");
    	assertTrue(SPSa.getPath().isValid());
    	System.out.println("	chemin bellman de même longueur que chemin dijkstra");
    	System.out.println("		-Dijkstra");
    	assert SPSbf.getPath().getLength() == SPS.getPath().getLength();
    	System.out.println("		-AStar");
    	assert SPSbf.getPath().getLength() == SPSa.getPath().getLength();
    	
    	System.out.println("Test map guadeloupe et chemin valide en temps");
    	data = new ShortestPathData(graphGuad, graphGuad.get(3205), graphGuad.get(21416), ArcInspectorFactory.getAllFilters().get(2));

    	SPSbf = runBellmanFord(data);
    	SPS = runDijkstra(data);
    	SPSa = runAStar(data);

    	System.out.println("	chemin trouvé");
    	System.out.println("		-Dijkstra");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPS.getStatus());
    	System.out.println("		-AStar");
    	assertEquals(AbstractSolution.Status.FEASIBLE, SPSa.getStatus());
    	System.out.println("	chemin valide");
    	System.out.println("		-Dijkstra");
    	assertTrue(SPS.getPath().isValid());
    	System.out.println("		-AStar");
    	assertTrue(SPSa.getPath().isValid());
    	System.out.println("	chemin bellman de même temps que chemin dijkstra");
    	System.out.println("		-Dijkstra");
    	assert SPSbf.getPath().getMinimumTravelTime() == SPS.getPath().getMinimumTravelTime();
    	System.out.println("		-AStar");
    	assert SPSbf.getPath().getMinimumTravelTime() == SPSa.getPath().getMinimumTravelTime();
        
    	System.out.println("Test map guadeloupe et chemin pas valide");
    	data = new ShortestPathData(graphGuad, graphGuad.get(14958), graphGuad.get(7749), ArcInspectorFactory.getAllFilters().get(0));

    	SPS = runDijkstra(data);
    	SPSa = runAStar(data);

    	System.out.println("		-Dijkstra");
    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPS.getStatus());
    	System.out.println("		-AStar");
    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPSa.getStatus());
    	

    	System.out.println("Test map guadeloupe et chemin de longueur nulle");
    	data = new ShortestPathData(graphGuad, graphGuad.get(14958), graphGuad.get(14958), ArcInspectorFactory.getAllFilters().get(0));

    	SPS = runDijkstra(data);
    	SPSa = runAStar(data);
    	
    	System.out.println("		-Dijkstra");
    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPS.getStatus());
    	assertTrue(SPS.getPath().isValid());
    	System.out.println("		-AStar");
    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPSa.getStatus());
    	assertTrue(SPSa.getPath().isValid());
    }
    
    private ShortestPathSolution runDijkstra(ShortestPathData data) {

    	Dj = new DijkstraAlgorithm(data);
    	
    	long startTime = System.currentTimeMillis();
    	ShortestPathSolution SPS = Dj.doRun();
    	long endTime = System.currentTimeMillis();

    	System.out.println("	Dijkstra en " + (endTime - startTime) + " millisecondes");
    	
    	return SPS;
    }
    
    private ShortestPathSolution runBellmanFord(ShortestPathData data) {

    	Bf = new BellmanFordAlgorithm(data);
    	
    	long startTime = System.currentTimeMillis();
    	ShortestPathSolution SPS = Bf.doRun();
    	long endTime = System.currentTimeMillis();

    	System.out.println("	Bellman-Ford en " + (endTime - startTime) + " millisecondes");
    	
    	return SPS;
    }
    
    private ShortestPathSolution runAStar(ShortestPathData data) {

    	AStar = new AStarAlgorithm(data);
    	
    	long startTime = System.currentTimeMillis();
    	ShortestPathSolution SPS = AStar.doRun();
    	long endTime = System.currentTimeMillis();

    	System.out.println("	A* en " + (endTime - startTime) + " millisecondes");
    	
    	return SPS;
    }
}
