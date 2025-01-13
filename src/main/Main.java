package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import grafics.LineChart;

import java.io.*;

import kruskal.KruskalAlgorithm;
import structures.graph.Edge;
import structures.graph.Graph;
import utilities.graphGenerator.GraphGenerator;

public class Main {
	
	// max vertices
	private static final int MAX_V = 5000;
	
	// variable k to use kruskal
	private static KruskalAlgorithm k;
	
	// sol is the list with the solution edges
	private static List<Edge> sol;
	
	// variable gen to generate a graph with a specific probability to add an edge
	private static GraphGenerator gen;
	
	// variable file where the data will go
	private static File file;
	
	public static void main(String[] args) {
		
		k  = new KruskalAlgorithm();
		
		gen = new GraphGenerator();
		
		// prob is the probability of put and edge between two vertices
		double prob = Double.valueOf(args[0]);
		
		try {

			file = new File("datos.txt");
			
			if(file.exists()) {
				
				List<Double> times = new ArrayList<Double>();
						
				executeProgram(times, prob);
				
				// this to be able to run the chart at the same time it is write in the file
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						LineChart ex = new LineChart(times);
			            ex.setVisible(true);
					}
				});
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				
				// this for is to write in the file
				int numVertex = 50;
				for(int i = 0; i < times.size(); i++) {

					if(i == 0)
						bw.write("10 vertex: " + times.get(i) + " ms\n");
					
					else {
						bw.write( numVertex + " vertex: " + times.get(i) + " ms\n");
						numVertex += 50;
					}
				}
				
				bw.close();
	            
			}
			
			else
				throw new IOException("No file found");
		}
		catch(IOException e) {
			System.out.println(e.getMessage()); 
		}
			
	}
	
	/**
	 * Method that executes MAX_V times, first graph has 10 vertex, next one's adding 50
	 * @param times a list where in each position saves the time in millisecond of every graph
	 * */
	private static void executeProgram(List<Double> times, double prob){
		
		Graph g;
		
		long timeStart;
		long timeEnds;
		long timeAux = 0;
		Double totalTime = 0.0;
		
		for (int i = 0; i < MAX_V; i++) {
			
			// graph with 10 vertices
			if(i == 10) {
				
				g = new Graph();
				
				gen.generate(g, prob, i);
				
				for (int j = 0; j < 3; j++) {
					
					timeStart = System.nanoTime();
					
					sol = k.kruskal(g);
					
					timeEnds = System.nanoTime() - timeStart;
					
					timeAux += timeEnds;
				}
				
				totalTime = ((double)(timeAux / 3)) / 1000000;
								
				times.add(totalTime);
			}
			
			// graphs with multiples of 50 vertices
			else if(i > 0 && i % 50 == 0) {
				
				g = new Graph();
				
				gen.generate(g, prob, i);
								
				for (int j = 0; j < 3; j++) {
					
					timeStart = System.nanoTime();
					
					sol = k.kruskal(g);
					
					timeEnds = System.nanoTime() - timeStart;
					
					timeAux += timeEnds;
				}
				
				totalTime = ((double)(timeAux / 3)) / 1000000;
								
				times.add(totalTime);
			}
			
		}
		
	}
	
	
}
