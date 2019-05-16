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
import org.insa.graph.Point;
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
    	ShortestPathData data = new ShortestPathData(graphCarre, graphCarre.get(23), graphCarre.get(0), ArcInspectorFactory.getAllFilters().get(0));
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
    	//La solution est optimale ??
    	Point origine = graphCarre.get(23).getPoint();
    	Point desti = graphCarre.get(0).getPoint();
     	double volOiseau = origine.distanceTo(desti);
     	if(SPS.getPath().getLength()>2*volOiseau)
     		System.out.println("Solution pas optimale");
    	
        //map carre et chemin valide temps
    	data = new ShortestPathData(graphCarre, graphCarre.get(23), graphCarre.get(0), ArcInspectorFactory.getAllFilters().get(2));
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
    	//La solution est optimale ??
    	origine = graphCarre.get(23).getPoint();
    	desti = graphCarre.get(0).getPoint();
     	volOiseau = origine.distanceTo(desti);
     	if(SPS.getPath().getLength()>2*volOiseau)
     		System.out.println("Solution pas optimale");

        //map guadeloupe et chemin valide longueur
        String mapNameGuad = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/guadeloupe.mapgr";
        reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapNameGuad))));
        Graph graphGuad = reader.read();
        
    	data = new ShortestPathData(graphGuad, graphGuad.get(3205), graphGuad.get(21416), ArcInspectorFactory.getAllFilters().get(0));
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
    	//La solution est optimale ??
    	origine = graphGuad.get(3205).getPoint();
    	desti = graphGuad.get(21416).getPoint();
     	volOiseau = origine.distanceTo(desti);
     	if(SPS.getPath().getLength()>2*volOiseau)
     		System.out.println("Solution pas optimale");
    	
        //map guadeloupe et chemin valide temps
    	data = new ShortestPathData(graphGuad, graphGuad.get(3205), graphGuad.get(21416), ArcInspectorFactory.getAllFilters().get(2));
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
    	//La solution est optimale ??
    	origine = graphGuad.get(3205).getPoint();
    	desti = graphGuad.get(21416).getPoint();
     	volOiseau = origine.distanceTo(desti);
     	if(SPS.getPath().getLength()>2*volOiseau)
     		System.out.println("Solution pas optimale");
        
        //map guadeloupe et chemin pas valide
    	data = new ShortestPathData(graphGuad, graphGuad.get(14958), graphGuad.get(7749), ArcInspectorFactory.getAllFilters().get(0));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();

    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPS.getStatus());
    	
    	
        //map guadeloupe et chemin de longueur nulle
    	data = new ShortestPathData(graphGuad, graphGuad.get(14958), graphGuad.get(14958), ArcInspectorFactory.getAllFilters().get(0));
    	Dj = new DijkstraAlgorithm(data);
    	SPS = Dj.doRun();

    	assertEquals(AbstractSolution.Status.INFEASIBLE, SPS.getStatus());
    	assertTrue(SPS.getPath().isValid());
    }
}
